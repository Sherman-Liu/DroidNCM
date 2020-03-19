package io.bunnyblue.droidncm.history;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NCMHistory {

    @PrimaryKey
    public long hash;
    @ColumnInfo(name = "hashStr")
    public String hashStr;
    @ColumnInfo(name = "fullPath")
    public String fullPath;
    @ColumnInfo(name = "targetPath")
    public String targetPath;
    @ColumnInfo(name = "time")
    public long time;

    @Override
    public String toString() {
        return "NCMHistory{" +
                "hash=" + hash +
                ", hashStr='" + hashStr + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", targetPath='" + targetPath + '\'' +
                ", time=" + time +
                '}';
    }

}
