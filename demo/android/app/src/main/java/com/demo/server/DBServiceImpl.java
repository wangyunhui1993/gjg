package com.demo.server;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import Utils.SqliteDBHelper;
import Utils.StringUtil;
import entity.Stu;

public class DBServiceImpl implements DBService{

    private SqliteDBHelper sqliteDBHelper;
    private SQLiteDatabase db;

    public DBServiceImpl(Context context) {
        this.sqliteDBHelper = new SqliteDBHelper(context);
        //db = sqliteDBHelper.getWritableDatabase();
        //System.out.println("数据库创建成功");
    }

    private void doConnect() {
        if (db == null || ! db.isOpen()) {
            db = sqliteDBHelper.getWritableDatabase();
        }
    }

    /**
     * 添加方法  第一种实现方法
     * @param sname  学生名
     * @param snumber 学号
     */
    public boolean add(String sname,String snumber){
        doConnect();
        db.beginTransaction();  //开始事务
        try {
            //实例化常量值
            ContentValues cValue = new ContentValues();
            //添加用户名
            cValue.put("sname",sname);
            //添加密码
            cValue.put("snumber",snumber);
            //调用insert()方法插入数据
            db.insert("stu_table",null,cValue);
            db.setTransactionSuccessful();  //设置事务成功完成
            return true;
        }catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();    //结束事务
            db.close();
        }
    }
    /**
     * 添加方法  第二次实现方法
     * @param sname  学生名
     * @param snumber 学号
     */
    public boolean add2(String sname,String snumber){
        doConnect();
        db.beginTransaction();  //开始事务
        try {
            String strSQL = "insert into stu_table(sname, snumber) values('" + sname + "', '" + snumber + "')";
            System.out.println("strSQL > " + strSQL);
            db.execSQL(strSQL);
            db.setTransactionSuccessful();  //设置事务成功完成
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();    //结束事务
            db.close();
        }
    }

    /**
     * 查询sql
     * @param page  页
     * @param size  每页几条
     * @param where  条件
     * @return
     */
    public List<Stu> select1(int page, int size, String where){
        //如果没传size 就默认15
        if (size <= 0) size = 15;
        //判断数据库连接是否打开
        doConnect();
        //拼接sql
        StringBuffer sql = new StringBuffer();
        sql.append("select t1.* from stu_table t1 ");
        if (StringUtil.isNotEmpty(where)) {
            sql.append(" WHERE ").append(where);
        }
        sql.append(" limit " + page * size + "," + size);
        String strSQL = sql.toString();
        System.out.println("SQL: " + strSQL);
        //执行sql
        Cursor cursor = db.rawQuery(strSQL, null);
        //移动游标
        cursor.moveToFirst();
        List<Stu> rtnlist = new ArrayList<Stu>();
        while (!cursor.isAfterLast()) {
            Stu stu = new Stu();
            stu.set_id(cursor.getInt(0));
            stu.setSname(cursor.getString(1));
            stu.setSnumber(cursor.getString(2));
            rtnlist.add(stu);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return rtnlist;
    }

    /**
     * 修改接口
     * @param stuList  修改后的数据
     * @return
     */
    public boolean update1(List<Stu> stuList){
        doConnect();
        db.beginTransaction();  //开始事务
        try {
            for (Stu stu:stuList) {
                StringBuffer sb = new StringBuffer();
                sb.append("update stu_table set snumber = ' ").append(stu.getSnumber()).append("'")
                        .append(" where _id = ' ").append(stu.get_id()).append(" ' ");
                System.out.println("strSQL > " + sb.toString());
                db.execSQL(sb.toString());
            }
            db.setTransactionSuccessful();  //设置事务成功完成
            return true;
        }catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();    //结束事务
            db.close();
        }
    }

    /**
     * 删除
     * @param id id
     * @return
     */
    public boolean delete1(String id){
        doConnect();
        db.beginTransaction();  //开始事务
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("delete from stu_table where _id = '").append(id).append("'");
            System.out.println("strSQL > " + sb.toString());
            db.execSQL(sb.toString());
            db.setTransactionSuccessful();  //设置事务成功完成
            return true;
        }catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }finally {
            db.endTransaction();    //结束事务
            db.close();
        }
    }
}
