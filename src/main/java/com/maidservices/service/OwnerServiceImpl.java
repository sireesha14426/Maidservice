package com.maidservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.dtos.OwnerDTO;
import com.maidservices.exceptions.ResourceNotFoundException;
import com.maidservices.models.Owner;
import com.maidservices.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = objectMapper.convertValue(ownerDTO, Owner.class);
        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));
        owner.setDescription("Owner created at " + currentTime);
        Owner savedOwner = ownerRepo.save(owner);
        return objectMapper.convertValue(savedOwner, OwnerDTO.class);
    }

    @Override
    public OwnerDTO getOwnerId(Long id) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + id));
        return objectMapper.convertValue(owner, OwnerDTO.class);
    }

    @Override
    public OwnerDTO updateOwnerById(Long id, OwnerDTO ownerDTO) {
        Owner existingOwner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + id));
        existingOwner.setName(ownerDTO.getName());
        existingOwner.setFlatName(ownerDTO.getFlatName());
        existingOwner.setHouseNo(ownerDTO.getHouseNo());
        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));
        existingOwner.setDescription("Owner updated at " + currentTime);
        Owner updatedOwner = ownerRepo.save(existingOwner);
        return objectMapper.convertValue(updatedOwner, OwnerDTO.class);
    }

    @Override
    public Map<String,Object> deleteOwnerById(Long id) {
        Map<String, Object> response = new HashMap<>();

        // Check if maid exists
        if (!ownerRepo.existsById(id)) {
            // If not found, throw custom exception
            throw new ResourceNotFoundException("Owner ID " + id + " is not present in the database.");
        }

        // If found, delete the maid
        ownerRepo.deleteById(id);

        // Build success response
        response.put("message", "Owner with ID " + id + " was deleted successfully.");
        response.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return response;
    }
}
