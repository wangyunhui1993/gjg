package com.demo;

import com.demo.server.DBServiceImpl;
import com.demo.server.OnServerChangeListener;
import com.demo.server.ServerPresenter;
import com.demo.utils.SendMsg;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import Utils.DiaryDao;
import Utils.SqliteDBHelper;
import entity.Stu;
public class MainActivity extends ReactActivity implements OnServerChangeListener {
    private ServerPresenter serverPresenter;
    private ReactContext reactContext;
    private DiaryDao diaryDao;
    private SqliteDBHelper sqliteDBHelper;
    private SQLiteDatabase db;
    private DBServiceImpl dbService;
	private static final String TAG = "MainActivity";
//    public MainActivity(ReactApplicationContext reactContext) {
//        super();
//        //给上下文对象赋值
//        SendMsg.myContext=reactContext;
//    }
    /**
     *
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 程序创建的时候执行
        Log.i(TAG, "程序MainActivity入口");
//        reactContext = (ReactContext) getApplicationContext();
        super.onCreate(savedInstanceState);


        serverPresenter = new ServerPresenter(this, this);
        serverPresenter.startServer(MainActivity.this);




        //数据库
//        try {
//            diaryDao = new DiaryDao(MainActivity.this);
//            initDatabase();
//            db = sqliteDBHelper.getReadableDatabase();
//            Log.i(TAG, "onCreate: 数据库启动");
////            db = this.openOrCreateDatabase("chaek123.db", MODE_PRIVATE, null);
////            this.deleteDatabase("chaek123.db");
////            db.close();
//        } catch (SQLException se) {
//            Toast.makeText(this, se.getMessage(), Toast.LENGTH_LONG).show();
//        }

//        dbService = new DBServiceImpl(MainActivity.this);
//        //新增
//            dbService.add("xiaozhang","001");
//            dbService.add2("xiaoliu","002");
//        //查询
//        List<Stu> stuArrayList = dbService.select1(0,10,"");
//        Log.i("select","查询到数据: "+stuArrayList.toString());
//        //修改源数据
//        stuArrayList.get(0).setSnumber("003");
//        Log.i("select","修改源数据: "+stuArrayList.toString());
//        dbService.update1(stuArrayList);
//        //修改后再次查询一次
//        List<Stu> stuArrayList2 = dbService.select1(0,10,"");
//        Log.i("select","修改数据后,查询到数据: "+stuArrayList2.toString());
//        //删除数据
//        dbService.delete1("1");
//        //查询
//        List<Stu> stuArrayList3 = dbService.select1(0,10,"");
//        Log.i("select","删除数据后,查询到数据: "+stuArrayList3.toString());
    }

    private void initDatabase() {
        // 创建数据库对象
        sqliteDBHelper = new SqliteDBHelper(MainActivity.this);
        sqliteDBHelper.getWritableDatabase();
        Log.i(TAG, "InitDatabase: 数据库启动");
    }




    @Override
    protected String getMainComponentName() {
        return "demo";
    }

    @Override
    public void onServerStarted(String ipAddress) {
        Log.i(TAG, "服务器开始运行了，IPdeAddress: " + ipAddress);
//        SendMsg.myContext= (ReactContext) getApplicationContext();
//        new SendMsg().send("testData","收到客户端发来的消息了");
    }

    @Override
    public void onServerStopped() {
        Log.i(TAG, "服务器停止了");
    }

    @Override
    public void onServerError(String errorMessage) {
        Log.i(TAG, "服务器出错了");
    }


    @Override
    protected void onDestroy() {
        //退出当前Activity时被调用,调用之后Activity就结束了
        super.onDestroy();
        Log.i(TAG, "Activity销毁了");
		serverPresenter.stopServer(MainActivity.this);
//         serverPresenter.unregister(this);
//         serverPresenter = null;
    }
}


