package com.project_sena.spring_boot.Util;

import com.project_sena.spring_boot.Util.Service.FileSystemService;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UtilServiceTests {

    private final UtilService utilService;
    private final FileSystemService fileSystemService;

    UtilServiceTests(UtilService utilService, FileSystemService fileSystemService, FileSystemService fileSystemService1) {
        this.utilService = utilService;

        this.fileSystemService = fileSystemService1;
    }

    @Test
    void UnitTestFor_IsEmailFormatCorrect(){
        assertFalse(utilService.IsEmailFormatCorrect("user_01"));
        assertTrue(utilService.IsEmailFormatCorrect("user_01@hotmail.com"));
    }
}