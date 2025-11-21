import { useMemo, useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Passagem } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Switch } from "@/components/ui/switch";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

const toDateTimeInput = (value?: string) => {
  if (!value) return "";
  const d = new Date(value);
  return Number.isNaN(d.getTime()) ? "" : d.toISOString().slice(0, 16);
};

export default function Passagens() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Passagem | null>(null);
  const [formData, setFormData] = useState({
    dataEmissao: "",
    valor: 0,
    ativo: true,
    pessoaId: "",
    viagemId: "",
    tipoPassagemId: "",
    metodoPagamentoId: "",
  });

  const queryClient = useQueryClient();

  const { data: passagens = [] } = useQuery({
    queryKey: ["passagens"],
    queryFn: () => api.get("/passagens", { page: 0, size: 1000 }),
  });

  const { data: pessoas = [] } = useQuery({
    queryKey: ["pessoas"],
    queryFn: () => api.get("/pessoas", { page: 0, size: 1000 }),
  });

  const { data: viagens = [] } = useQuery({
    queryKey: ["viagens"],
    queryFn: () => api.get("/viagens", { page: 0, size: 1000 }),
  });

  const { data: tiposPassagem = [] } = useQuery({
    queryKey: ["tipos-passagem"],
    queryFn: () => api.get("/tipos-passagem", { page: 0, size: 1000 }),
  });

  const { data: metodosPagamento = [] } = useQuery({
    queryKey: ["metodos-pagamento"],
    queryFn: () => api.get("/metodos-pagamento", { page: 0, size: 1000 }),
  });

  const createMutation = useMutation({
    mutationFn: (data: any) =>
      api.post("/passagens", {
        dataEmissao: data.dataEmissao ? new Date(data.dataEmissao).toISOString() : new Date().toISOString(),
        valor: parseFloat(data.valor),
        ativo: data.ativo,
        pessoa: { id: parseInt(data.pessoaId) },
        viagem: { id: parseInt(data.viagemId) },
        tipoPassagem: { id: parseInt(data.tipoPassagemId) },
        metodoPagamento: { id: parseInt(data.metodoPagamentoId) },
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["passagens"] });
      toast({ title: "Passagem criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar passagem", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) =>
      api.put(`/passagens/${data.id}`, {
        dataEmissao: data.dataEmissao ? new Date(data.dataEmissao).toISOString() : null,
        valor: parseFloat(data.valor),
        ativo: data.ativo,
        pessoa: { id: parseInt(data.pessoaId) },
        viagem: { id: parseInt(data.viagemId) },
        tipoPassagem: { id: parseInt(data.tipoPassagemId) },
        metodoPagamento: { id: parseInt(data.metodoPagamentoId) },
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["passagens"] });
      toast({ title: "Passagem atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar passagem", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/passagens/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["passagens"] });
      toast({ title: "Passagem excluida com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir passagem", variant: "destructive" }),
  });

  const normalizedPassagens = passagens.map((passagem: Passagem) => ({
    ...passagem,
    pessoa: passagem.pessoa ?? { id: 0, nome: "" },
    viagem: passagem.viagem ?? { id: 0 },
    tipoPassagem: passagem.tipoPassagem ?? { id: 0, descricao: "" },
    metodoPagamento: passagem.metodoPagamento ?? { id: 0, descricao: "" },
    valor: passagem.valor ?? 0,
    dataEmissao: passagem.dataEmissao ?? "",
    ativo: passagem.ativo ?? true,
  }));

  const filteredPassagens = normalizedPassagens
    .filter((passagem: Passagem) => {
      const term = searchTerm.toLowerCase();
      return (
        passagem.pessoa?.nome?.toLowerCase().includes(term) ||
        String(passagem.viagem?.id ?? "").includes(term) ||
        passagem.tipoPassagem?.descricao?.toLowerCase().includes(term)
      );
    })
    .sort((a, b) => (a.id ?? 0) - (b.id ?? 0));

  const openDialog = (item?: Passagem) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        dataEmissao: toDateTimeInput(item.dataEmissao),
        valor: item.valor ?? 0,
        ativo: item.ativo ?? true,
        pessoaId: item.pessoa?.id ? String(item.pessoa.id) : "",
        viagemId: item.viagem?.id ? String(item.viagem.id) : "",
        tipoPassagemId: item.tipoPassagem?.id ? String(item.tipoPassagem.id) : "",
        metodoPagamentoId: item.metodoPagamento?.id ? String(item.metodoPagamento.id) : "",
      });
    } else {
      setEditingItem(null);
      const defaultDate = toDateTimeInput(new Date().toISOString());
      setFormData({
        dataEmissao: defaultDate,
        valor: 0,
        ativo: true,
        pessoaId: "",
        viagemId: "",
        tipoPassagemId: "",
        metodoPagamentoId: "",
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
      { key: "pessoa.nome", label: "Passageiro" },
      { key: "viagem.id", label: "Viagem" },
      { key: "tipoPassagem.descricao", label: "Tipo" },
      { key: "metodoPagamento.descricao", label: "Pagamento" },
      { key: "valor", label: "Valor", render: (val: number) => `R$ ${Number(val ?? 0).toFixed(2)}` },
      {
        key: "dataEmissao",
        label: "Data de emissao",
        render: (val: string) => (val ? new Date(val).toLocaleString("pt-BR") : "-"),
      },
      { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
    ],
    []
  );

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Passagens</h1>
          <p className="text-muted-foreground mt-1">Emita e gerencie passagens</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova passagem
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por passageiro, viagem ou tipo..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredPassagens}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-3xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar passagem" : "Nova passagem"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="pessoaId">Passageiro</Label>
                <Select value={formData.pessoaId} onValueChange={(val) => setFormData({ ...formData, pessoaId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a pessoa" />
                  </SelectTrigger>
                  <SelectContent>
                    {pessoas.map((p: any) => (
                      <SelectItem key={p.id} value={String(p.id)}>
                        {p.nome}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="viagemId">Viagem</Label>
                <Select value={formData.viagemId} onValueChange={(val) => setFormData({ ...formData, viagemId: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a viagem" />
                  </SelectTrigger>
                  <SelectContent>
                    {viagens.map((viagem: any) => (
                      <SelectItem key={viagem.id} value={String(viagem.id)}>
                        {viagem.linha?.codigo || "Viagem"} -{" "}
                        {viagem.dataPartidaPrevista
                          ? new Date(viagem.dataPartidaPrevista).toLocaleString("pt-BR")
                          : "Sem data"}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="tipoPassagemId">Tipo de passagem</Label>
                <Select
                  value={formData.tipoPassagemId}
                  onValueChange={(val) => setFormData({ ...formData, tipoPassagemId: val })}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o tipo" />
                  </SelectTrigger>
                  <SelectContent>
                    {tiposPassagem.map((tipo: any) => (
                      <SelectItem key={tipo.id} value={String(tipo.id)}>
                        {tipo.descricao}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="metodoPagamentoId">Metodo de pagamento</Label>
                <Select
                  value={formData.metodoPagamentoId}
                  onValueChange={(val) => setFormData({ ...formData, metodoPagamentoId: val })}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o metodo" />
                  </SelectTrigger>
                  <SelectContent>
                    {metodosPagamento.map((metodo: any) => (
                      <SelectItem key={metodo.id} value={String(metodo.id)}>
                        {metodo.descricao}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="valor">Valor</Label>
                <Input
                  id="valor"
                  type="number"
                  step="0.01"
                  value={formData.valor}
                  onChange={(e) => setFormData({ ...formData, valor: parseFloat(e.target.value) })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="dataEmissao">Data de emissao</Label>
                <Input
                  id="dataEmissao"
                  type="datetime-local"
                  value={formData.dataEmissao}
                  onChange={(e) => setFormData({ ...formData, dataEmissao: e.target.value })}
                  required
                />
              </div>
            </div>
            <div className="flex items-center space-x-2">
              <Switch
                id="ativo"
                checked={formData.ativo}
                onCheckedChange={(checked) => setFormData({ ...formData, ativo: checked })}
              />
              <Label htmlFor="ativo">Ativa</Label>
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
