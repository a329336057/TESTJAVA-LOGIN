package com.ren.mvpdemo.presenter;

import com.ren.mvpdemo.bean.Bean;
import com.ren.mvpdemo.bean.newsBean;
import com.ren.mvpdemo.model.DemoModelImpl;
import com.ren.mvpdemo.view.DemoView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by asus on 2018/5/16.
 */

public class DemoPresenterImpl implements DemoPresenter {

    private DemoModelImpl demoModel;
    private DemoView demoView;

    public DemoPresenterImpl(DemoView demoView) {
        demoModel = DemoModelImpl.getInstance();
        this.demoView = demoView;
    }

    @Override
    public void getMyAddress(String baseUrl, String userId) {
        demoModel.getMyAddress(baseUrl, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bean>() {
                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {
                        demoView.getDataFailed();   
                    }

                    @Override
                    public void onNext(Bean bean) {
                        demoView.getDataSuccess(bean);
                    }
                });
    }

    @Override
    public void getnews(String baseUrl, String key, String type) {
        demoModel.getnews(baseUrl,key,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<newsBean>() {
                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {
                        demoView.getDataFailed();
                    }

                    @Override
                    public void onNext(newsBean bean) {
                        demoView.getDatanews(bean);
                    }
                });
    }


}
