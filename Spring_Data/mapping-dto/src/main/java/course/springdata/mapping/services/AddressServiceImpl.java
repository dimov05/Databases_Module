package course.springdata.mapping.services;

import course.springdata.mapping.dao.AddressRepository;
import course.springdata.mapping.entities.Address;
import course.springdata.mapping.exceptions.NonExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepo;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElseThrow(
                () -> new NonExistingEntityException(
                        String.format("Address with ID=%s does not exist",
                                id)));
    }

    @Override
    public Address addAddress(Address address) {
        address.setId(null);
        return addressRepo.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
        getAddressById(address.getId());
        return addressRepo.save(address);
    }

    @Override
    public Address deleteAddressById(Long id) {
        Address removed = getAddressById(id);
        addressRepo.delete(removed);
        return removed;
    }

    @Override
    public long getAddressCount() {
        return addressRepo.count();
    }
}
