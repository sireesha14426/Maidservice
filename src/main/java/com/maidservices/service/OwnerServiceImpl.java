package com.maidservices.service;

import com.maidservices.models.Owner;
import com.maidservices.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepo ownerRepo;

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepo.save(owner);
    }

    @Override
    public Owner getOwnerId(Long ownerid) {
        return ownerRepo.findById(ownerid).orElse(null);
    }

    @Override
    public String deleteByOwnerId(Long ownerid) {
        if(ownerRepo.existsById(ownerid)){
            ownerRepo.deleteById(ownerid);
            return "Owner deleted by Id: " + ownerid;
        }else{
            return "Owner doen't exist ";
        }

    }

    @Override
    public Owner updateOwner(Long ownerid, String ownername, Long houseno, String flatname) {
        Optional<Owner> optionalOwner=ownerRepo.findById(ownerid);
        if(optionalOwner.isPresent()){
            Owner existingOwner=optionalOwner.get();
            existingOwner.setOwnername(ownername);
            existingOwner.setFlatname(flatname);
            existingOwner.setHouseno(houseno);
            return ownerRepo.save(existingOwner);
        }
        return null;
    }
}
