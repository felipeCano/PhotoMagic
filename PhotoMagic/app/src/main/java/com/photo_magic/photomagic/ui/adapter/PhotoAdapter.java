package com.photo_magic.photomagic.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.photo_magic.photomagic.R;
import com.photo_magic.photomagic.data.model.PhotoItem;
import com.photo_magic.photomagic.ui.holder.PhotoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by luis- on 12/02/2018.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
    private List<PhotoItem> mListItems;

    private boolean canPresentShareDialog;
    private Context mContext;
    private Realm mRealm;

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));

            String title = "error";
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("HelloFacebook", "Success!");
            if (result.getPostId() != null) {
                String title = "ok";
                String id = result.getPostId();
                String alertMessage = "ok ok";
                showResult(title, alertMessage);
            }
        }

        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(mContext)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton("ok ok ok", null)
                    .show();
        }
    };

    @Inject
    public PhotoAdapter(Context context) {
        this.mContext = context;
        mListItems = new ArrayList<>();

        LoginManager.getInstance().logInWithPublishPermissions(
                (Activity) mContext,
                Arrays.asList(PERMISSION));

    }

    private CallbackManager callbackManagerLogin;

    private static final String PERMISSION = "publish_actions";

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_adapter,
                parent, false);
        shareDialog = new ShareDialog((Activity) mContext);
        canPresentShareDialog = ShareDialog.canShow(
                ShareLinkContent.class);

        callbackManager = CallbackManager.Factory.create();
        callbackManagerLogin = CallbackManager.Factory.create();

        mRealm = Realm.getDefaultInstance();

        shareDialog.registerCallback(callbackManager, shareCallback);

        LoginManager.getInstance().registerCallback(callbackManagerLogin,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {


                    }

                });

        return new PhotoViewHolder(itemView, mContext);

    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        PhotoItem photoItem = mListItems.get(position);

        try {

            Picasso.with(mContext)
                    .load("file://" + photoItem.getImagen()).config(Bitmap.Config.RGB_565).into(holder.mphoto_item);

        } catch (Exception e) {
            Log.d("este", e.getMessage());
        }

        holder.mbuttonDeletePhoto.setOnClickListener(view -> {
            try {
                mRealm.beginTransaction();
                RealmResults<PhotoItem> deletePhoto = mRealm.where(PhotoItem.class).equalTo("photoId", photoItem.getPhotoId()).findAll();
                deletePhoto.deleteAllFromRealm();
                mRealm.commitTransaction();
                mListItems.remove(position);
                notifyItemChanged(position);
            } catch (Exception e) {

            }

        });

        holder.mbuttonSharePhoto.setOnClickListener(view -> {

            Profile profile = Profile.getCurrentProfile();

            Bitmap bm = ((BitmapDrawable) holder.mphoto_item.getDrawable()).getBitmap();
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(bm)
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            if (canPresentShareDialog) {
                shareDialog.show(content);
            } else if (profile != null && hasPublishPermission()) {
                ShareApi.share(content, shareCallback);
            } else {
            }

        });

        holder.mbuttonSaveLocal.setOnClickListener(view -> {

            if(!photoItem.isSaved()){
                savePhotoLocal(photoItem, holder.mViewContainer);
                photoItem.setSaved(true);
                notifyItemChanged(position);
            }else{
                showInfo(holder.mViewContainer, "La imagen ya fue guardada ");
            }

        });

    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public void add(List<PhotoItem> list) {
        this.mListItems.clear();
        this.mListItems.addAll(list);
        this.notifyDataSetChanged();
    }

    public void savePhotoLocal(PhotoItem photoItem, View view) {
        mRealm.beginTransaction();

        PhotoItem photoObject = mRealm.createObject(PhotoItem.class,
                UUID.randomUUID().toString());

        photoObject.setImagen(photoItem.getImagen());
        photoObject.setSaved(true);

        mRealm.commitTransaction();

        showInfo(view, "Imagen guardada");

    }

    private void showInfo(View view, String mensaje){

        Snackbar snackbar = Snackbar
                .make(view, mensaje, Snackbar.LENGTH_LONG);

        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.main_title_color) );

        snackbar.show();

    }

    public void add(PhotoItem item) {
        this.mListItems.add(item);
        this.notifyDataSetChanged();
    }

    public PhotoItem getItem(int position) {
        return mListItems.get(position);
    }

}
