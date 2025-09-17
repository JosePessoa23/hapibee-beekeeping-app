import { useState } from "react";
import { View, Text } from "../../components/Themed";
import { StyleSheet, Image, TextInput, Pressable } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { apiario } from "../../interfaces/apiario";
import { ScrollView } from "react-native-gesture-handler";
import { createApiario } from "../../services/apiariosService";

export default function CriarApiario() {
  const [isPressed, setIsPressed] = useState(false);
  const [nome, setNome] = useState("");
  const [flora, setFlora] = useState("");
  const [proximidade, setProximidade] = useState("");
  const [latitude, setLatitude] = useState("");
  const [longitude, setLongitude] = useState("");

  const navigation = useNavigation<StackTypes>();

  async function buttonHandler() {
    const apiario: apiario = {nome_apiario: nome, flora:flora, proximidade_agua: Number(proximidade), latitude:Number(latitude), longitude:Number(latitude)}
    //navigation.navigate("pages/mostrarColmeias", {apiario: apiario});
    try {
      await createApiario(apiario);
      navigation.navigate("pages/menu");
    } catch(erro) {
      console.log(erro);
      console.log("erro");
    }
  }

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  function handleChangeNome(texto: string): void {
    setNome(texto);
  }

  function handleChangeFlora(texto: string): void {
    setFlora(texto);
  }

  function handleChangeProximidade(texto: string): void {
    setProximidade(texto);
  }

  function handleChangeLatitude(texto: string): void {
    setLatitude(texto);
  }

  function handleChangeLongitude(texto: string): void {
    setLongitude(texto);
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Pedido de instalação de apiário </Text>
      <View style={styles.centercontainer}>
        <ScrollView style={{width: '100%'}}>
        <Text style={styles.title}>Informações gerais do apiário </Text>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Nome do apiário</Text>
          <TextInput
            style={styles.input}
            placeholder="Nome do apiário"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeNome(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Flora predominante</Text>
          <TextInput
            style={styles.input}
            placeholder="Flora predominante"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeFlora(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Proximidade da água</Text>
          <TextInput
            style={styles.input}
            placeholder="Proximidade da água"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeProximidade(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Latitude</Text>
          <TextInput
            style={styles.input}
            placeholder="Latitude"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeLatitude(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Longitude</Text>
          <TextInput
            style={styles.input}
            placeholder="Longitude"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeLongitude(texto)}
          ></TextInput>
        </View>
        <Pressable
          style={[
            styles.button,
            {
              transform: [{ scale: isPressed ? 0.95 : 1 }],
            },
          ]}
          onPress={() => buttonHandler()}
          onPressIn={handlePressIn}
          onPressOut={handlePressOut}
        >
          <Text style={styles.text}>Submeter Pedido</Text>
        </Pressable>
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: "#FFE5C4",
    paddingTop: 60,
    paddingLeft: 30,
    paddingRight: 30,
  },
  title: {
    fontSize: 30,
    marginBottom: 20,
  },
  input: {
    width: "100%",
    height: 50,
    borderWidth: 1,
    borderRadius: 15,
    paddingLeft: 10,
    marginTop: 5,
  },
  inputTitle: {
    width: "100%",
    backgroundColor: "#FFE5C4",
    marginTop: 10,
  },
  inputText: {
    fontWeight: "bold",
  },
  centercontainer: {
    width: "100%",
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#FFE5C4",
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
