import { api, PaginatedResponse, getPaginated } from './api';
import { PontoParadaTuristico } from '@/types/transit';

export const pontoParadaTuristicoService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<PontoParadaTuristico>('/pontos-parada-turisticos', { page, size }),
  
  create: (data: PontoParadaTuristico) => 
    api.post<PontoParadaTuristico>('/pontos-parada-turisticos', data),
  
  delete: (idPontoParada: number, idPontoTuristico: number) => 
    api.delete(`/pontos-parada-turisticos/${idPontoParada}/${idPontoTuristico}`),
};
