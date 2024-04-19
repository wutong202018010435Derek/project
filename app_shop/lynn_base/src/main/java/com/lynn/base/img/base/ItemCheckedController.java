package com.lynn.base.img.base;

import android.database.DataSetObserver;

import java.util.ArrayList;

public interface ItemCheckedController<T> {
    void addCheckedSetObserver(DataSetObserver dataSetObserver);

    int getCheckedCount();

    ArrayList<T> getCheckedItemList();

    boolean isItemChecked(T t);

    void onItemCheckedChange(T t, boolean z);

    void onItemViewed(int i);

    void removeCheckedSetObserver(DataSetObserver dataSetObserver);
}
