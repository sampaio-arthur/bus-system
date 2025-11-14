import { api, PaginatedResponse } from './api';
import { Peca } from '@/types/transit';

export const pecaService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Peca>>('/pecas', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Peca>(`/pecas/${id}`),
  
  create: (data: Omit<Peca, 'id'>) => 
    api.post<Peca>('/pecas', data),
  
  update: (id: number, data: Partial<Peca>) => 
    api.put<Peca>(`/pecas/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/pecas/${id}`),
};
