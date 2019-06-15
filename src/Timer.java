package src;

import arc.*;
import src.*;



public class Timer extends Thread{
    // time variable
    private int intTime = 1;
    // setting variable
    private boolean blnSetting = false;
    // exit variable
    private volatile boolean blnExit = false;
    // console variable
    private Console con = null;
    // duration variable
    private int intDur;

    // initalize Timer with con
    public Timer(Console con){
      this.con = con;
    }
    // add duration
    public void addDur(int intDur){
      this.intDur = intDur;
    }
    // optional method to get the status of the method
    public boolean getStatus(){
      return blnExit;
    }
    // when Timer.start() is used
    public void run(){
        // while the timer isn't exiting
        while(blnExit != true){
            // try to sleep for 1 second
            try {
                sleep(1000);
                // if the thread is interrupted
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // clear console after every second
            con.clear();
            // print the time
            con.print(intDur - intTime + " ");
            // make time add one
            setTime(intTime + 1);
        }
    }
    // stop Timer method
    public void stopi(){
      // make exit variable equal to true
      blnExit = true;
      // close timer console
      con.closeWindow();
    }
    public synchronized int getTime(){
        // while the Timer is still running
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
