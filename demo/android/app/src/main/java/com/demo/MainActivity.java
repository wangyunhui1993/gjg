package com.demo;

import com.demo.server.DBServiceImpl;
import com.demo.server.OnServerChangeListener;
import com.demo.server.ServerPresenter;
import com.demo.utils.SendMsg;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import android.view.KeyEvent; // <--- import
    import com.github.kevinejohn.keyevent.KeyEventModule; // <--- import
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
	
	
	
	
	
	
	
	
	@Override  // <--- Add this method if you want to react to keyDown
      public boolean onKeyDown(int keyCode, KeyEvent event) {

        // A. Prevent multiple events on long button press
        //    In the default behavior multiple events are fired if a button
        //    is pressed for a while. You can prevent this behavior if you
        //    forward only the first event:
        //        if (event.getRepeatCount() == 0) {
        //            KeyEventModule.getInstance().onKeyDownEvent(keyCode, event);
        //        }
        //
        // B. If multiple Events shall be fired when the button is pressed
        //    for a while use this code:
        //        KeyEventModule.getInstance().onKeyDownEvent(keyCode, event);
        //
        // Using B.
        KeyEventModule.getInstance().onKeyDownEvent(keyCode, event);

        // There are 2 ways this can be done:
        //  1.  Override the default keyboard event behavior
        //    super.onKeyDown(keyCode, event);
        //    return true;

        //  2.  Keep default keyboard event behavior
        //    return super.onKeyDown(keyCode, event);

        // Using method #1 without blocking multiple
        super.onKeyDown(keyCode, event);
        return true;
      }

      @Override  // <--- Add this method if you want to react to keyUp
      public boolean onKeyUp(int keyCode, KeyEvent event) {
        KeyEventModule.getInstance().onKeyUpEvent(keyCode, event);

        // There are 2 ways this can be done:
        //  1.  Override the default keyboard event behavior
        //    super.onKeyUp(keyCode, event);
        //    return true;

        //  2.  Keep default keyboard event behavior
        //    return super.onKeyUp(keyCode, event);

        // Using method #1
        super.onKeyUp(keyCode, event);
        return true;
      }

      @Override
      public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
          KeyEventModule.getInstance().onKeyMultipleEvent(keyCode, repeatCount, event);
          return super.onKeyMultiple(keyCode, repeatCount, event);
      }
	
	
	
	
	
	
	
}


