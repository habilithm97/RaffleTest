package com.example.raffletest.Presenter;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void raffle() {
        view.raffleResult();
    }

    @Override
    public void addEdt() {
        view.addEdtResult();
    }

    @Override
    public void resetEdt() {
        view.resetEdtResult();
    }
}
