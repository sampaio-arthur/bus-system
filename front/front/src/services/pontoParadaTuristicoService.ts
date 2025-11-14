import { api, PaginatedResponse } from './api';
import { PontoParadaTuristico } from '@/types/transit';

export const pontoParadaTuristicoService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<PontoParadaTuristico>>('/pontos-parada-turisticos', { params: { page, size } }),
  
  create: (data: PontoParadaTuristico) => 
    api.post<PontoParadaTuristico>('/pontos-parada-turisticos', data),
  
  delete: (idPontoParada: number, idPontoTuristico: number) => 
    api.delete(`/pontos-parada-turisticos/${idPontoParada}/${idPontoTuristico}`),
};
