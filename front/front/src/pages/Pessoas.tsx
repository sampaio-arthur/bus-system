import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import Header from "@/components/Header";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Users, Plus, User, Wrench, UserCircle, Loader2 } from "lucide-react";
import { pessoaService } from "@/services/pessoaService";
import { useToast } from "@/hooks/use-toast";

const Pessoas = () => {
  const { toast } = useToast();
  const [dialogOpen, setDialogOpen] = useState(false);

  const { data: pessoasData, isLoading } = useQuery({
    queryKey: ['pessoas'],
    queryFn: async () => {
      const response = await pessoaService.getAll(0, 100);
      return response.data;
    },
  });

  const pessoas = pessoasData?.content || [];
  const passageiros = pessoas.filter(p => p.tipo === 0);
  const motoristas = pessoas.filter(p => p.tipo === 1);
  const mecanicos = pessoas.filter(p => p.tipo === 2);

  const handleCadastro = (e: React.FormEvent) => {
    e.preventDefault();
    toast({ title: "Pessoa cadastrada com sucesso!" });
    setDialogOpen(false);
  };

  const getTipoIcon = (tipo: string) => {
    switch (tipo) {
      case "motorista":
        return <User className="h-5 w-5 text-white" />;
      case "mecanico":
        return <Wrench className="h-5 w-5 text-white" />;
      default:
        return <UserCircle className="h-5 w-5 text-white" />;
    }
  };

  const getTipoBadge = (tipo: string) => {
    const badges = {
      passageiro: { label: "Passageiro", variant: "secondary" as const },
      motorista: { label: "Motorista", variant: "default" as const },
      mecanico: { label: "Mecânico", variant: "outline" as const },
    };
    return badges[tipo as keyof typeof badges] || badges.passageiro;
  };

  const PessoaCard = ({ pessoa }: { pessoa: any }) => {
    const badge = getTipoBadge(pessoa.tipo);
    
    return (
      <Card key={pessoa.id} className="animate-slide-in">
        <CardHeader>
          <div className="flex items-start justify-between">
            <div className="flex items-center gap-3">
              <div className="p-2 rounded-lg bg-gradient-transit">
                {getTipoIcon(pessoa.tipo)}
              </div>
              <div>
                <CardTitle className="text-base">{pessoa.nome}</CardTitle>
                <Badge variant={badge.variant} className="mt-1">
                  {badge.label}
                </Badge>
              </div>
            </div>
          </div>
        </CardHeader>
        <CardContent className="space-y-2">
          <div className="text-sm">
            <span className="text-muted-foreground">CPF: </span>
            <span className="font-medium">{pessoa.cpf}</span>
          </div>
          <div className="text-sm">
            <span className="text-muted-foreground">Email: </span>
            <span className="font-medium">{pessoa.email}</span>
          </div>
          <div className="text-sm">
            <span className="text-muted-foreground">Telefone: </span>
            <span className="font-medium">{pessoa.telefone}</span>
          </div>
          <div className="text-xs text-muted-foreground pt-2 border-t">
            Cadastrado em: {new Date(pessoa.data_cadastro).toLocaleDateString()}
          </div>
        </CardContent>
      </Card>
    );
  };

  if (isLoading) {
    return (
      <div className="min-h-screen bg-background">
        <Header />
        <main className="container py-8 flex items-center justify-center min-h-[60vh]">
          <Loader2 className="h-8 w-8 animate-spin text-primary" />
        </main>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-background">
      <Header />
      <main className="container py-8">
        <div className="mb-8 flex items-center justify-between">
          <div>
            <h1 className="text-4xl font-bold mb-2 bg-gradient-transit bg-clip-text text-transparent">
              Gestão de Pessoas
            </h1>
            <p className="text-muted-foreground">
              Gerencie motoristas, mecânicos e passageiros do sistema
            </p>
          </div>
          <Dialog open={dialogOpen} onOpenChange={setDialogOpen}>
            <DialogTrigger asChild>
              <Button size="lg">
                <Plus className="h-4 w-4 mr-2" />
                Cadastrar Pessoa
              </Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>Cadastrar Nova Pessoa</DialogTitle>
              </DialogHeader>
              <form onSubmit={handleCadastro} className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="nome">Nome Completo</Label>
                  <Input id="nome" placeholder="Digite o nome completo" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="cpf">CPF</Label>
                  <Input id="cpf" placeholder="000.000.000-00" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="email">E-mail</Label>
                  <Input id="email" type="email" placeholder="email@exemplo.com" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="telefone">Telefone</Label>
                  <Input id="telefone" placeholder="(00) 00000-0000" required />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="tipo">Tipo</Label>
                  <Select required>
                    <SelectTrigger>
                      <SelectValue placeholder="Selecione o tipo" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="passageiro">Passageiro</SelectItem>
                      <SelectItem value="motorista">Motorista</SelectItem>
                      <SelectItem value="mecanico">Mecânico</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
                <Button type="submit" className="w-full">Cadastrar</Button>
              </form>
            </DialogContent>
          </Dialog>
        </div>

        <Tabs defaultValue="todos" className="space-y-6">
          <TabsList>
            <TabsTrigger value="todos">
              <Users className="h-4 w-4 mr-2" />
              Todos ({pessoas.length})
            </TabsTrigger>
            <TabsTrigger value="passageiros">
              <UserCircle className="h-4 w-4 mr-2" />
              Passageiros ({passageiros.length})
            </TabsTrigger>
            <TabsTrigger value="motoristas">
              <User className="h-4 w-4 mr-2" />
              Motoristas ({motoristas.length})
            </TabsTrigger>
            <TabsTrigger value="mecanicos">
              <Wrench className="h-4 w-4 mr-2" />
              Mecânicos ({mecanicos.length})
            </TabsTrigger>
          </TabsList>

          <TabsContent value="todos">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {pessoas.map((pessoa) => (
                <PessoaCard pessoa={pessoa} />
              ))}
            </div>
          </TabsContent>

          <TabsContent value="passageiros">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {passageiros.map((pessoa) => (
                <PessoaCard pessoa={pessoa} />
              ))}
            </div>
          </TabsContent>

          <TabsContent value="motoristas">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {motoristas.map((pessoa) => (
                <PessoaCard pessoa={pessoa} />
              ))}
            </div>
          </TabsContent>

          <TabsContent value="mecanicos">
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {mecanicos.map((pessoa) => (
                <PessoaCard pessoa={pessoa} />
              ))}
            </div>
          </TabsContent>
        </Tabs>
      </main>
    </div>
  );
};

export default Pessoas;
