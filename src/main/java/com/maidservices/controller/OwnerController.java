package com.maidservices.controller;

import com.maidservices.dtos.OwnerDTO;
import com.maidservices.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/ownerservice")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/Owner")
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO createOwner = ownerService.createOwner(ownerDTO);
        return new ResponseEntity<>(createOwner, HttpStatus.CREATED);
    }

    @GetMapping("/Owner/{id}")
    public ResponseEntity<OwnerDTO> getOwnerId(@PathVariable("id")Long id){
        OwnerDTO ownerDTO = ownerService.getOwnerId(id);
        return ResponseEntity.ok(ownerDTO);
    }

    @PutMapping("/updateOwner/{id}")
    public ResponseEntity<OwnerDTO> updateByOwnerId(@PathVariable("id") Long id, @RequestBody OwnerDTO ownerDTO ){
        OwnerDTO updatedOwner = ownerService.updateOwnerById(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }
    @DeleteMapping("/Owner/{id}")
    public ResponseEntity<Map<String,Object>> deleteOwnerById(Long id){
        Map<String, Object> response = ownerService.deleteOwnerById(id);
        return ResponseEntity.ok(response);
    }
}
