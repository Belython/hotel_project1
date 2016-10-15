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

    UserDao useDao = UserDao.getInstance();

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
        User expected = UserDao.getInstance().add(newUser);
        User actual = UserDao.getInstance().getById(expected.getUserId());
        UserDao.getInstance().delete(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        User actual = UserDao.getInstance().getById(expectedUser.getUserId());
        assertEquals(expectedUser, actual);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User actual = UserDao.getInstance().getByLogin(expectedUser.getLogin());
        assertEquals(expectedUser, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        UserDao.getInstance().update(updatedUser);
        User actual = UserDao.getInstance().getById(updatedUser.getUserId());
        assertEquals(updatedUser, actual);
        UserDao.getInstance().update(expectedUser);
    }

    @Test
    public void testIsAuthorized() throws Exception {
        String login = expectedUser.getLogin();
        String password = expectedUser.getPassword();
        boolean isAuthorized = UserDao.getInstance().isAuthorized(login, password);
        assertTrue(isAuthorized);
    }

    @Test
    public void testIsNewUser() throws Exception {
        String login = newUser.getLogin();
        boolean isNewUser = UserDao.getInstance().isNewUser(login);
        assertTrue(isNewUser);
    }

    @Test(expected = DaoException.class)
    public void testDelete() throws Exception {
        User newUser = UserDao.getInstance().add(getNewUser());
        UserDao.getInstance().delete(newUser);
        User deletedUser = UserDao.getInstance().getById(newUser.getUserId());
        assertNull(deletedUser);
    }

    private User getNewUser() {
        User newUser = EntityBuilder.buildUser("testFirstNameNew", "testLastNameNew", "tesNew@test.com",
                "testLoginNew", "testPasswordNew", FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return newUser;
    }
    
    private User getUpdatedUser() {
        User updatedUser = EntityBuilder.buildUser(1, "testFirstNameUpdated", "testLastNameUpdated", "tesUpdated@test.com",
                "testLoginUpdated", "testPasswordUpdated", FieldValue.ROLE_CLIENT, FieldValue.STATUS_ACTIVE);
        return updatedUser;
    }

    private List<User> getExpectedUserList() {
        expectedUserList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            long testId = i;
            String testFirstName = "testFirstName" + i;
            String testLastName = "testLastName" + i;
            String testEmail = "test" + i + "@test.com";
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
