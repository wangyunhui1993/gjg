import {Alert, DeviceEventEmitter, ToastAndroid} from 'react-native';

import {DP} from '../Lib/ScreenUtil';
import DeviceInfo from 'react-native-device-info';
import {StackNavigator} from 'react-navigation';

import {gWebSocket} from './commondef';
import {gGetUniqueID} from './commondef';

var ws;
var lockReconnect = false; //避免重复连接
var tt;

//心跳检测,每5s心跳一次
var heartCheck = {
  timeout: 10000,
  timeoutObj: null,
  serverTimeoutObj: null,
  reset: function () {
    clearTimeout (this.timeoutObj);
    clearTimeout (this.serverTimeoutObj);
    this.start ();
  },
  start: function () {
    var self = this;
    this.timeoutObj = setTimeout (function () {
      //这里发送一个心跳，后端收到后，返回一个心跳消息，
      //onmessage拿到返回的心跳就说明连接正常
      try {
        var msg = 'heartBeat|' + gGetUniqueID;
        console.log ('客户端发送心跳：' + msg);
        ws.send (msg);
      } catch (error) {}
     
      self.serverTimeoutObj = setTimeout (function () {
        // ToastAndroid.showWithGravity('收到websocket连接超时的消息！', ToastAndroid.CENTER,ToastAndroid.CENTER);
        //如果超过一定时间还没重置，说明后端主动断开了
        ws.close (); //如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
      }, self.timeout);
    }, this.timeout);
  },
};

function reconnect () {
  // ToastAndroid.showWithGravity('websocket尝试重新连接！', ToastAndroid.CENTER,ToastAndroid.CENTER);
  if (lockReconnect) {
    return;
  }
  lockReconnect = true;
  //没连接上会一直重连，设置延迟避免请求过多
  tt && clearTimeout (tt);
  tt = setTimeout (function () {
    webSocketOptions ();
    lockReconnect = false;
  }, 4000);
}

function init () {
  ws.onopen = function (e) {
    // 建立连接
    console.log ('连接成功');
    heartCheck.start();    //在开启调试模式时，此行要注释掉。
    DeviceEventEmitter.emit ('wsConnectSuc');
  };
  ws.onmessage = function (e) {
    // 接收到了一个消息
    console.log ('接收到了一个消息', e);

    let data = e.data;
    if (data != 'heartBeat') {
      DeviceEventEmitter.emit ('wsMsg', data);
      // ToastAndroid.showWithGravity('收到websocket刷新的消息！', ToastAndroid.CENTER,ToastAndroid.TOP);
    } else {
      // ToastAndroid.showWithGravity('收到websocket验证连接的消息！', ToastAndroid.CENTER,ToastAndroid.TOP);
      heartCheck.reset ();
    }
  };
  ws.onerror = function (e) {
    // 发生了一个错误
    console.log ('发生了一个错误', e);
  };
  ws.onclose = function (e) {
    // 连接被关闭了
    console.log ('连接被关闭了');
    console.log ('连接关闭重连中......');
    DeviceEventEmitter.emit ('wsConnectErr');
    ToastAndroid.showWithGravity (
      '自动连接中...',
      ToastAndroid.SHORT,
      ToastAndroid.CENTER
    );
    reconnect ();
  };
}

async function webSocketOptions () {
  let path = {};
  try {
    path = await storage.load ({
      key: 'requestAddr',
      autoSync: false,
      syncInBackground: false,
    });
  } catch (e) {
    console.log (e);
    //TODO handle the exception
    DeviceEventEmitter.emit ('wsConnectErr');
  }
  let host = '';
  try {
    host = path.host ? path.host.replace (/\s+/g, '') : '';
  } catch (e) {
    //TODO handle the exception
    host = '';
  }

  try {
    var gWebSocket = 'ws://' + host + '/websocket/' + DeviceInfo.getUniqueID ();
    console.log (gWebSocket);
    ws = new WebSocket (gWebSocket);
  } catch (e) {
    //TODO handle the exception
    console.log (e);
  }

  console.log ('尝试创建连接中。。。');
  init ();

  DeviceEventEmitter.addListener ('wsSendMsg', param => {
    ws.send (param);
  });
}

webSocketOptions ();
