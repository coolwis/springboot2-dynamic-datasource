
package org.springboot.sample.datasource;

        import java.util.HashMap;
        import java.util.Map;

        import javax.sql.DataSource;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.MutablePropertyValues;
        import org.springframework.beans.PropertyValues;
        import org.springframework.beans.factory.support.BeanDefinitionRegistry;
        import org.springframework.beans.factory.support.GenericBeanDefinition;
//        import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//        import org.springframework.boot.bind.RelaxedDataBinder;
//        import org.springframework.boot.bind.RelaxedPropertyResolver;
        import org.springframework.boot.context.properties.bind.BindResult;
        import org.springframework.boot.context.properties.bind.Bindable;
        import org.springframework.boot.context.properties.bind.Binder;
        import org.springframework.boot.jdbc.DataSourceBuilder;
        import org.springframework.context.EnvironmentAware;
        import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
        import org.springframework.core.convert.ConversionService;
        import org.springframework.core.convert.support.DefaultConversionService;
        import org.springframework.core.env.Environment;
        import org.springframework.core.type.AnnotationMetadata;

/**
 *  Dynamic data source registration <br/>
 *  Start the dynamic data source in the start class (for example SpringBootSampleApplication )
 *  add  @Import(DynamicDataSourceRegister.class)
 *
 * @author  ChanHongYu (365384722)
 * @create 2016 years 1 month 24 day
 */
public class DynamicDataSourceRegister
        implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;

    //  Use this default value if the data source type is not specified in the configuration file
//    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
     private static final Object DATASOURCE_TYPE_DEFAULT = "com.zaxxer.hikari.HikariDataSource";

    //  The data source
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //  Add the master data source to more data sources
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        //  Add more data sources
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        //  create DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }

    /**
     *  create DataSource
     *
     */
    @SuppressWarnings("unchecked")
    public DataSource buildDataSource(Map<String, Object> dsMap) {
        try {
            Object type = dsMap.get("type");
            if (type == null)
                type = DATASOURCE_TYPE_DEFAULT;//  The default DataSource

            Class<? extends DataSource> dataSourceType;
            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);

            String driverClassName = dsMap.get("driver-class-name").toString();
            String url = dsMap.get("jdbc-url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
                    .username(username).password(password).type(dataSourceType);
            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Load the multi-data source configuration
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }

    /**
     *  Initializes the master data source
     *
     * @author SHANHY
     * @create 2016 years 1 month 24 day
     */
    private void initDefaultDataSource(Environment env) {
        //  Read the master data source
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        Map<String, Object> propertyResolver= Binder.get(env).bind("spring.datasource", Bindable.of(Map.class)).get();

        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("type", propertyResolver.get("type"));
        dsMap.put("driver-class-name", propertyResolver.get("driver-class-name"));
        dsMap.put("jdbc-url", propertyResolver.get("jdbc-url"));
        dsMap.put("username", propertyResolver.get("username"));
        dsMap.put("password", propertyResolver.get("password"));

//        dsMap.put("type", propertyResolver.getProperty("type"));
//        dsMap.put("driver-class-name", propertyResolver.getProperty("driver-class-name"));
//        dsMap.put("jdbc-url", propertyResolver.getProperty("jdbc-url"));
//        dsMap.put("username", propertyResolver.getProperty("username"));
//        dsMap.put("password", propertyResolver.getProperty("password"));

        defaultDataSource = buildDataSource(dsMap);

        dataBinder(defaultDataSource, env);
    }

    /**
     *  for DataSource Bind more data
     *
     * @param dataSource
     * @param env
     * @author SHANHY
     * @create 2016 years 1 month 25 day
     */
    private void dataBinder(DataSource dataSource, Environment env){
//        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
//        //dataBinder.setValidator(new LocalValidatorFactory().run(this.applicationContext));
//        dataBinder.setConversionService(conversionService);
//        dataBinder.setIgnoreNestedProperties(false);//false
//        dataBinder.setIgnoreInvalidFields(false);//false
//        dataBinder.setIgnoreUnknownFields(true);//true
//        if(dataSourcePropertyValues == null){
//
//           Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
//
//
//
//
//            Map<String, Object> values = new HashMap<>(rpr);
//            //  Exclude properties that have been set
//            values.remove("type");
//            values.remove("driver-class-name");
//            values.remove("jdbc-url");
//            values.remove("username");
//            values.remove("password");
//            dataSourcePropertyValues = new MutablePropertyValues(values);
//        }
//        dataBinder.bind(dataSourcePropertyValues);
    }

    /**
     *  Initialize more data sources
     *
     * @author SHANHY
     * @create 2016 years 1 month 24 day
     */
    private void initCustomDataSources(Environment env) {
        //  Read configuration files for more data sources, also available via defaultDataSource Read the database for more data sources
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        Map<String, Object> propertyResolver= Binder.get(env).bind("custom.datasource", Bindable.of(Map.class)).get();

        String dsPrefixs = propertyResolver.get("names").toString();
        for (String dsPrefix : dsPrefixs.split(",")) {//  Multiple data sources
//            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
//            Map<String, Object> dsMap = (Map<String, Object>) propertyResolver.get("custom.datasource."+dsPrefix);
            Map<String, Object> dsMap =Binder.get(env).bind("custom.datasource."+dsPrefix, Bindable.of(Map.class)).get();
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
//            dataBinder(ds, env);
        }
    }

}


