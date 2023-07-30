package ru.otus;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class AdapterGenerator {

    private final Class<?> clazz;

    public AdapterGenerator(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<String> generate() {
        ArrayList<String> list = new ArrayList<>();
        list.add(String.format("public class %sAdapter implements %s {", clazz.getName(), clazz.getName()));
        list.add("");
        list.add("UObject obj;");
        list.add(String.format("public %sAdapter(UObject obj) {", clazz.getName()));
        list.add("\tthis.obj = obj;");
        list.add( "}");
        list.add("");
        list.addAll(getGetMethods());
        list.addAll(getSetMethods());
        return list;
    }

    private List<String> getGetMethods() {
        ArrayList<String> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if ("get".equalsIgnoreCase(method.getName().substring(0, 3))) {
                list.add("public " + method.getReturnType().getSimpleName() + " " + method.getName() + "() {");
                list.add("\treturn IoC.Resolve(\"" + clazz.getSimpleName() + "." + method.getName().substring(3).toLowerCase()
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
                list.add("\treturn IoC.Resolve(\"" + clazz.getSimpleName() + "." + method.getName().substring(3).toLowerCase()
                        + ".set\", obj, newValue);");
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
