package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.AddressRequest;
import com.example.Eco_Backend.DTO.AddressResponse;
import com.example.Eco_Backend.Entity.Address;
import com.example.Eco_Backend.Entity.User;
import com.example.Eco_Backend.Repository.AddressRepository;
import com.example.Eco_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    public AddressResponse registerSeller(AddressRequest request){
        User user =userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found : "+request.getUserId()));
        Address address=Address.builder()
                .user(user)
                .address_line(request.getAddressLine())
                .pincode(request.getPinCode())
                .city(request.getCity())
                .is_default(request.isDefault())
                .build();
            return mapToResponse(addressRepository.save(address));
    }

    public List<AddressResponse>getAddressByUser(Long useId){
        return addressRepository.findByUserId(useId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public void deleteAddress(Long id){
        if (!addressRepository.existsById(id)){
            throw new RuntimeException("address not found : "+id);
        }
        addressRepository.deleteById(id);
    }
    public AddressResponse mapToResponse(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .addressLine(address.getAddress_line())
                .city(address.getCity())
                .pinCode(address.getPincode())
                .isDefault(address.is_default())
                .build();
    }
}
