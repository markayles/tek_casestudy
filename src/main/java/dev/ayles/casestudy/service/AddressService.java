package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses(){
        List<Address> addressList = new ArrayList<>();
        addressList = addressRepository.findAll();

        return addressList;
    }

    public Address getAddressById(Integer id){
        Address address = addressRepository.findById(id);
        return address;
    }

    public void save(Address address){
        addressRepository.save(address);
    }

}
