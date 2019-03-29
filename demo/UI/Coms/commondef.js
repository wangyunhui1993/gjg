import {
	Alert,
	ToastAndroid,
} from 'react-native';

import {
	DP
} from '../Lib/ScreenUtil';
import DeviceInfo from 'react-native-device-info';

























// export const gWebSocket = 'ws://'+loadStorageData('requestAddr')+'/websocket/'+DeviceInfo.getUniqueID();



// export const gServerAddr = 'http://192.168.1.85:8018';
// export const gWebSocket = 'ws://192.168.1.85:8018/websocket/'+DeviceInfo.getUniqueID();


// export const gServerAddr = 'http://192.168.8.108:8018';
// export const gWebSocket = 'ws://192.168.8.108:8018/websocket/'+DeviceInfo.getUniqueID();


// export const gServerAddr = 'http://192.168.1.122:8081/xcx';
// export const gWebSocket = 'ws://192.168.1.122:8081/xcx/websocket/'+DeviceInfo.getUniqueID();


export const gGetUniqueID = DeviceInfo.getUniqueID();


export async  function postForm(api, data, callback) {
	var baseUrl='localhost:80';
	var url=baseUrl+api;
	return new Promise(function(resolve, reject){
		fetch(url, {
				method: 'POST',
				// mode: 'cors',
				headers: {
					'Accept': 'application/json,text/plain, */*',
					// 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
					'Content-Type': 'application/json; charset=utf-8',
				},
				body: JSON.stringify(data)
			})
			.then((res) => res.json())
			.then(json => {
				console.log('json return', json);
				callback(json);
			})
			.catch((error) => error)
			.done();
	}
	
};

export const gUserToken = 'userToken';

export const fontSizeNormal = DP(16);
