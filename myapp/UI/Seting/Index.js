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
import {DP, PX,w_w,h_h} from './../Lib/ScreenUtil';
// import {postForm} from './Coms/commondef';
import Neo from './../utils/neo';
import KeyEvent from 'react-native-keyevent';
var TakeViewManager = NativeModules.TakeViewManager;

// const StateItem = ({index, state}) => (
//     <Image  source={require ('./../Img/box_empty.png')}>
// 	</Image>
// );
// const titleItem = ({name}) => (
// 	 <View><View><Text>{name}</Text></View></View>
// );




export default class Seting extends React.Component {
  componentDidMount () {
		
	}
  componentWillUnmount () {}





  constructor (props) {
    super (props);
    this.state = {
		titleList:[{name:'时间',url:'111'}]
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
	
set(){
	this.props.navigation.navigate('Steps')
}
  render () {
    return (
      <View style={styles.container}>
	   <View style={styles.body}>
	   </View>
			<Image  source={require ('./../Img/bkg.png')} style={styles.backgroundImage} />
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
	
		
	
});
