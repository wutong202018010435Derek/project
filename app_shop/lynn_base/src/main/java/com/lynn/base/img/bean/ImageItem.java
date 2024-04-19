package com.lynn.base.img.bean;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.File;

public class ImageItem implements Parcelable {
    public static final Creator<ImageItem> CREATOR = new Creator<ImageItem>() {
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        public ImageItem[] newArray(int size) {
            return new ImageItem[size];
        }
    };
    private final String data;
    private final String dateAdded;
    private final String displayName;
    private final float size;

    public ImageItem(Cursor cursor) {
        this.displayName = cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
        this.data = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
        this.dateAdded = cursor.getString(cursor.getColumnIndexOrThrow("date_added"));
        this.size = (cursor.getFloat(cursor.getColumnIndexOrThrow("_size")) / 1024.0f) / 1024.0f;
        if (!checkData()) {
            throw new IllegalArgumentException();
        }
    }

    protected ImageItem(Parcel in) {
        this.displayName = in.readString();
        this.data = in.readString();
        this.dateAdded = in.readString();
        this.size = in.readFloat();
    }

    private boolean checkData() {
        return !TextUtils.isEmpty(this.data) && new File(this.data).exists();
    }

    public String getData() {
        return this.data;
    }

    public float getSize() {
        return this.size;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.data.equals(((ImageItem) o).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.displayName);
        dest.writeString(this.data);
        dest.writeString(this.dateAdded);
        dest.writeFloat(this.size);
    }
}
