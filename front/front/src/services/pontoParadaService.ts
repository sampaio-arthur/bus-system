import { api, PaginatedResponse, getPaginated } from './api';
import { Parada } from '@/types/transit';

export const pontoParadaService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<Parada>('/pontos-parada', { page, size }),
  
  getById: (id: number) => 
    api.get<Parada>(`/pontos-parada/${id}`),
  
  create: (data: Omit<Parada, 'id'>) => 
    api.post<Parada>('/pontos-parada', data),
  
  update: (id: number, data: Partial<Parada>) => 
    api.put<Parada>(`/pontos-parada/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/pontos-parada/${id}`),
};
