package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.AddressRequest;
import com.example.Eco_Backend.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping("/create")
    public ResponseEntity<?>createAddress(@RequestBody AddressRequest request){
        return ResponseEntity.ok(addressService.registerAddress(request));
    }
    @GetMapping("/userId/{id}")
    public ResponseEntity<?>AddressByUserId(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressByUser(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
