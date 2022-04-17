package org.springboot.sample.datasource;

        import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *  Dynamic data source
 *
 * @author   ChanHongYu (365384722)
 * @create  2016 years 1 month 22 day
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }

}

