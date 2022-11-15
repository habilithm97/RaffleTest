package com.example.raffletest.Presenter;

public interface MainContract {
    interface View {
        void raffleResult();
        void addEdtResult();
    }

    interface Presenter {
        void raffle();
        void addEdt();
    }
}
