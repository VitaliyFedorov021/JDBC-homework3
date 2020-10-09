package ua.com.alevel.dao;

import ua.com.alevel.dao.PersonDao;
import ua.com.alevel.dao.PersonDaoImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            PersonDao dao = new PersonDaoImpl();
            dao.addPersonToTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
