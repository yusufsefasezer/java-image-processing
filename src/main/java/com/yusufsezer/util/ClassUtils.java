package com.yusufsezer.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ClassUtils {

    public static <T> List<Class<? extends T>> findClassesImplementing(String packageName, Class<T> interfaceClass) {
        List<Class<? extends T>> allClasses = new ArrayList<>();

        try {
            String packagePath = packageName.replace('.', '/');
            URL packageResource = ClassLoader.getSystemClassLoader().getResource(packagePath);
            URI packageUri = Objects.requireNonNull(packageResource).toURI();

            Path packageRootPath;
            if (packageUri.toString().startsWith("jar:")) {
                try {
                    packageRootPath = FileSystems.getFileSystem(packageUri).getPath(packagePath);
                } catch (FileSystemNotFoundException e) {
                    packageRootPath = FileSystems.newFileSystem(packageUri, Collections.emptyMap()).getPath(packagePath);
                }
            } else {
                packageRootPath = Paths.get(packageUri);
            }

            String extension = ".class";
            try (Stream<Path> allPaths = Files.walk(packageRootPath)) {
                allPaths.filter(Files::isRegularFile).forEach(file -> {
                    try {
                        int clazzNameIndex = file.getNameCount() - 1;
                        String clazzName = file.getName(clazzNameIndex).toString();
                        clazzName = clazzName.replace(extension, "");
                        String fullClazzName = packageName + "." + clazzName;
                        Class<?> clazz = Class.forName(fullClazzName);

                        boolean isAssignable = interfaceClass.isAssignableFrom(clazz)
                                && !clazz.equals(interfaceClass);
                        if (isAssignable) {
                            allClasses.add((Class<? extends T>) clazz);
                        }

                    } catch (ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
                    }
                });
            }
        } catch (IOException | URISyntaxException e) {
        }
        return allClasses;
    }

}
