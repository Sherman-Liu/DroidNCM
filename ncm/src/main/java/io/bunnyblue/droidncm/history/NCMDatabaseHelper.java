package io.bunnyblue.droidncm.history;

import androidx.room.Room;

import java.io.File;

import io.bunnyblue.droidncm.NCMApp;
import io.bunnyblue.droidncm.finder.Utils;
import io.bunnyblue.droidncm.finder.dummy.NCMFileContent;

public class NCMDatabaseHelper {
    public static NCMHistoryDB getInstance() {
        NCMHistoryDB db = Room.databaseBuilder(NCMApp.getInstance(),
                NCMHistoryDB.class, "ncm_history").build();
        return db;
    }

    public static NCMHistory buildHistory(NCMFileContent.NCMLocalFile file) {
        NCMHistory ncmHistory = new NCMHistory();
        ncmHistory.fullPath = file.localPath;
        ncmHistory.targetPath = file.targetPath;
        ncmHistory.time = System.currentTimeMillis();

        ncmHistory.hashStr = Utils.sha256File(new File(file.localPath));
        ncmHistory.hash = ncmHistory.hashStr.hashCode();
        return ncmHistory;
    }

}
