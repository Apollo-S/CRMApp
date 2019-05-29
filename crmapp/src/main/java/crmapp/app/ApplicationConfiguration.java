package crmapp.app;

import crmapp.app.repositories.base.BaseRepositoryImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "crmapp.app.repositories", 
	repositoryBaseClass= BaseRepositoryImpl.class)
public class ApplicationConfiguration {

	@Bean
	@ConfigurationProperties("app.hibernate")
	@Autowired
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", "${dialect}");

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan("crmapp.app");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		return localContainerEntityManagerFactoryBean.getObject();
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("${driver-class-name}");
		dataSource.setUrl("${url}");
		dataSource.setUsername("${username}");
		dataSource.setPassword("${password}");
		return dataSource;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
