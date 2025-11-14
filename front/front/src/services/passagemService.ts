import { api, PaginatedResponse } from './api';
import { Compra } from '@/types/transit';

export const passagemService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Compra>>('/passagens', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Compra>(`/passagens/${id}`),
  
  create: (data: Omit<Compra, 'id'>) => 
    api.post<Compra>('/passagens', data),
  
  update: (id: number, data: Partial<Compra>) => 
    api.put<Compra>(`/passagens/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/passagens/${id}`),
};
