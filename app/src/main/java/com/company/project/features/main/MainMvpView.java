package com.company.project.features.main;

import java.util.List;

import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;
import com.company.project.features.base.MvpView;

public interface MainMvpView extends MvpView {

    void showPosts(List<PostItem> postItems);

    void showProgress(boolean show);

    void showError(Throwable error);

    void showComments(List<CommentItem> comments, int position);
}
