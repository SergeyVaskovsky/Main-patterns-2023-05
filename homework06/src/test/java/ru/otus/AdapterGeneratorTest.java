package ru.otus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.ioc.IoC;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdapterGeneratorTest {

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
        assertEquals(adapterClass.get(0), "import ru.otus.Movable;");
        assertEquals(adapterClass.get(1), "import ru.otus.Vector;");
        assertEquals(adapterClass.get(2), "import ru.otus.GameObject;");
        assertEquals(adapterClass.get(3), "import ru.otus.ioc.IoC;");
        assertEquals(adapterClass.get(5),
                String.format("public class %sAdapter implements %s {", clazz.getSimpleName(), clazz.getSimpleName()));
    }

    @Test
    void shouldHaveConstructor() {
        List<String> adapterClass = adapterGenerator.generate();
        assertEquals(adapterClass.get(7), "GameObject obj;");
        assertEquals(adapterClass.get(8), String.format("public %sAdapter(GameObject obj) {", clazz.getSimpleName()));
        assertEquals(adapterClass.get(9), "\tthis.obj = obj;");
        assertEquals(adapterClass.get(10), "}");
    }

    @Test
    void shouldReleaseGetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        assertEquals("public Vector getPosition() {", adapterClass.get(12));
        assertEquals("\treturn IoC.resolve(\"Movable.position.get\", obj);", adapterClass.get(13));
        assertEquals("}", adapterClass.get(14));
        assertEquals("public Vector getVelocity() {", adapterClass.get(15));
        assertEquals("\treturn IoC.resolve(\"Movable.velocity.get\", obj);", adapterClass.get(16));
        assertEquals("}", adapterClass.get(17));
    }

    @Test
    void shouldReleaseSetMethods() {
        List<String> adapterClass = adapterGenerator.generate();

        assertEquals("public Vector setPosition(Vector arg0) {", adapterClass.get(18));
        assertEquals("\treturn IoC.resolve(\"Movable.position.set\", obj, arg0);", adapterClass.get(19));
        assertEquals("}", adapterClass.get(20));
    }

    @Test
    void shouldGenerateClass() {
        GameObject gameObject = new GameObjectImpl();
        var adapter = IoC.resolve("IoC.Adapter", Movable.class, gameObject);

        gameObject.setProperty(Consts.POSITION, new Vector(10,11));
        gameObject.setProperty(Consts.VELOCITY, new Vector(2,1));

        var currentPosition = ((Movable) adapter).getPosition();
        var currentVelocity = ((Movable) adapter).getVelocity();
        var newPosition = new Vector(
                currentPosition.getX() + currentVelocity.getX(),
                currentPosition.getY() + currentVelocity.getY());
        ((Movable) adapter).setPosition(newPosition);

        var newCurrentPosition = ((Movable) adapter).getPosition();

        assertEquals(newCurrentPosition.getX(), newPosition.getX());
        assertEquals(newCurrentPosition.getY(), newPosition.getY());

    }

}
