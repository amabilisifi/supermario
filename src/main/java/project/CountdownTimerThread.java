package project;

public class CountdownTimerThread extends Thread {
    private long time;

    public CountdownTimerThread(long time) {
        this.time = time;
    }

    public void run() {
        try {
            sleep(this.time);
        } catch (InterruptedException e) {

        }
    }
}
