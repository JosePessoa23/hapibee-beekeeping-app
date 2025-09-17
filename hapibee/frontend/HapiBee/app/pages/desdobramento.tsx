import SelectMenu from "../../components/selectMenu";
import { StyleSheet, Pressable, TextInput } from "react-native";
import { Text, View } from "../../components/Themed";
import { useNavigation, useRoute } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import { useState } from "react";
import { desdobrarComeia } from "../../services/colmeiaService";

export default function Desdobramento() {
  const [isPressed, setIsPressed] = useState(false);
  const [isPressedMais, setIsPressedMais] = useState(false);
  const [isPressedMenos, setIsPressedMenos] = useState(false);
  const [nAlças, setNAlcas] = useState(1);

  const navigation = useNavigation<StackTypes>();

  const route = useRoute();
  var apiarioID: number = 0;
  var colmeiaNum: number = 0;
  var numAlcas: number = 10;
  if (
    route.params != undefined &&
    "apiarioID" in route.params &&
    "colmeiaID" in route.params &&
    "nAlcas" in route.params
  ) {
    apiarioID = route.params.apiarioID as number;
    colmeiaNum = route.params.colmeiaID as number;
    numAlcas = route.params.nAlcas as number;
  }

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  const handlePressInMais = () => {
    setIsPressedMais(true);
  };

  const handlePressOutMais = () => {
    setIsPressedMais(false);
  };

  const handlePressInMenos = () => {
    setIsPressedMenos(true);
  };

  const handlePressOutMenos = () => {
    setIsPressedMenos(false);
  };

  const handlePressMais = () => {
    var numero = nAlças;
    if (numero < numAlcas - 1) {
      setNAlcas(numero + 1);
    }
  };

  const handlePressMenos = () => {
    var numero = nAlças;
    if (numero != 1) {
      setNAlcas(numero - 1);
    }
  };

  async function buttonHandler() {
    try {
      await desdobrarComeia(colmeiaNum, nAlças);
      navigation.goBack();
    } catch {
      console.log("erro");
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Desdobramento</Text>
      <Text style={styles.text}>Número de alças para a nova colmeia</Text>
      <Pressable
        style={[
          styles.button,
          {
            transform: [{ scale: isPressedMais ? 0.95 : 1 }],
          },
        ]}
        onPress={() => handlePressMais()}
        onPressIn={handlePressInMais}
        onPressOut={handlePressOutMais}
      >
        <Text style={styles.textButton}>+</Text>
      </Pressable>
      <Text style={styles.numero}>{nAlças}</Text>
      <Pressable
        style={[
          styles.button,
          {
            transform: [{ scale: isPressedMenos ? 0.95 : 1 }],
          },
        ]}
        onPress={() => handlePressMenos()}
        onPressIn={handlePressInMenos}
        onPressOut={handlePressOutMenos}
      >
        <Text style={styles.textButton}>-</Text>
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
        <Text style={styles.text}>Submeter</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
    backgroundColor: "#FFE5C4",
    justifyContent: "center",
    paddingTop: 60,
    paddingLeft: 30,
    paddingRight: 30,
  },
  numero: {
    marginTop: 30,
    fontSize: 30,
  },
  title: {
    fontSize: 30,
    marginBottom: 20,
  },
  inputText: {
    fontWeight: "bold",
  },
  input: {
    width: "100%",
    height: 50,
    borderWidth: 1,
    borderRadius: 15,
    paddingLeft: 10,
    marginTop: 10,
  },
  optionTitle: {
    fontSize: 30,
    fontWeight: "bold",
    marginBottom: 10,
    marginTop: 20,
  },
  button: {
    marginTop: 30,
    width: "100%",
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
  },
  textButton: {
    fontSize: 50,
    fontWeight: "bold",
  },
  inputTitle: {
    width: "100%",
    backgroundColor: "#FFE5C4",
    marginTop: 10,
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
