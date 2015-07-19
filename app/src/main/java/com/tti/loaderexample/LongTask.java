package com.tti.loaderexample;

/**
 * Created by shmuel on 7/19/15.
 */
public class LongTask {


    public static void RUN(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
