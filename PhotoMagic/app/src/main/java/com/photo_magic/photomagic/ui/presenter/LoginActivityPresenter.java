package com.photo_magic.photomagic.ui.presenter;

import android.widget.Toast;

import com.photo_magic.photomagic.data.DataManager;
import com.photo_magic.photomagic.ui.view.LoginActivityView;

import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by luis- on 12/02/2018.
 */

public class LoginActivityPresenter extends BasePresenter<LoginActivityView> {

    private CompositeSubscription mCompositeSubscription;

    private final DataManager mDataManager;

    @Inject
    public LoginActivityPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }


    public void credentialUser(String user, String password){
        if(user.isEmpty() == false && password.isEmpty() == false){
            getMvpView().showMain();
        }else{
            getMvpView().showMessageInvalideUser();
        }

    }


    @Override
    public void attachView(LoginActivityView mvpView) {
        super.attachView(mvpView);
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

}
