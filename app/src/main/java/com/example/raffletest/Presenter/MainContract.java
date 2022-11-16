package com.example.raffletest.Presenter;

public interface MainContract {
    interface View {
        void raffleResult();
        void addEdtResult();
        void deleteEdtResult();
    }

    interface Presenter {
        void raffle();
        void addEdt();
        void deleteEdt();
    }
}
