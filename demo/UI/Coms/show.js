import React, { Component } from 'react';
import {
  AppRegistry,
  View,
  Text,
  StyleSheet,
  Image,
  Dimensions,
  StatusBar,
  Alert,
  DeviceEventEmitter,
  ToastAndroid,
} from 'react-native';
import {postForm} from './commondef';

export default  show =async function(_this,nav,callback){
	_this.setState({show:false});  //确定按钮
	_this.setState({warnBtn:false});   
	_this.setState({table:false});
	_this.setState({showPicture:false});
	_this.setState({swipeState:false});
	
        var gServerAddr='';
        try{
            let path=await storage.load({
                    key: 'requestAddr',
                    autoSync: false,
                    syncInBackground: false,
                })
                gServerAddr='http://'+path.host;
        }catch(e){
            //TODO handle the exception
            return;
        }
        var url = gServerAddr + '/process/getProcessInfo';
        let info={};
        postForm(url, JSON.stringify(info), (data)=>{
                    let { errMsg, errCode, value, extraInfo,success} = data;
                    callback();
                        if(success){
                            console.log(value)
                            console.log('当前页面：'+nav);
                            let info=value.Node[0];
							
                            // ToastAndroid.showWithGravity(JSON.stringify(info), ToastAndroid.LONG,ToastAndroid.TOP);
                            if(nav=='Main'){
								 _this.setState({nodeId:info.nodeId});
                                if(info.nodeId=="_4" || info.nodeId=="_5" || info.nodeId=="_6" || info.nodeId=="_7"){
                                _this.setState({promptText:info.prompt});
                                
								
                                    if((info.nodeId==='_4' || info.nodeId==="_5") && info.ip=="Xapp"){
										//语音提示
										global.sounds.sounds(info.promptName);
                                    }else if((info.nodeId==='_6' || info.nodeId==="_7") && info.ip=="Dapp"){
										//语音提示
										global.sounds.sounds(info.promptName);
                                    }else{
                                         _this.props.navigation.navigate('Steps');
                                    }
									
                            }else if(info.nodeId=="_51" && info.ip=='Dapp'){
								 //语音提示
								global.sounds.sounds(info.promptName);
								_this.setState({promptText:info.prompt});
							}
							else if(info.nodeId=="_50" && info.ip=='Xapp'){
								 //语音提示
								global.sounds.sounds(info.promptName);
								_this.setState({promptText:info.prompt});
							}
							else{
                                _this.props.navigation.navigate('Steps');
                            }
                            }
                            else if(nav=='Steps'){
                                _this.setState({nodeId:info.nodeId});
                                _this.setState({currentState:info.prompt});
								 if((info.nodeId==='_4' || info.nodeId==="_5") && info.ip=="Xapp"){
									 _this.props.navigation.navigate('Main');
								}else if((info.nodeId==='_6' || info.nodeId==="_7") && info.ip=="Dapp"){
									_this.props.navigation.navigate('Main');
								}
								else if(info.nodeId=="_51" && info.ip=='Dapp'){
								_this.props.navigation.navigate('Main');
							}
							else if(info.nodeId=="_50" && info.ip=='Xapp'){
								_this.props.navigation.navigate('Main');
							}else{
                                    //语音提示
                                    global.sounds.sounds(info.promptName);
                                    if(info.nodeId=="_18" || info.nodeId=="_19"){
                                            _this.setState({show:true});
											_this.setState({table:true});
											var url = gServerAddr + '/process/getHighspeedInfo';
											  _this.setState({tableData:[]});
											postForm (url, JSON.stringify ({}), data => {
											  let {errMsg, errCode, value, extraInfo, success} = data;
											  if (success) {
												  var val=value.highspeedInfoList;
												  for(var item of val){
													  _this.setState({tableData:[..._this.state.tableData,[
														  item.track,
														  item.jobProject,
														  item.models,
														  item.carNumber,
													  ]]});
												  }
											  } else {
											    Alert.alert (errMsg);
											  }
											});
											console.log('基地址',gServerAddr);
                                            if(info.nodeId=="_18" && info.ip=='Dapp'){
                                                _this.setState({show:false});
                                            }else if(info.nodeId=="_19" && info.ip=='Xapp'){
                                                _this.setState({show:false});
                                            }
                                    }
// 									else if(info.nodeId=="_50" || info.nodeId=="_51"){
//                                             if(info.nodeId=="_51" && info.ip=='Dapp'){
//                                                 _this.setState({showPicture:true});
// 												_this.setState({swipeState:true});
//                                             }else	if(info.nodeId=="_50" && info.ip=='Xapp'){
//                                                 _this.setState({showPicture:true});
// 												_this.setState({swipeState:true});
//                                             }
//                                     }
									else if(info.nodeId=="_xinhao_JinJi"){
										_this.setState({show:false});
                                        _this.setState({warnBtn:true});
                                        
                                    }else{
                                        _this.setState({show:false});
                                    }
                                }
                            }
                           


                        
                        }else{
                            Alert.alert(errMsg);
                        }
                });
};


	// 获取洗车信息
// 	 async function getHighspeedInfo () {
// 	  let gServerAddr = '';
// 	 
// 	  // try {
// 	    let path = await storage.load ({
// 	      key: 'requestAddr',
// 	      autoSync: false,
// 	      syncInBackground: false,
// 	    });
// 	    gServerAddr = 'http://' + path.host;
// // 	  } catch (e) {
// // 	    //TODO handle the exception
// // 	    return;
// // 	  }
// 		
// 	  let url = gServerAddr + '/process/getHighspeedInfo';
// 	  let info = {};
// 	  postForm (url, JSON.stringify (info), data => {
// 		  debugger
// 	    let {errMsg, errCode, value, extraInfo, success} = data;
// 	    if (success) {
// 				console.log('洗车信息',value);
// 	    } else {
// 	      Alert.alert (errMsg);
// 	    }
// 	  });
// 	}