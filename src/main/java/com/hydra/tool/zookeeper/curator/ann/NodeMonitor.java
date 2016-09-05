package com.hydra.tool.zookeeper.curator.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lee on 15/12/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface NodeMonitor {
    String DEFAULT_TAG_NAME = "@@USE_METHOD_NAME";

    String tag() default "@@USE_METHOD_NAME";

    String message() default "";
}
