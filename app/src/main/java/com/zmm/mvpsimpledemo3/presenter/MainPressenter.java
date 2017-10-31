package com.zmm.mvpsimpledemo3.presenter;

import com.zmm.mvpsimpledemo3.view.MainView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:44
 */

public interface MainPressenter extends BasePresenter<MainView>{

    void loadGitHubData();
}
