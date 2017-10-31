package com.zmm.mvpsimpledemo3.view;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:42
 */

public interface BaseView<T> {

    void showProgress();

    void showErrorMessage();

    void showRecyclerView(T t);
}
