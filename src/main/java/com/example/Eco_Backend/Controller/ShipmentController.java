package com.example.Eco_Backend.Controller;

import com.example.Eco_Backend.DTO.ShipmentRequest;
import com.example.Eco_Backend.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {
    @Autowired
    ShipmentService shipmentService;
    @PostMapping("/create")
    public ResponseEntity<?>createShipment(@RequestBody ShipmentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.createShipment(request));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateShipment(@PathVariable Long id,@RequestParam String status){
        return ResponseEntity.ok(shipmentService.updateStatus(id,status));
    }
    @GetMapping("/{trackingId}")
    public ResponseEntity<?>getToTrack(@PathVariable String trackingId){
        return ResponseEntity.ok(shipmentService.getByTrackingId(trackingId));
    }
}
