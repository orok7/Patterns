package com.eins.learn.patterns.state;

public class Standby implements PhoneState{
    private Phone phone;

    public Standby(Phone phone) {
        System.out.println("Waiting...");
        this.phone = phone;
    }

    @Override
    public void ringing() {
        phone.changeState(new Ringing(this.phone));
    }

    @Override
    public void calling() {
        phone.changeState(new Calling(this.phone));
    }

    @Override
    public void speaking() {
    }

    @Override
    public void getBtnPressed() {
    }

    @Override
    public void dropBtnPressed() {
    }
}
