package lk.ijse.orm.config;


import lk.ijse.orm.entity.Program;
import lk.ijse.orm.entity.Student;
import lk.ijse.orm.entity.StudentProgramDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            Configuration configuration = new Configuration().configure()
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Program.class)
                    .addAnnotatedClass(StudentProgramDetails.class);

            sessionFactory = configuration.buildSessionFactory();
            System.out.println("SessionFactory initialized successfully.");
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}