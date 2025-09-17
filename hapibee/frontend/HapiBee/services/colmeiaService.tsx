import AsyncStorage from "@react-native-async-storage/async-storage";
import { inspecao } from "../interfaces/inspecao";

const BASE_URL = "http://192.168.1.188:8080";

export const desdobrarComeia = async (id: number, numAlcas: number) => {
  try {
    const config = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        // Adicione headers adicionais, como tokens de autenticação, conforme necessário
      },
    };

    const timeoutPromise = new Promise<Response>((_, reject) => {
      setTimeout(() => {
        reject(new Error("Tempo limite de requisição excedido"));
      }, 5000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
    });

    const respostaPromise = fetch(
      `${BASE_URL}/colmeia/${id}/${numAlcas}`,
      config
    );

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

export const adicionarRegisto = async (
  inspecao: inspecao,
  colmeiaID: number
) => {
  console.log("colmeiaid " + colmeiaID);
  try {
    var token = await AsyncStorage.getItem("Authorization");
    console.log(token);
    if(token) {
      const config = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": token,
          // Adicione headers adicionais, como tokens de autenticação, conforme necessário
        },
        body: JSON.stringify({
          higiene: inspecao.higiene,
          tendencia_enxamear: inspecao.tendencia_enxamear,
          agressividade: inspecao.agressividade,
          produtividade: inspecao.produtividade,
          capacidade_polinizadora: inspecao.capacidade_polinizadora,
          observacoes: inspecao.observaçoes,
        }),
      };

      const timeoutPromise = new Promise<Response>((_, reject) => {
        setTimeout(() => {
          reject(new Error("Tempo limite de requisição excedido"));
        }, 5000); // Defina o tempo limite desejado em milissegundos (5 segundos no exemplo)
      });

      const respostaPromise = fetch(
        `${BASE_URL}/registos/${colmeiaID}`,
        config
      );

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
    }
  } catch (erro) {
    throw erro; // Propague o erro para quem chamar a função, se necessário
  }
};

const obterLista = async (chave: string) => {
  try {
    const userId = await AsyncStorage.getItem(chave);
    return userId != null ? userId : "";
  } catch (erro) {
    console.error("Erro ao obter a lista:", erro);
    return "";
  }
};
