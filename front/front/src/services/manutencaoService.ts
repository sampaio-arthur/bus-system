import { api, PaginatedResponse } from './api';
import { Manutencao } from '@/types/transit';

export const manutencaoService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Manutencao>>('/manutencoes', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Manutencao>(`/manutencoes/${id}`),
  
  create: (data: Omit<Manutencao, 'id'>) => 
    api.post<Manutencao>('/manutencoes', data),
  
  update: (id: number, data: Partial<Manutencao>) => 
    api.put<Manutencao>(`/manutencoes/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/manutencoes/${id}`),
};
