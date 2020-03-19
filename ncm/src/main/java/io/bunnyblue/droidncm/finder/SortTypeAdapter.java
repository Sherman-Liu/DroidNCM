package io.bunnyblue.droidncm.finder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import io.bunnyblue.droidncm.R;

public class SortTypeAdapter extends BaseAdapter {
    private Context context;
    private String[] sortTypeList;

    public SortTypeAdapter(Context context) {
        this.context = context;
        sortTypeList = context.getResources().getStringArray(R.array.sortTypeList);

    }

    @Override
    public int getCount() {
        return sortTypeList.length;
    }

    @Override
    public String getItem(int position) {
        return sortTypeList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            View tmp = LayoutInflater.from(context).inflate(R.layout.view_sort_type_item, null);
            TextView label = tmp.findViewById(R.id.sortTypeLabel);
            SortHolder sortHolder = new SortHolder();
            sortHolder.label = label;
            convertView.setTag(sortHolder);
            label.setText(getItem(position));
            return tmp;
        } else {
            SortHolder sortHolder = (SortHolder) convertView.getTag();
            sortHolder.label.setText(getItem(position));
        }

        return convertView;
    }

    static class SortHolder {
        TextView label;
    }
}
