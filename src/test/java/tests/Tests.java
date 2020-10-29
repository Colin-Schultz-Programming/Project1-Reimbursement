package tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


import java.util.LinkedList;
import java.util.List;


import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.reimbursement.config.RDBConnection;
import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.Reimbursement_Status;
import com.reimbursement.model.Reimbursement_Type;
import com.reimbursement.model.User;
import com.reimbursement.model.User_Role;
import com.reimbursement.repo.ReimbursementDao;
import com.reimbursement.repo.ReimbursementStatusDao;
import com.reimbursement.repo.ReimbursementTypeDao;
import com.reimbursement.repo.UserDao;
import com.reimbursement.repo.UserRoleDao;
import com.reimbursement.service.UtilityFunctions;

/*
 * JUNIT
 * 
 * 		Unit testing is meant to test your code as independently as possible.
 * 		
 * 		There are other independent units of code that require other units,
 * 			they can cause the entire group to fail. So you want to test
 * 			them as independently as possible.
 * 
 * 		TDD
 * 			Test Driven Development is the ART of writing code to follow
 * 				a testing procedure.
 * 
 * 			phases
 * 				red, green, refactor
 * 
 * MOCKITO
 * 	
 * 		Used for mocking dependencies that our classes have on another.
 */
public class Tests {

	
	
	
	@BeforeClass
	public static void beforeAll() {
		//System.out.println("Initialize Classes");
		//Scanner sc = new Scanner(System.in);
		//Logger logmock = mock(Logger.class);
		
	}
	
	@Test
	public void testHash() {
		//System.out.println(UtilityFunctions.encryptPassword("potato"));
		assertEquals("Testing for Encryption Working", "8ee2027983915ec78acc45027d874316", UtilityFunctions.encryptPassword("potato"));
	}
	
