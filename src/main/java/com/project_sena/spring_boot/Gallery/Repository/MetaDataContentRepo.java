package com.project_sena.spring_boot.Gallery.Repository;

import com.project_sena.spring_boot.Gallery.Entity.ContentEntity;
import com.project_sena.spring_boot.Gallery.Entity.MetaDataContentEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface MetaDataContentRepo extends JpaRepository<MetaDataContentEntity,BigInteger> {

    @Query(value = "SELECT * FROM meta_data_content " +
                    "WHERE check_sum = :checkSum",nativeQuery = true)
    Optional<MetaDataContentEntity> getMetaDataContentByCheckSum(@Param("checkSum") int checkSum);

}
