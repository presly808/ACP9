package com.artcode.myproject.dao;

import com.artcode.myproject.exceptions.NoRentalRequirementsFoundException;
import com.artcode.myproject.model.AppartmentsType;
import com.artcode.myproject.model.RentalRequirements;

import java.sql.SQLException;
import java.util.List;

public interface RentalRequirementsDao {
    int create(RentalRequirements... RentalRequirements) throws SQLException;

    RentalRequirements find(int id) throws NoRentalRequirementsFoundException, SQLException;

    RentalRequirements find(AppartmentsType appartmentsType) throws NoRentalRequirementsFoundException;

    List<RentalRequirements> findAll() throws SQLException;

    RentalRequirements update(RentalRequirements RentalRequirements);

    boolean delete(RentalRequirements RentalRequirements);
}
