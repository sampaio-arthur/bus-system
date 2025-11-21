import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Veiculo } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Switch } from "@/components/ui/switch";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function Veiculos() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Veiculo | null>(null);
  const [formData, setFormData] = useState({
    placa: "",
    modelo: "",
    chassi: "",
    anoFabricacao: new Date().getFullYear(),
    capacidade: 40,
    ativo: true,
    tipoVeiculoId: "",
  });

  const queryClient = useQueryClient();

  const { data: veiculos = [] } = useQuery({
    queryKey: ["veiculos"],
    queryFn: () => api.get("/veiculos"),
  });

  const { data: tiposVeiculo = [] } = useQuery({
    queryKey: ["tipos-veiculo"],
    queryFn: () => api.get("/tipos-veiculo"),
  });

  const createMutation = useMutation({
    mutationFn: (data: any) => api.post("/veiculos", {
      ...data,
      tipoVeiculo: { id: parseInt(data.tipoVeiculoId) },
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["veiculos"] });
      toast({ title: "Veículo criado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar veículo", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) => api.put(`/veiculos/${data.id}`, {
      ...data,
      tipoVeiculo: { id: parseInt(data.tipoVeiculoId) },
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["veiculos"] });
      toast({ title: "Veículo atualizado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar veículo", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/veiculos/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["veiculos"] });
      toast({ title: "Veículo excluído com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir veículo", variant: "destructive" }),
  });

  const filteredVeiculos = veiculos.filter((veiculo: Veiculo) =>
    veiculo.placa.toLowerCase().includes(searchTerm.toLowerCase()) ||
    veiculo.modelo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openDialog = (item?: Veiculo) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        placa: item.placa,
        modelo: item.modelo,
        chassi: item.chassi,
        anoFabricacao: item.anoFabricacao,
        capacidade: item.capacidade,
        ativo: item.ativo,
        tipoVeiculoId: String(item.tipoVeiculo.id),
      });
    } else {
      setEditingItem(null);
      setFormData({
        placa: "",
        modelo: "",
        chassi: "",
        anoFabricacao: new Date().getFullYear(),
        capacidade: 40,
        ativo: true,
        tipoVeiculoId: "",
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
    { key: "placa", label: "Placa" },
    { key: "modelo", label: "Modelo" },
    { key: "tipoVeiculo.descricao", label: "Tipo" },
    { key: "anoFabricacao", label: "Ano" },
    { key: "capacidade", label: "Capacidade" },
    { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Veículos</h1>
          <p className="text-muted-foreground mt-1">Gerencie a frota de veículos</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Novo Veículo
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por placa ou modelo..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredVeiculos}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar Veículo" : "Novo Veículo"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="placa">Placa</Label>
                <Input
                  id="placa"
                  value={formData.placa}
                  onChange={(e) => setFormData({ ...formData, placa: e.target.value.toUpperCase() })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="modelo">Modelo</Label>
                <Input
                  id="modelo"
                  value={formData.modelo}
                  onChange={(e) => setFormData({ ...formData, modelo: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="chassi">Chassi</Label>
                <Input
                  id="chassi"
                  value={formData.chassi}
                  onChange={(e) => setFormData({ ...formData, chassi: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="tipoVeiculoId">Tipo de Veículo</Label>
                <Select value={formData.tipoVeiculoId} onValueChange={(val) => setFormData({ ...formData, tipoVeiculoId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o tipo" />
                  </SelectTrigger>
                  <SelectContent>
                    {tiposVeiculo.map((tipo: any) => (
                      <SelectItem key={tipo.id} value={String(tipo.id)}>
                        {tipo.descricao}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="anoFabricacao">Ano de Fabricação</Label>
                <Input
                  id="anoFabricacao"
                  type="number"
                  value={formData.anoFabricacao}
                  onChange={(e) => setFormData({ ...formData, anoFabricacao: parseInt(e.target.value) })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="capacidade">Capacidade</Label>
                <Input
                  id="capacidade"
                  type="number"
                  value={formData.capacidade}
                  onChange={(e) => setFormData({ ...formData, capacidade: parseInt(e.target.value) })}
                  required
                />
              </div>
              <div className="flex items-center space-x-2 pt-8">
                <Switch
                  id="ativo"
                  checked={formData.ativo}
                  onCheckedChange={(checked) => setFormData({ ...formData, ativo: checked })}
                />
                <Label htmlFor="ativo">Ativo</Label>
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
