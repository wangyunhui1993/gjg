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

const StateItem = ({index, state}) => (
    <Image  source={require ('./../Img/box_empty.png')}>
	</Image>
);
 const TitleItem = ({url,onPress}) => (
 	 <View style={styles.TitleItem}>
	 <TouchableOpacity onPress={onPress}>
      <Image  style={styles.TitleItemImg} source={url}></Image>
    </TouchableOpacity>
       

 	 </View>
 );




export default class Seting extends React.Component {
	componentDidMount() {

	}
	componentWillUnmount() {}


	skipItem(index) {
		console.log(index);
		switch (index) {
			case 0:
				console.log(index);
				break;
			case 1:
				console.log(index);
				break;
			case 2:
				console.log(index);
				break;
			case 3:
				console.log(index);
				break;
			case 4:
				console.log(index);
				break;
			case 5:
				console.log(index);
				break;
			case 6:
				console.log(index);
				break;
			case 7:
				console.log(index);
				break;
			case 8:
				console.log(index);
				break;
			case 9:
				console.log(index);
				break;
			case 10:
				console.log(index);
				break;
			case 11:
				console.log(index);
				break;
			case 12:
				console.log(index);
				break;

		}
	}


	constructor(props) {
		super(props);
		this.state = {
			titleList1: [{
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				},
				{
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				},
				{
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				}, {
					url: require('./../Img/cunqujilu.png')
				},
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

	set() {
		this.props.navigation.navigate('Steps')
	}





  render () {
    return (
      <View style={styles.container}>
	   <View style={styles.body}>
	       	 {this.state.titleList1.map ((val, index) => {
	                                 return (
	                                   <TitleItem
	                                     key={index}
	                                     url={val.url}
	                                     onPress={() => {
	                                       return this.skipItem (index, this);
	                                     }}
	                                   />
	                                 );
	                               })}
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
		flexDirection: 'row',
		flexWrap: 'wrap',
		justifyContent: 'flex-start',
		width: w_w
	},
	TitleItem: {
		width: w_w * 0.25,
	},
	TitleItemImg: {
		resizeMode: 'contain',
		width: '100%',
		height: h_h * 0.3
	},



});
