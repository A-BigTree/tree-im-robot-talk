package top.abigtree.im.robot.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XStreamCDATA {
}
