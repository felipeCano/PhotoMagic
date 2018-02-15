package com.photo_magic.photomagic.data.model;

import android.graphics.Bitmap;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by luis- on 12/02/2018.
 */
@RealmClass
public class PhotoItem extends RealmObject {

    @PrimaryKey
    private String photoId;
    private String imagen;
    private boolean isSaved = false;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public PhotoItem (String imagen){
        this.imagen = imagen;

    }
    public PhotoItem (){

    }

    public String getRoutePhoto() {
        return routePhoto;
    }

    public void setRoutePhoto(String routePhoto) {
        this.routePhoto = routePhoto;
    }

    private String routePhoto;
}
