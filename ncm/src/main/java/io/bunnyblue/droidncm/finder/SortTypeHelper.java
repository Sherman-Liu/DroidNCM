package io.bunnyblue.droidncm.finder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import io.bunnyblue.droidncm.finder.dummy.NCMFileContent;

public class SortTypeHelper {
    public static final String SORT_TYPE = "sort_type";
    //         <item>按名称升序</item>
//        <item>按名称降序</item>
//        <item>按日期升序</item>
//        <item>按日期降序</item>
    public static final int SORT_TYPE_NAME_ASC = 0;//按名称升序
    public static final int SORT_TYPE_NAME_DESC = 1;//按名称降序
    public static final int SORT_TYPE_TIME_ASC = 2;//按日期升序
    public static final int SORT_TYPE_TIME_DESC = 3;//按日期降序

    public static int getDefaultType(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ncm", Context.MODE_PRIVATE);
        int defaultType = sharedPreferences.getInt(SortTypeHelper.SORT_TYPE, SortTypeHelper.SORT_TYPE_NAME_ASC);
        return defaultType;
    }

    @SuppressLint("ApplySharedPref")
    public static int update(Context context, int type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ncm", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(SortTypeHelper.SORT_TYPE, type).commit();
        return type;
    }

    public static NCMFileContent resortNCMList(NCMFileContent content) {
        content.requestSort();
        return content;
    }

}
