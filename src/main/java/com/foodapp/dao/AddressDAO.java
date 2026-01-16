package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.Address;

public interface AddressDAO {

    List<Address> getAddressesByUser(int userId);

    void addAddress(Address address);

    void setDefaultAddress(int userId, int addressId);

	void saveAddress(Address a);
}
