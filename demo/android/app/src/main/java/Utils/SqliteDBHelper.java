package Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "chaek123.db";
    //数据表版本
    public static final int DB_VERSION = 2;

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
        // 用户信息表
        String createSql = "CREATE TABLE userInfo (id integer primary key autoincrement, cardNumber VARCHAR(30), " +
                "name VARCHAR(32), partment VARCHAR(255), lockFlag integer, userId VARCHAR(30), box VARCHAR(5))";
        db.execSQL(createSql);
        //给一个默认用户admin
        String strSQL = "insert into userInfo(cardNumber, name, partment, lockFlag, userId, box) values('" + 2072485831 + "', '" + "admin" + "' ,'"+"A部门"+"','"+ 0 +"', '"+"001"+"', '"+1+"' )";
        System.out.println("strSQL > " + strSQL);
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                // 用户信息表
                String createSql = "CREATE TABLE userInfo (id integer primary key autoincrement, cardNumber VARCHAR(30), " +
                        "name VARCHAR(32), partment VARCHAR(255), lockFlag integer, userId VARCHAR(30), box VARCHAR(5))";
                db.execSQL(createSql);
                //给一个默认用户admin
                String strSQL = "insert into userInfo(cardNumber, name, partment, lockFlag, userId, box) values('" + 2072485831 + "', '" + "admin" + "' ,'"+"A部门"+"','"+ 0 +"', '"+"001"+"', '"+1+"' )";
                System.out.println("strSQL > " + strSQL);
                db.execSQL(strSQL);
            default:
        }
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
