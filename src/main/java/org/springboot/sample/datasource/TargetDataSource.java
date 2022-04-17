package org.springboot.sample.datasource;

        import java.lang.annotation.Documented;
        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;

/**
 *  Used on a method to specify which data source to use
 *
 * @author   ChanHongYu (365384722)
 * @create  2016 years 1 month 23 day
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
