import AsyncStorage from "@react-native-async-storage/async-storage";
import { user } from "../interfaces/user";

const BASE_URL = 'http://192.168.1.188:8080';

export const getUserByID = async () => {

  try {
    var userId = await AsyncStorage.getItem('userId');

    const config = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
    };

    const resposta = await fetch(`${BASE_URL}/admin/user/${userId}`, config);

    if (!resposta.ok) {
      throw new Error('Erro na solicitação ao backend');
    }    

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

export const putUser = async (user: user) => {

  try {
    var userId = await AsyncStorage.getItem('userId');

    const config = {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
      body: JSON.stringify({
        'fullName': user.fullName,
      'nif': user.nif,
      'morada': user.morada,
      'codigo_Postal': user.codigo_Postal,
      'phonenumber': user.phonenumber,
      'numero_Apicultor': user.numero_Apicultor,
      }),
    };

    const resposta = await fetch(`${BASE_URL}/admin/user/${userId}`, config);

    if (!resposta.ok) {
      throw new Error('Erro na solicitação ao backend');
    }    

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};