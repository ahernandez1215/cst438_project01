package com.daclink.drew.sp22.cst438_project01_starter.db.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.drew.sp22.cst438_project01_starter.RecipeModel;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *  Recipe entity class for use with Room Database
 */

@Entity(tableName = AppDatabase.RECIPE_TABLE)
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int mRecipeId;

    private String mApiId;
    private String mName;
    private String mCategory;
    private String mArea;
    private String mInstructions;
    private String mThumbnail;
    private String mTags;
    private String mYoutube;
    private List<String> mIngredients;
    private List<String> mMeasurements;

    public Recipe(int mRecipeId, String mApiId, String mName, String mCategory, String mArea, String mInstructions, String mThumbnail, String mTags, String mYoutube, List<String> mIngredients, List<String> mMeasurements) {
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

    public Recipe(RecipeModel recipeModel) {
        List<String> ingredientsList = new ArrayList<String>();
        List<String> measureList = new ArrayList<String>();

        this.mApiId = recipeModel.getIdMeal();
        this.mName = recipeModel.getStrMeal();
        this.mCategory = recipeModel.getStrCategory();
        this.mArea = recipeModel.getStrArea();
        this.mInstructions = recipeModel.getStrInstructions();
        this.mThumbnail = recipeModel.getStrMealThumb();
        this.mYoutube = recipeModel.getStrYoutube();
        ingredientsList.add(recipeModel.getStrIngredient1());
        ingredientsList.add(recipeModel.getStrIngredient2());
        ingredientsList.add(recipeModel.getStrIngredient3());
        ingredientsList.add(recipeModel.getStrIngredient4());
        ingredientsList.add(recipeModel.getStrIngredient5());
        ingredientsList.add(recipeModel.getStrIngredient6());
        ingredientsList.add(recipeModel.getStrIngredient7());
        ingredientsList.add(recipeModel.getStrIngredient8());
        ingredientsList.add(recipeModel.getStrIngredient9());
        ingredientsList.add(recipeModel.getStrIngredient10());
        ingredientsList.add(recipeModel.getStrIngredient11());
        ingredientsList.add(recipeModel.getStrIngredient12());
        ingredientsList.add(recipeModel.getStrIngredient13());
        ingredientsList.add(recipeModel.getStrIngredient14());
        ingredientsList.add(recipeModel.getStrIngredient15());
        ingredientsList.add(recipeModel.getStrIngredient16());
        ingredientsList.add(recipeModel.getStrIngredient17());
        ingredientsList.add(recipeModel.getStrIngredient18());
        ingredientsList.add(recipeModel.getStrIngredient19());
        ingredientsList.add(recipeModel.getStrIngredient20());
        this.mIngredients = ingredientsList;
        measureList.add(recipeModel.getStrMeasure1());
        measureList.add(recipeModel.getStrMeasure2());
        measureList.add(recipeModel.getStrMeasure3());
        measureList.add(recipeModel.getStrMeasure4());
        measureList.add(recipeModel.getStrMeasure5());
        measureList.add(recipeModel.getStrMeasure6());
        measureList.add(recipeModel.getStrMeasure7());
        measureList.add(recipeModel.getStrMeasure8());
        measureList.add(recipeModel.getStrMeasure9());
        measureList.add(recipeModel.getStrMeasure10());
        measureList.add(recipeModel.getStrMeasure11());
        measureList.add(recipeModel.getStrMeasure12());
        measureList.add(recipeModel.getStrMeasure13());
        measureList.add(recipeModel.getStrMeasure14());
        measureList.add(recipeModel.getStrMeasure15());
        measureList.add(recipeModel.getStrMeasure16());
        measureList.add(recipeModel.getStrMeasure17());
        measureList.add(recipeModel.getStrMeasure18());
        measureList.add(recipeModel.getStrMeasure19());
        measureList.add(recipeModel.getStrMeasure20());
        this.mMeasurements = measureList;

    }

    public int getRecipeId() {
        return mRecipeId;
    }

    public void setRecipeId(int mRecipeId) {
        this.mRecipeId = mRecipeId;
    }

    public String getApiId() {
        return mApiId;
    }

    public void setApiId(String mApiId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return mRecipeId == recipe.mRecipeId && mApiId == recipe.mApiId && mName.equals(recipe.mName) && Objects.equals(mCategory, recipe.mCategory) && Objects.equals(mArea, recipe.mArea) && Objects.equals(mInstructions, recipe.mInstructions) && Objects.equals(mThumbnail, recipe.mThumbnail) && Objects.equals(mTags, recipe.mTags) && Objects.equals(mYoutube, recipe.mYoutube) && Objects.equals(mIngredients, recipe.mIngredients) && Objects.equals(mMeasurements, recipe.mMeasurements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRecipeId, mApiId, mName, mCategory, mArea, mInstructions, mThumbnail, mTags, mYoutube, mIngredients, mMeasurements);
    }
}
