package com.project_sena.spring_boot.Util.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CheckStringFormatCriteria {
    boolean approve;
    List<String> errorList;
}
