import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { PontoTuristico } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Switch } from "@/components/ui/switch";
import { Checkbox } from "@/components/ui/checkbox";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function PontosTuristicos() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<PontoTuristico | null>(null);
  const [formData, setFormData] = useState({
    nome: "",
    descricao: "",
    latitude: 0,
    longitude: 0,
    ativo: true,
    pontosParadaIds: [] as string[],
  });

  const queryClient = useQueryClient();

  const { data: pontosTuristicos = [] } = useQuery({
    queryKey: ["pontos-turisticos"],
    queryFn: () => api.get("/pontos-turisticos", { page: 0, size: 1000 }),
  });

  const { data: pontosParada = [] } = useQuery({
    queryKey: ["pontos-parada"],
    queryFn: () => api.get("/pontos-parada", { page: 0, size: 1000 }),
  });

  const buildPayload = (data: any, current?: PontoTuristico | null) => {
    const { pontosParadaIds, ...rest } = data;
    return {
      ...rest,
      id: current?.id,
      version: current?.version,
      pontosParadaProximos: pontosParadaIds.map((id: string) => ({ id: parseInt(id, 10) })),
    };
  };

  const createMutation = useMutation({
    mutationFn: (data: any) =>
      api.post("/pontos-turisticos", buildPayload(data)),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-turisticos"] });
      toast({ title: "Ponto turistico criado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar ponto turistico", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) =>
      api.put(`/pontos-turisticos/${data.id}`, buildPayload(data, editingItem)),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-turisticos"] });
      toast({ title: "Ponto turistico atualizado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar ponto turistico", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/pontos-turisticos/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-turisticos"] });
      toast({ title: "Ponto turistico excluido com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir ponto turistico", variant: "destructive" }),
  });

  const normalizedPontos = pontosTuristicos.map((ponto: PontoTuristico) => ({
    ...ponto,
    nome: ponto.nome ?? "",
    descricao: ponto.descricao ?? "",
    latitude: ponto.latitude ?? 0,
    longitude: ponto.longitude ?? 0,
    ativo: ponto.ativo ?? true,
    pontosParadaProximos: ponto.pontosParadaProximos ?? [],
  }));

  const filteredPontos = normalizedPontos
    .filter((ponto: PontoTuristico) => {
      const term = searchTerm.toLowerCase();
      return ponto.nome.toLowerCase().includes(term) || ponto.descricao.toLowerCase().includes(term);
    })
    .sort((a, b) => (a.id ?? 0) - (b.id ?? 0));

  const togglePontoParada = (id: string, checked: boolean | string) => {
    setFormData((prev) => {
      const exists = prev.pontosParadaIds.includes(id);
      const isChecked = checked === true;
      if (isChecked && !exists) {
        return { ...prev, pontosParadaIds: [...prev.pontosParadaIds, id] };
      }
      if (!isChecked && exists) {
        return { ...prev, pontosParadaIds: prev.pontosParadaIds.filter((p) => p !== id) };
      }
      return prev;
    });
  };

  const openDialog = (item?: PontoTuristico) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        nome: item.nome ?? "",
        descricao: item.descricao ?? "",
        latitude: item.latitude ?? 0,
        longitude: item.longitude ?? 0,
        ativo: item.ativo ?? true,
        pontosParadaIds: (item.pontosParadaProximos || []).map((p) => String(p.id)),
      });
    } else {
      setEditingItem(null);
      setFormData({
        nome: "",
        descricao: "",
        latitude: 0,
        longitude: 0,
        ativo: true,
        pontosParadaIds: [],
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

  const columns = [
    { key: "id", label: "ID" },
    { key: "nome", label: "Nome" },
    {
      key: "descricao",
      label: "Descricao",
      render: (val: string) => (val && val.length > 70 ? `${val.slice(0, 70)}...` : val),
    },
    {
      key: "pontosParadaProximos",
      label: "Paradas proximas",
      render: (val: any[]) => (val && val.length ? val.map((p) => p.nome).join(", ") : "-"),
    },
    { key: "latitude", label: "Latitude", render: (val: number) => Number(val ?? 0).toFixed(6) },
    { key: "longitude", label: "Longitude", render: (val: number) => Number(val ?? 0).toFixed(6) },
    { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Pontos turisticos</h1>
          <p className="text-muted-foreground mt-1">Cadastre e vincule pontos turisticos aos pontos de parada</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Novo ponto
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome ou descricao..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredPontos}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-3xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar ponto turistico" : "Novo ponto turistico"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="col-span-2">
                <Label htmlFor="nome">Nome</Label>
                <Input
                  id="nome"
                  value={formData.nome}
                  onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                  required
                />
              </div>
              <div className="col-span-2">
                <Label htmlFor="descricao">Descricao</Label>
                <Textarea
                  id="descricao"
                  value={formData.descricao}
                  onChange={(e) => setFormData({ ...formData, descricao: e.target.value })}
                  required
                  rows={3}
                />
              </div>
              <div>
                <Label htmlFor="latitude">Latitude</Label>
                <Input
                  id="latitude"
                  type="number"
                  step="0.000001"
                  value={formData.latitude}
                  onChange={(e) => setFormData({ ...formData, latitude: parseFloat(e.target.value) })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="longitude">Longitude</Label>
                <Input
                  id="longitude"
                  type="number"
                  step="0.000001"
                  value={formData.longitude}
                  onChange={(e) => setFormData({ ...formData, longitude: parseFloat(e.target.value) })}
                  required
                />
              </div>
              <div className="flex items-center space-x-2 pt-6">
                <Switch
                  id="ativo"
                  checked={formData.ativo}
                  onCheckedChange={(checked) => setFormData({ ...formData, ativo: checked })}
                />
                <Label htmlFor="ativo">Ativo</Label>
              </div>
              <div className="col-span-2 space-y-2">
                <Label>Pontos de parada proximos</Label>
                <div className="grid grid-cols-2 gap-2 max-h-48 overflow-auto border rounded-md p-2">
                  {pontosParada.map((ponto: any) => (
                    <label key={ponto.id} className="flex items-center space-x-2 text-sm">
                      <Checkbox
                        checked={formData.pontosParadaIds.includes(String(ponto.id))}
                        onCheckedChange={(checked) => togglePontoParada(String(ponto.id), checked)}
                      />
                      <span>{ponto.nome}</span>
                    </label>
                  ))}
                  {pontosParada.length === 0 && (
                    <span className="text-sm text-muted-foreground">Nenhum ponto de parada cadastrado</span>
                  )}
                </div>
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
