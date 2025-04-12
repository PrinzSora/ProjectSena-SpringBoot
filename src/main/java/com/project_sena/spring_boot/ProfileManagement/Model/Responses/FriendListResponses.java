package com.project_sena.spring_boot.ProfileManagement.Model.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project_sena.spring_boot.ProfileManagement.Entity.FriendListEntity;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendListResponses {

    List<FriendListEntity> friendList;
    ErrorResponses errorResponses;

}
