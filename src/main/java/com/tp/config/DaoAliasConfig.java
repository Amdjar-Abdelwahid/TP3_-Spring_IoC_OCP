package com.tp.config;

import com.tp.dao.DaoApi;
import com.tp.dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DaoAliasConfig {
    // Alias "dao" qui renvoie DaoApi comme implémentation
    @Bean(name = "dao")
    @Profile("api")  // Only create this alias when "api" profile is active
    public IDao daoAlias(DaoApi target) {
        return target; // alias "dao" -> "daoApi"
    }
}