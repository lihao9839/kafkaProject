package com.lihao.thread.pattern.promise;

import org.apache.kafka.common.protocol.types.Field;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 数据同步模块的入口类
 */
public class DataSyncTask implements Runnable {
    private final Map<String, String> taskParameters;

    public DataSyncTask(Map<String, String> taskParameters){
        this.taskParameters = taskParameters;
    }

    @Override
    public void run() {
        String ftpServer = taskParameters.get("server");
        String ftpUsername = taskParameters.get("userName");
        String password = taskParameters.get("password");
        //先开始初始化FTP客户端实例
        Future<FTPClientUtil> ftpClientUtilPromise = FTPClientUtil.newInstance(ftpServer, ftpUsername, password);
        //查询数据库生成脚本
        generateFileFromDB();
        FTPClientUtil ftpClientUtil = null;
        try{
            ftpClientUtil = ftpClientUtilPromise.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            throw new RuntimeException(e);
        }
        uploadFiles(ftpClientUtil);
    }


    private void generateFileFromDB(){

    }

    private void uploadFiles(FTPClientUtil ftpClientUtil){
        Set<File> files = retrieveGeneratedFiles();
        for(File file: files){
            try{
                ftpClientUtil.upload(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private Set<File> retrieveGeneratedFiles(){
        Set<File> files = new HashSet<>();
        return files;
    }
}
