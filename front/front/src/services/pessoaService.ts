import { api, PaginatedResponse } from './api';
import { Pessoa } from '@/types/transit';

export const pessoaService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Pessoa>>('/pessoas', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Pessoa>(`/pessoas/${id}`),
  
  create: (data: Omit<Pessoa, 'id'>) => 
    api.post<Pessoa>('/pessoas', data),
  
  update: (id: number, data: Partial<Pessoa>) => 
    api.put<Pessoa>(`/pessoas/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/pessoas/${id}`),
};
