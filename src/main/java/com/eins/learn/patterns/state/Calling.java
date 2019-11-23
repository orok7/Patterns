package com.eins.learn.patterns.state;

public class Calling implements PhoneState {
    private Phone phone;

    public Calling(Phone phone) {
        this.phone = phone;
        System.out.println("Calling to smb");
    }

    @Override
    public void ringing() {
    }

    @Override
    public void calling() {
    }

    @Override
    public void speaking() {
        phone.changeState(new Speaking(this.phone));
    }

    @Override
    public void getBtnPressed() {
    }

    @Override
    public void dropBtnPressed() {
        System.out.println("Calling canceled");
        phone.changeState(new Standby(this.phone));
    }
}
