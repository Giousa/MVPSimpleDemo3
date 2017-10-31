package com.zmm.mvpsimpledemo3.presenter;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/10/31
 * Time:下午4:42
 */

public interface BasePresenter<T> {

    void attachView(T t);

    void detachView();
}