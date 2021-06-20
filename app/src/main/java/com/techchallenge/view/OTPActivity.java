package com.techchallenge.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techchallenge.R;
import com.techchallenge.interfaces.LoginView;
import com.techchallenge.model.LoginParamModel;
import com.techchallenge.model.LoginResponseModel;
import com.techchallenge.model.OTPResponseModel;
import com.techchallenge.presenter.LoginPresenter;
import com.techchallenge.utils.UserSharedPrefernces;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Response;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private EditText edOTP;
    private TextView tvTimer;
    private Button btContinue;
    private LoginPresenter presenter;
    private UserSharedPrefernces userSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);
        init();
    }

    private void init() {
        edOTP = findViewById(R.id.edOTP);
        tvTimer=findViewById(R.id.tvTimer);
        btContinue = findViewById(R.id.btContinue);
        btContinue.setOnClickListener(this);
        presenter = new LoginPresenter(this);
        userSharedPrefernces = new UserSharedPrefernces(this);


        //******************CountDownTimer************************//
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                Long seconds = (millisUntilFinished / 1000) % 60;
                Long minutes = (millisUntilFinished / (1000 * 60)) % 60;
                tvTimer.setText( minutes + ":" + seconds);

            }

            public void onFinish() {

            }

        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btContinue:
                if (TextUtils.isEmpty(edOTP.getText().toString().trim())) {
                    Toast.makeText(this, "Please enter otp", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (edOTP.getText().toString().trim().length() < 4) {
                    Toast.makeText(this, "Please enter valid otp", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (!edOTP.getText().toString().trim().equals("1234")) {
                    Toast.makeText(this, "Please enter valid otp", Toast.LENGTH_SHORT).show();
                    return;

                }
                LoginParamModel model = new LoginParamModel();
                model.setNumber("+919876543212");
                model.setOtp(edOTP.getText().toString().trim());
                presenter.verifyNumber(model);

                break;

        }

    }

    @Override
    public void onSucessLogin(Response<LoginResponseModel> responseModel) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }


    //******************onSucessVerify************************//
    @Override
    public void onSucessVerify(Response<OTPResponseModel> responseModel) {
        if (isFinishing()) {
            return;
        }
        if (responseModel.isSuccessful() && responseModel.code() == 200) {
            if (responseModel.body() != null) {
                if (responseModel.body().getToken() != null) {

                    userSharedPrefernces.setTOKEN(responseModel.body().getToken());
                    userSharedPrefernces.setISLOGIN(true);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        }

    }
    //******************onFailureVerify************************//

    @Override
    public void onFailureVerify(Throwable throwable) {
        if (isFinishing()) {
            return;
        }
        if (throwable instanceof UnknownHostException) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        } else if (throwable instanceof SocketTimeoutException) {
            Toast.makeText(this, "Server is not responding. Please try again", Toast.LENGTH_SHORT).show();
        } else if (throwable instanceof ConnectException) {
            Toast.makeText(this, "Failed to connect server", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}