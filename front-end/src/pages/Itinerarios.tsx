import { useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Itinerario } from "@/types";
import { DataTable } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

export default function Itinerarios() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Itinerario | null>(null);
  const [formData, setFormData] = useState({
    ordem: "",
    linhaId: "",
    pontoParadaId: "",
  });

  const queryClient = useQueryClient();

  const { data: itinerarios = [] } = useQuery({
    queryKey: ["itinerarios"],
    queryFn: () => api.get("/itinerarios", { page: 0, size: 1000 }),
  });

  const { data: linhas = [] } = useQuery({
    queryKey: ["linhas"],
    queryFn: () => api.get("/linhas"),
  });

  const { data: pontosParada = [] } = useQuery({
    queryKey: ["pontos-parada"],
    queryFn: () => api.get("/pontos-parada", { page: 0, size: 1000 }),
  });

  const createMutation = useMutation({
    mutationFn: (data: any) =>
      api.post("/itinerarios", {
        ordem: Number(data.ordem),
        idLinha: parseInt(data.linhaId),
        idPontoParada: parseInt(data.pontoParadaId),
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["itinerarios"] });
      toast({ title: "Itinerario criado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar itinerario", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: async (data: any) => {
      const body = {
        ordem: Number(data.ordem),
        idLinha: parseInt(data.linhaId),
        idPontoParada: parseInt(data.pontoParadaId),
      };

      const chaveMudou =
        body.ordem !== data.originalOrdem ||
        body.idLinha !== data.originalLinhaId ||
        body.idPontoParada !== data.originalPontoParadaId;

      if (chaveMudou) {
        await api.delete(
          `/itinerarios/${data.originalOrdem}/${data.originalLinhaId}/${data.originalPontoParadaId}`
        );
        return api.post("/itinerarios", body);
      }

      return api.put(
        `/itinerarios/${data.originalOrdem}/${data.originalLinhaId}/${data.originalPontoParadaId}`,
        body
      );
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["itinerarios"] });
      toast({ title: "Itinerario atualizado com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar itinerario", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (item: Itinerario) =>
      api.delete(`/itinerarios/${item.ordem}/${item.idLinha}/${item.idPontoParada}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["itinerarios"] });
      toast({ title: "Itinerario excluido com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir itinerario", variant: "destructive" }),
  });

  const normalizedItinerarios = useMemo(() => {
    return itinerarios.map((it: Itinerario) => {
      const linha = linhas.find((l: any) => l.id === it.idLinha);
      const ponto = pontosParada.find((p: any) => p.id === it.idPontoParada);
      return {
        ...it,
        nomeLinha: it.nomeLinha || linha?.nome || linha?.codigo || "",
        nomePontoParada: it.nomePontoParada || ponto?.nome || "",
        compositeId: `${it.idLinha}-${it.idPontoParada}-${it.ordem}`,
      };
    });
  }, [itinerarios, linhas, pontosParada]);

  const filtered = normalizedItinerarios
    .filter((it: Itinerario) => {
      const term = searchTerm.toLowerCase();
      return (
        (it.nomeLinha || "").toLowerCase().includes(term) ||
        (it.nomePontoParada || "").toLowerCase().includes(term) ||
        String(it.ordem ?? "").includes(term)
      );
    })
    .sort((a, b) => (a.idLinha ?? 0) - (b.idLinha ?? 0) || (a.ordem ?? 0) - (b.ordem ?? 0));

  const openDialog = (item?: Itinerario) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        ordem: String(item.ordem ?? ""),
        linhaId: item.idLinha ? String(item.idLinha) : "",
        pontoParadaId: item.idPontoParada ? String(item.idPontoParada) : "",
      });
    } else {
      setEditingItem(null);
      const nextOrdem =
        (Math.max(0, ...normalizedItinerarios.map((it: Itinerario) => Number(it.ordem) || 0)) || 0) +
        1;
      setFormData({
        ordem: String(nextOrdem),
        linhaId: "",
        pontoParadaId: "",
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
    if (!formData.ordem || !formData.linhaId || !formData.pontoParadaId) {
      toast({ title: "Preencha todos os campos", variant: "destructive" });
      return;
    }
    if (editingItem) {
      updateMutation.mutate({
        ...formData,
        originalOrdem: editingItem.ordem,
        originalLinhaId: editingItem.idLinha,
        originalPontoParadaId: editingItem.idPontoParada,
      });
    } else {
      createMutation.mutate(formData);
    }
  };

  const columns = [
    { key: "ordem", label: "Ordem" },
    { key: "nomeLinha", label: "Linha" },
    { key: "nomePontoParada", label: "Ponto de parada" },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Itinerarios</h1>
          <p className="text-muted-foreground mt-1">Defina a ordem dos pontos nas linhas</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Novo itinerario
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por linha, ponto ou ordem..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filtered}
        columns={columns}
        idKey="compositeId"
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar itinerario" : "Novo itinerario"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="ordem">Ordem</Label>
                <Input
                  id="ordem"
                  type="number"
                  min={1}
                  value={formData.ordem}
                  onChange={(e) => setFormData({ ...formData, ordem: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="linhaId">Linha</Label>
                <Select value={formData.linhaId} onValueChange={(val) => setFormData({ ...formData, linhaId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a linha" />
                  </SelectTrigger>
                  <SelectContent>
                    {linhas.map((linha: any) => (
                      <SelectItem key={linha.id} value={String(linha.id)}>
                        {linha.codigo ? `${linha.codigo} - ${linha.nome}` : linha.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="pontoParadaId">Ponto de parada</Label>
                <Select
                  value={formData.pontoParadaId}
                  onValueChange={(val) => setFormData({ ...formData, pontoParadaId: val })}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o ponto" />
                  </SelectTrigger>
                  <SelectContent>
                    {pontosParada.map((ponto: any) => (
                      <SelectItem key={ponto.id} value={String(ponto.id)}>
                        {ponto.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
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
