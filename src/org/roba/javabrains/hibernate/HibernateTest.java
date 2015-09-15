package org.roba.javabrains.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.roba.javabrains.dto.UserDetails;

/**
 * Test class to work with Hibernate
 * 
 * @author roba
 *
 */
public class HibernateTest {

	/**
	 * Main method to work with
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Space for code

	}

	/**
	 * Just tested data retrieving from database
	 */
	private static void testingDataRetrievingFromDatabase() {
		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("First name");
		user.setAddress("First user's address");
		user.setJoinedDate(new Date());
		user.setDescription("This is a simple description");

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

		user = null;

		session = sessionFactory.openSession();
		session.beginTransaction();
		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("This is name retrieved from database "
				+ user.getAddress());
	}

	/**
	 * Here I have tested more of annotations (more in UserDetails class)
	 */
	private static void testingMoreAnnotations() {
		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("First name");
		user.setAddress("First user's address");
		user.setJoinedDate(new Date());
		user.setDescription("This is a simple description");
		StringBuilder sb = new StringBuilder("This is a simple description");

		// This error I will have to leave cause I don't know how to fix it at
		// the moment
		// user.setDescriptionLob(sb.toString().toCharacter());

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * Simple method which tested how column naming work with Hibernate
	 */
	private static void testedColumnNamingInHibernate() {
		UserDetails user = new UserDetails();
		user.setId(1);
		user.setUserName("First Name");

		// Using Hibernate 4
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * This is a method which is testing update and create properties in
	 * hibernate config file
	 */
	private static void testingUpdateAndCreateProperties() {
		UserDetails user = new UserDetails();
		user.setId(2);
		user.setUserName("Second Name");

		// Using Hibernate 4
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * This is a method which is keeping all used methods
	 */
	private static void testedMethods() {
		firstHibernateTest();
		testingUpdateAndCreateProperties();
		testedColumnNamingInHibernate();
		testingMoreAnnotations();
		testingDataRetrievingFromDatabase();
	}

	/**
	 * Just a simple hibernate test to see if everything is configured well
	 */
	private static void firstHibernateTest() {

		// This is for hibernate 3

		// SessionFactory sessionFactory = new Configuration().configure()
		// .buildSessionFactory();
		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// session.save(user);
		// session.getTransaction().commit();

		// This is an update for hibernate 4

		testedColumnNamingInHibernate();
	}
}
