package com.photo_magic.photomagic.ui.presenter;

import com.photo_magic.photomagic.data.DataManager;
import com.photo_magic.photomagic.ui.view.MainActivityView;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by luis- on 12/02/2018.
 */

public class MainActivityPresenter extends BasePresenter<MainActivityView>  {

    private CompositeSubscription mCompositeSubscription;

    @Inject
    public MainActivityPresenter(){

    }


    @Override
    public void attachView(MainActivityView mvpView) {
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
