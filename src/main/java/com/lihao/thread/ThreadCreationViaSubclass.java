package com.lihao.thread;

public class ThreadCreationViaSubclass {

    public static void main(String[] args){
        Thread thread = new CustomThread();
        thread.start();
    }

    static class CustomThread extends Thread{
        @Override
        public void run() {
            System.out.println("Running...");
        }
    }
}
