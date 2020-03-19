package io.bunnyblue.droidncm.finder.task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.List;

import io.bunnyblue.droidncm.finder.MainFinderActivity;
import io.bunnyblue.droidncm.finder.dummy.NCMFileContent;
import io.bunnyblue.droidncm.history.NCMDatabaseHelper;
import io.bunnyblue.droidncm.history.NCMHistory;

public class NCMFileFinder extends AsyncTask<File, String, NCMFileContent> {
    ProgressDialog progressDialog;
    Context context;

    public NCMFileFinder(Context context) {
        this.context = context;
    }

    private NCMHistory findNCMHistory(File file, List<NCMHistory> histories) {
        for (NCMHistory history : histories) {
            if (file.getAbsolutePath().equals(history.fullPath)) {

                if (new File(history.targetPath).exists()) {
                    return history;
                }
            }

        }
        return null;

    }

    @SuppressLint("WrongThread")
    @Override
    protected NCMFileContent doInBackground(File... files) {
        List<NCMHistory> histories = NCMDatabaseHelper.getInstance().ncmHistoryDAO().getAll();
        if (files != null) {
            NCMFileContent ncmFileContent = new NCMFileContent(context);
            for (File file : files) {
                if (!file.exists()) {
                    continue;
                }

                Collection<File> fileCollection = FileUtils.listFiles(file, new String[]{"ncm"}, true);

                for (File localFile :
                        fileCollection) {
                    NCMFileContent.NCMLocalFile ncmLocalFile = new NCMFileContent.NCMLocalFile();
                    ncmLocalFile.localPath = localFile.getAbsolutePath();
                    NCMHistory ncmHistory = findNCMHistory(localFile, histories);
                    if (ncmHistory != null) {
                        ncmLocalFile.targetPath = ncmHistory.targetPath;
                    }
                    ncmLocalFile.name = localFile.getName();
                    ncmLocalFile.ncmSize = localFile.length();
                    ncmLocalFile.lastModifyTime = localFile.lastModified();
                    ncmFileContent.addFile(ncmLocalFile);
                    onProgressUpdate(ncmLocalFile.name);

                }
            }

            return ncmFileContent;


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //  ProgressDialog  builder = new ProgressDialog.Builder(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("searching...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
            }
        });

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        });
    }

    @Override
    protected void onProgressUpdate(final String... values) {
        super.onProgressUpdate(values);

        ((MainFinderActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(values[0]);
            }
        });
    }

    @Override
    protected void onPostExecute(NCMFileContent ncmFileContent) {
        super.onPostExecute(ncmFileContent);
        ncmFileContent.requestSort();
        //   progressDialog.dismiss();
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
            }
        });
        if (ncmFileContent == null) {
            Toast.makeText(context, "没有找到文件", Toast.LENGTH_SHORT).show();
        } else {
            ((MainFinderActivity) context).ncmFileContent = ncmFileContent;
            ((MainFinderActivity) context).updateNCMFileList();
        }

    }
}
