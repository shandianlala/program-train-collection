package club.sdll.ptc.juc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2019年04月11日 15:34
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BeanProperty {

    String id();

    String description() default "no description";
}
