import FontAwesome from "@expo/vector-icons/FontAwesome";
import {
  DarkTheme,
  DefaultTheme,
  NavigationContainer,
  ThemeProvider,
} from "@react-navigation/native";
import { useFonts } from "expo-font";
import { SplashScreen } from "expo-router";
import { useEffect } from "react";
import { useColorScheme } from "react-native";
import Login from "./pages/login";
import * as Font from "expo-font";
import CriarApiario from "./pages/criarApiario";
import {
  createNativeStackNavigator,
  NativeStackNavigationProp,
} from "@react-navigation/native-stack";
import MostrarColmeias from "./pages/mostrarColmeias";
import AdicionarColmeia from "./pages/adicionarColmeia";
import InfoApicultorDA from "./pages/infoApicultorDA";
import InfoApiarioDA from "./pages/infoApiarioDA";
import Menu from "./pages/menu";
import Menu2 from "./pages/menu2";
import GestaoApiarios from "./pages/gestaoApiarios";
import InfoApiario from "./pages/infoApiario";
import PedidoTransumancia from "./pages/pedidoTransumancia";
import InfoColmeia from "./pages/infoColmeia";
import ComportamentoHigienico from "./pages/comportamentoHigienico";
import Enxamear from "./pages/enxamear";
import Agressividade from "./pages/agressividade";
import Produtividade from "./pages/produtividade";
import CapacidadePolinizadora from "./pages/capacidadePolinizadora";
import Observacoes from "./pages/observacoes";
import Desdobramento from "./pages/desdobramento";
import { apiario } from "../interfaces/apiario";
import { inspecao } from "../interfaces/inspecao";
import HistoricoApiario from "./pages/historicoApiario";

export {
  // Catch any errors thrown by the Layout component.
  ErrorBoundary,
} from "expo-router";

export const unstable_settings = {
  // Ensure that reloading on `/modal` keeps a back button present.
  initialRouteName: "pages/login",
};

// Prevent the splash screen from auto-hiding before asset loading is complete.
SplashScreen.preventAutoHideAsync();

export default function RootLayout() {
  const [loaded, error] = useFonts({
    SpaceMono: require("../assets/fonts/SpaceMono-Regular.ttf"),
    ...FontAwesome.font,
  });

  // Expo Router uses Error Boundaries to catch errors in the navigation tree.
  useEffect(() => {
    if (error) throw error;
  }, [error]);

  useEffect(() => {
    if (loaded) {
      SplashScreen.hideAsync();
    }
  }, [loaded]);

  if (!loaded) {
    return null;
  }

  return <RootLayoutNav />;
}

type rootstackparamlist = {
  "pages/login": undefined; // undefined because you aren't passing any params to the home screen
  "pages/criarApiario": undefined;
  "pages/mostrarColmeias": {apiario: apiario};
  "pages/adicionarColmeia": {apiario: apiario};
  "pages/infoApicultorDA": undefined;
  "pages/infoApiarioDA": undefined;
  "pages/menu": undefined;
  "pages/menu2": undefined;
  "pages/gestaoApiarios": undefined;
  "pages/infoApiario": {apiarioID: number};
  "pages/pedidoTransumancia": {apiarioID: number};
  "pages/infoColmeia": {apiarioID: number, colmeiaID: number};
  "pages/comportamentoHigienico": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/enxamear": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/agressividade": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/produtividade": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/capacidadePolinizadora": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/observacoes": {apiarioID: number, colmeiaID: number, inspecao:inspecao};
  "pages/desdobramento": {apiarioID: number, colmeiaID: number, nAlcas: number};
  "pages/historicoApiario": {apiarioID:number};
};

const Stack = createNativeStackNavigator();

export type StackTypes = NativeStackNavigationProp<rootstackparamlist>;

function RootLayoutNav() {
  const colorScheme = useColorScheme();

  return (
    <ThemeProvider value={colorScheme === "dark" ? DarkTheme : DefaultTheme}>
      <Stack.Navigator screenOptions={{ headerShown: false }}>
        <Stack.Screen name="pages/login" component={Login} />
        <Stack.Screen name="pages/criarApiario" component={CriarApiario} />
        <Stack.Screen name="pages/mostrarColmeias" component={MostrarColmeias} />
        <Stack.Screen name="pages/adicionarColmeia" component={AdicionarColmeia} />
        <Stack.Screen name="pages/infoApicultorDA" component={InfoApicultorDA} />
        <Stack.Screen name="pages/infoApiarioDA" component={InfoApiarioDA} />
        <Stack.Screen name="pages/menu" component={Menu} />
        <Stack.Screen name="pages/menu2" component={Menu2} />
        <Stack.Screen name="pages/gestaoApiarios" component={GestaoApiarios} />
        <Stack.Screen name="pages/infoApiario" component={InfoApiario} />
        <Stack.Screen name="pages/pedidoTransumancia" component={PedidoTransumancia} />
        <Stack.Screen name="pages/infoColmeia" component={InfoColmeia} />
        <Stack.Screen name="pages/comportamentoHigienico" component={ComportamentoHigienico} />
        <Stack.Screen name="pages/enxamear" component={Enxamear} />
        <Stack.Screen name="pages/agressividade" component={Agressividade} />
        <Stack.Screen name="pages/produtividade" component={Produtividade} />
        <Stack.Screen name="pages/capacidadePolinizadora" component={CapacidadePolinizadora} />
        <Stack.Screen name="pages/observacoes" component={Observacoes} />
        <Stack.Screen name="pages/desdobramento" component={Desdobramento} />
        <Stack.Screen name="pages/historicoApiario" component={HistoricoApiario} />
      </Stack.Navigator>
    </ThemeProvider>
  );
}
