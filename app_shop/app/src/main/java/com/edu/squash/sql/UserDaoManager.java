package com.edu.squash.sql;

import com.j256.ormlite.dao.Dao;
import com.lynn.base.beans.UserBean;

/**
 * Created by ABC

 * note
 * 用户表管理类
 *
 */
public class UserDaoManager {

    private static UserDaoManager mInstance;

    public synchronized static UserDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new UserDaoManager();
        }
        return mInstance;
    }


    public UserBean getFromDb(Object userName) {
        try {
            Dao<UserBean, Integer> dao = SQLiteHelper.getInstance().getUserDao();
            UserBean model = dao.queryBuilder().where().eq("userName", userName).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public UserBean getUerID(int uID) {
        try {
            Dao<UserBean, Integer> dao = SQLiteHelper.getInstance().getUserDao();
            UserBean model = dao.queryBuilder().where().eq("id", uID).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    public UserBean getFromDb(Object userName, String password) {
        try {
            Dao<UserBean, Integer> dao = SQLiteHelper.getInstance().getUserDao();
            UserBean model = dao.queryBuilder().where().eq("userName", userName).and().eq("password", password).queryForFirst();

            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }






    /**
     * 私有方法
     * <p>
     * 添加用户注册
     *
     * @param bean
     * @return
     */
    public boolean addUserToList(UserBean bean) {
        try {
            Dao<UserBean, Integer> dao = SQLiteHelper.getInstance().getUserDao();
            Dao.CreateOrUpdateStatus status = dao.createOrUpdate(bean);
            if (status.getNumLinesChanged() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
