package com.maidservices.service;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.repo.MaidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class MaidServiceImpl implements MaidService {

    @Autowired
    private MaidRepo maidRepo;

    @Override
    public ResponseEntity<MaidDTO> createMaid(MaidDTO maidDTO) {
        return maidRepo.save(maidDTO);
    }

    @Override
    public Maid getMaidId(Long maidid) {
        return maidRepo.findById(maidid).orElse(null);
    }

    @Override
    public Maid updateMaidById(Long maidid, String maidname, String field, int rating, String startTime, String endTime) {
        Optional<Maid> optionalMaid=maidRepo.findById(maidid);
        if(optionalMaid.isPresent()){
            Maid existingMaid=optionalMaid.get();
            existingMaid.setMaidname(maidname);
            existingMaid.setField(field);
            existingMaid.setRating(rating);
            existingMaid.setStartTime(startTime);
            existingMaid.setEndTime(endTime);
            return maidRepo.save(existingMaid);
        }else{
            return null;
        }
    }

    @Override
    public String deleteMaidById(Long maidid) {
        if(maidRepo.existsById(maidid)){
            maidRepo.deleteById(maidid);
            return "Maid deleted by Id: " + maidid;
        }
        else{
            return "Maid doesn't exist";
        }
    }
}
