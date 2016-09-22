import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.utils.EntityBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

    User expected;

    @Before
    public void setUp() {
        expected = EntityBuilder.buildUser(0, "testFirstName", "testLastName", "testEmail", "testLogin", "testPassword",
                "testRole", "testStatus");
    }

    @After
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        UserDao instance1 = UserDao.getInstance();
        UserDao instance2 = UserDao.getInstance();
        Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
    }

    @Test
    public void testAdd() throws Exception {
        UserDao.getInstance().add(expected);
        User actual = UserDao.getInstance().getById(expected.getUserId());
        Assert.assertEquals(expected, actual);
        UserDao.getInstance().delete(expected);
    }

//    @Test
//    public void testGetById() throws Exception {
//        expected = EntityBuilder.buildUser(1, "ADMIN", 0, 0);
//        User actual = UserDao.getInstance().getById(expected.getRoomTypeId());
//        Assert.assertEquals(expected, actual);
//    }

//    @Test
//    public void testUpdateAmount() throws Exception {
//        UserDao.getInstance().add(expected);
//        double adding = 100;
//        expected.setAmount(expected.getAmount() + adding);
//        UserDao.getInstance().updateAmount(adding, expected.getRoomTypeId());
//        User actual = UserDao.getInstance().getById(expected.getRoomTypeId());
//        UserDao.getInstance().delete(expected.getRoomTypeId());
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        UserDao.getInstance().add(expected);
//        UserDao.getInstance().delete(expected.getRoomTypeId());
//        User actual = UserDao.getInstance().getById(expected.getRoomTypeId());
//        Assert.assertNull(actual);
//    }

}
