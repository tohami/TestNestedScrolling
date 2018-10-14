package com.company.project.features.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import com.company.project.R;
import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;
import com.company.project.features.base.BaseActivity;
import com.company.project.features.common.ErrorView;
import com.company.project.injection.component.ActivityComponent;

import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView, ErrorView.ErrorListener {

    private static final int POKEMON_COUNT = 20;

    PostsAdapter postsAdapter;
    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_pokemon)
    RecyclerView pokemonRecycler;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getPosts());

        pokemonRecycler.setLayoutManager(new LinearLayoutManager(this));
        postsAdapter = new PostsAdapter(this , new CommentsCallBack() {
            @Override
            public void getPostComments(int position, int id) {
                mainPresenter.getComments(position , id);
            }
        } ) ;
        pokemonRecycler.setAdapter(postsAdapter);
        //pokemonClicked();
        errorView.setErrorListener(this);

        mainPresenter.getPosts();
    }

//    private void pokemonClicked() {
//        Disposable disposable =
//                postsAdapter
//                        .getPokemonClick()
//                        .subscribe(
//                                pokemon ->
//                                        startActivity(DetailActivity.getStartIntent(this, pokemon)),
//                                throwable -> {
//                                    Timber.e(throwable, "Pokemon click failed");
//                                    Toast.makeText(
//                                            this,
//                                            R.string.error_something_bad_happened,
//                                            Toast.LENGTH_LONG)
//                                            .show();
//                                });
//        mainPresenter.addDisposable(disposable);
//    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showPosts(List<PostItem> postItems) {
        postsAdapter.setPosts(postItems);
        pokemonRecycler.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (pokemonRecycler.getVisibility() == View.VISIBLE
                    && postsAdapter.getItemCount() > 0) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                progressBar.setVisibility(View.VISIBLE);

                pokemonRecycler.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }

            errorView.setVisibility(View.GONE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        pokemonRecycler.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void showComments(List<CommentItem> comments, int position) {
        if(postsAdapter == null)
            return;

        postsAdapter.setPostComments(comments , position);
    }

    @Override
    public void onReloadData() {
        mainPresenter.getPosts();
    }
}
