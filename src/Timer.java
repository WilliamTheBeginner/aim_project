package src;

import arc.*;
import src.*;



public class Timer extends Thread{
    private int time = 1;
    private boolean setting = false;
    private volatile boolean exit = false;
    private Console con = null;
    private int dur;

    public Timer(Console con){
      this.con = con;
    }
    public void addDur(int dur){
      this.dur = dur;
    }
    public boolean getStatus(){
      return exit;
    }
    public void run(){
        while(exit != true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            con.clear();
            con.print(dur - time + " ");
            setTime(time + 1);
        }
    }
    public void stopi(){
      exit = true;
      con.closeWindow();
    }
    public synchronized int getTime(){
        while(setting){
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }
        return time;
    }
    public synchronized void setTime(int t){
        setting = true;
        this.time = t;
        setting = false;
        notifyAll();
    }
}
