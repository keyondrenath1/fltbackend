package com.fixer.lt.service;

import com.fixer.lt.TenantRequest;
import com.fixer.lt.entity.Property;
import com.fixer.lt.entity.Tenant;
import com.fixer.lt.model.TenantResponse;
import com.fixer.lt.services.PropertyService;
import com.fixer.lt.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class TenantController {

  private PropertyService propertyService;
  private TenantService tenantService;

  @Autowired
  public TenantController(PropertyService propertyService, TenantService tenantService) {
    this.propertyService = propertyService;
    this.tenantService = tenantService;
  }

  @PostMapping("/tenant")
  public ResponseEntity<?> createTenant(@RequestBody TenantRequest tenantRequest) {
    Property foundProperty = propertyService.findPropertyById(tenantRequest.getPropertyById());
    Tenant newTenant = tenantRequest.getTenant();
    newTenant.setProperty(foundProperty);
    Tenant createdTenant = tenantService.createTenant(newTenant);
    return new ResponseEntity<>(createdTenant, HttpStatus.CREATED);
  }

  @GetMapping("/tenant/{id}")
  public ResponseEntity<?> getTenantAccount(@PathVariable long id) {
    Optional<Tenant> optionalTenant = tenantService.findTenantById(id);
    return new ResponseEntity<>(optionalTenant.orElse(null), HttpStatus.OK);
  }

  @GetMapping("/tenant")
  public ResponseEntity<?> getAllTenants() {
    List<Tenant> tenants = tenantService.getTenants();
    List<TenantResponse> responses = new ArrayList<>();
    for (Tenant t : tenants) {
      TenantResponse tenant =
              new TenantResponse(t.getFirst_name(), t.getLast_name(), t.getAccount().getEmail(), t.getId());
      responses.add(tenant);
    }
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }
}
