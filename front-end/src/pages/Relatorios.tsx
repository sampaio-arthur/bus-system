import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { api } from "@/lib/api";
import {
  RelatorioGastosManutencao,
  RelatorioMediaPassageiros,
  RelatorioPontosTuristicos,
} from "@/types";
import { useQuery } from "@tanstack/react-query";
import { useMemo, useState } from "react";
import {
  Bar,
  BarChart,
  CartesianGrid,
  Cell,
  Legend,
  Pie,
  PieChart,
  XAxis,
  YAxis,
} from "recharts";

const toNumber = (value: unknown) => {
  const num = Number(value);
  return Number.isFinite(num) ? num : 0;
};

const dynamicColor = (index: number, total: number) => {
  const hue = Math.round((360 / Math.max(total, 1)) * index);
  return `hsl(${hue} 70% 55%)`;
};

export default function Relatorios() {
  const [mesesGasto, setMesesGasto] = useState(24);
  const [mesesMedia, setMesesMedia] = useState(24);

  const { data: gastos = [] } = useQuery({
    queryKey: ["relatorios", "gastos-manutencao", mesesGasto],
    queryFn: () =>
      api.get("/relatorios/gastos-manutencao-por-veiculo", {
        meses: mesesGasto,
      }),
  });

  const { data: pontos = [] } = useQuery({
    queryKey: ["relatorios", "pontos-turisticos"],
    queryFn: () => api.get("/relatorios/pontos-turisticos-por-cidade"),
  });

  const { data: medias = [] } = useQuery({
    queryKey: ["relatorios", "media-passageiros", mesesMedia],
    queryFn: () =>
      api.get("/relatorios/media-passageiros-por-linha", { meses: mesesMedia }),
  });

  const gastosData = useMemo(() => {
    const list = (gastos as RelatorioGastosManutencao[]).map((g) => ({
      name: g.placa,
      value: toNumber(g.valorTotalGasto),
    }));
    return list.map((item, idx) => ({
      ...item,
      color: dynamicColor(idx, list.length),
    }));
  }, [gastos]);

  const gastosLegend = useMemo(
    () =>
      gastosData.map((item) => ({
        value: item.name,
        color: item.color,
        type: "circle" as const,
      })),
    [gastosData]
  );

  const pontosData = useMemo(() => {
    const list = (pontos as RelatorioPontosTuristicos[]).map((p) => ({
      name: `${p.cidade} - ${p.uf}`,
      value: toNumber(p.quantidadePontosTuristicos),
    }));
    return list.map((item, idx) => ({
      ...item,
      color: dynamicColor(idx, list.length),
    }));
  }, [pontos]);

  const pontosLegend = useMemo(
    () =>
      pontosData.map((item) => ({
        value: item.name,
        color: item.color,
        type: "circle" as const,
      })),
    [pontosData]
  );

  const mediasData = useMemo(() => {
    const list = (medias as RelatorioMediaPassageiros[]).map((m) => ({
      name: m.linha,
      value: toNumber(m.mediaPassageiros),
    }));
    return list.map((item, idx) => ({
      ...item,
      color: dynamicColor(idx, list.length),
    }));
  }, [medias]);

  const mediasLegend = useMemo(
    () =>
      mediasData.map((item) => ({
        value: item.name,
        color: item.color,
        type: "circle" as const,
      })),
    [mediasData]
  );

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold">Relatorios</h1>
        <p className="text-muted-foreground mt-1">
          Visoes consolidadas de manutencao, pontos turisticos e passageiros.
        </p>
      </div>

      <div className="grid grid-cols-1 gap-6 xl:grid-cols-2">
        <div className="xl:col-span-2 flex justify-center">
          <Card className="w-full max-w-5xl">
            <CardHeader className="pb-2">
              <CardTitle>Pontos turisticos por cidade</CardTitle>
              <CardDescription>
                Quantidade de pontos cadastrados
              </CardDescription>
            </CardHeader>
            <CardContent>
              <ChartContainer
                config={{
                  value: { label: "Pontos" },
                }}
                className="mx-auto h-[360px] w-full max-w-4xl"
              >
                {pontosData.length === 0 ? (
                  <div className="flex h-full items-center justify-center text-sm text-muted-foreground">
                    Sem dados para exibir
                  </div>
                ) : (
                  <BarChart
                    data={pontosData}
                    margin={{ top: 12, right: 12, left: 0, bottom: 24 }}
                  >
                    <CartesianGrid vertical={false} strokeDasharray="3 3" />
                    <XAxis
                      dataKey="name"
                      tickMargin={8}
                      interval={0}
                      angle={-20}
                      textAnchor="end"
                      height={70}
                    />
                    <YAxis allowDecimals={false} />
                    <ChartTooltip content={<ChartTooltipContent hideLabel />} />
                    <Legend
                      verticalAlign="bottom"
                      align="center"
                      height={28}
                      iconType="circle"
                      payload={pontosLegend}
                    />
                    <Bar dataKey="value" radius={[4, 4, 0, 0]}>
                      {pontosData.map((item, idx) => (
                        <Cell key={idx} fill={item.color} />
                      ))}
                    </Bar>
                  </BarChart>
                )}
              </ChartContainer>
            </CardContent>
          </Card>
        </div>
      </div>

      <div className="grid grid-cols-1 gap-6 xl:grid-cols-2">
        <Card>
          <CardHeader className="pb-2">
            <CardTitle>Gasto de manutencao por veiculo</CardTitle>
            <CardDescription>
              Informe os ultimos meses considerados (padrao 24 meses)
            </CardDescription>
            <div className="flex items-center gap-3">
              <Label htmlFor="mesesGasto" className="text-xs">
                Meses
              </Label>
              <Input
                id="mesesGasto"
                type="number"
                min={1}
                max={36}
                value={mesesGasto}
                onChange={(e) => setMesesGasto(Number(e.target.value) || 1)}
                className="h-8 w-20"
              />
            </div>
          </CardHeader>
          <CardContent>
            <ChartContainer
              config={{
                value: { label: "Gasto (R$)" },
              }}
              className="h-[280px]"
            >
              {gastosData.length === 0 ? (
                <div className="flex h-full items-center justify-center text-sm text-muted-foreground">
                  Sem dados para o periodo
                </div>
              ) : (
                <PieChart>
                  <Pie
                    data={gastosData}
                    dataKey="value"
                    nameKey="name"
                    innerRadius={50}
                    outerRadius={100}
                    paddingAngle={2}
                    stroke="none"
                  >
                    {gastosData.map((item, idx) => (
                      <Cell key={idx} fill={item.color} />
                    ))}
                  </Pie>
                  <ChartTooltip content={<ChartTooltipContent hideLabel />} />
                  <Legend
                    verticalAlign="bottom"
                    height={24}
                    iconType="circle"
                    payload={gastosLegend}
                  />
                </PieChart>
              )}
            </ChartContainer>
          </CardContent>
        </Card>

        <Card>
          <CardHeader className="pb-2">
            <CardTitle>Media de passageiros por linha</CardTitle>
            <CardDescription>
              Informe os ultimos meses considerados (padrao 24 meses)
            </CardDescription>
            <div className="flex items-center gap-3">
              <Label htmlFor="mesesMedia" className="text-xs">
                Meses
              </Label>
              <Input
                id="mesesMedia"
                type="number"
                min={1}
                max={36}
                value={mesesMedia}
                onChange={(e) => setMesesMedia(Number(e.target.value) || 1)}
                className="h-8 w-20"
              />
            </div>
          </CardHeader>
          <CardContent>
            <ChartContainer
              config={{
                value: { label: "Passageiros" },
              }}
              className="h-[320px]"
            >
              {mediasData.length === 0 ? (
                <div className="flex h-full items-center justify-center text-sm text-muted-foreground">
                  Sem dados para o periodo
                </div>
              ) : (
                <BarChart
                  data={mediasData}
                  margin={{ top: 12, right: 12, left: 0, bottom: 24 }}
                >
                  <CartesianGrid vertical={false} strokeDasharray="3 3" />
                  <XAxis
                    dataKey="name"
                    tickMargin={8}
                    interval={0}
                    angle={-20}
                    textAnchor="end"
                    height={60}
                  />
                  <YAxis allowDecimals={false} />
                  <ChartTooltip content={<ChartTooltipContent hideLabel />} />
                  <Legend
                    verticalAlign="bottom"
                    height={24}
                    iconType="circle"
                    payload={mediasLegend}
                  />
                  <Bar dataKey="value" radius={[4, 4, 0, 0]}>
                    {mediasData.map((item, idx) => (
                      <Cell key={idx} fill={item.color} />
                    ))}
                  </Bar>
                </BarChart>
              )}
            </ChartContainer>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
