package com.cloudy9101.weltec.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RecipeModel> dataModelArrayList;

    public RecipeListAdapter(Context context, ArrayList<RecipeModel> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataModelArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recipe_layout, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.readyInMinutes = (TextView) convertView.findViewById(R.id.readyInMinutes);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(dataModelArrayList.get(position).getImage()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getTitle());
        holder.readyInMinutes.setText("Ready in minutes: " + dataModelArrayList.get(position).getReadyInMinutes());

        return convertView;
    }

    private class ViewHolder {

        protected TextView name, readyInMinutes;
        protected ImageView iv;
    }

}