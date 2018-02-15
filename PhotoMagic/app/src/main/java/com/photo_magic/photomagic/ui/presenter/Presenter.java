package com.photo_magic.photomagic.ui.presenter;

import com.photo_magic.photomagic.ui.view.MvpView;

/**
 * Created by luis- on 12/02/2018.
 */

public interface Presenter<V extends MvpView> {
    void attachView(V MvpView);
    void detachView();
}
