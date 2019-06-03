import arc.*;

public class timer_test {

    public static synchronized void main(String[] args) {

        Timer timer = new Timer();
        timer.start();
        int foo = 0;
        
        Timer7 t7 = new Timer7(timer);
        t7.start();
		
        Timer15 t15 = new Timer15(timer);
        t15.start();
        

        Console con = new Console();

        for(int i = 0; i < 1000000000; i++)
        {
          foo++;
          con.sleep(100);
          con.println(foo);
        }

    }

}
class Timer extends Thread{
    private int time = 1;
    private boolean setting = false;

    public void run(){
        while(true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.print(time + " ");
            setTime(time + 1);
            //Switching the order of these 2 ^^^ statements and initializing time to 0 will give an output that is more accurate to the time.
        }
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
                    this.five = 0;
                    
                    System.out.print("\n5 Second Message\n");
                    
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
