/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';

// import SQLite from './sqlite';
// var sqLite = new SQLite();
// var db;



const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
	
	
	
	
	
	
// 	compennetDidUnmount(){
//     sqLite.close();
//   }
//   componentWillMount(){
//   	//开启数据库
//   	if(!db){
//       db = sqLite.open();
//     }
//     //建表
//     sqLite.createTable();
//     //删除数据
//     sqLite.deleteData();
//     //模拟一条数据
//     var userData = [];
//     var user = {};
//     user.name = "张三";
//     user.age = "28";
//     user.sex = "男";
//     user.phone = "18900001111";
//     user.email = "2343242@qq.com";
//     user.qq = "111222";
//     userData.push(user);
//     //插入数据
//     sqLite.insertUserData(userData);
//     //查询
//     db.transaction((tx)=>{
//       tx.executeSql("select * from user", [],(tx,results)=>{
//         var len = results.rows.length;
//         for(let i=0; i<len; i++){
//           var u = results.rows.item(i);
//           //一般在数据查出来之后，  可能要 setState操作，重新渲染页面
//           alert("姓名："+u.name+"，年龄："+u.age+"，电话："+u.phone);
//         }
//       });
//     },(error)=>{//打印异常信息
//       console.log(error);
//     });
// 		}
	
	
	
	
	
	
	
	
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
        <Text style={styles.instructions}>To get started, edit App.js</Text>
        <Text style={styles.instructions}>{instructions}</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
