package com.example.rj.openeyesvideo.base.Contract;

import com.example.rj.openeyesvideo.base.BasePresenter;
import com.example.rj.openeyesvideo.base.BaseView;

/**
 * Created by rj on 2017/12/20.
 */

public interface DailyContract {
    interface View extends BaseView{
        void showContent();
    }
    interface Presenter extends BasePresenter<View>{
        void getDailyData();
        void getMoreData();
    }
}