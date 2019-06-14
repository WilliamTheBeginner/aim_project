package src;

import arc.*;
import src.*;



public class Timer extends Thread{
    private int intTime = 1;
    private boolean blnSetting = false;
    private volatile boolean blnExit = false;
    private Console con = null;
    private int intDur;

    public Timer(Console con){
      this.con = con;
    }
    public void addDur(int intDur){
      this.intDur = intDur;
    }
    public boolean getStatus(){
      return blnExit;
    }
    public void run(){
        while(blnExit != true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            con.clear();
            con.print(intDur - intTime + " ");
            setTime(intTime + 1);
        }
    }
    public void stopi(){
      blnExit = true;
      con.closeWindow();
    }
    public synchronized int getTime(){
        while(blnSetting){
            try {
                wait(); //This will only be run on the off-chance that setTime is being run at the same time.
            } catch (InterruptedException e) {  }
        }
        return intTime;
    }
    public synchronized void setTime(int t){
        blnSetting = true;
        this.intTime = t;
        blnSetting = false;
        notifyAll();
    }
}
