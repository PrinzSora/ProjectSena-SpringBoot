package com.project_sena.spring_boot.Util.Service;


import com.project_sena.spring_boot.Util.Constance.Directory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileSystemService {

    public void checkStorageInSystem(){
        File diskPartition = new File("/");
        long totalSpaceInSystem = diskPartition.getTotalSpace();
        long freeSpaceInSystem = diskPartition.getFreeSpace();
        System.out.println("currentPath: "+diskPartition.getAbsolutePath());
        System.out.println("totalSpaceInSystem (MB): "+totalSpaceInSystem/(1024.0 * 1024.0));
        System.out.println("freeSpaceInSystem: (MB)"+freeSpaceInSystem/(1024.0 * 1024.0));
        File[] roots = File.listRoots();
        for (File root : roots){
            System.out.println("root: "+root.getPath());
            System.out.println("totalSpace: (MB)"+root.getTotalSpace()/(1024.0 * 1024.0));
            System.out.println("freeSpace: (MB)"+root.getFreeSpace()/(1024.0 * 1024.0));
        }
    }

    public void intiStorage() throws Exception{
        Directory rootDirectory = Directory.MAIN_DIRECTORY;
        File file = new File(rootDirectory.getValue());
        if(file.mkdir()){
            System.out.println(rootDirectory +" is generated at "+ file.getAbsolutePath());
        }
    }

    public boolean checkDirectory(String directoryName) throws Exception{
        File file = new File(directoryName);
        return file.exists();
    }

    //todo Hard code
    public void genarateDefaultDirectory(String directoryName) throws Exception{
        new File(Directory.MAIN_DIRECTORY.getValue()+Directory.IMAGE.getValue()).mkdir();
        new File(Directory.MAIN_DIRECTORY.getValue()+Directory.VDO.getValue()).mkdir();
        new File(Directory.MAIN_DIRECTORY.getValue()+Directory.MODEL.getValue()).mkdir();
    }

    public String makeDirectory(String directoryName) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append(Directory.MAIN_DIRECTORY.getValue()).append("/").append(directoryName);
        if(!checkDirectory(sb.toString())){
            File file = new File(sb.toString());
            file.mkdir();
            System.out.println(directoryName +" is generated at "+ file.getAbsolutePath());
        }else{
            System.out.println(directoryName +" is exist");
        }
        return sb.toString();
    }

}

