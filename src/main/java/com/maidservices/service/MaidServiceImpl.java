package com.maidservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.dtos.MaidDTO;
import com.maidservices.exceptions.ResourceNotFoundException;
import com.maidservices.models.Maid;
import com.maidservices.repo.MaidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MaidServiceImpl implements MaidService {

    @Autowired
    private MaidRepo maidRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public MaidDTO createMaid(MaidDTO maidDTO) {
            Maid maid = objectMapper.convertValue(maidDTO, Maid.class);
        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));
        maid.setDescription("Maid created at " + currentTime);

        String encodedPassword = passwordEncoder.encode(maidDTO.getPassword());
        maid.setPassword(encodedPassword);

            Maid savedMaid = maidRepo.save(maid);
            return objectMapper.convertValue(savedMaid, MaidDTO.class);
        }


    @Override
    public MaidDTO getMaidId(Long id) {
        Maid maid=maidRepo.findById(id)
                  .orElseThrow(()-> new ResourceNotFoundException("Maid not found with ID: " + id));
        return objectMapper.convertValue(maid, MaidDTO.class);
    }

    @Override
    public Map<String,Object> deleteMaidById(Long id) {
        HashMap<String,Object> response=new HashMap<>();

        if (!maidRepo.existsById(id)) {
            throw new ResourceNotFoundException("Maid not found with ID: " + id);
        }
        String currentTime=LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(("MMMM dd, yyyy hh:mm:ss a")));

        maidRepo.deleteById(id);
        response.put("Message","Maid with ID " + id + " was deleted successfully on " + currentTime + ".");
        response.put("timestamp" ,LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a")));
        return response;
    }

    @Override
    public MaidDTO updateMaidById(Long id, MaidDTO maidDTO) {
       Maid existingMaid= maidRepo.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Maid not found with id: " + id));
        existingMaid.setName(maidDTO.getName());
        existingMaid.setField(maidDTO.getField());
        existingMaid.setRating(maidDTO.getRating());
        existingMaid.setStartTime(maidDTO.getStartTime());
        existingMaid.setEndTime(maidDTO.getEndTime());
        //existingMaid.setDescription(maidDTO.getDescription());
        String currentTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm:ss a"));
        existingMaid.setDescription("Maid updated at " + currentTime);
            Maid updated = maidRepo.save(existingMaid);
            return objectMapper.convertValue(updated, MaidDTO.class);
    }

    public boolean loginByUserName(String userName, String password){
            Optional<Maid> maidOptional = maidRepo.findByUserName(userName);

            if (maidOptional.isPresent()) {
                Maid maid = maidOptional.get();

                // ✅ Match raw password with encoded password
                return passwordEncoder.matches(password, maid.getPassword());
            } else {
                return false; // Username not found
            }
        }

       /* System.out.println("Received login request for username: " + userName);

        Optional<Maid> maid = Optional.ofNullable(maidRepo.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("username was not found")));

        if (maid.isPresent()) {
            Maid maid1 = maid.get();
            boolean result = passwordEncoder.matches(password, maid1.getPassword());
            System.out.println("Password match result: " + result);
            return result;
        } else {
            return false;
        }
    }*/
}
