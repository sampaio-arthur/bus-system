import axios, { AxiosResponse } from 'axios';

const API_BASE_URL =
  import.meta.env.VITE_API_BASE || 'http://localhost:3000';

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para tratamento de erros global
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API Error:', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export interface PaginatedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export const normalizePaginatedResponse = <T>(
  data: PaginatedResponse<T> | T[],
  page = 0,
  size?: number,
): PaginatedResponse<T> => {
  if (Array.isArray(data)) {
    const safeSize = size ?? data.length;
    return {
      content: data,
      totalElements: data.length,
      totalPages: safeSize > 0 ? Math.max(1, Math.ceil(data.length / safeSize)) : 0,
      size: safeSize,
      number: page,
    };
  }

  return data;
};

export const getPaginated = async <T>(
  url: string,
  params?: Record<string, number | string>,
) => {
  const response = await api.get<PaginatedResponse<T> | T[]>(url, { params });
  const normalized = normalizePaginatedResponse(
    response.data,
    typeof params?.page === 'number' ? Number(params?.page) : 0,
    typeof params?.size === 'number' ? Number(params?.size) : undefined,
  );

  return {
    ...response,
    data: normalized,
  } as AxiosResponse<PaginatedResponse<T>>;
};
