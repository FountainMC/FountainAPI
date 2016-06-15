package org.fountainmc.api.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import net.techcable.event4j.EventBus;
import net.techcable.event4j.EventExecutor;
import org.fountainmc.api.event.Event;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class PluginManager {

    ArrayList<Object> plugins = new ArrayList<Object>();
    EventBus<Event, Object> eventBus;

    public PluginManager() {
        eventBus = EventBus.builder().eventClass(Event.class)
                .executorFactory(EventExecutor.Factory.ASM_LISTENER_FACTORY.get()).build();
    }

    public void registerListener(Object listener) {
        eventBus.register(listener);
    }

    public void fireEvent(Event event) {
        eventBus.fire(event);
    }

    public void loadPlugins(File dir) {
        if (dir.isDirectory()) {
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File file : directoryListing) {
                    try {
                        URL url = file.toURI().toURL();
                        URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                        method.setAccessible(true);
                        method.invoke(loader, url);
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
                    } catch (IOException | NoSuchMethodException | SecurityException | InvocationTargetException
                            | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadPlugin(ClassLoader loader, String className, ClassReader classReader) {
        try {
            Class<?> clazz = loader.loadClass(className);
            Plugin anno = clazz.getAnnotation(Plugin.class);
            System.out.println((anno.name().isEmpty() ? anno.id() : anno.name()) + " has been loaded.");
            Object plugin = clazz.newInstance();
            plugins.add(plugin);
            eventBus.register(plugin);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
                if (jarEntry == null)
                    break;
                if (jarEntry.getName().endsWith(".class")) {
                    classes.add(jarEntry.getName());
                }
            }
            jarStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    public ArrayList<Object> getPlugins() {
        return plugins;
    }

    public EventBus<Event, Object> getEventBus() {
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

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            if (desc.contains(Type.getInternalName(Plugin.class))) {
                pluginManager.loadPlugin(loader, className, classReader);
            }
            return super.visitAnnotation(desc, visible);
        }
    }

}
