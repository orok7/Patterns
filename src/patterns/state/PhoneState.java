package patterns.state;

public interface PhoneState {
    void ringing();
    void calling();
    void speaking();
    void getBtnPressed();
    void dropBtnPressed();
}