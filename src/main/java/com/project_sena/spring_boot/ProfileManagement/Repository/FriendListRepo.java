package com.project_sena.spring_boot.ProfileManagement.Repository;


import com.project_sena.spring_boot.ProfileManagement.Entity.FriendListEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface FriendListRepo extends JpaRepository<FriendListEntity, BigInteger> {

    @Query(value = "SELECT * FROM friend_list " +
                "WHERE PHF_user_uid = :UID " +
                "OR PHF_friend_uid = :UID   ",nativeQuery = true)
    List<FriendListEntity> getFriendListByUID(@Param("UID") String UID);

}
