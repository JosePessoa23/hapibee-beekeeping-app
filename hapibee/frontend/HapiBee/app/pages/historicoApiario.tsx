import { View, Text } from "../../components/Themed";
import { StyleSheet } from "react-native";

import AsyncStorage from "@react-native-async-storage/async-storage";
import { useEffect, useState } from "react";
import { getHistorico } from "../../services/apiariosService";
import {
  useFocusEffect,
  useNavigation,
  useRoute,
} from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { historico } from "../../interfaces/historico";

export default function HistoricoApiario() {
  const [historico, setHistorico] = useState<historico[]>([]);
  const navigation = useNavigation<StackTypes>();
  const route = useRoute();
  var apiarioID: number = 0;
  if (route.params != undefined && "apiarioID" in route.params) {
    apiarioID = route.params.apiarioID as number;
  }
  useEffect(() => {
    // declare the data fetching function
    const fetchData = async () => {
      try {
        const resposta = await getHistorico(apiarioID);
        setHistorico(resposta);
      } catch (error) {
      }
    };

    // call the function
    fetchData();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Histórico do Apiário</Text>
      <View style={styles.centercontainer}>
        <View style={styles.row}>
          <View style={[styles.col, { flex: 1, paddingRight: 50 }]}>
            <Text>Tipo</Text>
          </View>
          <View style={[styles.col, { flex: 1, paddingRight: 80 }]}>
            <Text>Nº Colmeia</Text>
          </View>
          <View style={[styles.col, { flex: 1 }]}>
            <Text>Data</Text>
          </View>
        </View>
        {historico.map((historico, index) => (
          <View key={index} style={styles.row}>
            <View style={[styles.col, { flex: 1, paddingRight: 50 }]}>
              <Text>{historico.tipo}</Text>
            </View>
            <View style={[styles.col, { flex: 1, paddingRight: 80 }]}>
              <Text>{historico.colmeiaId}</Text>
            </View>
            <View style={[styles.col, { flex: 1 }]}>
              <Text>{historico.date.split("T")[0]}</Text>
            </View>
          </View>
        ))}
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
  centercontainer: {
    width: "100%",
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#FFE5C4",
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

const obterLista = async (chave: string) => {
  try {
    const listaJSON = await AsyncStorage.getItem(chave);
    return listaJSON != null ? JSON.parse(listaJSON) : [];
  } catch (erro) {
    console.error("Erro ao obter a lista:", erro);
    return [];
  }
};

const limparLista = async (chave: string) => {
  AsyncStorage.removeItem(chave);
};
