package ua.artcode.eshop.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.artcode.eshop.db.AppDb;
import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import java.util.List;

/**
 *
 */
public class InnerUserDao implements UserDao {

    private static final Logger LOG = LogManager.getLogger(InnerUserDao.class);

    private AppDb appDb;

    public InnerUserDao(AppDb appDb) {
        this.appDb = appDb;
    }

    @Override
    public User create(User user) {
        return appDb.addUser(user);
    }

    @Override
    public User find(int id) throws NoUserFoundException {
        return appDb.getById(id);
    }

    @Override
    public User find(String email) throws NoUserFoundException {
        LOG.info("searching user by email " + email);
        return appDb.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return appDb.getUserList();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
