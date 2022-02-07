package com.daclink.drew.sp22.cst438_project01_starter.db.typeConverters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataTypeConverters {

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }
}
