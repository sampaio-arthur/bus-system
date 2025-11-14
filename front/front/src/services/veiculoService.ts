import { api, PaginatedResponse } from './api';
import { Veiculo } from '@/types/transit';

export const veiculoService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<Veiculo>>('/veiculos', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<Veiculo>(`/veiculos/${id}`),
  
  create: (data: Omit<Veiculo, 'id'>) => 
    api.post<Veiculo>('/veiculos', data),
  
  update: (id: number, data: Partial<Veiculo>) => 
    api.put<Veiculo>(`/veiculos/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/veiculos/${id}`),
};
