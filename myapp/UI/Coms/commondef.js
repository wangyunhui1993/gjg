import {
  Alert,
	ToastAndroid,
} from 'react-native';

import {DP} from '../Lib/ScreenUtil';
import DeviceInfo from 'react-native-device-info';
import { StackNavigator } from 'react-navigation';



// 读取
//  async function loadStorageData(key){
// 	await global.storage.load({
// 		key: key
// 	}).then(ret => {
// 		console.log('ret',ret);
// 		return (ret.host);
// 	}).catch(err => {
// 		ToastAndroid.showWithGravity('数据读取异常', ToastAndroid.SHORT,ToastAndroid.CENTER);
// 		return '';
// 	})
// }






// export const gWebSocket = 'ws://'+loadStorageData('requestAddr')+'/websocket/'+DeviceInfo.getUniqueID();



// export const gServerAddr = 'http://192.168.1.85:8018';
// export const gWebSocket = 'ws://192.168.1.85:8018/websocket/'+DeviceInfo.getUniqueID();


// export const gServerAddr = 'http://192.168.8.108:8018';
// export const gWebSocket = 'ws://192.168.8.108:8018/websocket/'+DeviceInfo.getUniqueID();


// export const gServerAddr = 'http://192.168.1.122:8081/xcx';
// export const gWebSocket = 'ws://192.168.1.122:8081/xcx/websocket/'+DeviceInfo.getUniqueID();


export const gGetUniqueID = DeviceInfo.getUniqueID();


export function postForm(url, data, callback){
    //try{
        fetch(url,{
            method: 'POST', 
            mode: 'cors',
            headers:{
                'Accept': 'application/json,text/plain, */*',
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
								// 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: data
        })
        .then((res)=>{
            // console.log('fetch return',res);
            if(res.ok){
                return res.json();
            }
        })
        .then(json=>{
                console.log('json return',json);
                callback(json);
            })
        .catch((er)=>{
					console.log('网络连接异常:' + er);
					// ToastAndroid.showWithGravity(JSON.stringify(er), ToastAndroid.SHORT,ToastAndroid.BOTTOM);
			
            
        })
		.done(); 
};

export const gUserToken = 'userToken';

export const fontSizeNormal = DP(16);