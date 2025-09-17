import { useEffect, useState } from "react";
import { View, Text } from "../../components/Themed";
import { StyleSheet, Image, TextInput, Pressable, ScrollView } from "react-native";
import { useFocusEffect, useNavigation } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { apiario } from "../../interfaces/apiario";
import GoBackArrow from "../../components/GoBackArrow";
import { getApiarios } from "../../services/apiariosService";
import { declaracaoAnual } from "../../services/declaracaoAnualService";

export default function InfoApiarioDA() {
  const [pressedApiarioIndex, setPressedApiarioIndex] = useState<number | null>(
    null
  );
  const [isPressed, setIsPressed] = useState(false);
  const [apiarios, setApiarios] = useState<apiario[]>([]);

  useEffect(() => {
    // declare the data fetching function
    const fetchData = async () => {
      try {
        const resposta = await getApiarios();
        setApiarios(resposta);
        salvarLista("apiarios", resposta);
      } catch (error) {
        try {
          const listapiarios: apiario[] = await obterLista("apiarios");
          setApiarios(listapiarios);
        } catch (errorObterLista) {
          console.error("Erro ao obter a lista de apiários:", errorObterLista);
        }
      }
    };
  
    // call the function
    fetchData();
  }, []);

  const navigation = useNavigation<StackTypes>();

  function buttonHandler() {
    if (pressedApiarioIndex !== null) {
        const apiarioSelecionado = apiarios[pressedApiarioIndex];
        const apiarioID = apiarios[pressedApiarioIndex].apiarioId;
        
        if (apiarioID != undefined && apiarioID != null) {
          navigation.navigate("pages/infoApiario", { apiarioID: apiarioID });
        } 
      }
  }

  const handlePressIn = (index: number) => {
    setPressedApiarioIndex(index);
  };

  const handlePressOut = () => {
    setPressedApiarioIndex(null);
  };

  const handlePressInButton = () => {
    setIsPressed(true);
  };

  const handlePressOutButton = () => {
    setIsPressed(false);
  };

  async function loginHandler(){
    await declaracaoAnual();
    navigation.navigate("pages/menu");
  }



  return (
    <View style={styles.container}>
        <View style={styles.flexDirection}>
        <View style={styles.arrow}>
      <GoBackArrow navigation={navigation} />
    </View>
    <Text style={styles.title}>Apiários</Text>
      </View>


      <ScrollView style={styles.scrollView}>
        <View style={styles.centercontainer}>
          {apiarios.map((apiario, index) => (
            <Pressable
              key={apiario.apiarioId} // Adicione uma chave única, como o id do apiário
              style={[
                styles.card,
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
              <Text style={styles.text}>Nome: {apiario.nome_apiario}</Text>
              <Text style={styles.text}>Flora: {apiario.flora}</Text>
              <Text style={styles.text}>Latitude: {apiario.latitude}</Text>
              <Text style={styles.text}>Longitude: {apiario.longitude}</Text>
            </Pressable>
          ))}
        </View>
      
    
        <Pressable
    style={[
        styles.button,
        {
          transform: [{ scale: isPressed ? 0.95 : 1 }], 
        },
      ]}
      onPress={() => loginHandler()}
      onPressIn={handlePressInButton}
      onPressOut={handlePressOutButton}
    >
      <Text style={styles.text}>Submeter</Text>
    </Pressable>
    </ScrollView>
      </View>
    
  );
}

const styles = StyleSheet.create({
    arrow: {
    
        justifyContent: "flex-start",
        backgroundColor: "#FFE5C4",
        paddingRight: "23%"
      },
      flexDirection: {
        width: "100%",
        flexDirection: "row",
        backgroundColor: "#FFE5C4"
      },
      scrollView: {
        width: "100%",
        backgroundColor: "#FFE5C4"
      },
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
    backgroundColor: "#FFD000",
    width: "100%",
    height: 150,
    marginTop: 10,
    marginBottom: 10,
    borderRadius: 30,
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

const salvarLista = async (chave: string, lista: apiario[]) => {
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
