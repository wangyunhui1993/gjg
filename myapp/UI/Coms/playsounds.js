import {ToastAndroid  } from 'react-native';
import Sound from 'react-native-sound';

var sounds={
	rele:null,
	sounds:function(url){
		var self=this;
		if(!url){
			ToastAndroid.show('该提示语音文件名没有配置', ToastAndroid.SHORT);
			return true;
	}
	let info={
		url: url,
		basePath: Sound.MAIN_BUNDLE,
	}
	try{
		console.log('要释放的对象',self.rele);
		self.rele.release();
	}catch(e){
		console.log(e);
	}
	
	 // If the audio is a 'require' then the second parameter must be the callback.
	 var soundInstance = new Sound(info.url, info.basePath, error => callback(error, soundInstance));
	 self.rele=soundInstance;
	 const callback = (error, sound) => {
		console.log('要播放的对象',sound);
		if (error) {
		ToastAndroid.show('语音播放失败', ToastAndroid.SHORT);
		return;
		}
		// Run optional pre-play callback
		sound.play(() => {
			// Success counts as getting to the end
			// Release when it's done so we're not using up resources
			sound.release();
		});
		// return sound;
	};
	}
}


// 对于react native
global.sounds = sounds;