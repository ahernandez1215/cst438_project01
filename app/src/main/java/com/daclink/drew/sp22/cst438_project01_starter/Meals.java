package com.daclink.drew.sp22.cst438_project01_starter;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meals{

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;
    public List<Meal> getMeals() {
        return meals;
    }
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }


}
