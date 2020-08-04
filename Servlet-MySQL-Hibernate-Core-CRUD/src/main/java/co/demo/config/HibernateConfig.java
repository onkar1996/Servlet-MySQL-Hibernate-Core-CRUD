package co.demo.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import co.demo.model.Employee;

public class HibernateConfig {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration config = new Configuration();
			Properties props = new Properties();
			props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			props.put(Environment.URL, co.demo.config.Configuration.DBUrl);
			props.put(Environment.USER, co.demo.config.Configuration.DBUserName);
			props.put(Environment.PASS, co.demo.config.Configuration.DBPassword);
			props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			props.put(Environment.SHOW_SQL, "true");
			props.put(Environment.HBM2DDL_AUTO, "none");
			props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			props.put(Environment.POOL_SIZE, "4");
			// props.put(Environment.JDBC_TIME_ZONE, "Asia/Kolkata"); // issue

			config.setProperties(props);

			// Add Entity classes here
			config.addAnnotatedClass(Employee.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
					.build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}
		return sessionFactory;
	}

}
