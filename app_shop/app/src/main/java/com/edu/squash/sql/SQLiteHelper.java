package com.edu.squash.sql;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.edu.squash.app.MyApp;
import com.edu.squash.bean.SysGoods;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.lynn.base.beans.UserBean;


/**
 * Created by ABC

 * note
 */
public class SQLiteHelper extends OrmLiteSqliteOpenHelper {

    private final String LOG_TAG = getClass().getSimpleName();

    // 版本号
    private static final int DATABASE_VERSION = 1;

    private static SQLiteHelper mInstance;

    private Dao<UserBean, Integer> mUsertDao = null;
    private Dao<SysGoods, Integer> mGoodsDao = null;






    private SQLiteHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }

    private static String getUserDatabaseName() {
        return "edu_shop" + ".db";
    }

    public synchronized static SQLiteHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SQLiteHelper(MyApp.getApp());
        }

        return mInstance;
    }

    /**
     * 创建SQLite数据库
     */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserBean.class);//创建用户表
            TableUtils.createTable(connectionSource, SysGoods.class);//


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新SQLite数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            onCreate(sqliteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public Dao<SysGoods, Integer> getSysGoodsDao() throws SQLException {
        if (mGoodsDao == null) {
            try {
                mGoodsDao = getDao(SysGoods.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mGoodsDao;
    }

    public Dao<UserBean, Integer> getUserDao() throws SQLException {
        if (mUsertDao == null) {
            try {
                mUsertDao = getDao(UserBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mUsertDao;
    }



    public synchronized void closeDB() {
        if (mInstance != null) {
            try {
                SQLiteDatabase db = mInstance.getWritableDatabase();
                db.close();
//                GoodsCollectManagerV3.getInstance().clearCacheData();
//                BuTieManager.getInstance().clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mInstance = null;
        }
    }

}
