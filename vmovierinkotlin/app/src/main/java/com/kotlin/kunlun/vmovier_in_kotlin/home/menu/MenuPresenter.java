package com.kotlin.kunlun.vmovier_in_kotlin.home.menu;

import android.support.v4.app.FragmentActivity;

import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter;

/**
 * Created by 0000- on 2016/7/1.
 */
public class MenuPresenter extends BasePresenter<IMenuView> {
    FragmentActivity activity;

    public MenuPresenter(FragmentActivity activity) {
        this.activity = activity;
    }


    public void hide() {
        activity.getSupportFragmentManager()
                .beginTransaction().hide((MenuFragment) getMvpView()).commit();
    }

    @Override
    public void detachView() {
        activity = null;
        super.detachView();
    }

}
