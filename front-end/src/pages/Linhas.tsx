import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Linha } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Switch } from "@/components/ui/switch";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function Linhas() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Linha | null>(null);
  const [formData, setFormData] = useState({
    nome: "",
    codigo: "",
    tarifa: 0,
    ativo: true,
    tempoPercursoEstimado: "",
  });

  const queryClient = useQueryClient();

  const { data: linhas = [] } = useQuery({
    queryKey: ["linhas"],
    queryFn: () => api.get("/linhas"),
  });

  const createMutation = useMutation({
    mutationFn: (data: Partial<Linha>) => api.post("/linhas", data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["linhas"] });
      toast({ title: "Linha criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar linha", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: Linha) => api.put(`/linhas/${data.id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["linhas"] });
      toast({ title: "Linha atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar linha", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/linhas/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["linhas"] });
      toast({ title: "Linha excluída com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir linha", variant: "destructive" }),
  });

  const filteredLinhas = linhas.filter((linha: Linha) =>
    linha.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
    linha.codigo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openDialog = (item?: Linha) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        nome: item.nome,
        codigo: item.codigo,
        tarifa: item.tarifa,
        ativo: item.ativo,
        tempoPercursoEstimado: item.tempoPercursoEstimado || "",
      });
    } else {
      setEditingItem(null);
      setFormData({ nome: "", codigo: "", tarifa: 0, ativo: true, tempoPercursoEstimado: "" });
    }
    setIsDialogOpen(true);
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
    setEditingItem(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editingItem) {
      updateMutation.mutate({ ...editingItem, ...formData });
    } else {
      createMutation.mutate(formData);
    }
  };

  const columns = [
    { key: "id", label: "ID" },
    { key: "codigo", label: "Código" },
    { key: "nome", label: "Nome" },
    { key: "tarifa", label: "Tarifa", render: (val: number) => `R$ ${val.toFixed(2)}` },
    { key: "tempoPercursoEstimado", label: "Tempo Estimado" },
    { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Linhas</h1>
          <p className="text-muted-foreground mt-1">Gerencie as linhas de transporte</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova Linha
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome ou código..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredLinhas}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar Linha" : "Nova Linha"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="codigo">Código</Label>
                <Input
                  id="codigo"
                  value={formData.codigo}
                  onChange={(e) => setFormData({ ...formData, codigo: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="tarifa">Tarifa (R$)</Label>
                <Input
                  id="tarifa"
                  type="number"
                  step="0.01"
                  value={formData.tarifa}
                  onChange={(e) => setFormData({ ...formData, tarifa: parseFloat(e.target.value) })}
                  required
                />
              </div>
              <div className="col-span-2">
                <Label htmlFor="nome">Nome da Linha</Label>
                <Input
                  id="nome"
                  value={formData.nome}
                  onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="tempoPercursoEstimado">Tempo Estimado</Label>
                <Input
                  id="tempoPercursoEstimado"
                  placeholder="Ex: 01:30:00"
                  value={formData.tempoPercursoEstimado}
                  onChange={(e) => setFormData({ ...formData, tempoPercursoEstimado: e.target.value })}
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
