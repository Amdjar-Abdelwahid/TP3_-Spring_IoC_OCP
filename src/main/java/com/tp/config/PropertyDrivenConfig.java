package com.tp.config;

import com.tp.dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Map;
@Configuration
@PropertySource(value = "classpath:app.properties", ignoreResourceNotFound = true)
@Profile("!api & !prod & !dev & !file")
public class PropertyDrivenConfig {

    private final Map<String, IDao> candidates;

    public PropertyDrivenConfig(Map<String, IDao> candidates) {
        this.candidates = candidates;
    }

    @Value("${dao.target:dao2}")
    private String target;

    @Bean(name = "dao")
    @DependsOn("propertySourcesPlaceholderConfigurer")
    public IDao selectedDao() {
        IDao bean = candidates.get(target);
        if (bean == null) {
            throw new IllegalArgumentException("Implémentation inconnue: " + target + " (dao|dao2|daoFile|daoApi)");
        }
        return bean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}