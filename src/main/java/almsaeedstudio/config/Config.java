package almsaeedstudio.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by oleg on 3/9/17.
 */
@Configuration
public class Config {

    @Value("${hikari.min_size}")
    private Integer minimumIdle;
    @Value("${hikari.max_size}")
    private Integer maximumPoolSize;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${hikari.timeout}")
    private Integer connectionTimeout;
    @Value("${hikari.idle_test_period}")
    private Integer idleTimeout;

    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private Boolean showSql;
    @Value("${hibernate.format_sql}")
    private Boolean formatSql;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws PropertyVetoException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(idleTimeout);
        return dataSource;
    }

    @Bean(destroyMethod = "destroy")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("almsaeedstudio.domain");
        factory.setPersistenceUnitName("almsaeedstudio");
        factory.setJpaDialect(new HibernateJpaDialect());
        factory.setJpaVendorAdapter(jpaVendorAdapter(dialect, showSql));
        factory.setJpaProperties(jpaProperties(formatSql));
        return factory;

    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws PropertyVetoException {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties jpaProperties(Boolean formatSql) {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    private JpaVendorAdapter jpaVendorAdapter(String dialect, Boolean showSql) {
        HibernateJpaVendorAdapter result = new HibernateJpaVendorAdapter();
        result.setDatabasePlatform(dialect);
        result.setShowSql(showSql);
        result.setDatabase(Database.MYSQL);
        return result;
    }
}
