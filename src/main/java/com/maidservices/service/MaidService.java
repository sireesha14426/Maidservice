package com.maidservices.service;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.models.Maid;
import org.springframework.http.ResponseEntity;

public interface MaidService {

    ResponseEntity<MaidDTO> createMaid(MaidDTO maidDTO);

    public Maid getMaidId(Long maidid);
    public Maid updateMaidById(Long maidid, String maidname, String field, int rating, String startTime, String endTime);
    public MaidDTO deleteMaidById(Long maidid);

    Maid updateMaidById(Long id, MaidDTO maidDTO);
}
