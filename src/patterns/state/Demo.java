package patterns.state;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        int pause = 1000;
        Thread.sleep(pause);
        phone.ringing();
        Thread.sleep(pause);
        phone.getBtnPressed();
        Thread.sleep(pause);
        phone.dropBtnPressed();
        Thread.sleep(pause);
        phone.ringing();
        Thread.sleep(pause);
        phone.dropBtnPressed();
        Thread.sleep(pause);
        phone.calling();
        Thread.sleep(pause);
        phone.speaking();
        Thread.sleep(pause);
        phone.dropBtnPressed();
        Thread.sleep(pause);
    }
}
