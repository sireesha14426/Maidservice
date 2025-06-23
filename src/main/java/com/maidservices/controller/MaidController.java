package com.maidservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.repo.MaidRepo;
import com.maidservices.service.MaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/maidservices")
public class MaidController {

    @Autowired
    private MaidService maidService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/Maid")
    public ResponseEntity<MaidDTO> createMaid(@RequestBody MaidDTO maidDto) {
        MaidDTO createdMaid = maidService.createMaid(maidDto);
        return new ResponseEntity<>(createdMaid, HttpStatus.OK);
    }

    @GetMapping("/Maid/{id}")
    public ResponseEntity<MaidDTO> getMaidId(@PathVariable("id") Long id){
        MaidDTO maidDTO= maidService.getMaidId(id);
            return ResponseEntity.ok(maidDTO);
    }

        @DeleteMapping("/Maid/{id}")
    public ResponseEntity<Map<String,Object>> deleteMaidById(@PathVariable("id") Long id){
          Map<String,Object> response= maidService.deleteMaidById(id);
            return ResponseEntity.ok(response);

    }

    @PutMapping("/updateMaidById/{id}")
    public ResponseEntity<MaidDTO> updateMaidById(@PathVariable Long id, @RequestBody MaidDTO maidDTO) {
        MaidDTO updatedMaid = maidService.updateMaidById(id, maidDTO);
        return ResponseEntity.ok(updatedMaid);
    }

    @PostMapping("/login/username")
    public ResponseEntity<Boolean> loginByUserName(@RequestParam String userName , @RequestParam String password) {
        boolean result = maidService.loginByUserName(userName, password);
        return ResponseEntity.ok(result);
    }
}
