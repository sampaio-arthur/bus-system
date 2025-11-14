import { api, PaginatedResponse, getPaginated } from './api';
import { MetodoPagamento } from '@/types/transit';

export const metodoPagamentoService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<MetodoPagamento>('/metodos-pagamento', { page, size }),
  
  getById: (id: number) => 
    api.get<MetodoPagamento>(`/metodos-pagamento/${id}`),
  
  create: (data: Omit<MetodoPagamento, 'id'>) => 
    api.post<MetodoPagamento>('/metodos-pagamento', data),
  
  update: (id: number, data: Partial<MetodoPagamento>) => 
    api.put<MetodoPagamento>(`/metodos-pagamento/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/metodos-pagamento/${id}`),
};
