package com.maidservices.service;

import com.maidservices.models.Owner;

public interface OwnerService {

    /* private long ownerid;
    private String ownername;
    private String flatname;
    private long houseno;*/

    public Owner createOwner(Owner owner);
    public Owner getOwnerId(Long ownerid);
    public String deleteByOwnerId(Long ownerid);
    public Owner updateOwner(Long ownerid, String ownername, Long houseno, String flatname);
}
