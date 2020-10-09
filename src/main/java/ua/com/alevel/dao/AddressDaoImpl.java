package ua.com.alevel.dao;

import ua.com.alevel.connection.Connector;
import ua.com.alevel.dto.Address;

import java.sql.*;
import java.util.Scanner;

public class AddressDaoImpl implements AddressDao {
    private final Connector connector = new Connector();
    @Override
    public int addAddressToTable() throws SQLException {
        Address address = createAddress();
        Connection connection = null;
        PreparedStatement statement = null;
        int key = 0;
        try {
            connection = connector.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO address (country, adressLine) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getAdressLine());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            while(set.next()) {
                key = set.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            statement.close();
            e.printStackTrace();
        }
        return key;
    }

    private Address createAddress() {
        Scanner sc = new Scanner(System.in);
        Address address = new Address();
        System.out.println("Enter the country");
        String country = sc.nextLine();
        System.out.println("Enter the address");
        String addressLine = sc.nextLine();
        address.setCountry(country);
        address.setAdressLine(addressLine);
        return address;
    }

}
