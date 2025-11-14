import { api, PaginatedResponse } from './api';
import { TipoVeiculo } from '@/types/transit';

export const tipoVeiculoService = {
  getAll: (page = 0, size = 10) => 
    api.get<PaginatedResponse<TipoVeiculo>>('/tipos-veiculo', { params: { page, size } }),
  
  getById: (id: number) => 
    api.get<TipoVeiculo>(`/tipos-veiculo/${id}`),
  
  create: (data: Omit<TipoVeiculo, 'id'>) => 
    api.post<TipoVeiculo>('/tipos-veiculo', data),
  
  update: (id: number, data: Partial<TipoVeiculo>) => 
    api.put<TipoVeiculo>(`/tipos-veiculo/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/tipos-veiculo/${id}`),
};
