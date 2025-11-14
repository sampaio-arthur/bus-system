import { api, PaginatedResponse } from './api';
import { TipoPassagem } from '@/types/transit';

export const tipoPassagemService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<TipoPassagem>>('/tipos-passagem', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<TipoPassagem>(`/tipos-passagem/${id}`),
  
  create: (data: Omit<TipoPassagem, 'id'>) => 
    api.post<TipoPassagem>('/tipos-passagem', data),
  
  update: (id: number, data: Partial<TipoPassagem>) => 
    api.put<TipoPassagem>(`/tipos-passagem/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/tipos-passagem/${id}`),
};
