package com.west.auth.anno;

import java.lang.annotation.*;

/**
 * 用于标记匿名访问方法
 *
 * @author west
 * @date 2021/3/5 16:07
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
