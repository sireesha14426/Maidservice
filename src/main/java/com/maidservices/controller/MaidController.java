package com.maidservices.controller;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import com.maidservices.service.MaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
