package ma.projet.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ma.projet.beans.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();

            // Charger application.properties
            Properties properties = new Properties();
            try (InputStream input = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                if (input == null) {
                    System.err.println("Unable to find application.properties");
                    throw new RuntimeException("application.properties not found");
                }
                properties.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Error loading application.properties", ex);
            }

            // Configuration Hibernate à partir de application.properties
            Properties hibernateProperties = new Properties();
            hibernateProperties.put(Environment.DRIVER, properties.getProperty("spring.datasource.driver-class-name"));
            hibernateProperties.put(Environment.URL, properties.getProperty("spring.datasource.url"));
            hibernateProperties.put(Environment.USER, properties.getProperty("spring.datasource.username"));
            hibernateProperties.put(Environment.PASS, properties.getProperty("spring.datasource.password"));
            hibernateProperties.put(Environment.DIALECT, properties.getProperty("spring.jpa.properties.hibernate.dialect"));
            hibernateProperties.put(Environment.SHOW_SQL, properties.getProperty("spring.jpa.show-sql"));
            hibernateProperties.put(Environment.FORMAT_SQL, properties.getProperty("spring.jpa.properties.hibernate.format_sql"));
            hibernateProperties.put(Environment.HBM2DDL_AUTO, properties.getProperty("spring.jpa.hibernate.ddl-auto"));
            hibernateProperties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            configuration.setProperties(hibernateProperties);

            // Ajouter les classes annotées
            configuration.addAnnotatedClass(Personne.class);
            configuration.addAnnotatedClass(Homme.class);
            configuration.addAnnotatedClass(Femme.class);
            configuration.addAnnotatedClass(Mariage.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
