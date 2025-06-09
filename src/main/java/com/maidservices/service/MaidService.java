package com.maidservices.service;

import com.maidservices.dtos.MaidDTO;

import java.awt.image.VolatileImage;
import java.util.Map;

public interface MaidService {

    MaidDTO createMaid(MaidDTO maidDTO);

    MaidDTO getMaidId(Long id);

    MaidDTO updateMaidById(Long id, MaidDTO maidDTO);

    public Map<String,Object> deleteMaidById(Long id);

}
