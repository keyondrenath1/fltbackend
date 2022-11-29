package com.fixer.lt.service;

import com.fixer.lt.entity.Fault;
import com.fixer.lt.exception.PropertyException;
import com.fixer.lt.model.FaultRequest;
import com.fixer.lt.services.FaultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class FaultsController {

    private FaultServiceImpl faultService;

    @Autowired
    public FaultsController(FaultServiceImpl faultService) {
        this.faultService = faultService;
    }

    @GetMapping("/faults/{landlordId}")
    public ResponseEntity<?> getFaults(@PathVariable long landlordId) {
        List<Fault> properties = faultService.getFaults(landlordId);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @GetMapping("/faults/tenant/{tenantEmail}")
    public ResponseEntity<?> getFaultsForTenant(@PathVariable String tenantEmail) {
        List<Fault> faults = faultService.getFaultsByTenant(tenantEmail);
        return new ResponseEntity<>(faults, HttpStatus.OK);
    }

    @PostMapping("/fault")
    public ResponseEntity<?> createFault(@RequestBody FaultRequest fault) {
        Fault savedFault = null;
        try {
            savedFault = faultService.createFault(fault);
        } catch (PropertyException e) {
            return new ResponseEntity<>(
                    "You have not been assigned to a property yet. Contact your landlord",
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping("/fault/status/{faultId}")
    public ResponseEntity<?> updateStatus(@PathVariable String faultId) {
        Fault fault = faultService.updateFault(faultId);
        return new ResponseEntity<>(fault, HttpStatus.CREATED);
    }
}
