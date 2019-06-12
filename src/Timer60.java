package src;

import arc.*;
import src.*;


public class Timer60 extends Thread{
    Timer timer;
    public Timer60(Timer t){
        this.timer = t;
    }

    public void run(){
        synchronized(timer){
            while(true){

                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(timer.getTime() % 60 == 0){
                    timer.stopi();
                }

            }
        }
    }
}
