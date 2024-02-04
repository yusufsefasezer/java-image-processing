package com.yusufsezer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {

    public static <T> List<Class<? extends T>> findClassesImplementing(String packageName, Class<T> interfaceClass) {
        List<Class<? extends T>> classes = new ArrayList<>();

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            File packageDirectory = new File(classLoader.getResource(path).getFile());

            if (packageDirectory.exists() && packageDirectory.isDirectory()) {
                classes.addAll(findClasses(packageDirectory, packageName, interfaceClass));
            }
        } catch (Exception e) {
            return classes;
        }

        return classes;
    }

    private static <T> List<Class<? extends T>> findClasses(File directory, String packageName, Class<T> interfaceClass) {
        List<Class<? extends T>> classes = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName(), interfaceClass));
                } else if (file.getName().endsWith(".class")) {
                    try {
                        Class<?> clazz = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                        if (interfaceClass.isAssignableFrom(clazz) && !clazz.equals(interfaceClass)) {
                            classes.add((Class<? extends T>) clazz);
                        }
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
        }

        return classes;
    }

}
