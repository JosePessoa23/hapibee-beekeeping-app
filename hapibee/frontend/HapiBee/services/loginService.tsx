import AsyncStorage from "@react-native-async-storage/async-storage";

// apiService.js
const BASE_URL = 'http://192.168.1.188:8080';

export const loginFetch = async (email: string, password: string) => {
  try {
    const config = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
      body: JSON.stringify({'username': email, 'password': password}),
    };

    const resposta = await fetch(`${BASE_URL}/auth/public/login`, config);

    if (!resposta.ok) {
      throw new Error('Erro na solicitação ao backend');
    }

    const token = resposta.headers.get('Authorization')
    const dados = await resposta.json();
    const userId = dados.userId;
    if(token != null && token != undefined){
        guardarToken(token,userId);
    }
    
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

const guardarToken = async (token: string,userId: string) => {
    try {
      await AsyncStorage.setItem('Authorization', token);
      await AsyncStorage.setItem('userId', userId);
      console.log('token salva com sucesso!');
    } catch (erro) {
      console.error('Erro ao salvar a token:', erro);
    }
  };