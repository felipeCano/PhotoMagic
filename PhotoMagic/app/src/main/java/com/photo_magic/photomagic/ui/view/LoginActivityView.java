package com.photo_magic.photomagic.ui.view;

/**
 * Created by luis- on 12/02/2018.
 */

public interface LoginActivityView extends MvpView {
    void showMain();
    void setEnabledLoginButton(boolean isEnabled);
    void showMessageInvalideUser();

}
