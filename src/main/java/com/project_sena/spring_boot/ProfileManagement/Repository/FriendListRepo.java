package com.project_sena.spring_boot.ProfileManagement.Repository;


import com.project_sena.spring_boot.ProfileManagement.Entity.FriendListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FriendListRepo extends JpaRepository<FriendListEntity, BigInteger> {
}
