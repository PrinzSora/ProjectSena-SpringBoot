package com.project_sena.spring_boot.Util.Constance;

public enum Directory {
    MAIN_DIRECTORY("/fileSystemForProjectSena"),
    IMAGE("/image"),
    VDO("/vdo"),
    MODEL("/3DModel");

    private String dir;

    Directory(String fileSystemForProjectSena) {
        this.dir = fileSystemForProjectSena;
    }

    public String getValue(){
        return  dir;
    }

}
