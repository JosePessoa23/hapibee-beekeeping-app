import { StyleSheet, Image, Pressable } from "react-native";

import { Text, View } from "../../components/Themed";
import { TextInput } from "react-native-gesture-handler";
import PasswordInput from "../../components/PasswordInput";
import ButtonComponent from "../../components/ButtonComponent";
import { useNavigation, useRoute } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { useEffect, useState } from "react";
import { getApiarioByID } from "../../services/apiariosService";
import { apiario } from "../../interfaces/apiario";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { transumancia } from "../../services/transumanciaService";

export default function PedidoTransumancia() {
  const [isPressed, setIsPressed] = useState(false);

  const [apiario, setApiario] = useState<apiario>({
    apiarioId: 0,
    nome_apiario: "",
    flora: "",
    proximidade_agua: 0,
    latitude: 0,
    longitude: 0,
    colmeiaList: [],
  });

  const navigation = useNavigation<StackTypes>();

  const route = useRoute();
  var apiarioID: number = 0;
  if (route.params != undefined && "apiarioID" in route.params) {
    apiarioID = route.params.apiarioID as number;
  }
  useEffect(() => {
    const fetchData = async () => {
      try {
        if (apiarioID != undefined) {
          const resposta = await getApiarioByID(apiarioID);
          setApiario(resposta);
        }
      } catch (error) {
        try {
          const listapiarios: apiario[] = await obterLista("apiarios");
          listapiarios.forEach((apiario) => {
            if (apiario.apiarioId == apiarioID) {
              setApiario(apiario);
            }
          });
        } catch (errorObterLista) {}
      }
    };

    fetchData().catch(console.error);
  }, []);

  const handleChangeLongitude = (numero:number) => {
    setApiario({
      apiarioId: apiario.apiarioId,
      nome_apiario: apiario.nome_apiario,
      flora: apiario.flora,
      proximidade_agua: apiario.proximidade_agua,
      latitude: apiario.latitude,
      longitude: numero,
      colmeiaList: apiario.colmeiaList,
    });
  };

  const handleChangeLatitude = (numero:number) => {
    setApiario({
      apiarioId: apiario.apiarioId,
      nome_apiario: apiario.nome_apiario,
      flora: apiario.flora,
      proximidade_agua: apiario.proximidade_agua,
      latitude: numero,
      longitude: apiario.longitude,
      colmeiaList: apiario.colmeiaList,
    });
  };

  
  

  async function buttonHandler() {
    await transumancia(apiario);
    navigation.navigate("pages/menu2");
  }

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Apiário {apiario.nome_apiario}</Text>
      <View style={styles.centercontainer}>
        <Text style={styles.title}>Pedido de transumância</Text>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Latitude</Text>
          <TextInput
            style={styles.input}
            placeholder="Latitude"
            placeholderTextColor="black"
            onChangeText={(texto) => {
              const numericValue = parseFloat(texto);
              handleChangeLatitude(numericValue);
            }}
          ></TextInput>
        </View>
        <View style={styles.inputTitle}>
          <Text style={styles.inputText}>Longitude</Text>
          <TextInput
            style={styles.input}
            placeholder="Longitude"
            placeholderTextColor="black"
            onChangeText={(texto) => {
              const numericValue = parseFloat(texto);
              handleChangeLongitude(numericValue);
            }}
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
          <Text style={styles.text}>Sumbeter pedido</Text>
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

const salvarLista = async (chave: string, lista: apiario[]) => {
    try {
      const listaJSON = JSON.stringify(lista);
      await AsyncStorage.setItem(chave, listaJSON);
      console.log("Lista salva com sucesso!");
    } catch (erro) {
      console.error("Erro ao salvar a lista:", erro);
    }
  };
  
  const obterLista = async (chave: string) => {
    try {
      const listaJSON = await AsyncStorage.getItem(chave);
      return listaJSON != null ? JSON.parse(listaJSON) : [];
    } catch (erro) {
      console.error("Erro ao obter a lista:", erro);
      return [];
    }
  };
