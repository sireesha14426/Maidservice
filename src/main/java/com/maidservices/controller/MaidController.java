package com.maidservices.controller;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.service.MaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MaidController {

    @Autowired
    private MaidService maidService;

    @PostMapping("/Maid")
    public ResponseEntity<MaidDTO> createMaid(@RequestBody MaidDTO maidDto) {
        try {
            MaidDTO maidDTO = maidService.createMaid(maidDto).getBody();
            return new ResponseEntity<>(maidDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Maid/{id}")
    public ResponseEntity<Maid> getMaidId(@PathVariable("maidid") Long maidid){
        Maid maidId=maidService.getMaidId(maidid);
        if(maidId!=null){
            return ResponseEntity.ok(maidId);
        }else{
           return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/Maid/{id}")
    public ResponseEntity<MaidDTO> deleteMaidId(@PathVariable("maidid")Long maidid){
            MaidDTO maidId =maidService.deleteMaidById(maidid);
        if (maidId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maidId);
    }

    @PutMapping("/Maid/{id}")
    public ResponseEntity<Maid> updateMaid(@PathVariable("id") Long id, @RequestBody MaidDTO maidDTO) {
        Maid updatedMaid = maidService.updateMaidById(id, maidDTO);
        if (updatedMaid == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMaid);
    }

}
