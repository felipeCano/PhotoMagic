package com.photo_magic.photomagic.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.photo_magic.photomagic.R;
import com.photo_magic.photomagic.ui.presenter.LoginActivityPresenter;
import com.photo_magic.photomagic.ui.view.LoginActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by luis- on 12/02/2018.
 */

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Inject
    LoginActivityPresenter mLoginActivityPresenter;

    @BindView(R.id.et_user)
    EditText mUserName;

    @BindView(R.id.et_pass)
    EditText mPassword;

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        mLoginActivityPresenter.attachView(this);

    }
    @OnClick(R.id.btn_login)
    public void userLogin(View v){
        mLoginActivityPresenter.credentialUser(mUserName.getText().toString(), mPassword.getText().toString());
    }

    public void showMessageInvalideUser(){
        Context context = getApplicationContext();
        CharSequence text = "User Invalide";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void showMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @Override
    public void setEnabledLoginButton(boolean isEnabled) {

        /* This could be a drawable, but.. time :p  */

        if (isEnabled) {
            mBtnLogin.setBackgroundColor(getResources().getColor(R.color.btn_enabled));
        } else {
            mBtnLogin.setBackgroundColor(getResources().getColor(R.color.btn_not_enabled));
        }

        mBtnLogin.setEnabled(isEnabled);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginActivityPresenter.detachView();
    }

    @Override
    public void showError() {

    }

}