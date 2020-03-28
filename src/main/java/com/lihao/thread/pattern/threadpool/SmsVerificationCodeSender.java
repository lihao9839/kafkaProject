package com.lihao.thread.pattern.threadpool;

import com.lihao.thread.pattern.threadspecificstorage.ThreadSpecificSecureRandom;

import java.text.DecimalFormat;
import java.util.concurrent.*;

public class SmsVerificationCodeSender {
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(),
            60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "VerfCodeSender");
            t.setDaemon(true);
            return t;
        }
    }, new ReEnqueueRejectedExcutionHandler());

    public void sendVerificationSms(final String msisdn){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int verificationCode = ThreadSpecificSecureRandom.INSTANCE.nextInt(999999);
                DecimalFormat df = new DecimalFormat("000000");
                String txtVerCode = df.format(verificationCode);

                sendSms(msisdn, txtVerCode);
            }
        };
        EXECUTOR.submit(task);
    }

    private void sendSms(String msisdn, String verificationCode){
        System.out.println("Sending verification code " + verificationCode + "to" + msisdn);
    }
}
