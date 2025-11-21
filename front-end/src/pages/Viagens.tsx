import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Viagem } from "@/types";
import { DataTable } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Badge } from "@/components/ui/badge";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

const statusOptions = [
  { value: 0, label: "Agendada", color: "bg-muted" },
  { value: 1, label: "Em andamento", color: "bg-primary" },
  { value: 2, label: "Concluída", color: "bg-success" },
  { value: 3, label: "Cancelada", color: "bg-destructive" },
];

export default function Viagens() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Viagem | null>(null);
  const [formData, setFormData] = useState({
    dataPartidaPrevista: "",
    dataChegadaPrevista: "",
    linhaId: "",
    veiculoId: "",
    motoristaId: "",
    status: 0,
  });

  const queryClient = useQueryClient();

  const { data: viagens = [] } = useQuery({
    queryKey: ["viagens"],
    queryFn: () => api.get("/viagens"),
  });

  const { data: linhas = [] } = useQuery({
    queryKey: ["linhas"],
    queryFn: () => api.get("/linhas"),
  });

  const { data: veiculos = [] } = useQuery({
    queryKey: ["veiculos"],
    queryFn: () => api.get("/veiculos"),
  });

  const { data: pessoas = [] } = useQuery({
    queryKey: ["pessoas"],
    queryFn: () => api.get("/pessoas"),
  });

  const motoristas = pessoas.filter((p: any) => p.tipoPessoa === 1);

  const createMutation = useMutation({
    mutationFn: (data: any) => api.post("/viagens", {
      dataPartidaPrevista: data.dataPartidaPrevista,
      dataChegadaPrevista: data.dataChegadaPrevista,
      linha: { id: parseInt(data.linhaId) },
      veiculo: { id: parseInt(data.veiculoId) },
      motorista: { id: parseInt(data.motoristaId) },
      status: data.status,
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["viagens"] });
      toast({ title: "Viagem criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar viagem", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) => api.put(`/viagens/${data.id}`, {
      ...data,
      linha: { id: parseInt(data.linhaId) },
      veiculo: { id: parseInt(data.veiculoId) },
      motorista: { id: parseInt(data.motoristaId) },
    }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["viagens"] });
      toast({ title: "Viagem atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar viagem", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/viagens/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["viagens"] });
      toast({ title: "Viagem excluída com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir viagem", variant: "destructive" }),
  });

  const filteredViagens = viagens.filter((viagem: Viagem) =>
    viagem.linha.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
    viagem.veiculo.placa.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openDialog = (item?: Viagem) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        dataPartidaPrevista: item.dataPartidaPrevista.substring(0, 16),
        dataChegadaPrevista: item.dataChegadaPrevista.substring(0, 16),
        linhaId: String(item.linha.id),
        veiculoId: String(item.veiculo.id),
        motoristaId: String(item.motorista.id),
        status: item.status,
      });
    } else {
      setEditingItem(null);
      setFormData({
        dataPartidaPrevista: "",
        dataChegadaPrevista: "",
        linhaId: "",
        veiculoId: "",
        motoristaId: "",
        status: 0,
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
    { key: "linha.codigo", label: "Linha" },
    { key: "linha.nome", label: "Nome Linha" },
    { key: "veiculo.placa", label: "Veículo" },
    { key: "motorista.nome", label: "Motorista" },
    { 
      key: "dataPartidaPrevista", 
      label: "Partida Prevista",
      render: (val: string) => new Date(val).toLocaleString('pt-BR')
    },
    { 
      key: "status", 
      label: "Status",
      render: (val: number) => {
        const status = statusOptions.find(s => s.value === val);
        return status ? <Badge className={status.color}>{status.label}</Badge> : null;
      }
    },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Viagens</h1>
          <p className="text-muted-foreground mt-1">Gerencie as viagens programadas</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova Viagem
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por linha ou veículo..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredViagens}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar Viagem" : "Nova Viagem"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="linhaId">Linha</Label>
                <Select value={formData.linhaId} onValueChange={(val) => setFormData({ ...formData, linhaId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a linha" />
                  </SelectTrigger>
                  <SelectContent>
                    {linhas.map((linha: any) => (
                      <SelectItem key={linha.id} value={String(linha.id)}>
                        {linha.codigo} - {linha.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="veiculoId">Veículo</Label>
                <Select value={formData.veiculoId} onValueChange={(val) => setFormData({ ...formData, veiculoId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o veículo" />
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
                <Label htmlFor="motoristaId">Motorista</Label>
                <Select value={formData.motoristaId} onValueChange={(val) => setFormData({ ...formData, motoristaId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o motorista" />
                  </SelectTrigger>
                  <SelectContent>
                    {motoristas.map((motorista: any) => (
                      <SelectItem key={motorista.id} value={String(motorista.id)}>
                        {motorista.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="status">Status</Label>
                <Select value={String(formData.status)} onValueChange={(val) => setFormData({ ...formData, status: parseInt(val) })}>
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    {statusOptions.map((status) => (
                      <SelectItem key={status.value} value={String(status.value)}>
                        {status.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="dataPartidaPrevista">Partida Prevista</Label>
                <Input
                  id="dataPartidaPrevista"
                  type="datetime-local"
                  value={formData.dataPartidaPrevista}
                  onChange={(e) => setFormData({ ...formData, dataPartidaPrevista: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="dataChegadaPrevista">Chegada Prevista</Label>
                <Input
                  id="dataChegadaPrevista"
                  type="datetime-local"
                  value={formData.dataChegadaPrevista}
                  onChange={(e) => setFormData({ ...formData, dataChegadaPrevista: e.target.value })}
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
