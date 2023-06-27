package project;

public class CountdownTimer extends Thread {
    /* Countdown timer */
    private CountdownTimerThread timer;
    public CountdownTimer(long time) {
        this.timer = new CountdownTimerThread(time);
    }

    public void run() {
        this.timer.start();
    }

    public boolean isFinished() {
        if(this.timer.getState() == Thread.State.TERMINATED)
            return true;
        return false;
    }
}
