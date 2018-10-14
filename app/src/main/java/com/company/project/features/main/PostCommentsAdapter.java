package com.company.project.features.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.project.R;
import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.CommentItem;
import com.company.project.data.model.response.PostItem;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostCommentsAdapter extends RecyclerView.Adapter<PostCommentsAdapter.PokemonViewHolder> {

    private List<CommentItem> CommentItems;
    //private Subject<String> pokemonClickSubject;

    PostCommentsAdapter() {
        //pokemonClickSubject = PublishSubject.create();
        CommentItems = Collections.emptyList();
    }

    public void setComments(List<CommentItem> Comments) {
        this.CommentItems = Comments;
        notifyDataSetChanged();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.comment_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        CommentItem pokemon = this.CommentItems.get(position);
        holder.onBind(pokemon);
    }

    @Override
    public int getItemCount() {
        return CommentItems.size();
    }

//    Observable<String> getPokemonClick() {
//        return pokemonClickSubject;
//    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_comment)
        TextView commentText;


        private CommentItem Comment;

        PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //itemView.setOnClickListener(v -> pokemonClickSubject.onNext(pokemon));
        }

        void onBind(CommentItem Comment) {
            this.Comment = Comment;
            commentText.setText(
                    String.format(
                            "%s%s"
                            , Comment.getName().substring(0, 1).toUpperCase()
                            , Comment.getName().substring(1)));
        }
    }
}
