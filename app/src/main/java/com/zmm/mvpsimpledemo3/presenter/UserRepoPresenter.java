package com.zmm.mvpsimpledemo3.presenter;

import com.zmm.mvpsimpledemo3.view.UserRepoView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:48
 */

public interface UserRepoPresenter extends BasePresenter<UserRepoView> {

    void loadGitHubUserRepoData(String username);
}
