const API_BASE_URL =
  import.meta.env.VITE_API_BASE ||
  (import.meta.env.DEV ? "/api" : "http://localhost:3000");

const resolveBaseUrl = () => {
  if (API_BASE_URL.startsWith("http://") || API_BASE_URL.startsWith("https://")) {
    return API_BASE_URL;
  }

  if (typeof window !== "undefined" && window.location) {
    return new URL(API_BASE_URL, window.location.origin).toString();
  }

  return API_BASE_URL;
};

const buildUrl = (endpoint: string, params?: Record<string, any>) => {
  const url = new URL(endpoint, resolveBaseUrl());

  if (params) {
    Object.entries(params).forEach(([key, value]) => {
      if (value !== undefined && value !== null) {
        url.searchParams.append(key, String(value));
      }
    });
  }

  return url.toString();
};

const handleResponse = async (response: Response) => {
  if (!response.ok) {
    throw new Error(`HTTP ${response.status}: ${response.statusText}`);
  }

  return response.status === 204 ? null : response.json();
};

export const api = {
  get: async (endpoint: string, params?: Record<string, any>) => {
    const response = await fetch(buildUrl(endpoint, params));
    return handleResponse(response);
  },

  post: async (endpoint: string, data: any) => {
    const response = await fetch(buildUrl(endpoint), {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    return handleResponse(response);
  },

  put: async (endpoint: string, data: any) => {
    const response = await fetch(buildUrl(endpoint), {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
    return handleResponse(response);
  },

  delete: async (endpoint: string) => {
    const response = await fetch(buildUrl(endpoint), {
      method: "DELETE",
    });
    return handleResponse(response);
  },
};
