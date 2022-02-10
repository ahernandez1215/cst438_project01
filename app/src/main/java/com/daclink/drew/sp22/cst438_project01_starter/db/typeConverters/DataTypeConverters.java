package com.daclink.drew.sp22.cst438_project01_starter.db.typeConverters;

import android.graphics.drawable.Icon;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DataTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }

    @TypeConverter
    public static List<String> convertStringToList(String string) {
        if(string == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(string, listType);
    }

    @TypeConverter
    public static String convertListToString(List<String> list) {

        return gson.toJson(list);
    }
}
