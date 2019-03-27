import React, { Component } from 'react';
import Nav from "./Nav";

console.ignoredYellowBox = ['Warning: BackAndroid is deprecated. Please use BackHandler instead.','source.uri should not be an empty string','Invalid props.style key']; 
console.disableYellowBox = true // 关闭全部黄色警告

import SplashScreen from 'react-native-splash-screen'   //启动页
import { Immersive } from 'react-native-immersive'     //沉浸式
import Orientation from 'react-native-orientation'    //横屏
// require('../server/test.js');
export default class App extends Component {
	componentDidMount() {
		SplashScreen.hide();
		Immersive.on();
		Immersive.setImmersive(true);
		Orientation.lockToLandscape() //转成横屏
		
		
		
		
		

	


		
    }
  render() {
    return <Nav />;
  }
}