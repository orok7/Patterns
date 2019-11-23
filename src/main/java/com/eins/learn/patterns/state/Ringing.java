package com.eins.learn.patterns.state;

public class Ringing implements PhoneState {
    private Phone phone;

    public Ringing(Phone phone) {
        this.phone = phone;
        System.out.println("Smb calls you");
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
        this.phone.changeState(new Speaking(this.phone));
    }

    @Override
    public void dropBtnPressed() {
        System.out.println("Ringing canceled");
        this.phone.changeState(new Standby(this.phone));
    }
}
