import { useState } from "react";
import { View, Text } from "../../components/Themed";
import { StyleSheet, Image, TextInput, Pressable } from "react-native";
import { useNavigation, useRoute } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { colmeia } from "../../interfaces/colmeia";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { apiario } from "../../interfaces/apiario";



export default function AdicionarColmeia() {
  const [isPressed, setIsPressed] = useState(false);
  const [ncolmeia, setNColmeia] = useState(0);
  const [tipo, setTipo] = useState("");
  const [nAlcas, setNAlcas] = useState(0);
  const [data, setData] = useState("");

  const route = useRoute();
  var apiario: apiario;
  if (
    route.params != undefined &&
    "apiario" in route.params
  ) {
    apiario = route.params.apiario as apiario;
  }
  
  const navigation = useNavigation<StackTypes>();

  const handleChangeNColmeia = (texto:string) => {
    setNColmeia(Number(texto));
  };

  const handleChangeNAlcas = (texto:string) => {
    setNAlcas(Number(texto));
  };

  const handleChangeTipo = (texto:string) => {
    setTipo(texto);
  };

  const handleData = (texto:string) => {
    setData(texto);
  };

  async function buttonHandler() {
    const listcolmeias: colmeia[] = await obterLista("colmeia");
    listcolmeias.push({
        numeroColmeia: ncolmeia,
        tipo: tipo,
        alças: nAlcas,
        dataRainha: data
    })
    await salvarLista("colmeia", listcolmeias);
    navigation.navigate("pages/mostrarColmeias", {apiario:apiario});
  }

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Pedido de instalação de apiário </Text>
      <View style={styles.centercontainer}>
        <Text style={styles.title}>Adicionar colmeia</Text>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Número da colmeia</Text>
          <TextInput
            style={styles.input}
            placeholder="Número da colmeia"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeNColmeia(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Tipo da colmeia</Text>
          <TextInput
            style={styles.input}
            placeholder="Tipo da colmeia"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeTipo(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Número de alças</Text>
          <TextInput
            style={styles.input}
            placeholder="Número de alças"
            placeholderTextColor="black"
            onChangeText={(texto) => handleChangeNAlcas(texto)}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Data da rainha</Text>
          <TextInput
            style={styles.input}
            placeholder="Data da rainha"
            placeholderTextColor="black"
            onChangeText={(texto) => handleData(texto)}
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
          <Text style={styles.text}>Adicionar colmeia</Text>
        </Pressable>
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

const salvarLista = async (chave: string, lista: colmeia[]) => {
    try {
      const listaJSON = JSON.stringify(lista);
      await AsyncStorage.setItem(chave, listaJSON);
      console.log('Lista salva com sucesso!');
    } catch (erro) {
      console.error('Erro ao salvar a lista:', erro);
    }
  };

  const obterLista = async (chave: string) => {
    try {
      const listaJSON = await AsyncStorage.getItem(chave);
      return listaJSON != null ? JSON.parse(listaJSON) : [];
    } catch (erro) {
      console.error('Erro ao obter a lista:', erro);
      return [];
    }
  };
