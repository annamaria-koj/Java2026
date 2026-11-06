import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStream;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class JpaUtil {

    private static EntityManagerFactory factory;

    private JpaUtil() {
    }

    public static synchronized EntityManagerFactory getFactory() {
        if (factory == null) {
            try {
                Properties properties = new Properties();

                try (InputStream input = JpaUtil.class
                        .getClassLoader()
                        .getResourceAsStream("application.properties")) {

                    if (input == null) {
                        throw new RuntimeException(
                                "application.properties not found");
                    }

                    properties.load(input);
                }

                Properties hibernateProperties = new Properties();

                hibernateProperties.put(
                        "jakarta.persistence.jdbc.url",
                        properties.getProperty("db.url"));

                hibernateProperties.put(
                        "jakarta.persistence.jdbc.user",
                        properties.getProperty("db.username"));

                hibernateProperties.put(
                        "jakarta.persistence.jdbc.password",
                        properties.getProperty("db.password"));

                hibernateProperties.put(
                        "jakarta.persistence.jdbc.driver",
                        "com.mysql.cj.jdbc.Driver");

                factory = Persistence.createEntityManagerFactory(
                        "sport-inventory",
                        hibernateProperties);

            } catch (Exception e) {
                throw new RuntimeException(
                        "Hibernate initialization failed", e);
            }
        }

        return factory;
    }

    public static void executeInTransaction(
            Consumer<EntityManager> action) {

        EntityManager em = getFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;

        } finally {
            em.close();
        }
    }

    public static <T> T executeRead(
            Function<EntityManager, T> action) {

        EntityManager em = getFactory().createEntityManager();

        try {
            return action.apply(em);
        } finally {
            em.close();
        }
    }

    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
            factory = null;
        }
    }
}
