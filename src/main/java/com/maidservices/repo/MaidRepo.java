package com.maidservices.repo;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MaidRepo extends JpaRepository<Maid, Long> {

    Maid save(Maid maid);
    Optional<Maid> findById(Long maidid);
    void deleteById(Long maidid);

    ResponseEntity<MaidDTO> save(MaidDTO maidDTO);
}
