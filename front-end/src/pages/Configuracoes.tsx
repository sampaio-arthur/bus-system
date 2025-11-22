import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { toast } from "@/hooks/use-toast";
import { API_BASE_URL } from "@/lib/api";
import { useEffect, useState } from "react";

type AdminAction = "migrate" | "seed" | "clean" | "reload";

const actions: {
  key: AdminAction;
  label: string;
  description: string;
  variant?: "default" | "destructive";
}[] = [
  {
    key: "migrate",
    label: "Aplicar migrações",
    description: "Executa migrações pendentes no banco",
  },
  {
    key: "seed",
    label: "Popular dados (seed)",
    description: "Executa scripts de seed configurados",
  },
  {
    key: "clean",
    label: "Limpar banco",
    description: "Limpa todas as tabelas (use com cautela)",
    variant: "destructive",
  },
  {
    key: "reload",
    label: "Clean + Migrate + Seed",
    description: "Recria o banco do zero e repovoa",
    variant: "destructive",
  },
];

export default function Configuracoes() {
  const [token, setToken] = useState("");
  const [isSubmitting, setIsSubmitting] = useState<AdminAction | null>(null);
  const tokenStorageKey = "admin-db-token";

  useEffect(() => {
    const storedToken = localStorage.getItem(tokenStorageKey);
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);

  const callAdmin = async (action: AdminAction) => {
    if (!token.trim()) {
      toast({
        title: "Token não informado",
        description: "Preencha o token administrativo antes de continuar.",
        variant: "destructive",
      });
      return;
    }

    setIsSubmitting(action);
    try {
      const res = await fetch(`${API_BASE_URL}/admin/db/${action}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "X-Admin-Token": token,
        },
      });
      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || `Erro HTTP ${res.status}`);
      }
      const data = await res.json();
      toast({
        title: `Ok: ${data.action}`,
        description: `${data.status} (${data.count})`,
      });
    } catch (err: any) {
      toast({
        title: `Erro ao executar ${action}`,
        description: err?.message ?? "Erro desconhecido",
        variant: "destructive",
      });
    } finally {
      setIsSubmitting(null);
    }
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold">Configurações</h1>
        <p className="text-muted-foreground mt-1">
          Ferramentas administrativas do banco
        </p>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Token administrativo</CardTitle>
          <CardDescription>
            Informe o token esperado pelo backend (header X-Admin-Token).
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-3">
          <Label htmlFor="token">Token</Label>
          <Input
            id="token"
            type="password"
            placeholder="DB_ADMIN_TOKEN"
            value={token}
            onChange={(e) => {
              const value = e.target.value;
              setToken(value);
              localStorage.setItem(tokenStorageKey, value);
            }}
          />
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>Operações no banco</CardTitle>
          <CardDescription>
            Dispare migrações, seed ou limpeza. Use com cuidado.
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          {actions.map((action, idx) => (
            <div key={action.key}>
              <div className="flex items-center justify-between gap-4">
                <div>
                  <p className="font-medium">{action.label}</p>
                  <p className="text-sm text-muted-foreground">
                    {action.description}
                  </p>
                </div>
                <Button
                  variant={
                    action.variant === "destructive" ? "destructive" : "default"
                  }
                  disabled={isSubmitting !== null}
                  onClick={() => callAdmin(action.key)}
                >
                  {isSubmitting === action.key ? "Executando..." : "Executar"}
                </Button>
              </div>
              {idx < actions.length - 1 && <Separator className="my-4" />}
            </div>
          ))}
        </CardContent>
      </Card>
    </div>
  );
}
