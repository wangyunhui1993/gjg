import React, { Component } from 'react';
import Nav from "./Nav";



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