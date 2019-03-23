package com.demo.handler;

import android.util.Log;

import com.demo.JsonUtil;
import com.demo.MainActivity;
import com.demo.utils.SendMsg;
import com.facebook.react.bridge.ReactApplicationContext;
import com.yanzhenjie.andserver.RequestHandler;
import com.yanzhenjie.andserver.RequestMethod;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.util.HttpRequestParser;

import org.apache.httpcore.HttpException;
import org.apache.httpcore.HttpRequest;
import org.apache.httpcore.HttpResponse;
import org.apache.httpcore.entity.StringEntity;
import org.apache.httpcore.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * 作者：leavesC
 * 时间：2018/4/5 16:30
 * 描述：https://github.com/leavesC/AndroidServer
 * https://www.jianshu.com/u/9df45b87cfdf
 */
public class JsonHandler implements RequestHandler {

    public JsonHandler(ReactApplicationContext reactContext) {
        super();
        //给上下文对象赋值
        SendMsg.myContext=reactContext;
    }


    @RequestMapping(method = {RequestMethod.POST})
    @Override
    public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        String content = HttpRequestParser.getContentFromBody(httpRequest);
        String decodeStr = URLDecoder.decode(content, "utf-8");
                Log.d("lct", "handle: content ="+decodeStr);
//        UserDateBean dateBean = JsonUtil.fromJson(decodeStr, UserDateBean.class);


//
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(decodeStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("客户端传来的消息", "请求内容"+httpRequest);
        Log.d("客户端传来的消息", "消息内容"+jsonObject);
//        MainActivity.getInstance().setImag(dateBean.getAvatarFile().toString());
//        if (jsonObject == null) {
//            jsonObject = new JSONObject();
//        }
//        try {
//            jsonObject.put("state", "success");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        new SendMsg().send("testData");


        StringEntity stringEntity = new StringEntity("收到demo", "utf-8");
        httpResponse.setStatusCode(200);
        httpResponse.setEntity(stringEntity);
    }

}
