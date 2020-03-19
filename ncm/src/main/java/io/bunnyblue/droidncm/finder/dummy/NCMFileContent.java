package io.bunnyblue.droidncm.finder.dummy;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.bunnyblue.droidncm.finder.SortTypeHelper;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class NCMFileContent implements Comparator<NCMFileContent.NCMLocalFile> {
    /**
     * An array of sample (dummy) items.
     */
    final List<NCMLocalFile> ITEMS = new ArrayList<NCMLocalFile>();
    private Context context;
    private int sortType;

    public NCMFileContent(Context context) {
        this.context = context;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public void updateSortType(int sortType) {
        this.sortType = sortType;
    }

    public void addFile(NCMLocalFile ncmLocalFile) {
        ITEMS.add(ncmLocalFile);
    }

    public List<NCMLocalFile> getITEMS() {
        return ITEMS;
    }

    public File[] getFiles() {
        File[] files = new File[ITEMS.size()];
        for (int i = 0; i < ITEMS.size(); i++) {
            files[i] = new File(ITEMS.get(i).localPath);
        }
        return files;
    }

    @Override
    public int compare(NCMLocalFile o1, NCMLocalFile o2) {
        switch (sortType) {
            case SortTypeHelper
                    .SORT_TYPE_NAME_ASC:
                return o1.name.compareTo(o2.name);
            //break;
            case SortTypeHelper
                    .SORT_TYPE_NAME_DESC:
                return o2.name.compareTo(o1.name);
            // break;
            case SortTypeHelper
                    .SORT_TYPE_TIME_ASC:
                return ((Long) o1.lastModifyTime).compareTo((Long) o2.lastModifyTime);
            //  break;
            case SortTypeHelper
                    .SORT_TYPE_TIME_DESC:
                return ((Long) o2.lastModifyTime).compareTo((Long) o1.lastModifyTime);
            //  break;
            default:
        }
        return o1.name.compareTo(o2.name);
    }

    public void requestSort() {
        Collections.sort(ITEMS, this);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class NCMLocalFile {
        public String id;
        public String name;
        public String details;
        public String localPath;

        public String targetPath;
        public long lastModifyTime = 0;
        public long ncmSize = 0;
        public String error = null;

        public NCMLocalFile() {

        }

        public NCMLocalFile(String id, String name, String details, long lastModifyTime) {
            this.id = id;
            this.name = name;
            this.details = details;
            this.lastModifyTime = lastModifyTime;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
