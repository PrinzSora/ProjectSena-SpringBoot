package com.project_sena.spring_boot.Util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UtilServiceTest {

    @Test
    void UnitTestFor_IsEmailFormatCorrect(){
        UtilService utilService = new UtilService();
        assertFalse(utilService.IsEmailFormatCorrect("user_01"));
        assertTrue(utilService.IsEmailFormatCorrect("user_01@hotmail.com"));
    }
}