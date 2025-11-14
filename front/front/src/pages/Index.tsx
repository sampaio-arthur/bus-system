import { Bus, MapPin, Landmark, Clock, ArrowRight } from "lucide-react";
import { Link } from "react-router-dom";
import Header from "@/components/Header";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { mockLinhas, mockViagens } from "@/data/mockData";

const Index = () => {
  const viagensAtivas = mockViagens.slice(0, 3);

  return (
    <div className="min-h-screen bg-background">
      <Header />
      
      {/* Hero Section */}
      <section className="relative overflow-hidden">
        <div className="absolute inset-0 bg-gradient-transit opacity-10" />
        <div className="container relative py-20 md:py-28">
          <div className="max-w-3xl mx-auto text-center space-y-6 animate-slide-in">
            <h1 className="text-5xl md:text-6xl font-bold leading-tight">
              <span className="bg-gradient-transit bg-clip-text text-transparent">
                Sistema Rodoviário
              </span>
              <br />
              <span className="text-foreground">de Araranguá</span>
            </h1>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Acompanhe em tempo real as linhas de ônibus, encontre paradas próximas e
              descubra pontos turísticos da cidade
            </p>
            <div className="flex flex-wrap gap-4 justify-center pt-4">
              <Button size="lg" asChild className="bg-gradient-transit hover:opacity-90">
                <Link to="/linhas">
                  Ver Linhas <ArrowRight className="ml-2 h-5 w-5" />
                </Link>
              </Button>
              <Button size="lg" variant="outline" asChild>
                <Link to="/paradas">
                  Encontrar Paradas
                </Link>
              </Button>
            </div>
          </div>
        </div>
      </section>

      {/* Quick Stats */}
      <section className="container py-12 md:py-16">
        <div className="grid gap-6 md:grid-cols-3">
          <Card className="shadow-card animate-slide-in">
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Linhas Ativas
              </CardTitle>
              <Bus className="h-4 w-4 text-primary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{mockLinhas.length}</div>
              <p className="text-xs text-muted-foreground mt-1">
                Cobrindo toda a cidade
              </p>
            </CardContent>
          </Card>

          <Card className="shadow-card animate-slide-in" style={{ animationDelay: "0.1s" }}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Ônibus em Operação
              </CardTitle>
              <Clock className="h-4 w-4 text-secondary" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">{viagensAtivas.length}</div>
              <p className="text-xs text-muted-foreground mt-1">
                Rastreamento em tempo real
              </p>
            </CardContent>
          </Card>

          <Card className="shadow-card animate-slide-in" style={{ animationDelay: "0.2s" }}>
            <CardHeader className="flex flex-row items-center justify-between pb-2">
              <CardTitle className="text-sm font-medium text-muted-foreground">
                Pontos Turísticos
              </CardTitle>
              <Landmark className="h-4 w-4 text-transit-orange" />
            </CardHeader>
            <CardContent>
              <div className="text-3xl font-bold">4</div>
              <p className="text-xs text-muted-foreground mt-1">
                Acessíveis por ônibus
              </p>
            </CardContent>
          </Card>
        </div>
      </section>

      {/* Live Trips */}
      <section className="container py-12 md:py-16">
        <div className="flex items-center justify-between mb-8">
          <div>
            <h2 className="text-3xl font-bold mb-2">Ônibus em Tempo Real</h2>
            <p className="text-muted-foreground">
              Acompanhe o progresso dos ônibus em operação
            </p>
          </div>
          <Button variant="ghost" asChild>
            <Link to="/linhas">
              Ver todas <ArrowRight className="ml-2 h-4 w-4" />
            </Link>
          </Button>
        </div>

        <div className="grid gap-6 md:grid-cols-3">
          {viagensAtivas.map((viagem, index) => {
            const linha = viagem.linha;
            if (!linha) return null;

            return (
              <Card
                key={viagem.id}
                className="shadow-card hover:shadow-hover transition-all animate-slide-in"
                style={{ animationDelay: `${index * 0.1}s` }}
              >
                <CardHeader>
                  <div className="flex items-center justify-between">
                    <div className="flex items-center gap-3">
                      <div
                        className="p-2 rounded-lg"
                        style={{ backgroundColor: linha.cor }}
                      >
                        <Bus className="h-4 w-4 text-white" />
                      </div>
                      <div>
                        <CardTitle className="text-sm">{linha.nome}</CardTitle>
                        <Badge variant="secondary" className="mt-1 text-xs">
                          {linha.codigo}
                        </Badge>
                      </div>
                    </div>
                    <div className="h-2 w-2 rounded-full bg-secondary animate-pulse-slow" />
                  </div>
                </CardHeader>
                <CardContent>
                  <div className="space-y-2">
                    <div className="text-sm text-muted-foreground">
                      Partida prevista: {viagem.dataPartidaPrevista ? new Date(viagem.dataPartidaPrevista).toLocaleTimeString() : 'N/A'}
                    </div>
                    <Badge className="bg-secondary">Em operação</Badge>
                  </div>
                </CardContent>
              </Card>
            );
          })}
        </div>
      </section>

      {/* Features */}
      <section className="container py-12 md:py-16">
        <h2 className="text-3xl font-bold text-center mb-12">
          Funcionalidades do Sistema
        </h2>
        <div className="grid gap-8 md:grid-cols-3">
          <Card className="text-center shadow-card hover:shadow-hover transition-all animate-slide-in">
            <CardHeader>
              <div className="mx-auto mb-4 p-4 rounded-full bg-gradient-transit w-fit">
                <Bus className="h-8 w-8 text-primary-foreground" />
              </div>
              <CardTitle>Rastreamento em Tempo Real</CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-sm text-muted-foreground">
                Acompanhe a posição dos ônibus e saiba exatamente quando eles chegarão
              </p>
            </CardContent>
          </Card>

          <Card className="text-center shadow-card hover:shadow-hover transition-all animate-slide-in" style={{ animationDelay: "0.1s" }}>
            <CardHeader>
              <div className="mx-auto mb-4 p-4 rounded-full bg-gradient-stop w-fit">
                <MapPin className="h-8 w-8 text-secondary-foreground" />
              </div>
              <CardTitle>Paradas e Itinerários</CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-sm text-muted-foreground">
                Encontre paradas próximas e consulte os itinerários completos de cada linha
              </p>
            </CardContent>
          </Card>

          <Card className="text-center shadow-card hover:shadow-hover transition-all animate-slide-in" style={{ animationDelay: "0.2s" }}>
            <CardHeader>
              <div className="mx-auto mb-4 p-4 rounded-full bg-gradient-to-br from-transit-orange to-transit-purple w-fit">
                <Landmark className="h-8 w-8 text-white" />
              </div>
              <CardTitle>Pontos Turísticos</CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-sm text-muted-foreground">
                Descubra atrações turísticas próximas às paradas e planeje seus passeios
              </p>
            </CardContent>
          </Card>
        </div>
      </section>
    </div>
  );
};

export default Index;
