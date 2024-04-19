package com.edu.squash.sql;


import com.edu.squash.app.MyApp;
import com.edu.squash.bean.SysGoods;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.lynn.base.beans.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABC
 * on 2020/3/19
 * note
 */
public class GoodsDaoManager {

    private static GoodsDaoManager mInstance;

    public synchronized static GoodsDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GoodsDaoManager();
        }
        return mInstance;
    }


    public SysGoods getDataWithGoodsId(Object messageObjectId) {
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();
            SysGoods model = dao.queryBuilder().where().eq("id", messageObjectId).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public SysGoods getMyCollectGoods(String goodsUserId, String goodsId) {
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();
            SysGoods model = dao.queryBuilder().where().eq("goodsUserId", goodsUserId).and().eq("goodsId", goodsId).and().eq("goodsCollect", 1).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public SysGoods getMyShopGoods(String goodsUserId, String goodsId) {
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();
            SysGoods model = dao.queryBuilder().where().eq("goodsUserId", goodsUserId).and().eq("goodsId", goodsId).and().eq("goodsShop", 1).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<SysGoods> getMyShop(Object goodsUserId) {

        List<SysGoods> tempList = new ArrayList<>();
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();

            QueryBuilder<SysGoods, Integer> builder = dao.queryBuilder();
            builder.where().in("goodsUserId", goodsUserId).and().in("goodsShop", 1);

//                QueryBuilder<SysGoods, Integer> builder = dao.queryBuilder();

//
            builder.orderBy("releaseTime", false);
            List<SysGoods> query = builder.query();
            if (query != null && query.size() > 0) {
                for (SysGoods bean : query) {
                    tempList.add(bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempList;
    }


    public List<SysGoods> getMyCollect(Object goodsUserId) {

        List<SysGoods> tempList = new ArrayList<>();
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();

            QueryBuilder<SysGoods, Integer> builder = dao.queryBuilder();
            builder.where().in("goodsUserId", goodsUserId).and().in("goodsCollect", 1);

//                QueryBuilder<SysGoods, Integer> builder = dao.queryBuilder();

//
            builder.orderBy("releaseTime", false);
            List<SysGoods> query = builder.query();
            if (query != null && query.size() > 0) {
                for (SysGoods bean : query) {
                    tempList.add(bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tempList;
    }

    public boolean addDataToList(SysGoods bean) {
        try {
            if (getMyShopGoods(MyApp.getApp().getUser().getUserId(), bean.getGoodsId()) == null) {
                Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();
                Dao.CreateOrUpdateStatus status = dao.createOrUpdate(bean);
                if (status.getNumLinesChanged() > 0) {
                    return true;
                }
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean dellData(SysGoods bean) {
        try {
            Dao<SysGoods, Integer> dao = SQLiteHelper.getInstance().getSysGoodsDao();
            int status = dao.delete(bean);
            if (status > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
