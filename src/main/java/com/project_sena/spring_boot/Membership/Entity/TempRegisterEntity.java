package com.project_sena.spring_boot.Membership.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "temp_register")
public class TempRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tr_id",columnDefinition = "BIGINT")
    private BigInteger ID;

    @Column(name="tr_username",length = 100,nullable = false)
    private String userName;

    @Column(name="tr_password",length = 256,nullable = false)
    private String userPassword;

    @Column(name="tr_otp_ref",length = 12,nullable = false)
    private String OTPRef;

    @Column(name="tr_otp_code",length = 8,nullable = false)
    private String OTPCode;

    @Column(name="tr_otp_send_DTM",nullable = false)
    private LocalDateTime OTPSendDTM;

    @Column(name="tr_otp_expr_DTM",nullable = false)
    private LocalDateTime OTPExprDTM;

    @Column(name="tr_verification_state",length = 8,nullable = false)
    private String verificationState;

    @Column(name="tr_status",length = 8,nullable = false)
    private String status;

    @Column(name="tr_created_by",length = 256,nullable = false)
    private String createdBy;

    @Column(name="tr_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="tr_updated_by",length = 256,nullable = false)
    private String updatedBy;

    @Column(name="tr_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
