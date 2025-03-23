package com.project_sena.spring_boot.Gallery.Entity;

import com.project_sena.spring_boot.Util.Constance.GroupContentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="group_content_entity")
public class GroupContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;
    private int UID;
    private String displayName;
    private GroupContentStatus status;
    private int groupContentRuleId;

}
