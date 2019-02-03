package com.dat153.andrew.mnamequizeapp.utils;

import com.google.firebase.database.Exclude;

public class Upload {
    private String imgName;
    private String imgUrl;
    private String imgKey;


    /**
     *
     */
    public Upload() {
    }

    /**
     *
     * @param imgName
     * @param imgUrl
     */
    public Upload(String imgName, String imgUrl) {
        if(imgName.trim().equals(""))
        {
            imgName="No name";
        }
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }




    public Upload(String imageName){
        this.imgName = imageName;
    }

    /**
     *
     * @return
     */
    public String getImgName() {
        return imgName;
    }

    /**
     *
     * @param imgName
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     *
     * @return
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     *
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Exclude
    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    @Exclude
    public String getImgKey() {
        return imgKey;
    }
}
