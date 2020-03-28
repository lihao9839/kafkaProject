package com.lihao.thread;

public class JavaThreadAnywhere {

    public static void main(String[] args){
        System.out.println("The main method was executed by thread:" + Thread.currentThread().getName());
        Helper helper = new Helper("Java Thread Anywhere");
        helper.run();
    }

    static class Helper implements Runnable{
        private final String message;
        public Helper(String message){
            this.message = message;
        }
        private void doSomething(String message){
            System.out.println("The doSomething method was executed by thread:" + Thread.currentThread().getName());
            System.out.println("Do Something with "+ message);
        }

        @Override
        public void run() {
            doSomething(message);
        }
    }
}
