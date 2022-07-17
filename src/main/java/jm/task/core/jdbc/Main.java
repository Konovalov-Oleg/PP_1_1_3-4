package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Oleg", "Konovalov", (byte) 25);
        userDao.saveUser("Ivan", "Ivanovich", (byte) 55);
        userDao.saveUser("Chip", "Chip", (byte) 24);
        userDao.saveUser("Misha", "Volkov", (byte) 99);

        System.out.println(userDao.getAllUsers());

        userDao.cleanUsersTable();

        userDao.dropUsersTable();

        Util.closeConnection();
    }
}
