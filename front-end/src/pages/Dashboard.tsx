import { useQuery } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Bus, Users, MapPin, Calendar, TrendingUp } from "lucide-react";
import { Skeleton } from "@/components/ui/skeleton";

export default function Dashboard() {
  const { data: relatorioGastos, isLoading: loadingGastos } = useQuery({
    queryKey: ["relatorios", "gastos-manutencao"],
    queryFn: () => api.get("/relatorios/gastos-manutencao-por-veiculo", { meses: 12 }),
  });

  const { data: relatorioPontos, isLoading: loadingPontos } = useQuery({
    queryKey: ["relatorios", "pontos-turisticos"],
    queryFn: () => api.get("/relatorios/pontos-turisticos-por-cidade"),
  });

  const { data: relatorioPassageiros, isLoading: loadingPassageiros } = useQuery({
    queryKey: ["relatorios", "media-passageiros"],
    queryFn: () => api.get("/relatorios/media-passageiros-por-linha", { meses: 6 }),
  });

  const stats = [
    {
      title: "Veículos Ativos",
      value: relatorioGastos?.length || 0,
      icon: Bus,
      color: "text-primary",
      bgColor: "bg-primary/10",
    },
    {
      title: "Cidades Atendidas",
      value: relatorioPontos?.length || 0,
      icon: MapPin,
      color: "text-secondary",
      bgColor: "bg-secondary/10",
    },
    {
      title: "Linhas Operando",
      value: relatorioPassageiros?.length || 0,
      icon: Calendar,
      color: "text-accent",
      bgColor: "bg-accent/10",
    },
    {
      title: "Total Passageiros",
      value: relatorioPassageiros?.reduce((acc: number, curr: any) => acc + curr.mediaPassageiros, 0) || 0,
      icon: Users,
      color: "text-warning",
      bgColor: "bg-warning/10",
    },
  ];

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-2">Dashboard</h1>
        <p className="text-muted-foreground">
          Visão geral do sistema de transporte público
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        {stats.map((stat) => {
          const Icon = stat.icon;
          return (
            <Card key={stat.title} className="border-border">
              <CardContent className="p-6">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm font-medium text-muted-foreground">
                      {stat.title}
                    </p>
                    <p className="text-2xl font-bold mt-2">{stat.value}</p>
                  </div>
                  <div className={`p-3 rounded-lg ${stat.bgColor}`}>
                    <Icon className={`h-6 w-6 ${stat.color}`} />
                  </div>
                </div>
              </CardContent>
            </Card>
          );
        })}
      </div>

      {/* Charts/Reports */}
      <div className="grid gap-6 md:grid-cols-2">
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <TrendingUp className="h-5 w-5 text-primary" />
              Gastos com Manutenção (12 meses)
            </CardTitle>
          </CardHeader>
          <CardContent>
            {loadingGastos ? (
              <div className="space-y-2">
                {[...Array(5)].map((_, i) => (
                  <Skeleton key={i} className="h-12 w-full" />
                ))}
              </div>
            ) : (
              <div className="space-y-3">
                {relatorioGastos?.slice(0, 5).map((item: any) => (
                  <div key={item.placa} className="flex items-center justify-between p-3 bg-muted/30 rounded-lg">
                    <span className="font-medium">{item.placa}</span>
                    <span className="text-sm font-semibold text-primary">
                      R$ {item.valorTotalGasto.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}
                    </span>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <MapPin className="h-5 w-5 text-secondary" />
              Pontos Turísticos por Cidade
            </CardTitle>
          </CardHeader>
          <CardContent>
            {loadingPontos ? (
              <div className="space-y-2">
                {[...Array(5)].map((_, i) => (
                  <Skeleton key={i} className="h-12 w-full" />
                ))}
              </div>
            ) : (
              <div className="space-y-3">
                {relatorioPontos?.slice(0, 5).map((item: any) => (
                  <div key={`${item.cidade}-${item.uf}`} className="flex items-center justify-between p-3 bg-muted/30 rounded-lg">
                    <span className="font-medium">{item.cidade} - {item.uf}</span>
                    <span className="text-sm font-semibold text-secondary">
                      {item.quantidadePontosTuristicos} pontos
                    </span>
                  </div>
                ))}
              </div>
            )}
          </CardContent>
        </Card>
      </div>

      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <Users className="h-5 w-5 text-accent" />
            Média de Passageiros por Linha (6 meses)
          </CardTitle>
        </CardHeader>
        <CardContent>
          {loadingPassageiros ? (
            <div className="grid gap-2 md:grid-cols-2 lg:grid-cols-3">
              {[...Array(6)].map((_, i) => (
                <Skeleton key={i} className="h-16 w-full" />
              ))}
            </div>
          ) : (
            <div className="grid gap-3 md:grid-cols-2 lg:grid-cols-3">
              {relatorioPassageiros?.map((item: any) => (
                <div key={item.linha} className="p-4 bg-muted/30 rounded-lg">
                  <p className="font-medium text-sm mb-1">{item.linha}</p>
                  <p className="text-2xl font-bold text-accent">
                    {Math.round(item.mediaPassageiros)}
                  </p>
                  <p className="text-xs text-muted-foreground mt-1">passageiros/viagem</p>
                </div>
              ))}
            </div>
          )}
        </CardContent>
      </Card>
    </div>
  );
}
