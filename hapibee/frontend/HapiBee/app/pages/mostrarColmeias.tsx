import { useEffect, useState } from "react";
import { View, Text } from "../../components/Themed";
import { StyleSheet, Image, TextInput, Pressable } from "react-native";
import {
  useFocusEffect,
  useNavigation,
  useRoute,
} from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { colmeia } from "../../interfaces/colmeia";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { apiario } from "../../interfaces/apiario";
import {
  adicionarColmeiasApiario,
  createApiario,
} from "../../services/apiariosService";

export default function MostrarColmeias() {
  const [colmeias, setColmeia] = useState<colmeia[]>([]);
  const [isPressed1, setIsPressed1] = useState(false);
  const [isPressed2, setIsPressed2] = useState(false);

  const route = useRoute();
  var apiario: apiario;
  if (route.params != undefined && "apiario" in route.params) {
    apiario = route.params.apiario as apiario;
  }

  useFocusEffect(() => {
    const carregarDados = async () => {
      try {
        // Realize operações assíncronas aqui
        const listcolmeias = await obterLista("colmeia");
        setColmeia(listcolmeias);
      } catch (error) {
        console.error("Erro ao carregar dados:", error);
      }
    };

    carregarDados();
  });

  const navigation = useNavigation<StackTypes>();

  function addColmeiaHandler() {
    navigation.navigate("pages/adicionarColmeia", { apiario: apiario });
  }

  async function submeterHandler() {
    try {
      if (apiario.apiarioId) {
        await adicionarColmeiasApiario(apiario.apiarioId, colmeias);
        limparLista("colmeia")
        navigation.navigate("pages/menu");
      }
    } catch (erro) {
      console.log(erro);
      console.log("erro");
    }
  }

  const handlePressIn1 = () => {
    setIsPressed1(true);
  };

  const handlePressOut1 = () => {
    setIsPressed1(false);
  };

  const handlePressIn2 = () => {
    setIsPressed2(true);
  };

  const handlePressOut2 = () => {
    setIsPressed2(false);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Instalação de colmeias </Text>
      <View style={styles.centercontainer}>
        <Text style={styles.title}>Colmeias</Text>
        <View style={styles.row}>
          <View style={[styles.col, { flex: 1, paddingRight: 8 }]}>
            <Text>Nº </Text>
          </View>
          <View style={[styles.col, { flex: 1, paddingRight: 8 }]}>
            <Text>Tipo </Text>
          </View>
          <View style={[styles.col, { flex: 1 }]}>
            <Text>Nº Alças </Text>
          </View>
        </View>

        {colmeias.map((colmeia, index) => (
          <View key={index} style={styles.row}>
            <View style={[styles.col, { flex: 1, paddingRight: 8 }]}>
              <Text>{colmeia.numeroColmeia}</Text>
            </View>
            <View style={[styles.col, { flex: 1, paddingRight: 8 }]}>
              <Text>{colmeia.tipo}</Text>
            </View>
            <View style={[styles.col, { flex: 1 }]}>
              <Text>{colmeia.alças}</Text>
            </View>
          </View>
        ))}
        <Pressable
          style={[
            styles.button,
            {
              transform: [{ scale: isPressed1 ? 0.95 : 1 }],
            },
          ]}
          onPress={() => addColmeiaHandler()}
          onPressIn={handlePressIn1}
          onPressOut={handlePressOut1}
        >
          <Text style={styles.text}>Adicionar colmeia</Text>
        </Pressable>
        <Pressable
          style={[
            styles.button,
            {
              transform: [{ scale: isPressed2 ? 0.95 : 1 }],
            },
          ]}
          onPress={() => submeterHandler()}
          onPressIn={handlePressIn2}
          onPressOut={handlePressOut2}
        >
          <Text style={styles.text}>Submeter pedido</Text>
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
