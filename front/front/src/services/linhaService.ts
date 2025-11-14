import { api, PaginatedResponse, getPaginated } from './api';
import { Linha } from '@/types/transit';

export const linhaService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<Linha>('/linhas', { page, size }),
  
  getById: (id: number) => 
    api.get<Linha>(`/linhas/${id}`),
  
  create: (data: Omit<Linha, 'id'>) => 
    api.post<Linha>('/linhas', data),
  
  update: (id: number, data: Partial<Linha>) => 
    api.put<Linha>(`/linhas/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/linhas/${id}`),
};
