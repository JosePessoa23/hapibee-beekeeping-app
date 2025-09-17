import AsyncStorage from "@react-native-async-storage/async-storage";
import { user } from "../interfaces/user";
import { apiario } from "../interfaces/apiario";
import { getUserByID } from "./userService";

// apiService.js
const BASE_URL = 'http://192.168.50.83:8081';

export const declaracaoAnual = async () => {
  try {

    const listapiarios: apiario[] = await obterLista("apiarios");
    const user = await getUserByID();
    
    const config = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
      body: JSON.stringify({'user':user,'listapiarios':listapiarios}),
    };

    const resposta = await fetch(`${BASE_URL}/declaracao`, config);

    if (!resposta.ok) {
      throw new Error('Erro na solicitação ao backend');
    }

    const dados = await resposta.json();
    
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
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