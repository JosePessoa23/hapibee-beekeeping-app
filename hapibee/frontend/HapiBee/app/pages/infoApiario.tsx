import React, { useEffect, useState } from "react";
import { View, Text } from "../../components/Themed";
import {
  StyleSheet,
  Image,
  TextInput,
  Pressable,
  ScrollView,
} from "react-native";
import {
  useFocusEffect,
  useNavigation,
  useRoute,
} from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { getApiarioByID, getApiarios } from "../../services/apiariosService";
import { apiario } from "../../interfaces/apiario";
import AsyncStorage from "@react-native-async-storage/async-storage";

export default function InfoApiario() {
  const [apiario, setApiario] = useState<apiario>({
    apiarioId: 0,
    nome_apiario: "Nan",
    flora: "Nan",
    proximidade_agua: 0,
    latitude: 0,
    longitude: 0,
    colmeiaList: [],
  });
  const navigation = useNavigation<StackTypes>();
  const [isPressed, setIsPressed] = useState(false);
  const [isPressedC, setIsPressedC] = useState(false);
  const [isPressedH, setIsPressedH] = useState(false);

  const [pressedApiarioIndex, setPressedApiarioIndex] = useState<number | null>(
    null
  );


  const handleTransumancia = () => {
    navigation.navigate("pages/pedidoTransumancia", { apiarioID: apiarioID });
  };

  const handlePressInT = () => {
    setIsPressed(true);
  };

  const handlePressOutT = () => {
    setIsPressed(false);
  };

  const handlePressIn = (index: number) => {
    setPressedApiarioIndex(index);
  };

  const handlePressOut = () => {
    setPressedApiarioIndex(null);
  };

  const handlePressInC = () => {
    setIsPressedC(true);
  };

  const handlePressOutC = () => {
    setIsPressedC(false);
  };

  const handlePressInH = () => {
    setIsPressedH(true);
  };

  const handlePressOutH = () => {
    setIsPressedH(false);
  };

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

  function buttonHandler() {
    if (pressedApiarioIndex !== null && apiario.colmeiaList) {
      const apiarioSelecionado = apiario.colmeiaList[pressedApiarioIndex];
      const colmeiaID = apiario.colmeiaList[pressedApiarioIndex].id;
      console.log(colmeiaID);

      if (apiarioID != undefined && apiarioID != null && colmeiaID) {
        navigation.navigate("pages/infoColmeia", {
          apiarioID: apiarioID,
          colmeiaID: colmeiaID,
        });
      }
    }
  }

  function buttonHandlerC() {
    navigation.navigate("pages/mostrarColmeias",{apiario});
  }

  function buttonHandlerH() {
    if(apiario.apiarioId){
    navigation.navigate("pages/historicoApiario", {apiarioID: apiario.apiarioId});
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Apiário {apiario?.nome_apiario} </Text>
      <ScrollView style={styles.scrollView}>
        <View style={styles.centercontainer}>
          <View
            key={apiario.apiarioId} // Adicione uma chave única, como o id do apiário
            style={styles.card}
          >
            <Text style={styles.text}>Nome: {apiario.nome_apiario}</Text>
            <Text style={styles.text}>Flora: {apiario.flora}</Text>
            <Text style={styles.text}>Latitude: {apiario.latitude}</Text>
            <Text style={styles.text}>Longitude: {apiario.longitude}</Text>
            <Text style={styles.text}>
              Proximidade de água: {apiario.proximidade_agua}m
            </Text>
          </View>
          <Pressable
            style={[
              styles.button,
              {
                transform: [{ scale: isPressed ? 0.95 : 1 }],
              },
            ]}
            onPressIn={handlePressInT}
            onPressOut={handlePressOutT}
            onPress={handleTransumancia}
          >
            <Text style={styles.text}>Pedido de transumância</Text>
          </Pressable>
          <Pressable
            style={[
              styles.button,
              {
                transform: [{ scale: isPressedC ? 0.95 : 1 }],
              },
            ]}
            onPressIn={handlePressInC}
            onPressOut={handlePressOutC}
            onPress={buttonHandlerC}
          >
            <Text style={styles.text}>Adicionar Colmeia</Text>
          </Pressable>
          <Pressable
            style={[
              styles.button,
              {
                transform: [{ scale: isPressedH ? 0.95 : 1 }],
              },
            ]}
            onPressIn={handlePressInH}
            onPressOut={handlePressOutH}
            onPress={buttonHandlerH}
          >
            <Text style={styles.text}>Visualizar histórico</Text>
          </Pressable>
          <Text style={[styles.title, { paddingTop: 20 }]}>Colmeias</Text>

          {apiario.colmeiaList &&
            apiario.colmeiaList.map((colmeia, index) => (
              <Pressable
                key={colmeia.numeroColmeia} // Adicione uma chave única, como o id do apiário
                style={[
                  styles.colmeiasStyle,
                  {
                    transform: [
                      { scale: pressedApiarioIndex === index ? 0.95 : 1 },
                    ],
                  },
                ]}
                onPressIn={() => handlePressIn(index)}
                onPressOut={handlePressOut}
                onPress={buttonHandler}
              >
                <Text style={styles.text}>Número: {colmeia.numeroColmeia}</Text>
                <Text style={styles.text}>Tipo: {colmeia.tipo}</Text>
              </Pressable>
            ))}
        </View>
      </ScrollView>
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
  card: {
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "transparent",
    width: "100%",
    marginTop: 30,
    marginBottom: 10,
    borderRadius: 30,
    borderWidth: 5,
    paddingTop: 20,
    paddingBottom: 20,
  },
  colmeiasStyle: {
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#FFD000",
    width: "100%",
    height: 150,
    marginTop: 10,
    marginBottom: 10,
    borderRadius: 30,
  },
  tabela: {
    width: "100%",
    marginTop: 10,
  },
  title: {
    fontSize: 30,
    marginBottom: 20,
  },
  row: {
    flexDirection: "row",
    backgroundColor: "#FFE5C4",
    borderBottomWidth: 2,
  },
  col: {
    flexDirection: "column",
    backgroundColor: "#FFE5C4",
  },
  scrollView: {
    width: "100%",
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
    padding: 20,
    textAlign: "center",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 20,
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
