package com.hydra.tool.object;

import com.hydra.tool.crontask.model.CronTask;
import com.hydra.tool.system.Sys;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by ZhengGong on 15/3/12.
 * Description 用于生成类中的属性对应的String常量 和 mapper中的where
 */
public class PojoConstantsUtil {
    private static final String PREFIX = "public static final String ";

    public static void console(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder fieldConstantsSb = new StringBuilder();

        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fieldConstantsSb.append(buildField(field));
            }
        }

        Sys.pl(fieldConstantsSb.toString());

        Sys.pl(buildToString(clazz, fields));
    }

    private static String buildField(Field field) {
        return PREFIX + upperCaseTo_(field.getName()).toUpperCase() + " = " + '"' + field.getName() + "\";\n";
    }

    public static String upperCaseTo_(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!Character.isLowerCase(c) && i != 0) {
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String buildToString(Class<?> clazz, Field[] fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("StringBuilder sb = new StringBuilder();").append("\n");
        sb.append("sb.append(\"" + clazz.getSimpleName() + "{\");").append("\n");
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers())) {
                if (i != fields.length - 1) {
                    sb.append("sb.append(\"" + fields[i].getName() + "=\").append(" + fields[i].getName() + ").append(\",\");").append("\n");
                } else {
                    sb.append("sb.append(\"" + fields[i].getName() + "=\").append(" + fields[i].getName() + ");").append("\n");
                }
            }


        }
        sb.append("sb.append(\"}\");").append("\n");
        sb.append("return sb.toString();").append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        console(CronTask.class);
    }
}
