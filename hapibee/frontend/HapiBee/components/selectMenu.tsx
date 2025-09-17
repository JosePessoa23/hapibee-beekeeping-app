import React, { useState } from "react";
import { StyleSheet,View, TextInput, TouchableOpacity, Image } from "react-native";
import { Text} from "./Themed";

export default function SelectMenu({set}: {set: Function}) {
    const [checkboxes, setCheckboxes] = useState([false, false, false]);
    const opcoes = ['Bom', 'Médio', 'Mau']

    const handleCheckboxPress = (index: number) => {
      // Cria uma nova matriz de checkboxes com o estado invertido para o checkbox selecionado
      const newCheckboxes = checkboxes.map((checkbox, i) => (i === index ? !checkbox : false));
  
      // Atualiza o estado com a nova matriz de checkboxes
      setCheckboxes(newCheckboxes);
      set(opcoes[index]);
    };

    
  
    return (
      <View style={{ alignContent:'space-between', width:'100%'}}>
        {checkboxes.map((isChecked, index) => (
          <TouchableOpacity key={index} onPress={() => handleCheckboxPress(index)}>
            <View style={{ paddingTop: 40,flexDirection: 'row', alignItems: 'center' }}>
              <View
                style={{
                  width: 100,
                  height: 100,
                  borderRadius: 50,
                  borderWidth: 2,
                  borderColor: isChecked ? 'orange' : 'gray',
                  marginRight: 8,
                  justifyContent: 'center',
                  alignItems: 'center',
                }}
              >
                {isChecked && (
                  <View
                    style={{
                      width: 70,
                      height: 70,
                      borderRadius: 35,
                      backgroundColor: 'orange',
                    }}
                  />
                )}
              </View>
              <Text style={{fontSize: 50}}>{opcoes[index]}</Text>
            </View>
          </TouchableOpacity>
        ))}
      </View>
    );
  };
