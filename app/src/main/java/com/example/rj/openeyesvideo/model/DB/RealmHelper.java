package com.example.rj.openeyesvideo.model.DB;

import android.util.Log;

import com.example.rj.openeyesvideo.model.bean.HistoryBean;
import com.example.rj.openeyesvideo.model.bean.ItemListBean;
import com.example.rj.openeyesvideo.model.bean.LikeBean;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rj on 2017/12/21.
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME="myrealm.vedio";
    private Realm mRealm;

    @Inject
    public RealmHelper(){
        mRealm=Realm.getInstance(new RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .name(DB_NAME)
        .build());
        Log.d("hzj", "RealmHelper: ");
    }

    /**
     * 增加阅读数据
     * @param itemListBean
     */
    @Override
    public void insertReadId(ItemListBean itemListBean) {
        HistoryBean historyBean=new HistoryBean();
            historyBean.setId(itemListBean.getData().getId());
            historyBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
            historyBean.setAuthorName(itemListBean.getData().getAuthor().getName());
            historyBean.setAuthorSlogen(itemListBean.getData().getAuthor().getDescription());
            historyBean.setImage(itemListBean.getData().getCover().getFeed());
            historyBean.setTitle(itemListBean.getData().getTitle());
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(historyBean);
            mRealm.commitTransaction();
        Log.d("hzj", "insertReadId: ");
    }



    @Override
    public void insertLikeId(ItemListBean itemListBean) {
        LikeBean likeBean=new LikeBean();
        likeBean.setId(itemListBean.getData().getId());
        likeBean.setAuthorIcon(itemListBean.getData().getAuthor().getIcon());
        likeBean.setAuthorName(itemListBean.getData().getAuthor().getName());
        likeBean.setAuthorSlogen(itemListBean.getData().getCategory());
        likeBean.setImage(itemListBean.getData().getCover().getFeed());
        likeBean.setTitle(itemListBean.getData().getTitle());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(likeBean);
        mRealm.commitTransaction();
    }



    @Override
    public void deleteLikeId(int id) {
        LikeBean bean = mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        mRealm.beginTransaction();
        if(bean!=null){
            bean.deleteFromRealm();
        }
        mRealm.commitTransaction();
    }

    @Override
    public List<HistoryBean> getHistoryBeans() {
        RealmResults<HistoryBean> realmResults=mRealm.where(HistoryBean.class).findAll();
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public List<LikeBean> getLikeBeans() {
        RealmResults<LikeBean> realmResults=mRealm.where(LikeBean.class).findAll();
        return mRealm.copyFromRealm(realmResults);
    }

    @Override
    public void checkHistoryTime(int id, long time) {

    }

    @Override
    public void checkLikeTime(int id, long time) {

    }

    @Override
    public HistoryBean getHistoryBean(int id) {
        HistoryBean historyBean;
        Log.d("hzj", "getHistoryBean: "+id);
        historyBean = mRealm.where(HistoryBean.class).equalTo("id",id).findFirst();
        if(historyBean==null){
            return null;
        }else {
            return mRealm.copyFromRealm(historyBean);
        }
    }

    @Override
    public boolean checkLike(int id) {
        LikeBean likeBean=mRealm.where(LikeBean.class).equalTo("id",id).findFirst();
        if(likeBean==null){
            return false;
        }else {
            return true;
        }
    }
}
