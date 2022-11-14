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
}