	@Test
	public void userDaoCreate() {
		UserDao create = new UserDao(mock(Logger.class));
		User test = (new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
		User returned = create.create(new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
		test.setUserID(returned.getUserID());
		create.delete(returned.getUserID());
		assertEquals(test.getClass(), returned.getClass());
		
		
	}
	@Test 
	public void userDeleteNotFound() {
		UserDao create = new UserDao(mock(Logger.class));
		int returned = create.delete(-1);
		assertEquals(1, returned);
	}
//	@Test
//	public void userDeleteDummy() {
//		UserDao create = new UserDao(mock(Logger.class));
//		User test = (new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
//		User returned = create.create(new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
//		test.setUserID(returned.getUserID());
//		int tester = create.delete(returned.getUserID());
//		assertEquals(1, tester);
//		
//	}
	@Test
	public void userUpdate() {
		assertNull(new UserDao(mock(Logger.class)).update(mock(User.class)));
	}
	@Test
	public void testRDBConnectionCreateRDBConnection() {
		assertNotNull(RDBConnection.CreateRDBConnection(mock(Logger.class)));
	}
	@Test
	public void userFindByID(){
		UserDao create = new UserDao(mock(Logger.class));
		User test = (new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
		User returned = create.create(new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
		test.setUserID(returned.getUserID());
		assertEquals(returned.getUsername(), create.findById(returned.getUserID()).getUsername());
		
		create.delete(returned.getUserID());
		
	}
	@Test
	public void userFindAll() {
		UserDao find = new UserDao(mock(Logger.class));
		User returned = find.create(new User(0, "test2", "potato", "testName", "lastName", "email2", 0));
		List<User> test = new LinkedList<User>();
		assertEquals(test.getClass(), find.findAll().getClass());
		find.delete(returned.getUserID());
	}
	@Test
	public void reimbFindAll() {
		ReimbursementDao find = new ReimbursementDao(mock(Logger.class));
		byte[] bytes = {10, 20, 30, 40};
		Reimbursement returned = find.create(new Reimbursement(0, 0, null, null, "test",bytes, 0, 0, 0, 0));
		List<Reimbursement> test = new LinkedList<Reimbursement>();
		assertEquals(test.getClass(), find.findAll().getClass());
		find.delete(returned.getReimbID());
	}
	@Test
	public void reimbFindAllByUser() {
		ReimbursementDao find = new ReimbursementDao(mock(Logger.class));
		byte[] bytes = {10, 20, 30, 40};
		Reimbursement returned = find.create(new Reimbursement(0, 0, null, null, "test",bytes, 0, 0, 0, 0));
		List<Reimbursement> test = new LinkedList<Reimbursement>();
		assertEquals(test.getClass(), find.findAllByUser(0).getClass());
		find.delete(returned.getReimbID());
	}
	@Test
	public void reimbFindByID() {
		ReimbursementDao find = new ReimbursementDao(mock(Logger.class));
		
		Reimbursement returned = find.create(new Reimbursement(0, 0, null, null, "test",null, 0, 0, 0, 0));
		assertEquals(returned.getClass(), find.findById(returned.getReimbID()).getClass());
		find.delete(returned.getReimbID());
	}
	@Test
	public void reimbUpdateD() {
		ReimbursementDao find = new ReimbursementDao(mock(Logger.class));
		Reimbursement returned = find.create(new Reimbursement(0, 0, null, null, "test",null, 0, 0, 1, 0));
		returned.setStatusID(1);
		assertNull(find.update(returned));
		find.delete(returned.getReimbID());
	}
	@Test
	public void reimbUpdateA() {
		ReimbursementDao find = new ReimbursementDao(mock(Logger.class));
		Reimbursement returned = find.create(new Reimbursement(0, 0, null, null, "test",null, 0, 0, 2, 0));
		returned.setStatusID(2);
		assertNull(find.update(returned));
		find.delete(returned.getReimbID());
	}
	@Test
	public void reimbStatusFindById() {
		ReimbursementStatusDao test = new ReimbursementStatusDao(mock(Logger.class));
		assertEquals("Pending", test.findById(0).getStatus());
	}
	@Test
	public void reimbStatusFindAll() {

		ReimbursementStatusDao find= new ReimbursementStatusDao(mock(Logger.class));
		List<Reimbursement_Status> test = new LinkedList<Reimbursement_Status>();
		assertEquals(test.getClass(), find.findAll().getClass());
	}
	@Test
	public void reimbStatusNulls() {
		ReimbursementStatusDao test = new ReimbursementStatusDao(mock(Logger.class));
		test.create(new Reimbursement_Status(1, "t"));
		test.delete(0);
		test.update(new Reimbursement_Status(1, "t"));
	}
	
	@Test
	public void reimbTypeFindById() {
		ReimbursementTypeDao test = new ReimbursementTypeDao(mock(Logger.class));
		assertEquals("Travel", test.findById(0).getType());
	}
	@Test
	public void reimbTypeFindAll() {

		ReimbursementTypeDao find= new ReimbursementTypeDao(mock(Logger.class));
		List<Reimbursement_Type> test = new LinkedList<Reimbursement_Type>();
		assertEquals(test.getClass(), find.findAll().getClass());
	}
	@Test
	public void reimbTypeNulls() {
		ReimbursementTypeDao test = new ReimbursementTypeDao(mock(Logger.class));
		test.create(new Reimbursement_Type(1, "t"));
		test.delete(0);
		test.update(new Reimbursement_Type(1, "t"));
	}
	
	@Test
	public void UserRoleFindById() {
		UserRoleDao test = new UserRoleDao(mock(Logger.class));
		assertEquals("employee", test.findById(0).getRole_type());
	}
	@Test
	public void userRoleFindAll() {

		UserRoleDao find= new UserRoleDao(mock(Logger.class));
		List<User_Role> test = new LinkedList<User_Role>();
		assertEquals(test.getClass(), find.findAll().getClass());
	}
	@Test
	public void userRoleNulls() {
		UserRoleDao test = new UserRoleDao(mock(Logger.class));
		test.create(new User_Role(1, "t"));
		test.delete(0);
		test.update(new User_Role(1, "t"));
	}
	
	
	
	
	
	
}
