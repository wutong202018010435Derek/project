package com.edu.squash.bean;

import com.edu.squash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC
 * on 2024/3/3
 * note
 */
public class DataBean {

    public Integer imageRes;
    public String imageUrl;
    public String title;

    private String bImg;//
    private String bText;

    public String getbImg() {
        return bImg;
    }

    public void setbImg(String bImg) {
        this.bImg = bImg;
    }

    public String getbText() {
        return bText;
    }

    public void setbText(String bText) {
        this.bText = bText;
    }




    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();

        list.add(new DataBean(R.mipmap.image2, "极致简约,梦幻小屋", 3));
        list.add(new DataBean(R.mipmap.image3, "超级卖梦人", 3));
        list.add(new DataBean(R.mipmap.image4, "夏季新搭配", 1));
        list.add(new DataBean(R.mipmap.image5, "绝美风格搭配", 1));
        list.add(new DataBean(R.mipmap.image6, "微微一笑 很倾城", 3));
        return list;
    }
}
