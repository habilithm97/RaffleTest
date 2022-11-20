package com.example.raffletest.Presenter;

public interface MainContract {
    interface View {
        void raffleResult();
        void addEdtResult();
        void resetEdtResult();
    }

    interface Presenter {
        void raffle();
        void addEdt();
        void resetEdt();
    }
}
