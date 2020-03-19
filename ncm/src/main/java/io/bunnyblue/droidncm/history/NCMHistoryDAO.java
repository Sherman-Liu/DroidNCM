package io.bunnyblue.droidncm.history;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NCMHistoryDAO {


    @Query("SELECT * FROM ncmhistory")
    List<NCMHistory> getAll();

    @Insert
    void insertAll(NCMHistory... histories);

    @Query("DELETE FROM ncmhistory WHERE  fullPath= :fullPath")
    abstract void deleteByLocalPath(String fullPath);

    @Delete
    void delete(NCMHistory user);


}
