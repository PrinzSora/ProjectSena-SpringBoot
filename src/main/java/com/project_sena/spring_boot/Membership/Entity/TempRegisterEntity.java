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
public class TempRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="TR_id")
    private BigInteger ID;

    @Column(name="TR_username",length = 100,nullable = false)
    private String userName;

    @Column(name="TR_password",length = 256,nullable = false)
    private String userPassword;

    @Column(name="TR_otp_ref",length = 12,nullable = false)
    private String OTPRef;

    @Column(name="TR_otp_code",length = 8,nullable = false)
    private String OTPCode;

    @Column(name="TR_otp_send_DTM",nullable = false)
    private LocalDateTime OTPSendDTM;

    @Column(name="TR_otp_expr_DTM",nullable = false)
    private LocalDateTime OTPExprDTM;

    @Column(name="TR_verification_state",length = 8,nullable = false)
    private String verificationState;

    @Column(name="TR_status",length = 8,nullable = false)
    private String status;

    @Column(name="TR_created_by",length = 256,nullable = false)
    private LocalDateTime createdBy;

    @Column(name="TR_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="TR_updated_by",length = 256,nullable = false)
    private LocalDateTime updatedBy;

    @Column(name="TR_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
