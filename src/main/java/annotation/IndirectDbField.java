package annotation;

import java.lang.annotation.*;

/**
 * 作用类似于@DbField，只不过该属性对应的列不在
 * 该类对应的数据库表中，而是位于其他类对应的数据库表中
 * @author Tptogiar
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IndirectDbField {
    String fieldName();
}
