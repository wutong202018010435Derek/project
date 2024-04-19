package com.lynn.base.img;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.lynn.base.R;
import com.lynn.base.base.BaseActivity;
import com.lynn.base.img.base.ItemCheckedController;
import com.lynn.base.img.bean.ImageItem;
import com.lynn.base.uitls.DensityUtil;
import com.lynn.base.uitls.ImageLoadUtils;
import com.lynn.base.uitls.ScreenUtils;
import com.lynn.base.view.AppTitleLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ImgPickerActivity extends BaseActivity implements ItemCheckedController<ImageItem>, ImgGalleryFragment.LoadFinishListener {

    public static final String EXTRA_MAX_COUNT = "max_images";
    private static final String KEY_CHECKED_ITEMS = "checked_items";
    private static final String KEY_LAST_FRAGMENT = "last_fragment";
    private static final String TAG = "ImgPickerActivity";
    private static int mMaxCount = 9;

//    @BindView(R.id.titleLayout)
    AppTitleLayout mTitleLayout;

    private boolean isTitleArrowUp;
    private ListView lvPhotoDirectory;
    private PhotoDirectoryAdapter mAdapter;
    private ArrayList<ImageItem> mCheckedItems = new ArrayList();
    private List<DataSetObserver> mCheckedSetObservers = new ArrayList();
    private String mLastFragment;
    private DirectoryItem mLastSelectedDirectory;
    private int mPhotoDirectorySelection;
    private PopupWindow mPopupWindow;


    private class DirectoryItem {
        boolean isSelected;
        String mDirectoryName;
        List<ImageItem> mImages;

        DirectoryItem(String mDirectoryName, List<ImageItem> mImages, boolean isSelected) {
            this.mDirectoryName = mDirectoryName;
            this.mImages = mImages;
            this.isSelected = isSelected;
        }
    }

    private class PhotoDirectoryAdapter extends ArrayAdapter<DirectoryItem> {
        Context mContext;

        public PhotoDirectoryAdapter(Context context) {
            super(context, 0);
            this.mContext = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View tItemView;
            int color;
            if (convertView != null) {
                tItemView = convertView;
            } else {
                tItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_photo_directory_item, parent, false);
            }
            DirectoryItem tItem = (DirectoryItem) getItem(position);
            if (tItem.isSelected) {
                color = this.mContext.getResources().getColor(R.color.color_black_05);
            } else {
                color = this.mContext.getResources().getColor(R.color.transparent);
            }
            tItemView.setBackgroundColor(color);

            ImageLoadUtils.loadImage(mContext, ((ImageItem) tItem.mImages.get(0)).getData(), (ImageView) tItemView.findViewById(R.id.iv_photo_directory_cover));
            TextView tvName = tItemView.findViewById(R.id.tv_photo_directory_name);
            tvName.setText(tItem.mDirectoryName);
            TextView tvNum = tItemView.findViewById(R.id.tv_photo_directory_num);
            tvNum.setText(tItem.mImages.size() + "");
            return tItemView;
        }
    }

    public String getTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_img_picker;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        isWhite = true;
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onInitView(Bundle bundle) {
        mTitleLayout = findViewById(R.id.titleLayout);
        mMaxCount = getIntent().getIntExtra(ImgPickerActivity.EXTRA_MAX_COUNT,9);
        mTitleLayout.setOnLeftClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTitleLayout.setOnRightTvClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImgPickerActivity activity = ImgPickerActivity.this;
                if (ImgPickerActivity.this.mCheckedItems == null || ImgPickerActivity.this.mCheckedItems.size() <= 0) {
                    activity.setResult(RESULT_CANCELED);
                } else {
//                    activity.setResult(RESULT_OK, new Intent().putParcelableArrayListExtra("data", ImgPickerActivity.this.mCheckedItems));
                    if (mMaxCount == 1){
                        Intent datat = new Intent();
                        datat.putExtra("photo", mCheckedItems.get(0).getData());
                        KLog.e("---头像" + mCheckedItems.get(0).getData());
                        setResult(RESULT_OK, datat);
                    }

                }
                finishOverAnim();
            }
        });
        mTitleLayout.getTvTitle().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                otherPhotos();
            }
        });

        initData(bundle);
        showGalleryView();
        preparePopupWindow();
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mLastFragment = savedInstanceState.getString(KEY_LAST_FRAGMENT, "");
            this.mCheckedItems = savedInstanceState.getParcelableArrayList(KEY_CHECKED_ITEMS);
        }
    }

    private void changeTitleArrow() {
        Drawable tArrow;
        if (this.isTitleArrowUp)
            tArrow = getResources().getDrawable(R.drawable.icon_arrow_up);
        else
            tArrow = getResources().getDrawable(R.drawable.icon_arrow_down);
        tArrow.setBounds(0, 0, tArrow.getMinimumWidth(), tArrow.getMinimumHeight());
        mTitleLayout.getTvTitle().setCompoundDrawablePadding(DensityUtil.dip2px(3));
        mTitleLayout.getTvTitle().setCompoundDrawables(null, null, tArrow, null);
    }

    public void otherPhotos() {
        boolean z = false;
        ImgPickerActivity.this.mPopupWindow.update();
        ImgPickerActivity.this.mPopupWindow.showAsDropDown(mTitleLayout, 0, 0);
        ImgPickerActivity.this.lvPhotoDirectory.post(new Runnable() {
            public void run() {
                ImgPickerActivity.this.lvPhotoDirectory.requestFocusFromTouch();
                ImgPickerActivity.this.lvPhotoDirectory.setSelection(ImgPickerActivity.this.mPhotoDirectorySelection);
            }
        });
        ImgPickerActivity imagePickerActivity = ImgPickerActivity.this;
        if (!ImgPickerActivity.this.isTitleArrowUp) {
            z = true;
        }
        imagePickerActivity.isTitleArrowUp = z;
        changeTitleArrow();
    }

    private void preparePopupWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.popup_album, null);
        this.mAdapter = new PhotoDirectoryAdapter(this);
        this.lvPhotoDirectory = (ListView) popupView.findViewById(R.id.lvPhotoDirectory);
        this.lvPhotoDirectory.setAdapter(this.mAdapter);
        this.mPopupWindow = new PopupWindow(popupView, -1, (ScreenUtils.getScreenHeight() * 6) / 10, true);
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.lvPhotoDirectory.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DirectoryItem item = (DirectoryItem) ImgPickerActivity.this.mAdapter.getItem(position);
                ((ImgGalleryFragment) ImgPickerActivity.this.getFragmentManager().findFragmentByTag(ImgGalleryFragment.TAG)).onPhotoDirectoryChanged(item.mImages);
                mTitleLayout.setTitleText(item.mDirectoryName);
                item.isSelected = true;
                ImgPickerActivity.this.mLastSelectedDirectory.isSelected = false;
                ImgPickerActivity.this.mLastSelectedDirectory = item;
                ImgPickerActivity.this.mPhotoDirectorySelection = position;
                ImgPickerActivity.this.mPopupWindow.dismiss();
            }
        });
        this.mPopupWindow.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                ImgPickerActivity.this.isTitleArrowUp = false;
                changeTitleArrow();
            }
        });
        changeTitleArrow();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_LAST_FRAGMENT, this.mLastFragment);
        outState.putParcelableArrayList(KEY_CHECKED_ITEMS, this.mCheckedItems);
    }

    private int showGalleryView() {
        ImgGalleryFragment tFragment = (ImgGalleryFragment) getFragmentManager().findFragmentByTag(ImgGalleryFragment.TAG);
        if (tFragment == null) {
            tFragment = new ImgGalleryFragment();
        }
        tFragment.setCheckedController(this);
        tFragment.setLoadFinishListener(this);
        this.mLastFragment = ImgGalleryFragment.TAG;
        return getFragmentManager().beginTransaction().replace(getContentPaneId(), tFragment, ImgGalleryFragment.TAG).addToBackStack(ImgGalleryFragment.TAG).commitAllowingStateLoss();
    }

    public boolean isItemChecked(ImageItem item) {
        return this.mCheckedItems.contains(item);
    }

    public void onItemCheckedChange(ImageItem item, boolean checked) {
        if (checked && this.mCheckedItems.size() == mMaxCount) {
            showToast("最多只能选" + mMaxCount + "张图片");
        } else if (!checked || item.getSize() <= 10.0f) {
            if (checked) {
                this.mCheckedItems.add(item);
            } else {
                this.mCheckedItems.remove(item);
            }
            for (DataSetObserver observer : this.mCheckedSetObservers) {
                observer.onChanged();
            }
        }
    }

    @Override
    public void onItemViewed(int i) {

    }

    public int getCheckedCount() {
        return this.mCheckedItems.size();
    }

    public ArrayList<ImageItem> getCheckedItemList() {
        return this.mCheckedItems;
    }

    public void addCheckedSetObserver(DataSetObserver observer) {
        this.mCheckedSetObservers.add(observer);
    }

    public void removeCheckedSetObserver(DataSetObserver observer) {
        this.mCheckedSetObservers.remove(observer);
    }


    public int getContentPaneId() {
        return R.id.frame_layout;
    }

    public void onLoadFinish(List<ImageItem> list) {
        this.mAdapter.clear();
        if (!list.isEmpty()) {
            List<DirectoryItem> photos = new ArrayList();
            HashMap<String, List<ImageItem>> tDirectoryMap = new HashMap();
            for (int i = 0; i < list.size(); i++) {
                String fullPath = ((ImageItem) list.get(i)).getData();
                int index = fullPath.lastIndexOf(47);
                if (index >= 0) {
                    String path = fullPath.substring(0, index);
                    if (tDirectoryMap.containsKey(path)) {
                        ((List) tDirectoryMap.get(path)).add(list.get(i));
                    } else {
                        List<ImageItem> tItems = new ArrayList();
                        tItems.add(list.get(i));
                        tDirectoryMap.put(path, tItems);
                    }
                }
            }
            this.mLastSelectedDirectory = new DirectoryItem("All pic", list, true);
            photos.add(this.mLastSelectedDirectory);
            for (String key : tDirectoryMap.keySet()) {
                photos.add(new DirectoryItem(key.substring(key.lastIndexOf(47) + 1), (List) tDirectoryMap.get(key), false));
            }
            this.mAdapter.addAll(photos);
        }
    }

    @Override
    protected void onDestroy() {
        if (ImgPickerActivity.this.mCheckedItems != null && !ImgPickerActivity.this.mCheckedItems.isEmpty()) {

            List<String> mList = new ArrayList<>();
            for (int i = 0; i < mCheckedItems.size(); i++) {
                mList.add(mCheckedItems.get(i).getData());

            }
            String listString = mList.toString();
            if (listString.length() > 2){
                listString = listString.substring(1,listString.length()-1);
            }
//            EventBus.getDefault().post(new PubEvent("photo", listString));


        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
