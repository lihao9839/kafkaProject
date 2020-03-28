package com.lihao.thread.pattern.threadspecificstorage;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ThreadSpecificSecureRandom {
    public static final ThreadSpecificSecureRandom INSTANCE = new ThreadSpecificSecureRandom();

    private static final ThreadLocal<SecureRandom> SECURE_RANDOM = new ThreadLocal<SecureRandom>(){
        @Override
        protected SecureRandom initialValue() {
            SecureRandom srnd;
            try{
                srnd = SecureRandom.getInstance("SHA1PRNG");
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
                srnd = new SecureRandom();
            }
            return srnd;
        }
    };

    private ThreadSpecificSecureRandom(){

    }

    public int nextInt(int upperBound){
        SecureRandom secureRandom = SECURE_RANDOM.get();
        return secureRandom.nextInt(upperBound);
    }

    public void setSeed(long seed){
        SecureRandom secureRandom = SECURE_RANDOM.get();
        secureRandom.setSeed(seed);
    }
}
