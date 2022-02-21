package com.daclink.drew.sp22.cst438_project01_starter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeCategoryInfo {
    @SerializedName("strMeal")
    @Expose
    private String strMeal;

    @SerializedName("strMealThumb")
    @Expose
    private String strMealThumb;

    @SerializedName("idMeal")
    @Expose
    private String idMeal;

    public RecipeCategoryInfo(String strMeal, String strMealThumb, String idMeal) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}
