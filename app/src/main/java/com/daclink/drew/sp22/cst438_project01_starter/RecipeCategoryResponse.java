package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeCategoryResponse {

    @SerializedName("meals")
    @Expose
    private List<RecipeCategoryInfo> mealsCategory = null;

        @NonNull
        public RecipeCategoryInfo getRecipeCategoryInfo() {
            return mealsCategory.get(0);
    }
}
