package io.bunnyblue.droidncm.history;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NCMHistory.class}, version = 1)
public abstract class NCMHistoryDB extends RoomDatabase {

    public void assertNotMainThread() {
    }

    public abstract NCMHistoryDAO ncmHistoryDAO();


}
