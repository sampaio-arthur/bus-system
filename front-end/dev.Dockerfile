# Development Dockerfile for Vite/React front-end
FROM node:20-alpine AS base
WORKDIR /app

# Install deps separately to leverage Docker cache
COPY package.json package-lock.json ./
RUN npm ci

# Copy the rest of the source
COPY . .

EXPOSE 8080
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0", "--port", "8080", "--strictPort"]
