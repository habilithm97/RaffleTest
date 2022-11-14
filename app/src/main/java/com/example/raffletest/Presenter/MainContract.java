package com.example.raffletest.Presenter;

public interface MainContract {
    interface View {
        void raffleResult();
    }

    interface Presenter {
        void raffle();
    }
}
