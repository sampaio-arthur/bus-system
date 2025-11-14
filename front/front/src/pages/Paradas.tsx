import { useQuery } from "@tanstack/react-query";
import { MapPin, Navigation, Loader2 } from "lucide-react";
import Header from "@/components/Header";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { pontoParadaService } from "@/services/pontoParadaService";
import { pontoTuristicoService } from "@/services/pontoTuristicoService";
import { pontoParadaTuristicoService } from "@/services/pontoParadaTuristicoService";

const Paradas = () => {
  const { data: paradasData, isLoading } = useQuery({
    queryKey: ['paradas'],
    queryFn: async () => {
      const response = await pontoParadaService.getAll(0, 100);
      return response.data;
    },
  });

  const { data: turisticosData } = useQuery({
    queryKey: ['pontos-turisticos'],
    queryFn: async () => {
      const response = await pontoTuristicoService.getAll(0, 100);
      return response.data;
    },
  });

  const { data: relacaoData } = useQuery({
    queryKey: ['pontos-parada-turisticos'],
    queryFn: async () => {
      const response = await pontoParadaTuristicoService.getAll(0, 100);
      return response.data;
    },
  });

  const paradas = paradasData?.content || [];
  const turisticos = turisticosData?.content || [];
  const relacoes = relacaoData?.content || [];

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
        <div className="mb-8">
          <h1 className="text-4xl font-bold mb-2 bg-gradient-transit bg-clip-text text-transparent">
            Paradas de Ônibus
          </h1>
          <p className="text-muted-foreground">
            Encontre as paradas mais próximas e veja pontos turísticos nas redondezas
          </p>
        </div>

        <div className="grid gap-6 md:grid-cols-2">
          {paradas.map((parada) => {
            const relacao = relacoes.find(r => r.idPontoParada === parada.id);
            const pontoProximo = relacao ? turisticos.find(t => t.id === relacao.idPontoTuristico) : null;

            return (
              <Card
                key={parada.id}
                className="transition-all hover:shadow-hover animate-slide-in"
              >
                <CardHeader>
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-3">
                      <div className="p-2 rounded-lg bg-gradient-stop">
                        <MapPin className="h-5 w-5 text-secondary-foreground" />
                      </div>
                      <CardTitle className="text-lg">{parada.nome}</CardTitle>
                    </div>
                    <Button size="sm" variant="ghost">
                      <Navigation className="h-4 w-4" />
                    </Button>
                  </div>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="flex gap-2 text-sm text-muted-foreground">
                    <Badge variant="secondary">Lat: {parada.latitude}</Badge>
                    <Badge variant="secondary">Lng: {parada.longitude}</Badge>
                  </div>

                  {pontoProximo && (
                    <div className="p-4 bg-gradient-to-r from-secondary/10 to-secondary/5 rounded-lg border border-secondary/20">
                      <h4 className="font-semibold text-sm mb-2 text-secondary">
                        🏛️ Ponto Turístico Próximo
                      </h4>
                      <p className="text-sm font-medium mb-1">{pontoProximo.nome}</p>
                      <p className="text-xs text-muted-foreground line-clamp-2">
                        {pontoProximo.descricao}
                      </p>
                    </div>
                  )}

                  <div className="pt-2">
                    <p className="text-sm text-muted-foreground">
                      Linhas que passam por aqui:
                    </p>
                    <div className="flex flex-wrap gap-2 mt-2">
                      <Badge>L101</Badge>
                      <Badge>L102</Badge>
                      {parada.id % 2 === 0 && <Badge>L201</Badge>}
                    </div>
                  </div>
                </CardContent>
              </Card>
            );
          })}
        </div>
      </main>
    </div>
  );
};

export default Paradas;
