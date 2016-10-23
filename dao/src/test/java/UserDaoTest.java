import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.EntityBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest extends Assert{

    UserDao userDao = UserDao.getInstance();

    private User expectedUser;
    private List<User> expectedUserList;
    private User newUser;
    private User updatedUser;

    @Before
    public void setUp() {
        expectedUserList = getExpectedUserList();
        expectedUser = expectedUserList.get(0);
        newUser = getNewUser();
        updatedUser = getUpdatedUser();
    }

    @After
    public void tearDown() {
        expectedUserList = null;
        expectedUser = null;
        newUser = null;
        updatedUser = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        UserDao instance1 = UserDao.getInstance();
        UserDao instance2 = UserDao.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception {
        User newUser = getNewUser();
        User expected = userDao.add(newUser);
        User actual = userDao.getById(expected.getUserId());
        userDao.delete(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        try {
            User actual = userDao.getById(expectedUser.getUserId());
            assertEquals(expectedUser, actual);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> actual = userDao.getAll();
        assertEquals(expectedUserList, actual);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User actual = userDao.getByLogin(expectedUser.getLogin());
        assertEquals(expectedUser, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(updatedUser);
        User actual = userDao.getById(updatedUser.getUserId());
        assertEquals(updatedUser, actual);
        userDao.update(expectedUser);
    }

    @Test
    public void testIsAuthorized() throws Exception {
        String login = expectedUser.getLogin();
        String password = expectedUser.getPassword();
        boolean isAuthorized = userDao.isAuthorized(login, password);
        assertTrue(isAuthorized);
    }

    @Test
    public void testIsNewUser() throws Exception {
        boolean isNewUser = userDao.isNewUser(newUser);
        assertTrue(isNewUser);
    }

    @Test(expected = DaoException.class)
    public void testDelete() throws Exception {
        User newUser = userDao.add(getNewUser());
        userDao.delete(newUser);
        User deletedUser = userDao.getById(newUser.getUserId());
        assertNull(deletedUser);
    }

    private User getNewUser() {
        User newUser = EntityBuilder.buildUser("testFirstNameNew", "testLastNameNew", "tesNew@test.com",
                "testLoginNew", "testPasswordNew", FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return newUser;
    }
    
    private User getUpdatedUser() {
        User updatedUser = EntityBuilder.buildUser(expectedUser.getUserId(), "testFirstNameUpdated",
                "testLastNameUpdated", "tesUpdated@test.com", "testLoginUpdated", "testPasswordUpdated",
                FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return updatedUser;
    }

    private List<User> getExpectedUserList() {
        expectedUserList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long testId = i;
            String testFirstName = "testFirstName" + i;
            String testLastName = "testLastName" + i;
            String testEmail = "test" + i + "@gmail.com";
            String testLogin = "testLogin" + i;
            String testPassword = "testPassword" + i;
            String role = FieldValue.ROLE_CLIENT;
            String status = FieldValue.STATUS_ACTIVE;
            User user = EntityBuilder.buildUser(testId, testFirstName, testLastName, testEmail, testLogin,
                    testPassword, role, status);
            expectedUserList.add(user);
        }
        return expectedUserList;
    }


}
