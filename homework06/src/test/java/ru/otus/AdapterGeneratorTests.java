package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;

public class AdapterGeneratorTests {

    private static AdapterGenerator adapterGenerator;
    private static Class<?> clazz;
    private static AdapterCompiler adapterCompiler;

    @BeforeAll
    static void newAdapterGenerator() {
        clazz = Movable.class;
        adapterGenerator = new AdapterGenerator(clazz);
    }

    @Test
    void shouldGenerateAdapterClass() {
        List<String> adapterClass = adapterGenerator.generate();
        Assertions.assertEquals(adapterClass.get(0), "import ru.otus.Movable;");
        Assertions.assertEquals(adapterClass.get(1), "import ru.otus.Vector;");
        Assertions.assertEquals(adapterClass.get(2), "import ru.otus.ioc.IoC;");
        Assertions.assertEquals(adapterClass.get(4),
                String.format("public class %sAdapter implements %s {", clazz.getSimpleName(), clazz.getSimpleName()));
    }

    @Test
    void shouldHaveConstructor() {
        List<String> adapterClass = adapterGenerator.generate();
        Assertions.assertEquals(adapterClass.get(6), "Object obj;");
        Assertions.assertEquals(adapterClass.get(7), String.format("public %sAdapter(Object obj) {", clazz.getSimpleName()));
        Assertions.assertEquals(adapterClass.get(8), "\tthis.obj = obj;");
        Assertions.assertEquals(adapterClass.get(9), "}");
    }

    @Test
    void shouldReleaseGetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        Assertions.assertEquals("public Vector getPosition() {", adapterClass.get(10));
        Assertions.assertEquals("\treturn IoC.resolve(\"Movable.position.get\", obj);", adapterClass.get(11));
        Assertions.assertEquals("}", adapterClass.get(12));
        Assertions.assertEquals("public Vector getVelocity() {", adapterClass.get(13));
        Assertions.assertEquals("\treturn IoC.resolve(\"Movable.velocity.get\", obj);", adapterClass.get(14));
        Assertions.assertEquals("}", adapterClass.get(15));
    }

    @Test
    void shouldReleaseSetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        Assertions.assertEquals("public Vector setPosition(Vector arg0) {", adapterClass.get(16));
        Assertions.assertEquals("\treturn IoC.resolve(\"Movable.position.set\", obj, arg0);", adapterClass.get(17));
        Assertions.assertEquals("}", adapterClass.get(18));
    }

    @Test
    void shouldGenerateClass() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<String> sourceCode = adapterGenerator.generate();
        Object adapter = adapterCompiler.compile(clazz.getSimpleName(), sourceCode);
    }

}
