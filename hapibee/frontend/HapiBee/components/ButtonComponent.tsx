import { Pressable, StyleSheet } from "react-native";
import { Text } from "../components/Themed";
import { useState } from "react";
import { StackTypes } from "../app/_layout";
import { useNavigation } from "@react-navigation/native";

interface props {
  handle: Function;
  text: string;
}

export default function ButtonComponent(props: props) {
  const [isPressed, setIsPressed] = useState(false);

  const navigation = useNavigation<StackTypes>();

  const handlePressIn = () => {
    setIsPressed(true);
  };

  const handlePressOut = () => {
    setIsPressed(false);
  };

  return (
    <Pressable
    style={[
        styles.button,
        {
          transform: [{ scale: isPressed ? 0.95 : 1 }], 
        },
      ]}
      onPress={() => props.handle}
      onPressIn={handlePressIn}
      onPressOut={handlePressOut}
    >
      <Text style={styles.text}>{props.text}</Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
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
    fontWeight: 'bold',
  },
});
