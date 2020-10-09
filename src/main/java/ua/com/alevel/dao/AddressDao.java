package ua.com.alevel.dao;

import ua.com.alevel.dto.Address;

import java.sql.SQLException;

public interface AddressDao {
    int addAddressToTable() throws SQLException;
}
