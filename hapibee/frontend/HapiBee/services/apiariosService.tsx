import AsyncStorage from "@react-native-async-storage/async-storage";
import { apiario } from "../interfaces/apiario";
import { colmeia } from "../interfaces/colmeia";

const BASE_URL = "http://192.168.1.188:8080";
const BASE_URL_PORTAL = "http://192.168.1.188:8081";

export const getApiarios = async () => {
  try {
    var userId = await AsyncStorage.getItem("userId");
    const token = await AsyncStorage.getItem("Authorization");

    const headers: HeadersInit = {
      "Content-Type": "application/json",
    };

    if (token) {
      headers["Authorization"] = token;
    }

   
      const config = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          //Authorization: token,
          // Adicione headers adicionais, como tokens de autenticação, conforme necessário
        },
        // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
      };

      const timeoutPromise = new Promise<Response>((_, reject) => {
        setTimeout(() => {
          reject(new Error("Tempo limite de requisição excedido"));
        }, 5000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
      });

      const respostaPromise = fetch(`${BASE_URL}/apiarios`, config);

      // Use Promise.race para resolver ou rejeitar com a primeira promessa que for resolvida ou rejeitada
      const resposta: Response = await Promise.race([
        respostaPromise,
        timeoutPromise,
      ]);

      if (!resposta.ok) {
        throw new Error("Erro na solicitação ao backend");
      }

      const dados = await resposta.json();
      return dados;
 
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

export const getApiarioByID = async (id: number) => {
  try {
    const config = {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
      // Adicione o corpo da solicitação se estiver fazendo um pedido POST ou PUT
    };

    const timeoutPromise = new Promise<Response>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Tempo limite de requisição excedido"));
      }, 5000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
    });

    const respostaPromise = fetch(`${BASE_URL}/apiarios/${id}`, config);

    // Use Promise.race para resolver ou rejeitar com a primeira promessa que for resolvida ou rejeitada
    const resposta: Response = await Promise.race([
      respostaPromise,
      timeoutPromise,
    ]);

    if (!resposta.ok) {
      throw new Error("Erro na solicitação ao backend");
    }

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

export const createApiario = async (apiario: apiario) => {
  try {
    const token = await AsyncStorage.getItem("Authorization");

    const headers: HeadersInit = {
      "Content-Type": "application/json",
    };

    if (token) {
      headers["Authorization"] = token;
    }

    const config = {
      method: "POST",
      headers: headers,
      body: JSON.stringify({
        flora: apiario.flora,
        nome_apiario: apiario.nome_apiario,
        proximidade_agua: apiario.proximidade_agua,
        latitude: apiario.latitude,
        longitude: apiario.longitude,
      }),
    };

    const timeoutPromise = new Promise<Response>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Tempo limite de requisição excedido"));
      }, 50000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
    });

    const respostaPromise = fetch(`${BASE_URL_PORTAL}/apiarios`, config);

    // Use Promise.race para resolver ou rejeitar com a primeira promessa que for resolvida ou rejeitada
    const resposta: Response = await Promise.race([
      respostaPromise,
      timeoutPromise,
    ]);

    if (!resposta.ok) {
      console.log(resposta);
      throw new Error("Erro na solicitação ao backend");
    }

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

export const adicionarColmeiasApiario = async (apiarioid: number, colmeiasList: colmeia[]) => {
  try {
    const token = await AsyncStorage.getItem("Authorization");

    const headers: HeadersInit = {
      "Content-Type": "application/json",
    };

    if (token) {
      headers["Authorization"] = token;
    }

    const config = {
      method: "PATCH",
      headers: headers,
      body: JSON.stringify({
        "colmeiaList": colmeiasList.map(colmeia => ({
          "numeroColmeia": colmeia.numeroColmeia,
          "alças": colmeia.alças,
          "tipo": colmeia.tipo,
          "dataRainha": colmeia.dataRainha
        }))
      }),
    };

    const timeoutPromise = new Promise<Response>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Tempo limite de requisição excedido"));
      }, 50000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
    });

    const respostaPromise = fetch(`${BASE_URL}/apiarios/${apiarioid}/colmeia`, config);

    // Use Promise.race para resolver ou rejeitar com a primeira promessa que for resolvida ou rejeitada
    const resposta: Response = await Promise.race([
      respostaPromise,
      timeoutPromise,
    ]);

    if (!resposta.ok) {
      console.log(resposta);
      throw new Error("Erro na solicitação ao backend");
    }

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

export const getHistorico = async (apiarioid: number) => {
  try {
    const token = await AsyncStorage.getItem("Authorization");

    const headers: HeadersInit = {
      "Content-Type": "application/json",
    };

    if (token) {
      headers["Authorization"] = token;
    }

    const config = {
      method: "GET",
      headers: headers,
    };

    const timeoutPromise = new Promise<Response>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Tempo limite de requisição excedido"));
      }, 50000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
    });

    const respostaPromise = fetch(`${BASE_URL}/apiarios/${apiarioid}/historico`, config);

    // Use Promise.race para resolver ou rejeitar com a primeira promessa que for resolvida ou rejeitada
    const resposta: Response = await Promise.race([
      respostaPromise,
      timeoutPromise,
    ]);

    if (!resposta.ok) {
      console.log(resposta);
      throw new Error("Erro na solicitação ao backend");
    }

    const dados = await resposta.json();
    return dados;
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};
