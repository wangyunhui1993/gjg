import Storage from 'react-native-storage';
import {ToastAndroid, AsyncStorage,DeviceEventEmitter  } from 'react-native';
import { gGetUniqueID} from './commondef';
import DeviceInfo from 'react-native-device-info';
var storage = new Storage({
  // 最大容量，默认值1000条数据循环存储
  size: 1000,

  // 存储引擎：对于RN使用AsyncStorage，对于web使用window.localStorage
  // 如果不指定则数据只会保存在内存中，重启后即丢失
  storageBackend: AsyncStorage,
    
  // 数据过期时间，默认一整天（1000 * 3600 * 24 毫秒），设为null则永不过期
  defaultExpires: null,
    
  // 读写时在内存中缓存数据。默认启用。
  enableCache: true,
    
  // 如果storage中没有相应数据，或数据已过期，
  // 则会调用相应的sync方法，无缝返回最新数据。
  // sync方法的具体说明会在后文提到
  // 你可以在构造函数这里就写好sync的方法
  // 或是在任何时候，直接对storage.sync进行赋值修改
  // 或是写到另一个文件里，这里require引入
  sync: null
  	
})  
  
// 最好在全局范围内创建一个（且只有一个）storage实例，方便直接调用
  
// 对于web
// window.storage = storage;
  
// 对于react native
global.storage = storage;


// 这样，在此**之后**的任意位置即可以直接调用storage
// 注意：全局变量一定是先声明，后使用
// 如果你在某处调用storage报错未定义
// 请检查global.storage = storage语句是否确实已经执行过了




require ('./websockets'); //websockets





	// 	var ws;
	// 	// var timeoutObj=null;
	// 	 var lockReconnect = false;//避免重复连接
	// 	var tt;
		
		
	// 	//心跳检测,每5s心跳一次
	// 	var heartCheck = {
	// 	    timeout: 10000,
	// 	    timeoutObj: null,
	// 	    serverTimeoutObj: null,
	// 	    reset: function(){
	// 	        clearTimeout(this.timeoutObj);
	// 	        clearTimeout(this.serverTimeoutObj);
	// 	        this.start();
	// 	    },
	// 	    start: function(){
	// 	        var self = this;
	// 	        this.timeoutObj = setTimeout(function(){
	// 	            //这里发送一个心跳，后端收到后，返回一个心跳消息，
	// 	            //onmessage拿到返回的心跳就说明连接正常
	// 							let msg="heartBeat|"+gGetUniqueID;
	// 							console.log("客户端发送心跳："+msg);
	// 							ws.send(msg);
	// 	            self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
	// 	                ws.close();//如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
	// 	            }, self.timeout)
	// 	        }, this.timeout)
	// 	    }
	// 	}

	// 	function reconnect() {
    //   if(lockReconnect) {
    //     return;
    //   };
    //   lockReconnect = true;
    //   //没连接上会一直重连，设置延迟避免请求过多
    //   tt && clearTimeout(tt);
    //   tt = setTimeout(function () {
    //     webSocketOptions();
    //     lockReconnect = false;
    //   }, 4000);
    // }
		
		
	// 	function init() {
	// 		ws.onopen = function(e) {
	// 			// 建立连接
	// 			console.log('连接成功');
	// 			heartCheck.start();
	// 			// clearTimeout(timeoutObj);
	// 			DeviceEventEmitter.emit('wsConnectSuc');
	// 		}
	// 		ws.onmessage = function(e) {
	// 			// 接收到了一个消息
	// 			console.log('接收到了一个消息', e);
				
	// 			let data=e.data;
	// 			if(data!='heartBeat'){
	// 				DeviceEventEmitter.emit('wsMsg', data);
	// 			}else{
	// 				heartCheck.reset();
	// 			}
	// 		}
	// 		ws.onerror = function(e) {
	// 			// 发生了一个错误
	// 			console.log('发生了一个错误', e);
	// 		}
	// 		ws.onclose = function(e) {
	// 			// 连接被关闭了
	// 			console.log('连接被关闭了');
	// 			console.log('连接关闭重连中......');
				
				
	// 			DeviceEventEmitter.emit('wsConnectErr');
	// 				ToastAndroid.showWithGravity('自动连接中...', ToastAndroid.SHORT,ToastAndroid.CENTER);
	// 				reconnect();
				
				
	// 		}
	// 	}
		
		
		
		
		
		
	// 	async function webSocketOptions() {
	// 		console.log('尝试创建连接中。。。');
	// 		let path={};
	// 		try{
	// 			path=await storage.load({
	// 					key: 'requestAddr',
	// 					autoSync: false,
	// 					syncInBackground: false,
	// 				})
	// 		}catch(e){
	// 			console.log(e);
	// 			//TODO handle the exception
	// 			DeviceEventEmitter.emit('wsConnectErr');
	// 		}
	// 		let host='';
	// 		try{
	// 			host=path.host?path.host.replace(/\s+/g,""):''
	// 		}catch(e){
	// 			//TODO handle the exception
	// 			host='';
	// 		}
			
	// 		try{
	// 				var gWebSocket = 'ws://'+host+'/websocket/'+DeviceInfo.getUniqueID();
	// 				console.log(gWebSocket);
	// 				ws = new WebSocket(gWebSocket);
	// 		}catch(e){
	// 			//TODO handle the exception
	// 			console.log(e);
	// 		}
				
	// 		console.log('尝试创建连接中。。。');
	// 		init();
			
	// 		DeviceEventEmitter.addListener('wsSendMsg',(param)=>{
	// 			ws.send(param);	
	// 		});
	// 		}
			
	// 		webSocketOptions();





// import websockets from './websockets.js';