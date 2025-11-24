import { useMemo, useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Manutencao } from "@/types";
import { DataTable } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

const toDateTimeInput = (value?: string) => {
  if (!value) return "";
  const d = new Date(value);
  return Number.isNaN(d.getTime()) ? "" : d.toISOString().slice(0, 16);
};

export default function Manutencoes() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Manutencao | null>(null);
  const [formData, setFormData] = useState({
    veiculoId: "",
    mecanicoId: "",
    descricao: "",
    custoTotal: 0,
    dataInicio: "",
    dataFim: "",
  });

  const queryClient = useQueryClient();

  const { data: manutencoes = [] } = useQuery({
    queryKey: ["manutencoes"],
    queryFn: () => api.get("/manutencoes", { page: 0, size: 1000 }),
  });

  const { data: veiculos = [] } = useQuery({
    queryKey: ["veiculos"],
    queryFn: () => api.get("/veiculos", { page: 0, size: 1000 }),
  });

  const { data: pessoas = [] } = useQuery({
    queryKey: ["pessoas"],
    queryFn: () => api.get("/pessoas", { page: 0, size: 1000 }),
  });

  const mecanicos = pessoas.filter((p: any) => (p.tipoPessoa || "").toUpperCase() === "MECANICO");

  const createMutation = useMutation({
    mutationFn: (data: any) =>
      api.post("/manutencoes", {
        descricao: data.descricao,
        custoTotal: parseFloat(data.custoTotal),
        dataInicio: data.dataInicio ? new Date(data.dataInicio).toISOString() : new Date().toISOString(),
        dataFim: data.dataFim ? new Date(data.dataFim).toISOString() : null,
        veiculo: { id: parseInt(data.veiculoId) },
        mecanico: { id: parseInt(data.mecanicoId) },
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["manutencoes"] });
      toast({ title: "Manutencao criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar manutencao", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) =>
      api.put(`/manutencoes/${data.id}`, {
        descricao: data.descricao,
        custoTotal: parseFloat(data.custoTotal),
        dataInicio: data.dataInicio ? new Date(data.dataInicio).toISOString() : null,
        dataFim: data.dataFim ? new Date(data.dataFim).toISOString() : null,
        veiculo: { id: parseInt(data.veiculoId) },
        mecanico: { id: parseInt(data.mecanicoId) },
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["manutencoes"] });
      toast({ title: "Manutencao atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar manutencao", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/manutencoes/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["manutencoes"] });
      toast({ title: "Manutencao excluida com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir manutencao", variant: "destructive" }),
  });

  const normalizedManutencoes = manutencoes.map((manutencao: Manutencao) => ({
    ...manutencao,
    descricao: manutencao.descricao ?? "",
    custoTotal: manutencao.custoTotal ?? 0,
    dataInicio: manutencao.dataInicio ?? "",
    dataFim: manutencao.dataFim ?? "",
    veiculo: manutencao.veiculo ?? { id: 0, placa: "" },
    mecanico: manutencao.mecanico ?? { id: 0, nome: "" },
  }));

  const filteredManutencoes = normalizedManutencoes
    .filter((manutencao: Manutencao) => {
      const term = searchTerm.toLowerCase();
      return (
        manutencao.veiculo?.placa?.toLowerCase().includes(term) ||
        manutencao.mecanico?.nome?.toLowerCase().includes(term) ||
        manutencao.descricao.toLowerCase().includes(term)
      );
    })
    .sort((a, b) => (a.id ?? 0) - (b.id ?? 0));

  const openDialog = (item?: Manutencao) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        veiculoId: item.veiculo?.id ? String(item.veiculo.id) : "",
        mecanicoId: item.mecanico?.id ? String(item.mecanico.id) : "",
        descricao: item.descricao ?? "",
        custoTotal: item.custoTotal ?? 0,
        dataInicio: toDateTimeInput(item.dataInicio),
        dataFim: toDateTimeInput(item.dataFim),
      });
    } else {
      setEditingItem(null);
      setFormData({
        veiculoId: "",
        mecanicoId: "",
        descricao: "",
        custoTotal: 0,
        dataInicio: toDateTimeInput(new Date().toISOString()),
        dataFim: "",
      });
    }
    setIsDialogOpen(true);
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
    setEditingItem(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const payload = { ...formData, id: editingItem?.id };
    if (editingItem) {
      updateMutation.mutate(payload);
    } else {
      createMutation.mutate(formData);
    }
  };

  const columns = useMemo(
    () => [
      { key: "id", label: "ID" },
      { key: "veiculo.placa", label: "Veiculo" },
      { key: "mecanico.nome", label: "Mecanico" },
      { key: "descricao", label: "Descricao", render: (val: string) => (val && val.length > 60 ? `${val.slice(0, 60)}...` : val) },
      { key: "custoTotal", label: "Custo", render: (val: number) => `R$ ${Number(val ?? 0).toFixed(2)}` },
      { key: "dataInicio", label: "Inicio", render: (val: string) => (val ? new Date(val).toLocaleString("pt-BR") : "-") },
      { key: "dataFim", label: "Fim", render: (val: string) => (val ? new Date(val).toLocaleString("pt-BR") : "-") },
    ],
    []
  );

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Manutencoes</h1>
          <p className="text-muted-foreground mt-1">Controle manutencoes, mecanicos e custos</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova manutencao
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por placa, mecanico ou descricao..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredManutencoes}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-3xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar manutencao" : "Nova manutencao"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="veiculoId">Veiculo</Label>
                <Select value={formData.veiculoId} onValueChange={(val) => setFormData({ ...formData, veiculoId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o veiculo" />
                  </SelectTrigger>
                  <SelectContent>
                    {veiculos.map((veiculo: any) => (
                      <SelectItem key={veiculo.id} value={String(veiculo.id)}>
                        {veiculo.placa} - {veiculo.modelo}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="mecanicoId">Mecanico</Label>
                <Select value={formData.mecanicoId} onValueChange={(val) => setFormData({ ...formData, mecanicoId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o mecanico" />
                  </SelectTrigger>
                  <SelectContent>
                    {mecanicos.map((mecanico: any) => (
                      <SelectItem key={mecanico.id} value={String(mecanico.id)}>
                        {mecanico.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="col-span-2">
                <Label htmlFor="descricao">Descricao</Label>
                <Textarea
                  id="descricao"
                  value={formData.descricao}
                  onChange={(e) => setFormData({ ...formData, descricao: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="custoTotal">Custo total</Label>
                <Input
                  id="custoTotal"
                  type="number"
                  step="0.01"
                  value={formData.custoTotal}
                  onChange={(e) => setFormData({ ...formData, custoTotal: parseFloat(e.target.value) })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="dataInicio">Data de inicio</Label>
                <Input
                  id="dataInicio"
                  type="datetime-local"
                  value={formData.dataInicio}
                  onChange={(e) => setFormData({ ...formData, dataInicio: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="dataFim">Data de conclusao</Label>
                <Input
                  id="dataFim"
                  type="datetime-local"
                  value={formData.dataFim}
                  onChange={(e) => setFormData({ ...formData, dataFim: e.target.value })}
                />
              </div>
            </div>
            <DialogFooter>
              <Button type="button" variant="outline" onClick={closeDialog}>
                Cancelar
              </Button>
              <Button type="submit">Salvar</Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </div>
  );
}
