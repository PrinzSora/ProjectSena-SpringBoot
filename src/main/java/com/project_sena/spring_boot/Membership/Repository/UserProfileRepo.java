package com.project_sena.spring_boot.Membership.Repository;
import com.project_sena.spring_boot.Membership.Entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfileEntity, BigInteger> {

    boolean existsByuserName(String userName);

}
