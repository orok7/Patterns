package patterns.state;

public class Speaking implements PhoneState {
    private Phone phone;

    public Speaking(Phone phone) {
        this.phone = phone;
        System.out.println("Bla bla bla...");
    }

    @Override
    public void ringing() {
    }

    @Override
    public void calling() {
    }

    @Override
    public void speaking() {
    }

    @Override
    public void getBtnPressed() {
    }

    @Override
    public void dropBtnPressed() {
        System.out.println("Call ended");
        this.phone.changeState(new Standby(this.phone));
    }
}
