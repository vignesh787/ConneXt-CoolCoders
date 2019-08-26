import * as React from 'react';
import { Text, View, StyleSheet , TextInput , Button } from 'react-native';
import Constants from 'expo-constants';
import Landing from './Landing';
import Login from './Login';
import changePassword from './ChangePassword';

// You can import from local files
import AssetExample from './components/AssetExample';

// or any pure javascript modules available in npm
import { Card } from 'react-native-paper';
import {createStackNavigator , createAppContainer} from 'react-navigation';

const MainNavigator = createStackNavigator({
     Login : {screen : Login} ,
     Landing : {screen : Landing},
     changePassword : {screen : changePassword}
});

const App = createAppContainer(MainNavigator);

export default App;