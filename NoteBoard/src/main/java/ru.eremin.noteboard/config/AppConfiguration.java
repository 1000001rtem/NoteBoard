package ru.eremin.noteboard.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(DataSourceConfiguration.class)
@EnableJpaRepositories("ru.eremin.noteboard.repository")
@ComponentScan("ru.eremin.noteboard")
public class AppConfiguration {
}
