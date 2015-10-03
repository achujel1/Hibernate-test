package org.roba.javabrains.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.roba.javabrains.dto.Address;
import org.roba.javabrains.dto.FourWheeler;
import org.roba.javabrains.dto.TwoWheeler;
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

	/**
	 * Tested how hql and the query objects work in hibernate
	 */
	private static void hqlAndTheQueryObjects() {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 0; i < 9; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("user name");
			session.save(user);
		}

		Query query = session.createQuery("from UserDetails");
		List users = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size of list result = " + users.size());
	}

	/**
	 * Here I'm craeting an object after session's transaction has begun
	 * working. I'm closing session, creating a new one and updating the same
	 * object.
	 */
	private static void persistedDetachedObjects() {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);

		session.getTransaction().commit();
		session.close();

		user.setUserName("Changed name");

		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Tested how Transient, Persistent and Detached Objects work in hibernate
	 */
	private static void transientPersistenAndDetachedObjects() {
		// Creating a new user object
		UserDetails user = new UserDetails();
		user.setUserName("First user name");

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Changing user's name
		// This is transient?
		user.setUserName("Name changed before session.save()");
		session.save(user);

		// This will be written into database
		// This is persistent?
		user.setUserName("Name changed after session.save()");

		session.getTransaction().commit();
		session.close();

		// This is detach object
		// This won't be written into database
		user.setUserName("Name changed after session.close()");
	}

	/**
	 * Tested some of the CRUD operations with database
	 */
	private static void crudOperations() {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Using loop to create some user objects in database
		for (int i = 1; i < 10; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("UserName " + i);
			System.out.println("Creating: " + user.getUserName());
			session.save(user);
		}

		// Using loop to retrieve some of the objects in database
		for (int i = 1; i < 10; i++) {
			UserDetails user = (UserDetails) session.get(UserDetails.class, i);
			System.out.println("Retrieving: " + user.getUserName());
		}

		// Using loop to update and delete some of the values in database
		for (int i = 1; i < 10; i++) {
			UserDetails user = (UserDetails) session.get(UserDetails.class, i);
			if (i % 2 == 0) {
				user.setUserName("Updated");
				session.update(user);
			} else if (i % 3 == 0) {
				session.delete(user);
			}
		}

		// Finally getting all of the UserDetails objects in databse
		for (int i = 1; i < 10; i++) {
			UserDetails user = (UserDetails) session.get(UserDetails.class, i);
			// Avoiding user's null value
			if (user != null) {
				System.out.println(user.getUserName());
			}

		}

		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Implemented inheritance with table per class strategy
	 */
	private static void implementingInheritanceWithTablePerClassStrategy() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");

		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("suzuki");
		bike.setSteeringHandle("Bike steering handle");

		FourWheeler car = new FourWheeler();
		car.setVehicleName("ferrari");
		car.setSteeringWheel("car steering wheel");

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(car);
		session.save(bike);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Tested how to implement inheritance with single table
	 */
	private static void implementingInheritanceSingleTable() {
		implementingInheritanceWithTablePerClassStrategy();
	}

	/**
	 * Implemented inheritance to vehicle class and tested how everything worked
	 * out
	 */
	private static void implementingInheritance() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("car");

		TwoWheeler bike = new TwoWheeler();
		bike.setVehicleName("ducatti");
		bike.setSteeringHandle("Bike steering handle");

		FourWheeler car = new FourWheeler();
		car.setVehicleName("porsche");
		car.setSteeringWheel("car steering wheel");

		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(bike);
		session.save(car);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Testing cascade and NotFound annotation
	 */
	private static void testingCascadeAndNotFound() {
		UserDetails user = new UserDetails();
		user.setUserName("First name");

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("First vehicle");

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Second vehicle");

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
		// Using persist instead of save to SAVE reference objects in user
		// objects
		session.persist(user);
		session.getTransaction().commit();
		session.close();
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
		testingCascadeAndNotFound();
		implementingInheritance();
		implementingInheritanceSingleTable();
		implementingInheritanceWithTablePerClassStrategy();
		crudOperations();
		transientPersistenAndDetachedObjects();
		persistedDetachedObjects();
		hqlAndTheQueryObjects();
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
