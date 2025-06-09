package com.maidservices.service;

import com.maidservices.dtos.MaidDTO;
import com.maidservices.dtos.OwnerDTO;

import java.util.Map;

public interface OwnerService {

    /* private long ownerid;
    private String ownername;
    private String flatname;
    private long houseno;*/

    OwnerDTO createOwner(OwnerDTO ownerDTO);
    OwnerDTO getOwnerId(Long id);
    OwnerDTO updateOwnerById(Long id, OwnerDTO ownerDTO);
    public Map<String,Object> deleteOwnerById(Long id);

}
