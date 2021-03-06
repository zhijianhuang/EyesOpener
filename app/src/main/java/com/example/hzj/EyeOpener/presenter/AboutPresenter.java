package com.example.hzj.EyeOpener.presenter;

import com.example.hzj.EyeOpener.base.BaseView;
import com.example.hzj.EyeOpener.base.RxPresenter;
import com.example.hzj.EyeOpener.model.DataManager;

import javax.inject.Inject;

/**
 * Created by rj on 2018/1/31.
 * aboutActivity所关联的presenter，暂时不做任何处理
 */

public class AboutPresenter extends RxPresenter<BaseView>{

    @Inject
    public AboutPresenter(DataManager dataManager){
        this.mDataManager=dataManager;
    }
}
