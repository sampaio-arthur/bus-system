FROM node:20-bullseye AS deps
WORKDIR /app

# Install dependencies first for better caching
COPY package*.json ./
RUN npm install

FROM node:20-bullseye As dev
WORKDIR /app
ENV TZ=America/Sao_Paulo \
    HOST=0.0.0.0 \
    PORT=5173

COPY --from=deps /app/node_modules ./node_modules
COPY . .

EXPOSE 5173

# Vite needs --host to be reachable outside the container.
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0", "--port", "5173", "--strictPort"]
