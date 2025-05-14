import java.time.LocalTime;

public class Alarm implements Runnable {
    protected int alarmId;
    protected LocalTime time;

    public Alarm(LocalTime time) {
        this.alarmId++;
        this.time = time;
    }
    @Override
    public void run() {
        System.out.println(this.time);
        while (true) {
            System.out.printf("\r%02d:%02d:%02d", LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());
            if (this.time.isBefore(LocalTime.now())) {
                System.out.println("yes");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("there is a problem in sleep");
            }
        }
    }
}
