package ru.otus;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterGenerator {

    private final Class<?> clazz;

    public AdapterGenerator(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<String> generate() {
        ArrayList<String> list = new ArrayList<>();
        list.add("import ru.otus.Movable;");
        list.add("import ru.otus.Vector;");
        list.add("import ru.otus.GameObject;");
        list.add("import ru.otus.ioc.IoC;");
        list.add("");
        list.add(String.format("public class %sAdapter implements %s {", clazz.getSimpleName(), clazz.getSimpleName()));
        list.add("");
        list.add("GameObject obj;");
        list.add(String.format("public %sAdapter(GameObject obj) {", clazz.getSimpleName()));
        list.add("\tthis.obj = obj;");
        list.add( "}");
        list.add("");
        list.addAll(getGetMethods());
        list.addAll(getSetMethods());
        list.add("}");
        return list;
    }

    private List<String> getGetMethods() {
        ArrayList<String> list = new ArrayList<>();
        var methods = Arrays.stream(clazz.getDeclaredMethods()).sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).toList();
        for (Method method : methods) {
            if ("get".equalsIgnoreCase(method.getName().substring(0, 3))) {
                list.add("public " + method.getReturnType().getSimpleName() + " " + method.getName() + "() {");
                list.add("\treturn IoC.resolve(\"" + clazz.getSimpleName() + "." + method.getName().substring(3).toLowerCase()
                        + ".get\", obj);");
                list.add("}");
            }
        }
        return list;
    }

    private List<String> getSetMethods() {
        ArrayList<String> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if ("set".equalsIgnoreCase(method.getName().substring(0, 3))) {
                list.add("public " + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + getSetParameters(method)+ ") {");
                list.add("\treturn IoC.resolve(\"" + clazz.getSimpleName() + "." + method.getName().substring(3).toLowerCase()
                        + ".set\", obj, arg0);");
                list.add("}");
            }
        }
        return list;
    }

    private String getSetParameters(Method method) {
        StringBuilder parameters = new StringBuilder();
        for (Parameter parameter : method.getParameters()) {
            parameters.append(parameter.getType().getSimpleName());
            parameters.append(" ");
            parameters.append(parameter.getName());
            parameters.append(", ");
        }
        String stringBeforeReturn = parameters.toString();
        return stringBeforeReturn.substring(0, stringBeforeReturn.length() - 2);
    }
}
