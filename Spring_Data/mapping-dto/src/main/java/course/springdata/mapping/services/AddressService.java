package course.springdata.mapping.services;

import course.springdata.mapping.entities.Address;

import java.util.List;


public interface AddressService {
    List<Address> getAllAddresses();

    Address getAddressById(Long id);

    Address addAddress(Address address);

    Address updateAddress(Address address);

    Address deleteAddressById(Long id);

    long getAddressCount();

}
