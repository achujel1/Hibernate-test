package org.roba.javabrains.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.roba.javabrains.dto.Address;
import org.roba.javabrains.dto.UserDetails;
import org.roba.javabrains.dto.Vehicle;

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

		// Space for some code

	}

	private static void testingManyToManyAnnotations() {
		UserDetails user = new UserDetails();
		user.setUserName("First user");

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");

		user.getListOfVehicles().add(vehicle);
		user.getListOfVehicles().add(vehicle2);
		vehicle.getListOfUsers().add(user);
		vehicle2.getListOfUsers().add(user);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(vehicle);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	private static void testingOneToManyAnnotation() {
		UserDetails user = new UserDetails();
		user.setUserName("First user");

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Jeep");

		// Commenting because methods have been changed
		// user.getVehicle().add(vehicle);
		// user.getVehicle().add(vehicle2);
		// vehicle.setUser(user);
		// vehicle2.setUser(user);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(vehicle);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Part 13 One to One mapping
	 * 
	 * Tested how one to one mapping works: - Added new class Vehicle - Added
	 * annotations @Entity, @Id, @GeneratedValue - Added mapping in
	 * hibernate.cfg.xml - Tesetd addition of Vahicle object to UserDetails
	 * table
	 */
	private static void testingOneToOneMapping() {
		UserDetails user = new UserDetails();
		user.setUserName("UserName");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleId(2);
		vehicle.setVehicleName("Vehicle name");

		// user.setVehicle(vehicle);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(vehicle);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Tested how fetchType works and what it is
	 */
	private static void testingFetchType() {
		UserDetails user = new UserDetails();
		user.setUserName("UserName");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		UserDetails user2 = new UserDetails();
		user2.setUserName("Second username");
		user2.setJoinedDate(new Date());
		user2.setDescription("Simple description");

		Address addr1 = new Address();
		addr1.setStreet("First street");
		addr1.setState("First state");
		addr1.setCity("First city");
		addr1.setPincode("123456");

		Address addr2 = new Address();
		addr2.setStreet("Second street");
		addr2.setState("Second state");
		addr2.setCity("Second city");
		addr2.setPincode("564123");

		user.getListOfAddresses().add(addr1);
		user.getListOfAddresses().add(addr2);

		Address addr3 = new Address();
		addr3.setStreet("Third street");
		addr3.setState("Third state");
		addr3.setCity("Third city");
		addr3.setPincode("789456");

		Address addr4 = new Address();
		addr4.setStreet("Fourth street");
		addr4.setState("Fourth state");
		addr4.setCity("Fourth city");
		addr4.setPincode("987654");

		user2.getListOfAddresses().add(addr3);
		user2.getListOfAddresses().add(addr4);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();

		// Testing out how fetchType.EAGER and fetchType.LAZY works
		session = sessionFactory.openSession();
		user = (UserDetails) session.get(UserDetails.class, 1);
		session.close();
		System.out.println(user.getListOfAddresses().size());
	}

	/**
	 * Tested how to create a primary key in a collection
	 */
	private static void testingPrimeryKeysInCollections() {
		UserDetails user = new UserDetails();
		user.setUserName("UserName");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		UserDetails user2 = new UserDetails();
		user2.setUserName("Second username");
		user2.setJoinedDate(new Date());
		user2.setDescription("Simple description");

		Address addr1 = new Address();
		addr1.setStreet("First street");
		addr1.setState("First state");
		addr1.setCity("First city");
		addr1.setPincode("123456");

		Address addr2 = new Address();
		addr2.setStreet("Second street");
		addr2.setState("Second state");
		addr2.setCity("Second city");
		addr2.setPincode("564123");

		user.getListOfAddresses().add(addr1);
		user.getListOfAddresses().add(addr2);

		Address addr3 = new Address();
		addr3.setStreet("Third street");
		addr3.setState("Third state");
		addr3.setCity("Third city");
		addr3.setPincode("789456");

		Address addr4 = new Address();
		addr4.setStreet("Fourth street");
		addr4.setState("Fourth state");
		addr4.setCity("Fourth city");
		addr4.setPincode("987654");

		user2.getListOfAddresses().add(addr3);
		user2.getListOfAddresses().add(addr4);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Here I'm testing collections of elements and trying out @ElementCollection
	 * annotation
	 */
	private static void testingCollectionsOfElements() {
		UserDetails user = new UserDetails();
		user.setUserName("UserName");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		UserDetails user2 = new UserDetails();
		user2.setUserName("Second username");
		user2.setJoinedDate(new Date());
		user2.setDescription("Simmple description");

		Address addr1 = new Address();
		addr1.setStreet("Frist street");
		addr1.setState("First state");
		addr1.setCity("First city");
		addr1.setPincode("12345");

		Address addr2 = new Address();
		addr2.setStreet("Second street");
		addr2.setState("Second state");
		addr2.setCity("Second city");
		addr2.setPincode("54321");

		user.getListOfAddresses().add(addr1);
		user.getListOfAddresses().add(addr2);

		Address addr3 = new Address();
		addr3.setStreet("Third Name");
		addr3.setState("Third state");
		addr3.setCity("Third city name name");
		addr3.setPincode("1234567");

		Address addr4 = new Address();
		addr4.setStreet("Fourth Name");
		addr4.setState("Fourth state");
		addr4.setCity("Fourth city name name");
		addr4.setPincode("412312");

		user2.getListOfAddresses().add(addr3);
		user2.getListOfAddresses().add(addr4);

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * I've tested how setting and embedded objects work. Also, tested on how to
	 * set these embedded objects with different column name. Did this with @AttributesOverride
	 * annotation.
	 */
	private static void testingEmbeddedObjects() {
		UserDetails user = new UserDetails();
		user.setUserName("UserName");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		Address addr = new Address();
		addr.setStreet("Street Name");
		addr.setState("State Name");
		addr.setCity("City name");
		addr.setPincode("1234567");

		// Commenting this for future tests
		// user.setWorkAddress(addr);

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
	}

	/**
	 * Tested how embedded objects are working in hibernate
	 */
	private static void testedEmbeddedObjects() {
		UserDetails user = new UserDetails();
		user.setUserName("Username");
		user.setJoinedDate(new Date());
		user.setDescription("Simple description");

		Address addr = new Address();
		addr.setStreet("Street name");
		addr.setState("Sate name");
		addr.setCity("City name");
		addr.setPincode("123465798");

		user.setAddress(addr);

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
	}

	/**
	 * Tested how primary keys are automatically added with Hibernate to
	 * database
	 */
	private static void testingAutomaticAdditionOfPrimaryKeys() {
		UserDetails user = new UserDetails();
		user.setUserName("Second Name");
		UserDetails user2 = new UserDetails();
		user2.setUserName("Third Name");

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user2);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Just tested data retrieving from database
	 */
	private static void testingDataRetrievingFromDatabase() {
		UserDetails user = new UserDetails();
		user.setUserId(1);
		user.setUserName("First name");
		// user.setAddress("First user's address");
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
		// user.setAddress("First user's address");
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
		testingAutomaticAdditionOfPrimaryKeys();
		testedEmbeddedObjects();
		testingEmbeddedObjects();
		testingCollectionsOfElements();
		testingPrimeryKeysInCollections();
		testingFetchType();
		testingOneToOneMapping();
		testingOneToManyAnnotation();
		testingManyToManyAnnotations();
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
