import { api, PaginatedResponse, getPaginated } from './api';
import { TipoPassagem } from '@/types/transit';

export const tipoPassagemService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<TipoPassagem>('/tipos-passagem', { page, size }),
  
  getById: (id: number) => 
    api.get<TipoPassagem>(`/tipos-passagem/${id}`),
  
  create: (data: Omit<TipoPassagem, 'id'>) => 
    api.post<TipoPassagem>('/tipos-passagem', data),
  
  update: (id: number, data: Partial<TipoPassagem>) => 
    api.put<TipoPassagem>(`/tipos-passagem/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/tipos-passagem/${id}`),
};
