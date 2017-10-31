package com.zmm.mvpsimpledemo3.presenter.impl;

import com.zmm.mvpsimpledemo3.model.GithubService;
import com.zmm.mvpsimpledemo3.model.Repo;
import com.zmm.mvpsimpledemo3.presenter.MainPressenter;
import com.zmm.mvpsimpledemo3.view.MainView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:49
 */

public class MainPresenterImpl implements MainPressenter{

    private MainView mMainView;
    private Subscription mSubscribe;
    private List<Repo> mRepos;

    @Override
    public void attachView(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void detachView() {
        this.mMainView = null;
    }

    @Override
    public void loadGitHubData() {
        mMainView.showProgress();
        String url = "http://github.laowch.com/json/java_daily" ;
        mSubscribe = GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        if (mRepos != null) {
                            mMainView.showRecyclerView(mRepos);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainView.showErrorMessage();
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        mRepos = repos;
                    }
                });
    }
}
