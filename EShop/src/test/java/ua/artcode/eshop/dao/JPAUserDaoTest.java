package ua.artcode.eshop.dao;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.Product;
import ua.artcode.eshop.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPAUserDaoTest {

    private static String userEmail;
    private static int lastAddedUserId;

    @Autowired
    @Qualifier(value = "jpaUserDao")
    private UserDao dao;

    @BeforeClass // before all tests
    public static void setUpClass(){

    }

    @Test
    public void _01testSimpleCreate(){
        String email = System.currentTimeMillis() + "@email.com";
        User user = new User(email, "user test fullname", "+380933091219", "1234");
        dao.create(user);

        try {
            User found = dao.find(email);
            assertEquals(found.getEmail(), email);
        } catch (NoUserFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void _02testFindUserById(){
        List<User> all = dao.findAll();

        User forDelete = all.get(all.size() - 1);

        try {
            org.junit.Assert.assertNotNull(dao.find(forDelete.getId()));
        } catch (NoUserFoundException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = NoUserFoundException.class)
    public void _03testSimpleDelete() throws NoUserFoundException {
        List<User> all = dao.findAll();

        User forDelete = all.get(all.size() - 1);

        dao.delete(forDelete);

        dao.find(forDelete.getId());

    }

    @Test
    public void _04addUserWithProduct(){
        String email = System.currentTimeMillis() + "@email.com";
        User user = new User(email, "user test fullname", "+380933091219", "1234");

        user.getProducts().add(new Product("name1", 300, "title1", "desc1", user));
        user.getProducts().add(new Product("name2", 456, "title2", "desc2", user));

        dao.create(user);

        lastAddedUserId = user.getId();

        try {
            assertEquals(dao.find(email).getProducts().size(), 2);
        } catch (NoUserFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void _05testUserProductFetchType(){
        try {
            User found = dao.find(lastAddedUserId);

            // not in one ntityManager
            List<Product> products = found.getProducts();

            int size = products.size();

            assertEquals(size, 2);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void testSimpleAddSeveralAll(){
        // first add several
        // then check list
        //List<User> all = dao.findAll();
    }

    @Ignore
    @Test
    public void testFindUserByEmail(){

    }

}
