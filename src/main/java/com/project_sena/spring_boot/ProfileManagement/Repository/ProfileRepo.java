package com.project_sena.spring_boot.ProfileManagement.Repository;


import com.project_sena.spring_boot.ProfileManagement.Entity.ProfileEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<ProfileEntity,BigInteger> {

    @Query(value = "SELECT * FROM Profile WHERE pmp_uid = :UID",nativeQuery = true)
    Optional<ProfileEntity> getProfile(@Param("UID") String UID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Profile WHERE pmp_uid = :UID",nativeQuery = true)
    void deleteProfileByUID(@Param("UID") String UID);


}
