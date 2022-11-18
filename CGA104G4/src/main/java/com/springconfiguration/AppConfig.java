package com.springconfiguration;


import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.*.model")

@EnableTransactionManagement
public class AppConfig {
    @Bean
    public TransactionManager transactionManager() throws IllegalArgumentException,
            NamingException {
        return new HibernateTransactionManager(sessionFactory());
    }


    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/FOOD");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        dataSource.setMinimumIdle(5);
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(30000);
        return dataSource;

    }

    @Bean
    public SessionFactory sessionFactory() throws IllegalArgumentException{
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.*.model")
                .addProperties(getHibernateProperties())
                .buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", MySQL8Dialect.class.getName());
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty(
                "hibernate.current_session_context_class",
                SpringSessionContext.class.getName());
        return properties;
    }


}
