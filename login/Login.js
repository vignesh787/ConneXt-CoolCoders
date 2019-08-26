import * as React from 'react';
import { Text, View, StyleSheet , TextInput , Button } from 'react-native';
import Constants from 'expo-constants';
import Landing from './Landing';
import base64 from 'react-native-base64';
import $ from 'jquery';

// You can import from local files
import AssetExample from './components/AssetExample';

// or any pure javascript modules available in npm
import { Card } from 'react-native-paper';

export default class Login extends React.Component {
  constructor(props) {
	   super(props);
	   this.state = { screen: 'Initial' , userid : '' , pwd: '' };
  }

  validate() {
    let uid=String(this.state.userid);
    //if (uid.contains(" ")== true){
     // alert("No space allowed in user id");
     // return false;
    //}
    if (uid.length !=7){
      alert("Userid length should be 7");
    }
    let pwd1=String(this.state.pwd.length);
    if (pwd1.length <7 && pwd1.length >15){
      alert("Password length should be 7 to 15 characters");
      return false;
    }
    return true;
  }
  success () {
     alert('Success');
  }
  failure  () {
     alert('failure');
  }
  render() {
       const {navigate} = this.props.navigation;
    
  return (
    <View style={styles.container}>
      <Text>Login</Text>
	  <TextInput style={{height : 40}} onChangeText={(userid) => this.setState({userid})}  placeholder="Enter the Userid  " value={this.state.userid}/>
	  <TextInput style={{height : 40}} secureTextEntry={true} onChangeText={(pwd) => this.setState({pwd})}        placeholder="Enter the Password" value={this.state.pwd}/>
	  <Button onPress={() => { let user = this.state.userid; 
                                if(this.validate()) {
                                  let authWrapper = {
                                       userid : this.state.userid ,
                                       password : this.state.pwd
                                  }
                       $.ajax({ 
                              type: 'POST',
                              url : 'http://localhost:8080/login',
                              data : authWrapper,
                              success : this.success ,
                              failure : this.failure ,
                              error : this.error
                       })         
                   
                                  //navigate('Landing', {userid: user});
                                  navigate('changePassword');  
                                } 
                                }} title="Submit"/>
    </View>
  ); 
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    paddingTop: Constants.statusBarHeight,
    backgroundColor: '#ecf0f1',
    padding: 8,
    margin: 24
  }
});
