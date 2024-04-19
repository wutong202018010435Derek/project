package com.lynn.base.img;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lynn.base.R;
import com.lynn.base.img.base.ItemCheckedController;
import com.lynn.base.img.base.SqlCriteriaBuilder;
import com.lynn.base.img.bean.ImageItem;
import com.lynn.base.uitls.DensityUtil;
import com.lynn.base.uitls.ScreenUtils;


import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class ImgGalleryFragment extends Fragment implements OnItemClickListener, OnScrollListener {

    public static final String TAG = ImgGalleryFragment.class.getSimpleName();

    private boolean isLoadAll = true;
    private GalleryAdapter mAdapter;
    private ItemCheckedController<ImageItem> mCheckedController;
    private DataSetObserver mCheckedSetObserver;
    private GridView mGridView;
    private List<ImageItem> mImageList;
    private LoadFinishListener mLoadFinishListener;
    private LoaderCallbacks<Cursor> mLoaderCallback = new LoaderCallbacks<Cursor>() {
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String where = new SqlCriteriaBuilder().where("_size>0").andWhere("mime_type=?").orWhere("mime_type=?").build();
            String[] whereArgs = new String[]{"image/jpeg", "image/png"};
            switch (id) {
                case 0:
                    return new CursorLoader(ImgGalleryFragment.this.getActivity(), Media.EXTERNAL_CONTENT_URI, null, where, whereArgs, "date_added DESC");
                case 1:
                    return new CursorLoader(ImgGalleryFragment.this.getActivity(), Media.EXTERNAL_CONTENT_URI, null, where, whereArgs, "date_added DESC LIMIT 200");
                default:
                    return null;
            }
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (ImgGalleryFragment.this.mImageList == null || ImgGalleryFragment.this.isLoadAll) {
                switch (loader.getId()) {
                    case 0:
                        ImgGalleryFragment.this.isLoadAll = false;
                        ImgGalleryFragment.this.mParseImagesTask = new ParseImagesTask(ImgGalleryFragment.this);
                        ImgGalleryFragment.this.mParseImagesTask.execute(new Cursor[]{data});
                        break;
                    case 1:
                        ImgGalleryFragment.this.mImageList = ImgGalleryFragment.getImageByCursor(data);
                        ImgGalleryFragment.this.mAdapter.addAll(ImgGalleryFragment.this.mImageList);
                        break;
                }
            }
        }

        public void onLoaderReset(Loader<Cursor> loader) {
        }
    };
    private ParseImagesTask mParseImagesTask;

    private class CheckedSetObserver extends DataSetObserver {
        private CheckedSetObserver() {
        }

        public void onChanged() {
            ImgGalleryFragment.this.mAdapter.notifyDataSetChanged();
        }
    }

    private static class GalleryAdapter extends ArrayAdapter<ImageItem> {
        private ItemCheckedController<ImageItem> mImageController;
        private final int mItemMaxSize;

        public GalleryAdapter(Context context) {
            super(context, 0);
            this.mItemMaxSize = (ScreenUtils.getScreenWidth() - DensityUtil.dip2px(14)) / 4;
            if (getContext() instanceof ItemCheckedController)
                mImageController = (ItemCheckedController) getContext();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView;
            if (convertView != null) {
                itemView = convertView;
            } else {
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_image, parent, false);
            }
            ImageItem item = (ImageItem) getItem(position);
            ImageView img = itemView.findViewById(R.id.image);

            RequestOptions options = new RequestOptions()
                    .override(this.mItemMaxSize, this.mItemMaxSize)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            if (item != null)
                Glide.with(getContext()).load(item.getData()).apply(options).into(img);

            View view = itemView.findViewById(R.id.checkbox);
            if (!mImageController.isItemChecked(item)) {
                view.setVisibility(View.GONE);
            } else
                view.setVisibility(View.VISIBLE);
            return itemView;
        }
    }

    public interface LoadFinishListener {
        void onLoadFinish(List<ImageItem> list);
    }

    private static class ParseImagesTask extends AsyncTask<Cursor, Void, List<ImageItem>> {
        private SoftReference<ImgGalleryFragment> mImageGalleryFragment;

        ParseImagesTask(ImgGalleryFragment imageGalleryFragment) {
            this.mImageGalleryFragment = new SoftReference(imageGalleryFragment);
        }

        /* Access modifiers changed, original: protected|varargs */
        public List<ImageItem> doInBackground(Cursor... params) {
            try {
                return ImgGalleryFragment.getImageByCursor(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(List<ImageItem> imageItems) {
            if (this.mImageGalleryFragment.get() != null && imageItems != null) {
                if (imageItems.size() > 200) {
                    ((ImgGalleryFragment) this.mImageGalleryFragment.get()).mAdapter.addAll(imageItems.subList(200, imageItems.size()));
                }
                if (((ImgGalleryFragment) this.mImageGalleryFragment.get()).mLoadFinishListener != null) {
                    ((ImgGalleryFragment) this.mImageGalleryFragment.get()).mLoadFinishListener.onLoadFinish(imageItems);
                }
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAdapter = new GalleryAdapter(getActivity());
        this.mCheckedSetObserver = new CheckedSetObserver();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pic_gallery, container, false);
        this.mGridView = (GridView) rootView.findViewById(R.id.grid_view);
        this.mGridView.setOnItemClickListener(this);
        this.mGridView.setOnScrollListener(this);
        this.mGridView.setAdapter(this.mAdapter);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, this.mLoaderCallback);
        getLoaderManager().initLoader(0, null, this.mLoaderCallback);
    }

    public void onStart() {
        super.onStart();
        this.mCheckedController.addCheckedSetObserver(this.mCheckedSetObserver);
    }

    public void onStop() {
        super.onStop();
        this.mCheckedController.removeCheckedSetObserver(this.mCheckedSetObserver);
    }

    private static ArrayList<ImageItem> getImageByCursor(Cursor data) {
        ArrayList<ImageItem> list = new ArrayList<>();
        if (data == null || !data.moveToFirst()) {
            return list;
        }
        do {
            try {
                list.add(new ImageItem(data));
            } catch (Exception e) {
            }
        } while (data.moveToNext());
        return list;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.mCheckedController.onItemCheckedChange(this.mAdapter.getItem(position), !view.findViewById(R.id.checkbox).isShown());
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        try {
            if (scrollState == 2) {
                Glide.with(getActivity()).pauseRequests();
            } else {
                Glide.with(getActivity()).resumeRequests();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public void setLoadFinishListener(LoadFinishListener loadFinishListener) {
        this.mLoadFinishListener = loadFinishListener;
    }

    public void setCheckedController(ItemCheckedController<ImageItem> checkedController) {
        this.mCheckedController = checkedController;
    }

    public void onPhotoDirectoryChanged(List<ImageItem> list) {
        this.mAdapter.clear();
        this.mAdapter.addAll(list);
        this.mAdapter.notifyDataSetChanged();
    }

    public void onDestroy() {
        if (this.mParseImagesTask != null && this.mParseImagesTask.getStatus().equals(Status.RUNNING)) {
            this.mParseImagesTask.cancel(true);
        }
        super.onDestroy();
    }
}
