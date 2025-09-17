import { StyleSheet, Image, Pressable } from "react-native";

import { Text, View } from "../../components/Themed";
import { TextInput } from "react-native-gesture-handler";
import PasswordInput from "../../components/PasswordInput";
import ButtonComponent from "../../components/ButtonComponent";
import { useNavigation } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { useEffect, useState } from "react";
import { loginFetch } from "../../services/loginService";

export default function Login() {
  const [isPressed, setIsPressed] = useState(false);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigation = useNavigation<StackTypes>();

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  async function loginHandler() {
    try{
    const resposta = await loginFetch(email, password);
    navigation.navigate("pages/menu");
    }catch{
      console.log("Login falhou");
    }
  }

  const handleChangeEmail = (texto:string) => {
    setEmail(texto);
  };

  return (
    <View style={styles.container}>
      <Image
        source={require("../../assets/images/happibee_logo.png")} // Caminho para a imagem
        style={styles.image}
      />
      <Text style={styles.title}>Entrar</Text>
      <TextInput
        style={styles.input}
        placeholder="Email"
        placeholderTextColor="black"
        onChangeText={(texto) => handleChangeEmail(texto)}
      ></TextInput>
      <PasswordInput onPasswordChange={setPassword}></PasswordInput>
      <Pressable
        style={[
          styles.button,
          {
            transform: [{ scale: isPressed ? 0.95 : 1 }],
          },
        ]}
        onPress={() => loginHandler()}
        onPressIn={handlePressIn}
        onPressOut={handlePressOut}
      >
        <Text style={styles.text}>Entrar</Text>
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
    width: 383,
    height: 163,
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
    marginTop: 25,
    width: "100%",
    backgroundColor: "#FFD000",
    padding: 10,
    textAlign: "center",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 15,
  },
  text: {
    fontSize: 20,
    fontWeight: "bold",
  },
});
