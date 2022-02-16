package com.daclink.drew.sp22.cst438_project01_starter.db.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;

import java.util.List;

/**
 *  Recipe entity class for use with Room Database
 */

@Entity(tableName = AppDatabase.RECIPE_TABLE)
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int mRecipeId;

    private int mApiId;
    private String mName;
    private String mCategory;
    private String mArea;
    private String mInstructions;
    private String mThumbnail;
    private String mTags;
    private String mYoutube;
    private List<String> mIngredients;
    private List<String> mMeasurements;

    public Recipe(int mRecipeId, int mApiId, String mName, String mCategory, String mArea, String mInstructions, String mThumbnail, String mTags, String mYoutube, List<String> mIngredients, List<String> mMeasurements) {
        this.mRecipeId = mRecipeId;
        this.mApiId = mApiId;
        this.mName = mName;
        this.mCategory = mCategory;
        this.mArea = mArea;
        this.mInstructions = mInstructions;
        this.mThumbnail = mThumbnail;
        this.mTags = mTags;
        this.mYoutube = mYoutube;
        this.mIngredients = mIngredients;
        this.mMeasurements = mMeasurements;
    }

    public int getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(int mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public int getApiId() {
        return mApiId;
    }

    public void setApiId(int mApiId) {
        this.mApiId = mApiId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String mArea) {
        this.mArea = mArea;
    }

    public String getInstructions() {
        return mInstructions;
    }

    public void setInstructions(String mInstructions) {
        this.mInstructions = mInstructions;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getTags() {
        return mTags;
    }

    public void setTags(String mTags) {
        this.mTags = mTags;
    }

    public String getYoutube() {
        return mYoutube;
    }

    public void setYoutube(String mYoutube) {
        this.mYoutube = mYoutube;
    }

    public List<String> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<String> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public List<String> getMeasurements() {
        return mMeasurements;
    }

    public void setMeasurements(List<String> mMeasurements) {
        this.mMeasurements = mMeasurements;
    }
}
