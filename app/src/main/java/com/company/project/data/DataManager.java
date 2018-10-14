package com.company.project.data;

import android.util.Pair;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;
import com.company.project.data.remote.PokemonService;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import rx.functions.Func1;

/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private PokemonService pokemonService;

    @Inject
    public DataManager(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Single<List<PostItem>> getPostsList() {
        return pokemonService.getPostsList();
    }

    public Single<List<CommentItem>> getComments(int postId) {
        return pokemonService.getPostComments(postId);
    }
}
