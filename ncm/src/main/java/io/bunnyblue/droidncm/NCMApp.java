package io.bunnyblue.droidncm;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;

import io.bunnyblue.droidncm.history.NCMDatabaseHelper;
import io.bunnyblue.droidncm.history.NCMHistory;

public class NCMApp extends Application {
    static NCMApp ncmApp;
    Handler handler;

    public static NCMApp getInstance() {
        return ncmApp;
    }

    public Handler getHandler() {
        return handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.myLooper());
        ncmApp = this;
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                List<NCMHistory> list = NCMDatabaseHelper.getInstance().ncmHistoryDAO().getAll();
//                for (NCMHistory hi :
//                        list) {
//                    Log.e("bunny", "onCreate: " + hi);
//
//                }
//            }
//        });


    }
}
