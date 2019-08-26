import * as React from 'react';
import { Text, View, StyleSheet , TextInput , Button } from 'react-native';
import Constants from 'expo-constants';

// You can import from local files
import AssetExample from './components/AssetExample';

// or any pure javascript modules available in npm
import { Card } from 'react-native-paper';

export default class Landing extends React.Component {
  constructor(props) {
	   super(props);
  }   
  render() {
  return (
    <View style={styles.container}>
      <Text>Welcome {this.props.navigation.state.params.userid}</Text>
      <Button title="Change Password"/>
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
  }
});
