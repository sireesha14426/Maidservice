package com.maidservices.service;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import org.springframework.http.ResponseEntity;

public interface MaidService {

    MaidDTO createMaid(MaidDTO maidDTO);

    public Maid getMaidId(Long id);

    MaidDTO updateMaidById(Long id, MaidDTO maidDTO);

    public String deleteMaidById(Long id);

}
