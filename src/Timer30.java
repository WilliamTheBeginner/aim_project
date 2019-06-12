package src;

import arc.*;
import src.*;

class Timer30 extends Thread{
    Timer timer;

    public int five = 1;
    public Timer30(Timer t){
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

                if(timer.getTime() % 30 == 0){

                    timer.stopi();
                }
            }
        }
    }
}
