package com.demo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class TestOneService  extends Service {
    public void aa(){
        Log.i("Kathy", "onBind - Thread ID = ");
//        Toast.makeText(TestOneService.this, "显示Toast", Toast.LENGTH_SHORT).show();
        Log.e("Kathy","onCreate - Thread ID = ");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
