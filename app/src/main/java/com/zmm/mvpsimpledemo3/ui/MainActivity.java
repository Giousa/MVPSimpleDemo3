package com.zmm.mvpsimpledemo3.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zmm.mvpsimpledemo3.R;
import com.zmm.mvpsimpledemo3.adapter.RepoListAdapter;
import com.zmm.mvpsimpledemo3.model.Repo;
import com.zmm.mvpsimpledemo3.presenter.impl.MainPresenterImpl;
import com.zmm.mvpsimpledemo3.view.MainView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private Toolbar toolbar ;
    private RecyclerView recyclerView;
    private TextView text_description ;
    private ProgressBar progress ;
    private TextView text_info ;

    private MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        text_description = (TextView) findViewById(R.id.text_description);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);

        setSupportActionBar(toolbar);
        text_description.setText("GitHub Java");

        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.attachView(this);
        mMainPresenter.loadGitHubData();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repo> repos) {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        RepoListAdapter repoListAdapter = new RepoListAdapter(this,repos);
        recyclerView.setAdapter(repoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }
}
