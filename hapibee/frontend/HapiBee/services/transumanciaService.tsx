import AsyncStorage from "@react-native-async-storage/async-storage";
import { apiario } from "../interfaces/apiario";

// apiService.js
const BASE_URL = 'http://192.168.50.83:8081';

export const transumancia = async (apiario: apiario) => {
  try {


    //var token = await AsyncStorage.getItem('Authorization');

    const config = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
      body: JSON.stringify({'apiarioId': apiario.apiarioId, 'latitude': apiario.latitude, 'longitude': apiario.longitude,'nome_apiario':apiario.nome_apiario,'flora':apiario.flora,'colmeiaList':apiario.colmeiaList}),
    };

    const resposta = await fetch(`${BASE_URL}/transumancia`, config);

    if (!resposta.ok) {
      throw new Error('Erro na solicitação ao backend');
    }

    //const dados = await resposta.json();
    
    //return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};
