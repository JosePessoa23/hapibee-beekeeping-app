import SelectMenu from "../../components/selectMenu";
import { StyleSheet, Pressable } from "react-native";
import { Text, View } from "../../components/Themed";
import { useNavigation, useRoute } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { useEffect, useState } from "react";
import { inspecao } from "../../interfaces/inspecao";

export default function Produtividade() {
  const [isPressed, setIsPressed] = useState(false);
  const [isPresseda, setIsPresseda] = useState(false);
  const [selected, setSelected] = useState("");
  const [colmeiaIdent, setColmeiaIdent] = useState(0);

  const navigation = useNavigation<StackTypes>();

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  const handlePressIna = () => {
    setIsPresseda(true);
  };

  const handlePressOuta = () => {
    setIsPresseda(false);
  };

  const [inspecao, setInspecao] = useState<inspecao>({
    higiene: "",
    tendencia_enxamear: "",
    agressividade: "",
    produtividade: "",
    capacidade_polinizadora: "",
    observaçoes: "",
  });

  var apiarioID: number = 0;
  var colmeiaNum: number = 0;
  const route = useRoute();

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (
          route.params != undefined &&
          "apiarioID" in route.params &&
          "colmeiaID" in route.params &&
          "inspecao" in route.params
        ) {
          apiarioID = route.params.apiarioID as number;
          colmeiaNum = route.params.colmeiaID as number;
          setColmeiaIdent(colmeiaNum);
          setInspecao(route.params.inspecao as inspecao);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  async function buttonHandler() {
    navigation.navigate("pages/capacidadePolinizadora", {
      apiarioID: apiarioID,
      colmeiaID: colmeiaIdent,
      inspecao: {
        higiene: inspecao.higiene,
        tendencia_enxamear: inspecao.tendencia_enxamear,
        agressividade: inspecao.agressividade,
        produtividade: selected,
        capacidade_polinizadora: inspecao.capacidade_polinizadora,
        observaçoes: inspecao.observaçoes,
      },
    });
  }

  async function buttonHandlera() {
    navigation.navigate("pages/agressividade", {
      apiarioID: apiarioID,
      colmeiaID: colmeiaNum,
      inspecao: {
        higiene: inspecao.higiene,
        tendencia_enxamear: inspecao.tendencia_enxamear,
        agressividade: inspecao.agressividade,
        produtividade: selected,
        capacidade_polinizadora: inspecao.capacidade_polinizadora,
        observaçoes: inspecao.observaçoes,
      },
    });
  }
  return (
    <View style={styles.topContainer}>
      <Text style={styles.title}>Formulário de inspeção</Text>
      <View style={styles.container}>
        <Text style={styles.optionTitle}>Produtividade</Text>
        <SelectMenu set={setSelected}></SelectMenu>
        <View style={styles.buttonView}>
          <Pressable
            style={[
              styles.button,
              {
                transform: [{ scale: isPresseda ? 0.95 : 1 }],
              },
            ]}
            onPress={() => buttonHandlera()}
            onPressIn={handlePressIna}
            onPressOut={handlePressOuta}
          >
            <Text style={styles.text}>Anterior</Text>
          </Pressable>
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
            <Text style={styles.text}>Seguinte</Text>
          </Pressable>
        </View>
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
    height: "100%",
    width: "100%",
  },
  topContainer: {
    flex: 1,
    backgroundColor: "#FFE5C4",
    alignItems: "center",
    paddingTop: 60,
    paddingLeft: 30,
    paddingRight: 30,
    height: "100%",
    width: "100%",
  },
  title: {
    fontSize: 30,
    marginBottom: 20,
  },
  optionTitle: {
    fontSize: 30,
    fontWeight: "bold",
    marginBottom: 10,
    marginTop: 20,
  },
  button: {
    width: "45%",
    backgroundColor: "#FFD000",
    padding: 10,
    textAlign: "center",
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 15,
    height: 80,
  },
  text: {
    fontSize: 20,
    fontWeight: "bold",
  },
  buttonView: {
    flexDirection: "row",
    backgroundColor: "#FFE5C4",
    alignItems: "center",
    justifyContent: "space-between",
    width: "100%",
    marginTop: 30,
  },
});
