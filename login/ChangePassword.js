import * as React from 'react';
import { Text, View, StyleSheet , TextInput , Button } from 'react-native';
import Constants from 'expo-constants';

// You can import from local files
import AssetExample from './components/AssetExample';

// or any pure javascript modules available in npm
import { Card } from 'react-native-paper';

export default class changePassword extends React.Component {
  constructor(props) {
	   super(props);
     this.state = { newpwd : '' , oldpwd: '' };
  }   
  render() {
  return (
    <View style={styles.container}>
      <TextInput style={{height : 40}} secureTextEntry={true} onChangeText={(oldpwd) => this.setState({oldpwd})}        placeholder="Enter the Old Password" value={this.state.oldpwd}/>
      <TextInput style={{height : 40}} secureTextEntry={true} onChangeText={(newpwd) => this.setState({newpwd})}        placeholder="Enter the New Password" value={this.state.newpwd}/>
      <Button title="Submit"/>
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
    margin : 24
  }
});
