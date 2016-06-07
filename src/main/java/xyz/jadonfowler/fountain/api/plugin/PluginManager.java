package xyz.jadonfowler.fountain.api.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import net.techcable.event4j.EventBus;
import net.techcable.event4j.EventExecutor;
import xyz.jadonfowler.fountain.api.event.Event;
import xyz.jadonfowler.fountain.api.event.Listener;

public class PluginManager {

    ArrayList<Object> plugins = new ArrayList<Object>();
    EventBus<Event, Listener> eventBus;

    public PluginManager() {
        eventBus = EventBus.builder().eventClass(Event.class).listenerClass(Listener.class)
                .executorFactory(EventExecutor.Factory.ASM_LISTENER_FACTORY.get()).build();
    }

    public void registerListener(Listener listener) {
        eventBus.register(listener);
    }

    public void loadPlugins(File dir) {
        if (dir.isDirectory()) {
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File file : directoryListing) {
                    try {
                        ClassLoader loader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });
                        ArrayList<String> classes = getClasses(file);
                        JarFile jar = new JarFile(file);
                        for (String className : classes) {
                            JarEntry entry = jar.getJarEntry(className);
                            String m = className.replaceAll("/", "\\.");
                            String clazz = m.substring(0, m.length() - 6);
                            InputStream in = jar.getInputStream(entry);
                            ClassReader cr = new ClassReader(in);
                            cr.accept(new AnnotationScanner(this, loader, clazz, cr), 0);
                        }
                        jar.close();
                    }
                    catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadPlugin(ClassLoader loader, String className, ClassReader classReader) {
        try {
            System.out.println("Plugin found: " + className);
            classReader.accept(new ListenerImplementer(), 0);
            @SuppressWarnings("unchecked") Class<? extends Listener> clazz = (Class<? extends Listener>) loader
                    .loadClass(className);
            Listener plugin = clazz.newInstance();
            plugins.add(plugin);
            eventBus.register(plugin);
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getClasses(File jar) {
        ArrayList<String> classes = new ArrayList<String>();
        try {
            JarInputStream jarStream = new JarInputStream(new FileInputStream(jar));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarStream.getNextJarEntry();
                if (jarEntry == null) break;
                if (jarEntry.getName().endsWith(".class")) {
                    classes.add(jarEntry.getName());
                }
            }
            jarStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    public ArrayList<Object> getPlugins() {
        return plugins;
    }

    public EventBus<Event, Listener> getEventBus() {
        return eventBus;
    }

    class AnnotationScanner extends ClassVisitor {

        private PluginManager pluginManager;
        private ClassLoader loader;
        private String className;
        private ClassReader classReader;

        private AnnotationScanner(PluginManager pluginManager, ClassLoader loader, String className,
                ClassReader classReader) {
            super(Opcodes.ASM4);
            this.pluginManager = pluginManager;
            this.loader = loader;
            this.className = className;
            this.classReader = classReader;
        }

        @Override public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            if (desc.contains(Type.getInternalName(Plugin.class))) {
                pluginManager.loadPlugin(loader, className, classReader);
            }
            return super.visitAnnotation(desc, visible);
        }
    }

    class ListenerImplementer extends ClassVisitor {

        private ListenerImplementer() {
            super(Opcodes.ASM4);
        }

        @Override public void visit(int version, int access, String name, String signature, String superName,
                String[] interfaces) {
            interfaces = concat(interfaces == null ? new String[] {} : interfaces,
                    new String[] { Type.getInternalName(Listener.class) });
            System.out.println(Arrays.toString(interfaces));
            super.visit(version, access, name, signature, superName, interfaces);
        }

        private String[] concat(String[] a, String[] b) {
            int aLen = a.length;
            int bLen = b.length;
            String[] c = new String[aLen + bLen];
            System.arraycopy(a, 0, c, 0, aLen);
            System.arraycopy(b, 0, c, aLen, bLen);
            return c;
        }
    }
}
