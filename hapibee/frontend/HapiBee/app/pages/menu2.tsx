import { Pressable, StyleSheet, Image } from "react-native";

import { Text, View } from "../../components/Themed";
import { useEffect, useState } from "react";
import { useNavigation } from "expo-router";
import { StackTypes } from "../_layout";
import { getApiarios } from "../../services/apiariosService";

export default function Menu2() {
  const [isPressed, setIsPressed] = useState(false);
  const [isPressed2, setIsPressed2] = useState(false);

  const navigation = useNavigation<StackTypes>();

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  function buttonHandler(): void {
    navigation.navigate("pages/criarApiario");
  };

  const handlePressIn2 = () => {
    setIsPressed2(true);
  };

  const handlePressOut2 = () => {
    setIsPressed2(false);
  };

  function buttonHandler2(): void {
    navigation.navigate("pages/gestaoApiarios");
  };

  return (
    <View style={styles.container}>
      <Image
        source={require("../../assets/images/happibee_logo.png")} // Caminho para a imagem
        style={styles.logo}
      />
      <Pressable
        style={[
          styles.button,
          {
            transform: [{ scale: isPressed ? 0.95 : 1 }],
            marginBottom: 50,
          },
        ]}
        onPress={() => buttonHandler()}
        onPressIn={handlePressIn}
        onPressOut={handlePressOut}
      >
        <View
          style={[
            {
              backgroundColor: "#FFD000",
              flexDirection: "row",
            },
          ]}
        >
          <Image
            source={require("../../assets/images/plus.png")}
            style={styles.image}
          ></Image>
        </View>

        <Text style={styles.text}>Pedido de instalação novo apiário</Text>
      </Pressable>
      <Pressable
        style={[
          styles.button,
          {
            transform: [{ scale: isPressed2 ? 0.95 : 1 }],
          },
        ]}
        onPress={() => buttonHandler2()}
        onPressIn={handlePressIn2}
        onPressOut={handlePressOut2}
      >
        <Image
          source={require("../../assets/images/manage.png")}
          style={styles.image}
        ></Image>
        <Text style={styles.text}>Gerir apiários</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#FFE5C4",
    paddingLeft: 30,
    paddingRight: 30,
  },
  title: {
    fontSize: 40,
    marginBottom: 20,
  },
  separator: {
    marginVertical: 30,
    height: 1,
    width: "80%",
  },
  image: {
    width: 50,
    height: 50,
  },
  logo: {
    width: 255,
    height: 108,
  },
  input: {
    width: "100%",
    height: 50,
    borderWidth: 1,
    borderRadius: 15,
    paddingLeft: 10,
    fontWeight: "bold",
  },
  marginTop: {
    marginTop: 20,
  },
  icon: {
    width: 20,
    height: 20,
    zIndex: 2,
  },
  button: {
    marginTop: 50,
    width: "100%",
    backgroundColor: "#FFD000",
    textAlign: "center",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 15,
    height: 200,
  },
  text: {
    fontSize: 30,
    fontWeight: "bold",
    textAlign: "center",
  },
});
