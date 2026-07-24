package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.ShipmentRequest;
import com.example.Eco_Backend.DTO.ShipmentResponse;
import com.example.Eco_Backend.Entity.Order;
import com.example.Eco_Backend.Entity.Shipment;
import com.example.Eco_Backend.Repository.OrderRepository;
import com.example.Eco_Backend.Repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public ShipmentResponse createShipment(ShipmentRequest request){
        Order order=orderRepository.findById(request.getOrderId())
                .orElseThrow(()->new RuntimeException("Order not found : "+request.getOrderId()));
        Shipment shipment=Shipment.builder()
                .order(order)
                .courierPartner(request.getCourierPartner())
                .trackingId(request.getTrackingId())
                .status("PENDING")
                .build();
        return mapToResponse(shipmentRepository.save(shipment));
    }
    public ShipmentResponse mapToResponse(Shipment shipment){
        return ShipmentResponse.builder()
                .id(shipment.getId())
                .orderId(shipment.getOrder().getId())
                .courierPartner(shipment.getCourierPartner())
                .trackingId(shipment.getTrackingId())
                .status(shipment.getStatus())
                .build();
    }
    public ShipmentResponse updateStatus(Long id,String status){
        Shipment shipment=shipmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Shipment not found : "+id));
        shipment.setStatus(status);
        return mapToResponse(shipmentRepository.save(shipment));
    }
    public ShipmentResponse getByTrackingId(String trackingId){
        Shipment shipment=shipmentRepository.findByTrackingId(trackingId)
                .orElseThrow(()->new RuntimeException("Shipment not found for tracnking id : "+trackingId));
        return mapToResponse(shipment);
    }
}
