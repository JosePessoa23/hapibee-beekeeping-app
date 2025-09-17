import { useState,useEffect } from "react";
import { View, Text } from "../../components/Themed";
import { StyleSheet, Image,Pressable, ScrollView } from "react-native";
import { TextInput } from "react-native-gesture-handler";
import { useNavigation } from "@react-navigation/native";
import { StackTypes } from "../_layout";
import GoBackArrow from "../../components/GoBackArrow";
import { getUserByID, putUser } from "../../services/userService";
import { user } from "../../interfaces/user";

export default function InfoApicultorDA() {
    const [isPressed, setIsPressed] = useState(false);
    const [user, setUser] = useState<user>({
      userId:"",
      fullName: "",
    nif: "",
    morada: "",
    codigo_Postal: "",
    phonenumber: "",
    numero_Apicultor: "",
    });

    const navigation = useNavigation<StackTypes>();
  
    const handlePressIn = () => {
      setIsPressed(true);
    };
  
    const handlePressOut = () => {
      setIsPressed(false);
    };
  
    async function loginHandler(){
      await putUser(user);
      navigation.navigate("pages/infoApiarioDA");
    }

    const handleChangeFullName = (texto:string) => {
      setUser({
        fullName: texto,
      nif: user.nif,
      morada: user.morada,
      codigo_Postal: user.codigo_Postal,
      phonenumber: user.phonenumber,
      numero_Apicultor: user.numero_Apicultor,
      });
    };

    useEffect(() => {
      // declare the data fetching function
      const fetchData = async () => {
        const resposta = await getUserByID();
        setUser(resposta);
      };
  
      // call the function
      fetchData()
        // make sure to catch any error
        .catch(console.error);
    },[]);

    
    
    
    
  return (
    <ScrollView style={styles.scrollView}>
    <View style={styles.container}>
      <View style={styles.flexDirection}>
        <View style={styles.arrow}>
      <GoBackArrow navigation={navigation} />
    </View>
      <Text style={styles.title}>Informação pessoal </Text>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>Nome</Text>
        <TextInput
          style={styles.input}
          placeholder="Nome"
          placeholderTextColor="#707070"
          value={user.fullName}
          onChangeText={(texto) => handleChangeFullName(texto)}
        ></TextInput>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>NIF</Text>
        <TextInput
          style={styles.input}
          placeholder="NIF"
          placeholderTextColor="#707070"
          value={user.nif}
        ></TextInput>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>Morada</Text>
        <TextInput
          style={styles.input}
          placeholder="Morada"
          placeholderTextColor="#707070"
          value={user.morada}
        ></TextInput>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>Código Postal</Text>
        <TextInput
          style={styles.input}
          placeholder="Código Postal"
          placeholderTextColor="#707070"
          value={user.codigo_Postal}
        ></TextInput>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>Nº de telemóvel</Text>
        <TextInput
          style={styles.input}
          placeholder="Nº de telemóvel"
          placeholderTextColor="#707070"
          value={user.phonenumber}
        ></TextInput>
      </View>
      <View style={styles.inputTitle}>
        <Text style={styles.inputText}>Nº de registo do apicultor</Text>
        <TextInput
          style={styles.input}
          placeholder="Nº de registo do apicultor"
          placeholderTextColor="#707070"
          value={user.numero_Apicultor}
        ></TextInput>
      </View>
      <Pressable
    style={[
        styles.button,
        {
          transform: [{ scale: isPressed ? 0.95 : 1 }], 
        },
      ]}
      onPress={() => loginHandler()}
      onPressIn={handlePressIn}
      onPressOut={handlePressOut}
    >
      <Text style={styles.text}>Seguinte</Text>
    </Pressable>
      </View>
      </ScrollView>
  );
}

const styles = StyleSheet.create({
  arrow: {
    justifyContent: "flex-start",
    backgroundColor: "#FFE5C4",
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
  text: {
    fontSize: 20,
    fontWeight: 'bold',
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
    marginTop: 5
  },
  inputTitle: {
    width: '100%',
    backgroundColor: "#FFE5C4",
    marginTop: 10,
  },
  inputText: {
    fontWeight: "bold",
  },
  centercontainer:{
    width: '100%',
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#FFE5C4",

  }
});
