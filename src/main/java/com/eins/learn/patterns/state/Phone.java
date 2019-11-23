package com.eins.learn.patterns.state;

public class Phone implements PhoneState {
    private PhoneState state;

    public Phone() {
        state = new Standby(this);
    }

    public void changeState(PhoneState state) {
        this.state = state;
    }

    @Override
    public void ringing() {
        state.ringing();
    }

    @Override
    public void calling() {
        state.calling();
    }

    @Override
    public void speaking() {
        state.speaking();
    }

    @Override
    public void getBtnPressed() {
        state.getBtnPressed();
    }

    @Override
    public void dropBtnPressed() {
        state.dropBtnPressed();
    }
}
