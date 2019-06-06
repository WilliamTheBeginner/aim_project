import arc.*;
import java.util.concurrent.Callable;

public class timer_test {

    public static int i = 0;

    public static synchronized void main(String[] args) {

        Console con = new Console();
        Console cons = new Console();

        Timer timer = new Timer(con);

        timer.start();
        int foo = 30;

        Timer7 t7 = new Timer7(timer);
        t7.start();

        Timer15 t15 = new Timer15(timer);
        t15.start();

        timercheck(timer, cons);

        Console con2 = new Console();
        con2.println("LOL");


    }
    public static int timercheck(Timer timer, Console cons){
      while(true){
        if(timer.isAlive() == false){
          cons.println("D");
          cons.sleep(3000);
          cons.closeWindow();
          return 1;
        }
      }
    }

}
class DScore {
  public DScore(int score, Console con){
    con.println("Score = " +  score);
  }
  public static void test(){
    ;
  }
}
class Timer extends Thread{
    private int time = 1;
    private boolean setting = false;
    private volatile boolean exit = false;
    private Console con = null;

    public Timer(Console con){
      this.con = con;
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
            con.print(29 - time + " ");
            //System.out.print(29 - time + " ");
            setTime(time + 1);
            //Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
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

class Timer7 extends Thread{
    Timer timer;

    public int five = 1;
    public Timer7(Timer t){
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

                if(timer.getTime() % 5 == 0){

                    timer.stopi();

                }

            }
        }
    }
}
class Timer15 extends Thread{
    Timer timer;
    public Timer15(Timer t){
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

                if(timer.getTime() % 15 == 0){
                    System.out.print("\n15 Second Message\n");
                }

            }
        }
    }
}
