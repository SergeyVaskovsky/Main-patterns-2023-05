package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdapterGeneratorTests {

    private static AdapterGenerator adapterGenerator;
    private static Class<?> clazz;

    @BeforeAll
    static void newAdapterGenerator() {
        clazz = Movable.class;
        adapterGenerator = new AdapterGenerator(clazz);
    }

    @Test
    void shouldGenerateAdapterClass() {
        List<String> adapterClass = adapterGenerator.generate();
        Assertions.assertEquals(adapterClass.get(0),
                String.format("public class %sAdapter implements %s {", clazz.getName(), clazz.getName()));
    }

    @Test
    void shouldHaveConstructor() {
        List<String> adapterClass = adapterGenerator.generate();
        Assertions.assertEquals(adapterClass.get(2), "UObject obj;");
        Assertions.assertEquals(adapterClass.get(3), String.format("public %sAdapter(UObject obj) {", clazz.getName()));
        Assertions.assertEquals(adapterClass.get(4), "\tthis.obj = obj;");
        Assertions.assertEquals(adapterClass.get(5), "}");
    }

    @Test
    void shouldReleaseGetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        Assertions.assertEquals("public Vector getPosition() {", adapterClass.get(7));
        Assertions.assertEquals("\treturn IoC.Resolve(\"Movable.position.get\", obj);", adapterClass.get(8));
        Assertions.assertEquals("}", adapterClass.get(9));
        Assertions.assertEquals("public Vector getVelocity() {", adapterClass.get(10));
        Assertions.assertEquals("\treturn IoC.Resolve(\"Movable.velocity.get\", obj);", adapterClass.get(11));
        Assertions.assertEquals("}", adapterClass.get(12));
    }

    @Test
    void shouldReleaseSetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        Assertions.assertEquals("public Vector setPosition(Vector arg0) {", adapterClass.get(13));
        Assertions.assertEquals("\treturn IoC.Resolve(\"Movable.position.set\", obj, newValue);", adapterClass.get(14));
        Assertions.assertEquals("}", adapterClass.get(15));
    }

}
