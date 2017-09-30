package com.meiji.zhihusharedialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Meiji on 2017/9/30.
 */

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<AppInfo> mAppList;
    private OnItemClickLitener mOnItemClickLitener;

    public AppInfoAdapter(Context mContext, ArrayList<AppInfo> date) {
        this.mAppList = date;
        this.mContext = mContext;
    }

    @Override
    public AppInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appinfo_item, viewGroup, false);
        return new AppInfoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(mAppList.get(i).getAppName());
        viewHolder.img.setBackground(mAppList.get(i).getAppIcon());

        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mAppList.get(pos), viewHolder.itemView, pos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(mAppList.get(pos), viewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(AppInfo parent, View view, int position);

        void onItemLongClick(AppInfo parent, View view, int position);
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;

        ViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.text_list_item);
            img = view.findViewById(R.id.img_list_item);
        }
    }
}