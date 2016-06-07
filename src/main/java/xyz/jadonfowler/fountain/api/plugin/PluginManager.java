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
                            cr.accept(new AnnotationScanner(this, loader, clazz), 0);
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

    private void loadPlugin(ClassLoader loader, String className) {
        try {
            System.out.println("Plugin found: " + className);
            plugins.add(loader.loadClass(className).newInstance());
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

    class AnnotationScanner extends ClassVisitor {

        private PluginManager pluginManager;
        private ClassLoader loader;
        private String className;

        private AnnotationScanner(PluginManager pluginManager, ClassLoader loader, String className) {
            super(Opcodes.ASM4);
            this.pluginManager = pluginManager;
            this.loader = loader;
            this.className = className;
        }

        @Override public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            if (desc.contains(Type.getInternalName(Plugin.class))) {
                pluginManager.loadPlugin(loader, className);
            }
            return super.visitAnnotation(desc, visible);
        }
    }
}
