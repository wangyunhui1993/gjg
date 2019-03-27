package Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wangyh on 2018/1/1.
 */

public class DiaryDao {
    private SqliteDBHelper sqliteDBHelper;
    private SQLiteDatabase db;

    // 重写构造方法
    public DiaryDao(Context context) {
        this.sqliteDBHelper = new SqliteDBHelper(context);
        //db = sqliteDBHelper.getWritableDatabase();
    }

    public void dbConnect() {
        if (db == null || ! db.isOpen()) {
            db = sqliteDBHelper.getWritableDatabase();
        }
    }

    // 读操作
    public String execQuery(final String strSQL) {
        try {
            System.out.println("strSQL>" + strSQL);
            // Cursor相当于JDBC中的ResultSet
            Cursor cursor = db.rawQuery(strSQL, null);
            // 始终让cursor指向数据库表的第1行记录
            cursor.moveToFirst();
            // 定义一个StringBuffer的对象，用于动态拼接字符串
            StringBuffer sb = new StringBuffer();
            // 循环游标，如果不是最后一项记录
            while (!cursor.isAfterLast()) {
                sb.append(cursor.getInt(0) + "/" + cursor.getString(1) + "/"
                        + cursor.getString(2) + "/" + cursor.getString(3) + "/"
                        + cursor.getString(4)+"#");
                //cursor.getColumnNames()
                //cursor游标移动
                cursor.moveToNext();
            }
            db.close();
            return sb.deleteCharAt(sb.length()-1).toString();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }

    }

    // 写操作
    public boolean execOther(final String strSQL) {
        dbConnect();
        db.beginTransaction();  //开始事务
        try {
            System.out.println("strSQL" + strSQL);
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
}
