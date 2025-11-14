import { api, PaginatedResponse, getPaginated } from './api';
import { Cronograma } from '@/types/transit';

export const cronogramaService = {
  getAll: (page = 0, size = 10) => 
    getPaginated<Cronograma>('/cronogramas', { page, size }),
  
  getById: (id: number) => 
    api.get<Cronograma>(`/cronogramas/${id}`),
  
  create: (data: Omit<Cronograma, 'id'>) => 
    api.post<Cronograma>('/cronogramas', data),
  
  update: (id: number, data: Partial<Cronograma>) => 
    api.put<Cronograma>(`/cronogramas/${id}`, data),
  
  delete: (id: number) => 
    api.delete(`/cronogramas/${id}`),
};
