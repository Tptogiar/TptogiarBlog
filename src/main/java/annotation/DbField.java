package annotation;

import java.lang.annotation.*;

/**
 * @author Tptogiar
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbField {

    String fieldName();

    boolean isInsert() default false;

    boolean isUpdate() default true;

    boolean isQuery() default false;

}
