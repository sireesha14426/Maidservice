package com.maidservices.repo;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.dtos.OwnerDTO;
import com.maidservices.models.Maid;
import com.maidservices.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
    Owner save(Owner owner);
    Optional<Owner> findById(Long id);
    void deleteById(Long id);
    ResponseEntity<OwnerDTO> save(OwnerDTO ownerDTO);
}
