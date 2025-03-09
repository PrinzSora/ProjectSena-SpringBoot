package com.project_sena.spring_boot.Membership.Repository;
import com.project_sena.spring_boot.Membership.Entity.TempRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface TempRegisterRepo extends JpaRepository<TempRegisterEntity,BigInteger> {

    boolean existsByuserName(String userName);

    @Query(value = "select * from temp_register" +
            " where temp_register.TR_otp_code == :userName", nativeQuery = true)
    String getOTPByUserName(String userName);

    Optional<TempRegisterEntity> findByuserName(String username);
}
