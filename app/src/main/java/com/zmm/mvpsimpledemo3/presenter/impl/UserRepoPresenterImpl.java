package com.zmm.mvpsimpledemo3.presenter.impl;

import com.zmm.mvpsimpledemo3.model.GithubService;
import com.zmm.mvpsimpledemo3.model.Repository;
import com.zmm.mvpsimpledemo3.presenter.UserRepoPresenter;
import com.zmm.mvpsimpledemo3.view.UserRepoView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:51
 */

public class UserRepoPresenterImpl implements UserRepoPresenter {

    private UserRepoView mUserRepoView;
    private List<Repository> mRepositories;

    @Override
    public void attachView(UserRepoView userRepoView) {
        this.mUserRepoView = userRepoView;
    }

    @Override
    public void detachView() {
        this.mUserRepoView = null;
    }

    @Override
    public void loadGitHubUserRepoData(String username) {
        mUserRepoView.showProgress();
        GithubService.Factory.create().publicRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

                    @Override
                    public void onCompleted() {
                        if(mRepositories != null){
                            mUserRepoView.showRecyclerView(mRepositories);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserRepoView.showErrorMessage();
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        mRepositories = repositories ;
                    }
                });
    }
}
