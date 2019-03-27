package Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "chaek123.db";
    //数据表版本
    public static final int DB_VERSION = 1;
    //创建 stu_table 表的 sql 语句
    private static final String stu_table="create table stu_table(_id integer primary key autoincrement,sname text,snumber text)";

    public SqliteDBHelper(Context context) {
        // 传递数据库名与版本号给父类
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * onCreate 函数会在第一次创建数据库时被调用，因此，在这里执行创建 students 数据库的 SQL 语句。
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 在这里通过 db.execSQL 函数执行 SQL 语句创建所需要的表
        // 创建 students 表
        db.execSQL(stu_table);
        //实例化常量值
        ContentValues cValue = new ContentValues();
        //添加用户名
        cValue.put("sname","xiaozhang");
        //添加密码
        cValue.put("snumber","01007");
        //调用insert()方法插入数据
        db.insert("stu_table",null,cValue);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // 启动外键
            db.execSQL("PRAGMA foreign_keys = 1;");

            //或者这样写
            String query = String.format("PRAGMA foreign_keys = %s", "ON");
            db.execSQL(query);
        }
    }

}
