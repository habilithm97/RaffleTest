package com.example.raffletest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raffletest.Presenter.MainContract;
import com.example.raffletest.Presenter.MainPresenter;
import com.example.raffletest.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    MainContract.Presenter presenter;

    TextView resultTv;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    String[] randomStr;
    LinearLayout layout;
    EditText inputEdt;
    EditText editText;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        init();
    }

    private void init() {
        layout = (LinearLayout)findViewById(R.id.layout);
        resultTv = (TextView)findViewById(R.id.resultTv);
        inputEdt = (EditText)findViewById(R.id.inputEdt);

        Button addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addEdt();
            }
        });

        Button resetBtn = (Button)findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.resetEdt();
            }
        });

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false); // 불확정적

        Button raffleBtn = (Button)findViewById(R.id.raffleBtn);
        raffleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.raffle();
            }
        });
    }

    @Override
    public void addEdtResult() {
        layout.removeAllViews();

        num = Integer.parseInt(inputEdt.getText().toString());

        if(inputEdt.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "갯수를 입력해주세요. ", Toast.LENGTH_SHORT).show();
        } else {
            for(int i = 1; i <= num; i++) {
                editText = new EditText(getApplicationContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                editText.setLayoutParams(p);
                editText.setHint(i + "번");
                editText.setId(i);
                layout.addView(editText);
            }
        }
        keybordDown(); // 키보드 내리기
    }

    public void keybordDown() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(inputEdt.getWindowToken(), 0);
    }

    @Override
    public void resetEdtResult() {
        layout.removeAllViews();
        resultTv.setText("결과가 여기에 표시됩니다. ");
    }

    @Override
    public void raffleResult() {
        randomStr = new String[]{editText.getText().toString()};

        if (editText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "모두 입력해 주세요. ", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            ProgressThread thread = new ProgressThread();
            thread.execute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("추첨하는 중입니다...");
            progressDialog.show();
        }
    }

    class ProgressThread extends AsyncTask<Integer, Integer, Integer> {

        int value;

        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (isCancelled() == false) {
                value++;
                if(value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
            return value;
        }

        protected void onProgressUpdate(Integer ... values) {
            progressBar.setProgress(values[0].intValue());
        }

        protected void onPostExecute(Integer result) {
            progressBar.setProgress(0);
            progressBar.setVisibility(View.GONE);
            progressDialog.dismiss();
            
            Random random = new Random();
            int n = random.nextInt(randomStr.length);
            resultTv.setText(randomStr[n]);
        }
    }
}