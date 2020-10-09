package ua.com.alevel.dao;

import ua.com.alevel.connection.Connector;
import ua.com.alevel.dto.Address;
import ua.com.alevel.dto.Person;

import java.sql.*;
import java.util.Scanner;

public class PersonDaoImpl implements PersonDao {
    private final Connector connector = new Connector();
    private final AddressDao dao = new AddressDaoImpl();
    public PersonDaoImpl() throws SQLException {
    }

    @Override
    public void addPersonToTable() throws SQLException {
        int key = dao.addAddressToTable();
        Person person = createPerson();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connector.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO person (firstName, lastName, addressId) values (?, ?, ?)");
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, key);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            statement.close();
            e.printStackTrace();
        }
    }

    private Person createPerson() {
        Scanner sc = new Scanner(System.in);
        Person person = new Person();
        System.out.println("Enter the first name");
        String firstName = sc.nextLine();
        System.out.println("Enter the last name");
        String lastName = sc.nextLine();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }
}
