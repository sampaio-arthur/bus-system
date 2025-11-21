import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Cidade } from "@/types";
import { DataTable } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function Cidades() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Cidade | null>(null);
  const [formData, setFormData] = useState({ nome: "", uf: "" });

  const queryClient = useQueryClient();

  const { data: cidades = [], isLoading } = useQuery({
    queryKey: ["cidades"],
    queryFn: () => api.get("/cidades"),
  });

  const createMutation = useMutation({
    mutationFn: (data: Partial<Cidade>) => api.post("/cidades", data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["cidades"] });
      toast({ title: "Cidade criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar cidade", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: Cidade) => api.put(`/cidades/${data.id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["cidades"] });
      toast({ title: "Cidade atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar cidade", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/cidades/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["cidades"] });
      toast({ title: "Cidade excluída com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir cidade", variant: "destructive" }),
  });

  const filteredCidades = cidades.filter((cidade: Cidade) =>
    cidade.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
    cidade.uf.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openDialog = (item?: Cidade) => {
    if (item) {
      setEditingItem(item);
      setFormData({ nome: item.nome, uf: item.uf });
    } else {
      setEditingItem(null);
      setFormData({ nome: "", uf: "" });
    }
    setIsDialogOpen(true);
  };

  const closeDialog = () => {
    setIsDialogOpen(false);
    setEditingItem(null);
    setFormData({ nome: "", uf: "" });
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
    { key: "nome", label: "Nome" },
    { key: "uf", label: "UF" },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Cidades</h1>
          <p className="text-muted-foreground mt-1">Gerencie as cidades atendidas pelo sistema</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova Cidade
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome ou UF..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredCidades}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar Cidade" : "Nova Cidade"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <Label htmlFor="nome">Nome</Label>
              <Input
                id="nome"
                value={formData.nome}
                onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                required
              />
            </div>
            <div>
              <Label htmlFor="uf">UF</Label>
              <Input
                id="uf"
                value={formData.uf}
                onChange={(e) => setFormData({ ...formData, uf: e.target.value.toUpperCase() })}
                maxLength={2}
                required
              />
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
