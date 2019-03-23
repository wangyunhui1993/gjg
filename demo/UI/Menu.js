import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  Image,
  Dimensions,
  ToastAndroid,
  DeviceEventEmitter,
	NativeModules,
	Alert,
	TouchableOpacity
} from 'react-native';
import {DP, PX,w_w,h_h} from './Lib/ScreenUtil';
// import {postForm} from './Coms/commondef';
import Neo from './utils/neo';
import KeyEvent from 'react-native-keyevent';
import DeviceInfo from 'react-native-device-info';
var TakeViewManager = NativeModules.TakeViewManager;
const StateItem = ({index, state}) => (
    <Image  style={styles.feature} source={state===0?require ('./Img/box_empty.png'):state===1?require ('./Img/box_frozen.png'):require ('./Img/box_full.png')}></Image>
);


var deEmitter, deEmitterConnect, deEmitterSuc;
export default class Menu extends React.Component {
  componentDidMount () {
		this.setTime();
		//注册接收器
	      DeviceEventEmitter.addListener('testData', e => {   //for Android
				console.log(456);
	          ToastAndroid.show(e.data, 2000);
	          //更新状态及其他操作
	      });
		  DeviceInfo.getIPAddress().then(ip => {
		  this.setState ({IPAddress: ip});
		});
	}
  componentWillUnmount () {}


// 设置时间
	setTime(){
		let time=Neo.format_time(new Date(),'h:mm');
		this.setState ({currentTime: time});
		setInterval(()=>{
			let time=Neo.format_time(new Date(),'h:mm');
			this.setState ({currentTime: time});
		},1000);
	}



  constructor (props) {
    super (props);
    this.state = {
			currentTime:null,
			listInfo:[
				{state:0},
				{state:1},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:0},
				{state:1},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:0},
				{state:1},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:0},
				{state:1},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
				{state:2},
			],
			showCard:false,
			
			nodeId:'',
      cardNumber: '',
      promptText: '',
      connectState: true,
      KeyboardShown: false,
      modalVisible: true,
      textColor: '#fff',
      imgState: true,
	  serverState:'未知',
	  IPAddress:'',
    };

    const didFocusSubscription = this.props.navigation.addListener (
      'didFocus',
      payload => {
		 KeyEvent.onKeyUpListener (keyEvent => {
		  // this.keyboardEvent (keyEvent);
		});
        
      }
    );

    const didBlurSubscription = this.props.navigation.addListener (
      'didBlur',
      payload => {
		// didFocusSubscription.remove ();
      }
    );
  }
	
	 ceshi(){
		  TakeViewManager.addEventCeshi();
//         TakeViewManager.addEventCeshi(('测试'),(error,events) =>{
// 					console.log(events);
//         });
  }
	op(type){
		this.ceshi();
		this.state.showCard=true;
	}
cancal(){
	this.state.showCard=false;
}
set(){
		this.props.navigation.navigate('Seting');
}
  render () {
		var body = this.state.showCard?<View  style={styles.pushCardView}>
						<Image  source={require ('./Img/scan_card_please.png')} style={styles.pushCardImg} />
						 <TouchableOpacity onPress={this.cancal.bind (this)} style={styles.cancal}>
								<Text style={{color:"#000"}}>取消</Text>
						</TouchableOpacity>
				 </View>:
				 <View style={styles.body}>
				    <View style={styles.time}>
				     <Text style={styles.timeText}>{this.state.currentTime}</Text>
				   </View>
				 	<View  style={styles.bottomPart}>
				 		 <View style={styles.leftPart}>
				 		 {this.state.listInfo.map ((val, index) => {
				 		    return (
				 		      <StateItem
				 		        info={val}
				 		        key={index}
				 						state={val.state}
				 		      />
				 		    );
				 		  })}
				 		</View>
				 		 <View style={styles.centerPart}>
				 		 
				 		 <View>
				    <Text style={styles.title}>文件柜管理系统</Text>
				 		 </View>
				 		 <View style={styles.opImg}>
				 		  <TouchableOpacity onPress={()=>this.op(1)}  style={styles.TouchImg}>
				 		 								<Image source={require ('./Img/deposit.png')} style={styles.clickImg}></Image>
				 		 </TouchableOpacity>
				 		 <TouchableOpacity onPress={()=>this.op(2)} style={styles.TouchImg}>
				 		 		<Image source={require ('./Img/extraction.png')} style={styles.clickImg}></Image>
				 		 </TouchableOpacity>
				 		 </View>
				 		</View>
				 	</View>
					<View style={styles.setView}>
					  <TouchableOpacity onPress={this.set.bind (this)} style={styles.setBtn}>
					 		<Text style={{color:"#000"}}>设置</Text>
					 </TouchableOpacity>
					</View>
					
					<View style={styles.serverView}>
					 	<Text style={{color:"#000"}}>{this.state.IPAddress}-{this.state.serverState}</Text>
					</View>
				 </View>;
				 
    return (
      <View style={styles.container}>
        {body}
				 <Image  source={require ('./Img/bkg.png')} style={styles.backgroundImage} />
      </View>
    );
  }

}

const styles = StyleSheet.create ({
  container: {
    flex: 1,
  },
	backgroundImage:{
		position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    bottom: 0,
    height: null,
    width: null,
    zIndex: -1,
	},
	body:{
		flex: 1, 
		width: w_w
		},
	time:{
		paddingLeft:10,
	},
	timeText:{
		fontSize:PX(40)
	},
	leftPart:{
		paddingLeft:PX(10),
		flexDirection :'column',
		flexWrap:"wrap",
		height:h_h*0.85,
		width:w_w*0.33,
	},
	feature:{
		width:PX(50),
		height:PX(50)
	},
	bottomPart:{
		flexDirection :'row'
	},
	centerPart:{
		width:w_w*0.33,
		alignItems: 'center',
		
	},
	opImg:{
		flex: 1,
	},
	TouchImg:{
		width: PX(400),
		height: PX(400),
	},
	clickImg:{
		 flex: 1,
		 resizeMode:'contain',
		// flexDirection: 'column',
		//backgroundColor: '#F7F7F7',
		// alignItems: 'center',
		width: PX(400),
		height: PX(400),
		//不加这句，就是按照屏幕高度自适应
		//加上这几，就是按照屏幕自适应
		// resizeMode: Image.resizeMode.cover,
		//祛除内部元素的白色背景
		// backgroundColor: 'rgba(0,0,0,0)',
	},
	title:{
		color:'#FFD700',
		fontSize:PX(60),
		alignItems: 'center',
		textAlign:'center',
	},
	pushCardView:{
		position:'absolute',
		height: null,
		width: null,
		zIndex:999,
		left: 0,
		right: 0,
		top: 0,
		bottom: 0,
	},
	pushCardImg:{
		height: h_h,
		width: w_w,
		resizeMode: 'contain',
	},
	cancal:{
		position:'absolute',
		right: 50,
		bottom: 20,
		backgroundColor:'#fff',
		paddingLeft:10,
		paddingRight:10,
		paddingTop:5,
		paddingBottom:5,
	},
	setView:{
		position:'absolute',
		right: 20,
		top: 10,
	},
	serverView:{
		position:'absolute',
		right: 20,
		bottom: 10,
	},
	setBtn:{
		
	}
});
