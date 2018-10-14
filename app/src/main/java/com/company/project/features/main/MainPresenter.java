package com.company.project.features.main;

import javax.inject.Inject;

import com.company.project.data.DataManager;
import com.company.project.features.base.BasePresenter;
import com.company.project.injection.ConfigPersistent;
import com.company.project.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPosts() {
        checkViewAttached();
        getView().showProgress(true);
        dataManager
                .getPostsList()
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        postItemList -> {
                            getView().showProgress(false);
                            getView().showPosts(postItemList);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
    public void getComments(int position , int postId) {
        checkViewAttached();
        getView().showProgress(true);
        dataManager
                .getComments(postId)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        comments -> {
                            getView().showProgress(false);
                            getView().showComments(comments , position);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }

}
