
import React from 'react';
import { TouchableOpacity,Image,StyleSheet } from 'react-native';
import {NavigationProp } from '@react-navigation/native';


type Props = {
  navigation: NavigationProp<any>;
};

const GoBackArrow: React.FC<Props> = ({ navigation }) => {
  const handlePress = () => {
    navigation.goBack();
  };

  return (
    <TouchableOpacity style={styles.container} onPress={handlePress}>
      <Image
        source={require("../assets/images/arrow.png")} // Caminho para a imagem
        style={styles.image}
      />
    </TouchableOpacity>
  );
};

export default GoBackArrow;



const styles = StyleSheet.create({
  container: {
    backgroundColor: "#FFE5C4",
  },
  image: {
    width: 50,
    height: 40,
  },
});
