import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
import {
  StackNavigator
} from 'react-navigation';

import Home from './Home';
import Menu from './Menu';
// import Seting from './Seting/Index';
export default  MyAPP6 =  StackNavigator(
{
		Home: {screen: Home,navigationOptions:{
       header:null,
    },},
		Menu: {screen: Menu,navigationOptions:{
		   header:null,
		},},
// 		Seting: {screen: Seting,navigationOptions:{
// 		   header:null,
// 		},},
		
		
    
}, 
{
    initialRouteName: 'Menu', 
}
);




