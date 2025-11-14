import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { Bus, Clock, MapPin, ShoppingCart, Loader2 } from "lucide-react";
import Header from "@/components/Header";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { linhaService } from "@/services/linhaService";
import { cronogramaService } from "@/services/cronogramaService";
import { viagemService } from "@/services/viagemService";
import ModalDetalhesLinha from "@/components/ModalDetalhesLinha";
import ModalCompraPassagem from "@/components/ModalCompraPassagem";
import { Linha } from "@/types/transit";

const Linhas = () => {
  const [selectedLinha, setSelectedLinha] = useState<Linha | null>(null);
  const [detalhesOpen, setDetalhesOpen] = useState(false);
  const [compraOpen, setCompraOpen] = useState(false);

  const { data: linhasData, isLoading: linhasLoading } = useQuery({
    queryKey: ['linhas'],
    queryFn: async () => {
      const response = await linhaService.getAll(0, 100);
      return response.data;
    },
  });

  const { data: cronogramasData } = useQuery({
    queryKey: ['cronogramas'],
    queryFn: async () => {
      const response = await cronogramaService.getAll(0, 100);
      return response.data;
    },
  });

  const { data: viagensData } = useQuery({
    queryKey: ['viagens'],
    queryFn: async () => {
      const response = await viagemService.getAll(0, 100);
      return response.data;
    },
  });

  const linhas = linhasData?.content || [];
  const cronogramas = cronogramasData?.content || [];
  const viagens = viagensData?.content || [];

  if (linhasLoading) {
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
        <div className="mb-8">
          <h1 className="text-4xl font-bold mb-2 bg-gradient-transit bg-clip-text text-transparent">
            Linhas de Ônibus
          </h1>
          <p className="text-muted-foreground">
            Confira todas as linhas disponíveis em Araranguá
          </p>
        </div>

        <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
          {linhas.map((linha) => {
            const linhasCronogramas = cronogramas.filter(c => c.linha?.id === linha.id);
            const viagemAtiva = viagens.find(v => v.linha?.id === linha.id && v.status === 1);

            return (
              <Card
                key={linha.id}
                className="transition-all hover:shadow-hover animate-slide-in"
              >
                <CardHeader>
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-3">
                      <div
                        className="p-2 rounded-lg"
                        style={{ backgroundColor: linha.cor }}
                      >
                        <Bus className="h-5 w-5 text-white" />
                      </div>
                      <div>
                        <CardTitle className="text-lg">{linha.nome}</CardTitle>
                        <Badge variant="secondary" className="mt-1">
                          {linha.codigo}
                        </Badge>
                      </div>
                    </div>
                  </div>
                </CardHeader>
                <CardContent className="space-y-4">
                  {viagemAtiva && (
                    <div className="p-3 bg-muted rounded-lg">
                      <div className="flex items-center gap-2 text-sm font-medium text-secondary">
                        <div className="h-2 w-2 rounded-full bg-secondary animate-pulse-slow" />
                        Ônibus em operação
                      </div>
                      <p className="text-xs text-muted-foreground mt-1">
                        Partida: {viagemAtiva.dataPartidaPrevista ? new Date(viagemAtiva.dataPartidaPrevista).toLocaleTimeString() : 'N/A'}
                      </p>
                    </div>
                  )}

                  <div className="space-y-2">
                    <div className="flex items-center gap-2 text-sm">
                      <Clock className="h-4 w-4 text-muted-foreground" />
                      <span className="text-muted-foreground">Próximos horários:</span>
                    </div>
                    <div className="flex flex-wrap gap-2">
                      {linhasCronogramas.slice(0, 3).map((cronograma) => (
                        <Badge key={cronograma.id} variant="outline">
                          {cronograma.horaPartida}
                        </Badge>
                      ))}
                    </div>
                  </div>

                  <div className="flex gap-2 pt-2">
                    <Button
                      size="sm"
                      variant="outline"
                      className="flex-1"
                      onClick={() => {
                        setSelectedLinha(linha);
                        setDetalhesOpen(true);
                      }}
                    >
                      <MapPin className="h-4 w-4 mr-1" />
                      Ver Rota
                    </Button>
                    <Button
                      size="sm"
                      className="flex-1"
                      onClick={() => {
                        setSelectedLinha(linha);
                        setCompraOpen(true);
                      }}
                    >
                      <ShoppingCart className="h-4 w-4 mr-1" />
                      Comprar
                    </Button>
                  </div>
                </CardContent>
              </Card>
            );
          })}
        </div>

        <ModalDetalhesLinha
          linha={selectedLinha}
          open={detalhesOpen}
          onOpenChange={setDetalhesOpen}
        />
        <ModalCompraPassagem
          linha={selectedLinha}
          open={compraOpen}
          onOpenChange={setCompraOpen}
        />
      </main>
    </div>
  );
};

export default Linhas;
