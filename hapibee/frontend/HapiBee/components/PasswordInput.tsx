import React, { useState } from "react";
import { StyleSheet,View, TextInput, TouchableOpacity, Image } from "react-native";

export default function PasswordInput({ onPasswordChange }: {onPasswordChange: Function}) {
  const [secureTextEntry, setSecureTextEntry] = useState(true);

  const toggleSecureEntry = () => {
    setSecureTextEntry(!secureTextEntry);
  };

  const handleChangePassword = (texto:string) => {
    onPasswordChange(texto);
  };

  return (
    <View style={styles.container}>
      <TextInput
        secureTextEntry={secureTextEntry}
        placeholder="Palavra-passe"
        placeholderTextColor="black"
        style={styles.input}
        onChangeText={(texto) => handleChangePassword(texto)}
      />
      <TouchableOpacity
        onPress={toggleSecureEntry}
        style={{ position: "absolute", right: 15, top: 25 }}
      >
        <Image
          source={
            secureTextEntry
              ? require("../assets/images/eye.png")
              : require("../assets/images/show.png")
          }
          style = {styles.icon}
        />
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
    container: {
        width: '100%',
    },
    input: {
      width: "100%",
      height: 50,
      borderWidth: 1,
      borderRadius: 15,
      paddingLeft: 10,
      marginTop: 10,
      fontWeight: 'bold',
    },
    icon: {
      width: 20,
      height: 20,
      zIndex: 2
    },
  });
  
