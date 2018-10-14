package com.company.project.data.remote;

import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {

    @GET("posts")
    Single<List<PostItem>> getPostsList();

    @GET("comments")
    Single<List<CommentItem>> getPostComments(@Query("postId") int postId);

}
