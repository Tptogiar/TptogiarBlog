package annotation;

import java.lang.annotation.*;

/**用于标记某类属性在数据库中对应的列
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
