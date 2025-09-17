import { colmeia } from "./colmeia";

export interface apiario{
    apiarioId?: number,
    nome_apiario: string,
    flora: string,
    proximidade_agua: number,
    latitude:number,
    longitude:number,
    colmeiaList?: colmeia[],
}