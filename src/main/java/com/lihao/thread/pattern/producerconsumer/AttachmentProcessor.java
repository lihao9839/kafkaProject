package com.lihao.thread.pattern.producerconsumer;

import com.lihao.thread.pattern.twophrasetermination.AbstractTerminatableThread;

import java.io.*;
import java.text.Normalizer;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;

public class AttachmentProcessor {
    private final String ATTACHMENT_SHORT_BASE_DIR = "";
    private final Channel<File> channel = new BlockingQueueChannel<File>(new ArrayBlockingQueue<File>(200));

    //
    private final AbstractTerminatableThread indexingThread = new AbstractTerminatableThread(){
        @Override
        protected void doRun() throws Exception {
            File file = null;
            file = channel.take();
            try{
                indexFile(file);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                terminationToken.reservations.decrementAndGet();
            }
        }

        private void indexFile(File file) throws Exception{
            Random rd = new Random();
            try{
                Thread.sleep(rd.nextInt(100));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    };

    public void init(){
        indexingThread.start();
    }

    public void shutdown(){
        indexingThread.terminate();
    }

    public void saveAttachment(InputStream in, String documentId, String originalFileName) throws IOException {
        File file = saveAsFile(in, documentId, originalFileName);
        try{
            channel.put(file);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        indexingThread.terminationToken.reservations.incrementAndGet();
    }

    private File saveAsFile(InputStream in, String documentId, String originalFileName)throws IOException{
        String dirName = ATTACHMENT_SHORT_BASE_DIR + documentId;
        File dir = new File(dirName);
        dir.mkdir();
        File file = new File(dirName + "/" + Normalizer.normalize(originalFileName, Normalizer.Form.NFC));
        //防止目录跨越攻击
        if(!dirName.equals(file.getCanonicalFile().getParent())){
            throw new SecurityException("Invalid originalFilaName:" + originalFileName);
        }

        BufferedOutputStream bos = null;
        BufferedInputStream bis = new BufferedInputStream(in);
        byte[] buf = new byte[2048];
        int len = -1;
        try{
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while((len = bis.read(buf)) > 0){
                bos.write(buf, 0, len);
            }
            bos.flush();
        }finally {
            try{
                bis.close();
            }catch (IOException e){
                ;
            }
            try{
                if(null != bos){
                    bos.close();
                }
            }catch (IOException e){
                ;
            }
        }
        return file;
    }
}
