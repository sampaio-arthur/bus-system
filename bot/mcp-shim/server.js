import express from "express";
import fetch from "node-fetch";

const app = express();
app.use(express.json());

// 🔧 lê variáveis do ambiente com fallback
const QUARKUS_PORT = process.env.SERVER_EXTERNAL_PORT || 3000;
const MCP_PORT = process.env.MCP_SHIM_PORT || 5151;

/**
 * Proxy universal: a IA manda { endpoint, method, body, query, headers }
 * e o shim repassa para o Quarkus.
 */
app.post("/tools/proxy", async (req, res) => {
  try {
    const { method = "GET", endpoint, body, query, headers = {} } = req.body;

    if (!endpoint) {
      return res
        .status(400)
        .json({ ok: false, error: "⚠️ Campo 'endpoint' é obrigatório." });
    }

    // monta URL com query params, se existirem
    let url = `http://host.docker.internal:${QUARKUS_PORT}${endpoint}`;
    if (query && typeof query === "object") {
      const qs = new URLSearchParams(query).toString();
      url += `?${qs}`;
    }

    console.log(`➡️ Chamando Quarkus: ${method} ${url}`);

    const response = await fetch(url, {
      method,
      headers: { "Content-Type": "application/json", ...headers },
      body: method !== "GET" ? JSON.stringify(body) : undefined,
      timeout: 10000, // 10s
    });

    const text = await response.text();

    let data;
    try {
      data = JSON.parse(text);
    } catch {
      data = text; // fallback se não for JSON
    }

    console.log(`✅ Resposta Quarkus (${response.status}):`, data);

    return res.json({ ok: true, status: response.status, data });
  } catch (err) {
    console.error("❌ Erro no proxy:", err);
    return res.status(500).json({ ok: false, error: err.message });
  }
});

/**
 * Echo simples para teste
 */
app.post("/tools/echo", (req, res) => {
  res.json({
    ok: true,
    received: req.body,
    timestamp: new Date().toISOString(),
  });
});

app.listen(MCP_PORT, "0.0.0.0", () =>
  console.log(`✅ MCP shim rodando em http://localhost:${MCP_PORT}`)
);
