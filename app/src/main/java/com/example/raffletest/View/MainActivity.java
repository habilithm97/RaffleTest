package com.example.raffletest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raffletest.Presenter.MainContract;
import com.example.raffletest.Presenter.MainPresenter;
import com.example.raffletest.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    MainContract.Presenter presenter;

    TextView resultTv;
    EditText edt, edt1, edt2, edt3;
    String str, str1, str2, str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        init();
    }

    private void init() {
        resultTv = (TextView)findViewById(R.id.resultTv);
        edt = (EditText)findViewById(R.id.edt);
        edt1 = (EditText)findViewById(R.id.edt1);
        edt2 = (EditText)findViewById(R.id.edt2);
        edt3 = (EditText)findViewById(R.id.edt3);
        Button addBtn = (Button)findViewById(R.id.addBtn);
        Button deleteBtn = (Button)findViewById(R.id.deleteBtn);

        /*
        str = edt.getText().toString();
        str1 = edt1.getText().toString();
        str2 = edt2.getText().toString();
        str3 = edt3.getText().toString();
         */

        Button raffleBtn = (Button)findViewById(R.id.raffleBtn);
        raffleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.raffle();
            }
        });
    }

    @Override
    public void raffleResult() {
        String[] randomStr = {edt.getText().toString(), edt1.getText().toString(), edt2.getText().toString(), edt3.getText().toString()};
        if (edt.getText().toString().equals("") || edt1.getText().toString().equals("") || edt2.getText().toString().equals("") || edt3.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "모두 입력해 주세요. ", Toast.LENGTH_SHORT).show();
        } else {
            Random random = new Random();
            int n = random.nextInt(randomStr.length);
            resultTv.setText(randomStr[n]);
        }
    }
}