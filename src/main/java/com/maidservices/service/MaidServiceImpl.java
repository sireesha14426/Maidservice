package com.maidservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.repo.MaidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MaidServiceImpl implements MaidService {

    @Autowired
    private MaidRepo maidRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public MaidDTO createMaid(MaidDTO maidDTO) {

        return maidRepo.save(maidDTO).getBody();
    }

    @Override
    public Maid getMaidId(Long id) {

        return maidRepo.findById(id).orElse(null);
    }

    @Override
    public MaidDTO updateMaidById(Long id, MaidDTO maidDTO) {
       Maid existingMaid= maidRepo.findById(id)
               .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.NOT_FOUND,
                       "Maid not found with id: " + id
               ));

        existingMaid.setName(maidDTO.getName());
        existingMaid.setField(maidDTO.getField());
        existingMaid.setRating(maidDTO.getRating());
        existingMaid.setStartTime(maidDTO.getStartTime());
        existingMaid.setEndTime(maidDTO.getEndTime());
        try {
            Maid updated = maidRepo.save(existingMaid);
            return objectMapper.convertValue(updated, MaidDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update Business Owner.", e);
        }

    }

    @Override
    public String deleteMaidById(Long id) {
        if(maidRepo.existsById(id)){
            maidRepo.deleteById(id);
            return "Maid deleted by Id: " + id;
        }
        else{
            return "Maid doesn't exist";
        }
    }
}
