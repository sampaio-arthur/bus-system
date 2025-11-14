import { api, PaginatedResponse } from './api';
import { Cidade } from '@/types/transit';

export const cidadeService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Cidade>>('/cidades', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Cidade>(`/cidades/${id}`),
  
  create: (data: Omit<Cidade, 'id'>) => 
    api.post<Cidade>('/cidades', data),
  
  update: (id: number, data: Partial<Cidade>) => 
    api.put<Cidade>(`/cidades/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/cidades/${id}`),
};
