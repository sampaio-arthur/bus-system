import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { MessageSquare, MapPin, Route, Clock } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function Atendimento() {
  const [cidade, setCidade] = useState("");
  const [linhaNome, setLinhaNome] = useState("");
  const [pontoTuristicoNome, setPontoTuristicoNome] = useState("");
  const [pontoParadaNome, setPontoParadaNome] = useState("");

  const { data: itinerarios, refetch: refetchItinerarios } = useQuery({
    queryKey: ["spaik", "itinerarios", cidade],
    queryFn: () => api.get("/spaik/itinerarios", { cidade }),
    enabled: false,
  });

  const { data: viagens, refetch: refetchViagens } = useQuery({
    queryKey: ["spaik", "viagens", cidade],
    queryFn: () => api.get("/spaik/viagens", { cidade }),
    enabled: false,
  });

  const { data: ultimoProgresso, refetch: refetchProgresso } = useQuery({
    queryKey: ["spaik", "progresso", cidade, linhaNome],
    queryFn: () => api.get("/spaik/viagens/ultimo-progresso", { cidade, linhaNome }),
    enabled: false,
  });

  const { data: pontosParada, refetch: refetchPontosParada } = useQuery({
    queryKey: ["spaik", "pontos-parada", cidade, pontoTuristicoNome],
    queryFn: () => api.get("/spaik/pontos-parada", { cidade, pontoTuristicoNome }),
    enabled: false,
  });

  const { data: pontosTuristicos, refetch: refetchPontosTuristicos } = useQuery({
    queryKey: ["spaik", "pontos-turisticos", cidade, pontoParadaNome],
    queryFn: () => api.get("/spaik/pontos-turisticos", { cidade, pontoParadaNome }),
    enabled: false,
  });

  const buscarItinerarios = () => {
    if (!cidade) {
      toast({ title: "Digite o nome da cidade", variant: "destructive" });
      return;
    }
    refetchItinerarios();
  };

  const buscarViagens = () => {
    if (!cidade) {
      toast({ title: "Digite o nome da cidade", variant: "destructive" });
      return;
    }
    refetchViagens();
  };

  const buscarProgresso = () => {
    if (!cidade || !linhaNome) {
      toast({ title: "Digite a cidade e o nome da linha", variant: "destructive" });
      return;
    }
    refetchProgresso();
  };

  const buscarPontosParada = () => {
    if (!cidade || !pontoTuristicoNome) {
      toast({ title: "Digite a cidade e o ponto turístico", variant: "destructive" });
      return;
    }
    refetchPontosParada();
  };

  const buscarPontosTuristicos = () => {
    if (!cidade || !pontoParadaNome) {
      toast({ title: "Digite a cidade e o ponto de parada", variant: "destructive" });
      return;
    }
    refetchPontosTuristicos();
  };

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold flex items-center gap-2">
          <MessageSquare className="h-8 w-8 text-primary" />
          Atendimento / Bot
        </h1>
        <p className="text-muted-foreground mt-1">
          Consultas rápidas para atendimento aos passageiros
        </p>
      </div>

      <div className="grid gap-6 md:grid-cols-2">
        {/* Itinerários */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Route className="h-5 w-5 text-primary" />
              Consultar Itinerários
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div>
              <Label htmlFor="cidadeItinerario">Cidade</Label>
              <Input
                id="cidadeItinerario"
                placeholder="Nome da cidade"
                value={cidade}
                onChange={(e) => setCidade(e.target.value)}
              />
            </div>
            <Button onClick={buscarItinerarios} className="w-full">
              Buscar Itinerários
            </Button>
            {itinerarios && itinerarios.length > 0 && (
              <div className="space-y-2 mt-4 max-h-64 overflow-y-auto">
                {itinerarios.map((it: any, idx: number) => (
                  <div key={idx} className="p-3 bg-muted/30 rounded-lg text-sm">
                    <p className="font-medium">{it.nomeLinha}</p>
                    <p className="text-muted-foreground">{it.nomePontoParada} (Ordem: {it.ordem})</p>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>

        {/* Viagens */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Clock className="h-5 w-5 text-secondary" />
              Consultar Viagens
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div>
              <Label htmlFor="cidadeViagem">Cidade</Label>
              <Input
                id="cidadeViagem"
                placeholder="Nome da cidade"
                value={cidade}
                onChange={(e) => setCidade(e.target.value)}
              />
            </div>
            <Button onClick={buscarViagens} className="w-full" variant="secondary">
              Buscar Viagens
            </Button>
            {viagens && viagens.length > 0 && (
              <div className="space-y-2 mt-4 max-h-64 overflow-y-auto">
                {viagens.map((viagem: any, idx: number) => (
                  <div key={idx} className="p-3 bg-muted/30 rounded-lg text-sm">
                    <p className="font-medium">{viagem.linha.nome}</p>
                    <p className="text-muted-foreground">
                      Partida: {new Date(viagem.dataPartidaPrevista).toLocaleString('pt-BR')}
                    </p>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>

        {/* Último Progresso */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <MapPin className="h-5 w-5 text-accent" />
              Último Progresso da Viagem
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div>
              <Label htmlFor="cidadeProgresso">Cidade</Label>
              <Input
                id="cidadeProgresso"
                placeholder="Nome da cidade"
                value={cidade}
                onChange={(e) => setCidade(e.target.value)}
              />
            </div>
            <div>
              <Label htmlFor="linhaProgresso">Nome da Linha</Label>
              <Input
                id="linhaProgresso"
                placeholder="Nome da linha"
                value={linhaNome}
                onChange={(e) => setLinhaNome(e.target.value)}
              />
            </div>
            <Button onClick={buscarProgresso} className="w-full">
              Buscar Progresso
            </Button>
            {ultimoProgresso && (
              <div className="p-4 bg-accent/10 rounded-lg mt-4">
                <p className="font-medium">Última atualização:</p>
                <p className="text-sm text-muted-foreground">
                  {new Date(ultimoProgresso.data).toLocaleString('pt-BR')}
                </p>
                <Badge className="mt-2 bg-accent">ID Viagem: {ultimoProgresso.idViagem}</Badge>
              </div>
            )}
          </CardContent>
        </Card>

        {/* Pontos de Parada por Ponto Turístico */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2 text-sm">
              <MapPin className="h-5 w-5 text-warning" />
              Pontos de Parada próximos
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div>
              <Label htmlFor="cidadePontoParada">Cidade</Label>
              <Input
                id="cidadePontoParada"
                placeholder="Nome da cidade"
                value={cidade}
                onChange={(e) => setCidade(e.target.value)}
              />
            </div>
            <div>
              <Label htmlFor="pontoTuristico">Ponto Turístico</Label>
              <Input
                id="pontoTuristico"
                placeholder="Nome do ponto turístico"
                value={pontoTuristicoNome}
                onChange={(e) => setPontoTuristicoNome(e.target.value)}
              />
            </div>
            <Button onClick={buscarPontosParada} className="w-full" variant="outline">
              Buscar Pontos de Parada
            </Button>
            {pontosParada && pontosParada.length > 0 && (
              <div className="space-y-2 mt-4 max-h-48 overflow-y-auto">
                {pontosParada.map((pp: any, idx: number) => (
                  <div key={idx} className="p-2 bg-muted/30 rounded text-sm">
                    {pp.nome}
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>

        {/* Pontos Turísticos por Ponto de Parada */}
        <Card className="md:col-span-2">
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <MapPin className="h-5 w-5 text-success" />
              Pontos Turísticos próximos a um Ponto de Parada
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="cidadePontoTuristico">Cidade</Label>
                <Input
                  id="cidadePontoTuristico"
                  placeholder="Nome da cidade"
                  value={cidade}
                  onChange={(e) => setCidade(e.target.value)}
                />
              </div>
              <div>
                <Label htmlFor="pontoParada">Ponto de Parada</Label>
                <Input
                  id="pontoParada"
                  placeholder="Nome do ponto de parada"
                  value={pontoParadaNome}
                  onChange={(e) => setPontoParadaNome(e.target.value)}
                />
              </div>
            </div>
            <Button onClick={buscarPontosTuristicos} className="w-full" variant="outline">
              Buscar Pontos Turísticos
            </Button>
            {pontosTuristicos && pontosTuristicos.length > 0 && (
              <div className="grid gap-3 md:grid-cols-3 mt-4">
                {pontosTuristicos.map((pt: any, idx: number) => (
                  <div key={idx} className="p-3 bg-success/10 rounded-lg">
                    <p className="font-medium text-sm">{pt.nome}</p>
                    <p className="text-xs text-muted-foreground mt-1">{pt.descricao}</p>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
