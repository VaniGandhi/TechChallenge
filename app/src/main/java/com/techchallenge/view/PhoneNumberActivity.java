package com.techchallenge.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PhoneNumberActivity extends AppCompatActivity implements LoginView , View.OnClickListener{

    private EditText etPhoneNumber;
    private Button btContinue;
    private LoginPresenter presenter;
    private UserSharedPrefernces userSharedPrefernces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        init();
    }

    private  void init()
    {
        userSharedPrefernces=new UserSharedPrefernces(this);
        if(userSharedPrefernces.getISLOGIN())
        {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        btContinue=findViewById(R.id.btContinue);
        btContinue.setOnClickListener(this);
        presenter= new LoginPresenter(this);

    }

    //******************onSucessLogin************************//

    @Override
    public void onSucessLogin(Response<LoginResponseModel> responseModel) {
        if (isFinishing()) {
            return;
        }
        if(responseModel.isSuccessful() && responseModel.code()==200)
        {
            if(responseModel.body()!=null)
            {
                if(responseModel.body().getStatus())
                {
                    Intent intent = new Intent(this, OTPActivity.class);
                    startActivity(intent);
                    finish();


                }
            }
        }

    }
    //******************onFailureLogin************************//


    @Override
    public void onFailure(Throwable throwable) {
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

    @Override
    public void onSucessVerify(Response<OTPResponseModel> responseModel) {

    }

    @Override
    public void onFailureVerify(Throwable throwable) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btContinue:
                if(TextUtils.isEmpty(etPhoneNumber.getText().toString().trim()))
                {
                    Toast.makeText(this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(etPhoneNumber.getText().toString().trim().length()<10)
                {
                    Toast.makeText(this, "Please enter phone  valid number", Toast.LENGTH_SHORT).show();
                    return;

                }
                LoginParamModel model=new LoginParamModel();
                model.setNumber("+91"+etPhoneNumber.getText().toString().trim());
                presenter.login(model);

                break;

        }

    }
}