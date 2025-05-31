package com.maidservices.controller;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.service.MaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MaidController {

    @Autowired
    private MaidService maidService;

    @PostMapping("/Maid")
    public ResponseEntity<MaidDTO> createMaid(@RequestBody MaidDTO maidDto) {
        try {
            MaidDTO maidDTO = maidService.createMaid(maidDto);
            return new ResponseEntity<>(maidDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Maid/{id}")
    public ResponseEntity<String> getMaidId(@PathVariable Long id){
        String maidId= String.valueOf(maidService.getMaidId(id));
        if(maidId!=null){
            return ResponseEntity.ok(maidId);
        }else{
           return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/Maid/{id}")
    public ResponseEntity<String> deleteMaidId(@PathVariable Long id){
            String maidId =maidService.deleteMaidById(id);
        if (maidId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maidId);
    }

    @PutMapping("/Maid/{id}")
    public ResponseEntity<MaidDTO> updateMaidById(@PathVariable Long id, @RequestBody MaidDTO maidDTO) {
        try {
            MaidDTO updatedMaid = maidService.updateMaidById(id, maidDTO);
            return ResponseEntity.ok(updatedMaid);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
}
