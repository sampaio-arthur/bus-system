import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { PontoParada } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Switch } from "@/components/ui/switch";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function PontosParada() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<PontoParada | null>(null);
  const [formData, setFormData] = useState({
    nome: "",
    latitude: 0,
    longitude: 0,
    ativo: true,
    cidadeId: "",
  });

  const queryClient = useQueryClient();

  const { data: pontos = [] } = useQuery({
    queryKey: ["pontos-parada"],
    queryFn: () => api.get("/pontos-parada", { page: 0, size: 1000 }),
  });

  const { data: cidades = [] } = useQuery({
    queryKey: ["cidades"],
    queryFn: () => api.get("/cidades"),
  });

  const createMutation = useMutation({
    mutationFn: (data: any) => api.post("/pontos-parada", {
      ...data,
      cidade: { id: parseInt(data.cidadeId) },
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-parada"] });
      toast({ title: "Ponto de parada criado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar ponto de parada", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) => api.put(`/pontos-parada/${data.id}`, {
      ...data,
      cidade: { id: parseInt(data.cidadeId) },
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-parada"] });
      toast({ title: "Ponto de parada atualizado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar ponto de parada", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/pontos-parada/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pontos-parada"] });
      toast({ title: "Ponto de parada excluído com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir ponto de parada", variant: "destructive" }),
  });

  const normalizedPontos = pontos.map((ponto: PontoParada) => ({
    ...ponto,
    nome: ponto.nome ?? "",
    latitude: ponto.latitude ?? 0,
    longitude: ponto.longitude ?? 0,
    cidade: {
      id: ponto.cidade?.id ?? null,
      nome: ponto.cidade?.nome ?? "",
      uf: ponto.cidade?.uf ?? "",
    },
  }));

  const filteredPontos = normalizedPontos
    .filter((ponto: PontoParada) =>
      ponto.nome.toLowerCase().includes(searchTerm.toLowerCase())
    )
    .sort((a, b) => (a.id ?? 0) - (b.id ?? 0));

  const openDialog = (item?: PontoParada) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        nome: item.nome ?? "",
        latitude: item.latitude ?? 0,
        longitude: item.longitude ?? 0,
        ativo: item.ativo ?? true,
        cidadeId: item.cidade?.id ? String(item.cidade.id) : "",
      });
    } else {
      setEditingItem(null);
      setFormData({ nome: "", latitude: 0, longitude: 0, ativo: true, cidadeId: "" });
    }
    setIsDialogOpen(true);
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
    setEditingItem(null);
    setFormData({ nome: "", latitude: 0, longitude: 0, ativo: true, cidadeId: "" });
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
    { key: "cidade.nome", label: "Cidade" },
    { key: "cidade.uf", label: "UF" },
    { key: "latitude", label: "Latitude", render: (val: number) => Number(val ?? 0).toFixed(6) },
    { key: "longitude", label: "Longitude", render: (val: number) => Number(val ?? 0).toFixed(6) },
    { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Pontos de Parada</h1>
          <p className="text-muted-foreground mt-1">Gerencie os pontos de parada das linhas</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Novo Ponto
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome..."
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
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar Ponto de Parada" : "Novo Ponto de Parada"}</DialogTitle>
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
              <div>
                <Label htmlFor="cidadeId">Cidade</Label>
                <Select value={formData.cidadeId} onValueChange={(val) => setFormData({ ...formData, cidadeId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a cidade" />
                  </SelectTrigger>
                  <SelectContent>
                    {cidades.map((cidade: any) => (
                      <SelectItem key={cidade.id} value={String(cidade.id)}>
                        {cidade.nome} - {cidade.uf}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="flex items-center space-x-2">
                <Switch
                  id="ativo"
                  checked={formData.ativo}
                  onCheckedChange={(checked) => setFormData({ ...formData, ativo: checked })}
                />
                <Label htmlFor="ativo">Ativo</Label>
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
