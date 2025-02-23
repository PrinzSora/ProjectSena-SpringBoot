package com.project_sena.spring_boot.Membership.Repository;
import com.project_sena.spring_boot.Membership.Entity.TempRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TempRegisterRepo extends JpaRepository<TempRegisterEntity,BigInteger> {

    boolean existsByuserName(String userName);
}
