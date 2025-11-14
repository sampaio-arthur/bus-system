import { api, PaginatedResponse } from './api';
import { ManutencaoPeca } from '@/types/transit';

export const manutencaoPecaService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<ManutencaoPeca>>('/manutencoes-pecas', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<ManutencaoPeca>(`/manutencoes-pecas/${id}`),
  
  create: (data: Omit<ManutencaoPeca, 'id'>) => 
    api.post<ManutencaoPeca>('/manutencoes-pecas', data),
  
  update: (id: number, data: Partial<ManutencaoPeca>) => 
    api.put<ManutencaoPeca>(`/manutencoes-pecas/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/manutencoes-pecas/${id}`),
};
