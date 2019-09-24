package net.tou.mantri.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "net.tou.mantri.shoppingbackend.dto" })
@EnableTransactionManagement
public class HibernateConfig {

	// Change the below according to you DBMS
	private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DATABASE_DRIVER = "org.h2.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
	private final static String DATABASE_USERNAME = "sa";
	private final static String DATABASE_PASSWORD = "";

	// datasource bean will be available
	@Bean
	public DataSource getDataSource() {

		BasicDataSource datasource = new BasicDataSource();

		datasource.setDriverClassName(DATABASE_DRIVER);
		datasource.setUrl(DATABASE_URL);
		datasource.setUsername(DATABASE_USERNAME);
		datasource.setPassword(DATABASE_PASSWORD);

		return datasource;
	}

	// session factory bean will be available
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

		builder.addProperties(getHibernateProperties());

		builder.scanPackages("net.tou.mantri.shoppingbackend.dto");

		return builder.buildSessionFactory();
	}

//ALll the HB prop will be returned in this method
	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");

		return properties;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;

	}
}
