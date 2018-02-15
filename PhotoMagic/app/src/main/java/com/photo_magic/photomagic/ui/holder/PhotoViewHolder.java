package com.photo_magic.photomagic.ui.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.photo_magic.photomagic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis- on 12/02/2018.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    @BindView(R.id.item_photo_container)
    public View mViewContainer;

    @BindView(R.id.photo_item)
    public ImageView mphoto_item;

    @BindView(R.id.buttonDeletePhoto)
    public Button mbuttonDeletePhoto;

    @BindView(R.id.buttonSharePhoto)
    public Button mbuttonSharePhoto;

    @BindView(R.id.buttonSaveLocal)
    public Button mbuttonSaveLocal;

    private Context mContext;


    public PhotoViewHolder(View itemView, Context mContext) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        mViewContainer.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
