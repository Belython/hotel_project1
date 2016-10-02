import by.kanarski.booking.dao.impl.RoomDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomDaoTest extends Assert{

    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        
    }

    @Test
    public void testGetInstance() throws Exception {
        RoomDao instance1 = RoomDao.getInstance();
        RoomDao instance2 = RoomDao.getInstance();
        assertEquals(instance1.hashCode(), instance2.hashCode());
    }



}
