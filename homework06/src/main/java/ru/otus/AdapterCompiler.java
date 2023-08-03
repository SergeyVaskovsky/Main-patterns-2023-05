package ru.otus;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.List;

public class AdapterCompiler {
    public Object compile(String className, List<String> sourceCode, GameObject obj, Class<?> clazz) {
        try {
            String source = String.join("\n", sourceCode);

            File root = Files.createTempDirectory("java").toFile();
            File sourceFile = new File(root, className + ".java");
            sourceFile.getParentFile().mkdirs();
            Files.writeString(sourceFile.toPath(), source);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, sourceFile.getPath());

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
            Class<?> cls = Class.forName(className, true, classLoader);
            return cls.getDeclaredConstructor(clazz).newInstance(obj);
        } catch (Exception ex) {
            throw new CompileException("Compile exception");
        }
    }
}
