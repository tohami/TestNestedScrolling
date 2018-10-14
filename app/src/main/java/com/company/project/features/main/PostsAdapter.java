package com.company.project.features.main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.company.project.R;
import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private final CommentsCallBack commentsCallBack;
    private final Context context;
    private List<PostItem> postItems;
    //private Subject<String> pokemonClickSubject;

    PostsAdapter(Context context , CommentsCallBack callBack) {
        //pokemonClickSubject = PublishSubject.create();
        postItems = Collections.emptyList();
        this.commentsCallBack = callBack ;
        this.context = context ;
    }

    public void setPosts(List<PostItem> posts) {
        this.postItems = posts;
        notifyDataSetChanged();
    }

    public void setPostComments(List<CommentItem> comments , int position) {
        postItems.get(position).setCommentItems(comments);
        notifyItemChanged(position);
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pokemon, parent, false);
        return new PostsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        PostItem pokemon = this.postItems.get(position);
        holder.onBind(pokemon);
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

//    Observable<String> getPokemonClick() {
//        return pokemonClickSubject;
//    }

    class PostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView nameText;

        @BindView(R.id.rv_comments)
        RecyclerView commentsRecycleView;

        PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //itemView.setOnClickListener(v -> pokemonClickSubject.onNext(pokemon));
        }

        void onBind(PostItem post) {
            nameText.setText(
                    String.format(
                            "%s%s"
                            , post.getTitle().substring(0, 1).toUpperCase()
                            , post.getTitle().substring(1)));
            if(post.getCommentItems()!=null) {
                PostCommentsAdapter adapter = new PostCommentsAdapter();
                adapter.setComments(post.getCommentItems());
                commentsRecycleView.setLayoutManager(new LinearLayoutManager(context));
                commentsRecycleView.setAdapter(adapter);
                commentsRecycleView.setVisibility(View.VISIBLE);
                if(post.getCommentItems().size()>0)
                    if(!post.getCommentItems().get(0).getPostId().equals(post.getId()))
                        Log.e("ids" , "fdjsfhagdsjfgadsgfhdgasfdasd" ) ;
                Log.e("sizeeeeeeeeeee" , post.getCommentItems().size() + "<<<<<<<<<<");
            }else{
                commentsCallBack.getPostComments(getAdapterPosition() , post.getId());
                commentsRecycleView.setVisibility(View.GONE);
            }
        }
    }


}
