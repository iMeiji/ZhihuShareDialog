package com.meiji.zhihusharedialog;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AppInfo> mAppinfoList;
    private AppInfoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Intent shareIntent;

    {
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "www.github.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAppinfoList = Utils.getShareAppList(this, shareIntent);
    }

    private void initView() {
        Button bt_zhihu = findViewById(R.id.bt_zhihu);
        Button bt_stock = findViewById(R.id.bt_stock);

        bt_zhihu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
        bt_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "分享文字";
                Intent chooser = Intent.createChooser(shareIntent, title);
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }

    private void showBottomDialog() {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet);
        initBottomDialog(mBottomSheetDialog);
        mBottomSheetDialog.show();
    }

    private void initBottomDialog(final Dialog dialog) {
        mAdapter = new AppInfoAdapter(this, mAppinfoList);

        mRecyclerView = dialog.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new AppInfoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(AppInfo appInfo, View view, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setComponent(new ComponentName(appInfo.getPkgName(), appInfo.getLaunchClassName()));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "www.github.com");
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(AppInfo appInfo, View view, int position) {
                // 打开应用信息界面
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + appInfo.getPkgName()));
                startActivity(intent);
            }
        });
    }
}
