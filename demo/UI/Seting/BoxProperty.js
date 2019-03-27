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
import {
	DP,
	PX,
	w_w,
	h_h
} from './../Lib/ScreenUtil';
// import {postForm} from './Coms/commondef';
import Neo from './../utils/neo';
import KeyEvent from 'react-native-keyevent';
// var { GiftedForm, GiftedFormManager } = require('react-native-gifted-form');
import RadioForm, {RadioButton, RadioButtonInput, RadioButtonLabel} from 'react-native-simple-radio-button';    //单选按钮


var stateList = [
  {label: '物品状态', value: 0 },
  {label: '箱门状态', value: 1 },
  {label: '锁定状态', value: 2 },
];
var boxList=[
  {label: '001~036箱', value: 0 },
  {label: '037~073箱', value: 1 },
  {label: '073~108箱', value: 2 },
  {label: '109~144箱', value: 3 },
  {label: '145~180箱', value: 4 },
  {label: '181~216箱', value: 5 },
  {label: '217~252箱', value: 6 },
  {label: '253~288箱', value: 7 },
];

 const BoxItem = ({num,onPress}) => (
 	 <View style={styles.boxItem}>
		<TouchableOpacity style={{margin:5}} onPress={onPress}>
      <Text style={{textAlign:'center',fontSize:20,fontWeight:'bold',backgroundColor:'#b3ff99',lineHeight:50,borderRadius:5}}>{num}</Text>
    </TouchableOpacity>
 	 </View>
 );
 



export default class Seting extends React.Component {
	componentDidMount() {

	}
	componentWillUnmount() {}




	constructor(props) {
		super(props);
		this.state = {
			value: 0,
			boxItemList:[
				{num:1,type:1},
				{num:2,type:1},
				{num:3,type:1},
				{num:4,type:0},
				{num:5,type:1},
				{num:6,type:1},
				{num:7,type:1},
				{num:8,type:0},
				{num:9,type:1},
				{num:10,type:1},
				{num:11,type:1},
				{num:12,type:1},
				{num:13,type:0},
				{num:14,type:1},
				{num:15,type:1},
				{num:16,type:1},
				{num:17,type:0},
				{num:18,type:1},
			],
			boxItemList2:[
				{num:19,type:1},
				{num:20,type:1},
				{num:21,type:1},
				{num:22,type:0},
				{num:23,type:1},
				{num:24,type:1},
				{num:25,type:1},
				{num:26,type:0},
				{num:27,type:1},
				{num:28,type:1},
				{num:29,type:1},
				{num:30,type:1},
				{num:31,type:0},
				{num:32,type:1},
				{num:33,type:1},
				{num:34,type:1},
				{num:35,type:0},
				{num:36,type:1},
			]
		};

		const didFocusSubscription = this.props.navigation.addListener(
			'didFocus',
			payload => {
				KeyEvent.onKeyUpListener(keyEvent => {
					// this.keyboardEvent (keyEvent);
				});

			}
		);

		const didBlurSubscription = this.props.navigation.addListener(
			'didBlur',
			payload => {
				// didFocusSubscription.remove ();
			}
		);
	}


clickItem(){
	
}


  render () {
    return (
      <View style={styles.container}>
	   <View style={styles.body}>
			 <View style={styles.title}>
				<Text  style={styles.titleText}>箱体属性</Text>
			</View>
			<View style={styles.boxState}>
				<RadioForm
          radio_props={stateList}
          initial={0}
		  formHorizontal={true}
		  buttonColor={'#07233f'}
		  selectedButtonColor={'#009688'}
		  style={styles.radioStateStyle}
		  labelStyle={styles.radioStateLabelStyle}
		  buttonSize={12}
          onPress={(value) => {this.setState({value:value})}}
        />		
			</View>
			<View style={styles.boxs}>
				<RadioForm
				  radio_props={boxList}
				  initial={0}
				  formHorizontal={true}
				  buttonColor={'#07233f'}
				  selectedButtonColor={'#009688'}
				  style={styles.radioBoxStyle}
				  labelStyle={styles.radioBoxLabelStyle}
				  buttonSize={12}
				  onPress={(value) => {this.setState({value:value})}}
				/>				
			</View>
			<View style={styles.content}>
				<View style={styles.content_left}>
					{this.state.boxItemList.map ((val, index) => {
					                        return (
					                          <BoxItem
					                            key={index}
					                            num={val.num}
					                            onPress={() => {
					                              return this.clickItem (index, this);
					                            }}
					                          />
					                        );
					                      })}
				</View>
				<View style={styles.content_center}>
				<Text>显示屏</Text>
				</View>
				<View style={styles.content_right}>
				{this.state.boxItemList2.map ((val, index) => {
				                        return (
				                          <BoxItem
				                            key={index}
				                            num={val.num}
				                            onPress={() => {
				                              return this.clickItem (index, this);
				                            }}
				                          />
				                        );
				                      })}
				</View>
			</View>
	   </View>
			<Image  source={require ('./../Img/bkg.png')} style={styles.backgroundImage} />
      </View>
    );
  }

}

const styles = StyleSheet.create({
	container: {
		flex: 1,
	},
	backgroundImage: {
		position: 'absolute',
		left: 0,
		right: 0,
		top: 0,
		bottom: 0,
		height: null,
		width: null,
		zIndex: -1,
	},
	body: {
		flex: 1,
		flexDirection: 'column',
// 		flexWrap: 'wrap',
// 		justifyContent: 'flex-start',
		width: '100%'
	},
	title:{
		width: '100%',
		paddingLeft:10,
	},
	titleText:{
		fontSize:25,
		fontWeight:'bold',
		lineHeight:40,
		color:'#99cc00'
	},
	boxState:{
		paddingTop:10
	},
	radioStateStyle:{
		paddingLeft:20
	},
	radioStateLabelStyle:{
		paddingRight:20,
		color:'#fff',
		fontSize:18
	},
	boxs:{
		paddingTop:10
	},
		radioBoxStyle:{
		paddingLeft:20
	},
	radioBoxLabelStyle:{
		paddingRight:5,
		color:'#fff',
		fontSize:15
	},
	content:{
		flex: 1,
		flexDirection: 'row',
		paddingTop:10
	},
	content_left:{
		width:'40%',
		flexDirection: 'row',
		flexWrap:'wrap'
	},
	content_center:{
		width:'20%'
	},
	content_right:{
		width:'40%',
		flexDirection: 'row',
		flexWrap:'wrap'
		
	},

boxItem:{
	width:'30%',
},

});
