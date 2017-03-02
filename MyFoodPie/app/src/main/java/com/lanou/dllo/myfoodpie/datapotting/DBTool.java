package com.lanou.dllo.myfoodpie.datapotting;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/*
         |              |
         | \            | \
         |   | | | | | |    | | | | |||||\
         |                          |||||||\
         |         ( )              ||||||||
         |                           |||||/
         |                  | | | | | |||/
         |    |             |          |
         |    |             |          |
       / |   | |            |          |\
      |      |/             |          \|
       \ |                  |
         |                  |
           \ | | | | | | | /
             |       |            <-----弱鸡
             |       |
             |       |
*/
public class DBTool {
    private static DBTool ourInstance;
    private QueryDataDao dataDao;

    public static DBTool getOurInstance() {
        if (ourInstance == null) {
            synchronized (DBTool.class) {
                if (ourInstance == null) {
                    ourInstance = new DBTool();
                }
            }
        }
        return ourInstance;
    }

    private DBTool() {
        dataDao = GlobalVariable.getDaoSession().getQueryDataDao();
    }

    public void insertQuery(QueryData queryData) {
        dataDao.insert(queryData);
    }

    public void insertAllQuery(List<QueryData> queryDataList) {
        dataDao.insertInTx(queryDataList);
    }

    public void deleteQuery(QueryData queryData) {
        dataDao.delete(queryData);
    }

    public void deleteAll() {
        dataDao.deleteAll();
    }

    public void deleteByText(String text) {
        DeleteQuery<QueryData> deleteQuery = dataDao.queryBuilder().where(QueryDataDao.Properties.Text.eq(text)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public List<QueryData> queryAll() {
        List<QueryData> list = dataDao.loadAll();
        return list;
    }

    public boolean isSave(QueryData queryData) {
        QueryBuilder<QueryData> builder = dataDao.queryBuilder().where(QueryDataDao.Properties.Text.eq(queryData.getText()));
        Long count = builder.buildCount().count();
        return count > 0 ? true : false;
    }

}
