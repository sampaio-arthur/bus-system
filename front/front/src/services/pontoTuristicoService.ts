import { api, PaginatedResponse, getPaginated } from './api';
import { PontoTuristico } from '@/types/transit';

export const pontoTuristicoService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<PontoTuristico>('/pontos-turisticos', { page, size }),
  
  getById: (id: number) => 
    api.get<PontoTuristico>(`/pontos-turisticos/${id}`),
  
  create: (data: Omit<PontoTuristico, 'id'>) => 
    api.post<PontoTuristico>('/pontos-turisticos', data),
  
  update: (id: number, data: Partial<PontoTuristico>) => 
    api.put<PontoTuristico>(`/pontos-turisticos/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/pontos-turisticos/${id}`),
};
