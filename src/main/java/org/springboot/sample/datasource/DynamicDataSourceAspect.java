
package org.springboot.sample.datasource;

        import org.aspectj.lang.*;
        import org.aspectj.lang.annotation.After;
        import org.aspectj.lang.annotation.Aspect;
        import org.aspectj.lang.annotation.Before;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.core.annotation.Order;
        import org.springframework.stereotype.Component;
//        import org.aspectj.lang.annotation.After;
//        import org.aspectj.lang.annotation.Aspect;
//        import org.aspectj.lang.annotation.Before;
//        import org.slf4j.Logger;
//        import org.slf4j.LoggerFactory;
//        import org.springframework.core.annotation.Order;
//        import org.springframework.stereotype.Component;

/**
 *  Switching data sources Advice
 *
 * @author  ChanHongYu (365384722)
 * @create 2016 years 1 month 23 day
 */
@Aspect
@Order(-1)//  Ensure that the AOP in @Transactional Before performing
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        String dsId = ds.name();
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.error(" The data source [{}] Does not exist, use the default data source  > {}", ds.name(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }

}


