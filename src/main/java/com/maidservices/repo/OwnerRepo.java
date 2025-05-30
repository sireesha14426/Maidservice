package com.maidservices.repo;

import com.maidservices.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
}
