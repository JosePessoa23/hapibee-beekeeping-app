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
import { colmeia } from "../../interfaces/colmeia";
import { inspecao } from "../../interfaces/inspecao";

export default function InfoColmeia() {
  const [apiario, setApiario] = useState<apiario>({
    apiarioId: 0,
    nome_apiario: "Nan",
    flora: "Nan",
    proximidade_agua: 0,
    latitude: 0,
    longitude: 0,
    colmeiaList: [],
  });

  const [colmeia, setColmeia] = useState<colmeia>({
    id: 0,
    numeroColmeia: 0,
    tipo: "",
    alças: 0,
    dataRainha: "",
  });
  const [inspecao, setInspecao] = useState<inspecao>({
    higiene: "",
    tendencia_enxamear: "",
    agressividade: "",
    produtividade: "",
    capacidade_polinizadora:"",
    observaçoes: "",
  });
  const navigation = useNavigation<StackTypes>();
  const [isPressed, setIsPressed] = useState(false);
  const [isPressedD, setIsPressedD] = useState(false);

  const [pressedApiarioIndex, setPressedApiarioIndex] = useState<number | null>(
    null
  );

  const handleDesdobramento = () => {
    if (colmeia.id) {
      navigation.navigate("pages/desdobramento", {
        apiarioID: apiarioID,
        colmeiaID: colmeia.id,
        nAlcas: colmeia.alças,
      });
    }
  };

  const handleRegisto = () => {
    if(colmeia.id){
      console.log("eqqqq " + colmeia.id)
    navigation.navigate("pages/comportamentoHigienico", {
      apiarioID: apiarioID,
      colmeiaID: colmeia.id,
      inspecao: inspecao
    });
  }
  };

  const handlePressInT = () => {
    setIsPressed(true);
  };

  const handlePressOutT = () => {
    setIsPressed(false);
  };

  const handlePressIn = () => {
    setIsPressedD(true);
  };

  const handlePressOut = () => {
    setIsPressedD(false);
  };

  const route = useRoute();
  var apiarioID: number = 0;
  var colmeiaNum: number = 0;
  if (
    route.params != undefined &&
    "apiarioID" in route.params &&
    "colmeiaID" in route.params
  ) {
    apiarioID = route.params.apiarioID as number;
    colmeiaNum = route.params.colmeiaID as number;
  }
  useEffect(() => {
    const fetchData = async () => {
      try {
        if (apiarioID !== undefined) {
          const resposta = await getApiarioByID(apiarioID);
          setApiario(resposta);
        }
      } catch (error) {
        try {
          const listApiarios: apiario[] = await obterLista("apiarios");
          const apiarioEncontrado = listApiarios.find(
            (a) => a.apiarioId === apiarioID
          );
          if (apiarioEncontrado) {
            setApiario(apiarioEncontrado);
          }
        } catch (errorObterLista) {}
      }
    };

    fetchData().catch(console.error);
  }, [apiarioID]); // Adicione apiarioID como uma dependência

  // Atualize o estado da colmeia quando o apiario muda
  useEffect(() => {
    const colmeiaEncontrada = apiario.colmeiaList?.find(
      (colmeia) => colmeia.id === colmeiaNum
    );
    

    if (colmeiaEncontrada) {
      setColmeia(colmeiaEncontrada);
    }
  }, [apiario, colmeiaNum]);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>
        Colmeia do apiário {apiario?.nome_apiario}
      </Text>
      <ScrollView style={styles.scrollView}>
        <View style={styles.centercontainer}>
          <View
            key={apiario.apiarioId} // Adicione uma chave única, como o id do apiário
            style={styles.card}
          >
            <Text style={styles.text}>Numero: {colmeia.numeroColmeia}</Text>
            <Text style={styles.text}>Tipo: {colmeia.tipo}</Text>
            <Text style={styles.text}>Número de alças: {colmeia.alças}</Text>
            <Text style={styles.text}>
              Data da rainha: {colmeia.dataRainha}
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
            onPress={handleRegisto}
          >
            <Text style={styles.text}>Adiconar Registo</Text>
          </Pressable>
          <Pressable
            style={[
              styles.button,
              {
                transform: [{ scale: isPressedD ? 0.95 : 1 }],
              },
            ]}
            onPressIn={handlePressIn}
            onPressOut={handlePressOut}
            onPress={handleDesdobramento}
          >
            <Text style={styles.text}>Desdobramento</Text>
          </Pressable>
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
