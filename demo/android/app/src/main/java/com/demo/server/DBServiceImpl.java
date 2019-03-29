package com.demo.server;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Utils.SqliteDBHelper;
import Utils.StringUtil;
import android_serialport_api.SerialPort;
import entity.Stu;
import entity.User;

public class DBServiceImpl implements DBService{

    private SqliteDBHelper sqliteDBHelper;
    private SQLiteDatabase db;
    private SerialPort serialPort;

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

    /**
     * 刷卡
     * @param number  卡号
     * @param cq  存取状态 0 存  1 取
     * @return
     */
    public String shuaka(String number,int cq){
        doConnect();
        //获取数据,因为数据库还没完全确定.所以使用假数据//:TODO: 根据卡号获取用户信息
        StringBuffer sql = new StringBuffer();
        sql.append("select t1.* from userInfo t1 ");
//        sql.append(number);
//        sql.append("  '");
        String strSQL = sql.toString();
        System.out.println("SQL: " + strSQL);
        //执行sql
        Cursor cursor = db.rawQuery(strSQL, null);
        //移动游标
        cursor.moveToFirst();
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setCardNumber(cursor.getString(1));
        user.setName(cursor.getString(2));
        user.setPartment(cursor.getString(3));
        user.setLockFlag(cursor.getInt(4));
        user.setUserId(cursor.getString(5));
        user.setBox(cursor.getString(6));
        Log.i("user",user.toString());
        System.out.println("cq = " + cq);
        System.out.println(cq == 1);
        System.out.println(cq == 0);
        System.out.println(user.getBox() == "" ||  "".equals(user.getBox()));
        System.out.println(user.getBox() != "" ||  !"".equals(user.getBox()));
        cursor.close();

        String ret = "默认";
        if (user != null){
            //0 存
            if(cq == 0){
                if (user.getBox() == "" ||  "".equals(user.getBox())){
                    //组装并发送plc信号
                    try{
                        serialPort = new SerialPort(new File("/dev/ttyAMA0"),9600,0);
                        //            serialPort.read();
                        //TODO: 这里需要打开随机柜门(随机柜门需要读取所有柜门状态表),并将柜门号记录返回给前台
                        serialPort.sendHex("3A30313035303530304646303046360D0A");
                        //            serialPort.read();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if (serialPort != null) {
                            serialPort.close();
                        }
                    }
                    ret =  "打开柜门成功,柜门号:1，存放手机后请记得关闭柜门";
                    StringBuffer sb = new StringBuffer();
                    sb.append("update userInfo set box = '1' ");
                    System.out.println("strSQL > " + sb.toString());
                    db.execSQL(sb.toString());
                    db.close();
                }
                else {
                    ret = "您已经存放了一部手机，请点击取，再刷卡";
                }
            }
            //1 取
            if(cq == 1){
                if (user.getBox() != "" ||  !"".equals(user.getBox())){
                    //组装并发送plc信号
                    try{
                        serialPort = new SerialPort(new File("/dev/ttyAMA0"),9600,0);
                        //            serialPort.read();
                        //TODO: 这里需要根据user.getBox()的值来打开柜门
                        serialPort.sendHex("3A30313035303530304646303046360D0A");
                        //            serialPort.read();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if (serialPort != null) {
                            serialPort.close();
                        }
                    }
                    ret =  "打开柜门成功,柜门号:1，取出手机后请关闭柜门";
                    StringBuffer sb = new StringBuffer();
                    sb.append("update userInfo set box = '' ");
                    System.out.println("strSQL > " + sb.toString());
                    db.execSQL(sb.toString());
                    db.close();
                }else {
                    ret = "未找到您存放手机的信息，如果您确认已存放手机，请联系管理员";
                }
            }
            return ret;
        }else {
            return "该卡号没有对应用户";
        }
    }
}
