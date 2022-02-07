package com.daclink.drew.sp22.cst438_project01_starter;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;

import java.util.List;

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

    public int getmRecipeId() {
        return mRecipeId;
    }

    public void setmRecipeId(int mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public int getmApiId() {
        return mApiId;
    }

    public void setmApiId(int mApiId) {
        this.mApiId = mApiId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmArea() {
        return mArea;
    }

    public void setmArea(String mArea) {
        this.mArea = mArea;
    }

    public String getmInstructions() {
        return mInstructions;
    }

    public void setmInstructions(String mInstructions) {
        this.mInstructions = mInstructions;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }

    public void setmThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getmTags() {
        return mTags;
    }

    public void setmTags(String mTags) {
        this.mTags = mTags;
    }

    public String getmYoutube() {
        return mYoutube;
    }

    public void setmYoutube(String mYoutube) {
        this.mYoutube = mYoutube;
    }

    public List<String> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(List<String> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public List<String> getmMeasurements() {
        return mMeasurements;
    }

    public void setmMeasurements(List<String> mMeasurements) {
        this.mMeasurements = mMeasurements;
    }
}
