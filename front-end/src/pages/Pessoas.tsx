import { useMemo, useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { Pessoa } from "@/types";
import { DataTable, ActiveBadge } from "@/components/DataTable";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Switch } from "@/components/ui/switch";
import { Plus, Search } from "lucide-react";
import { toast } from "@/hooks/use-toast";

const tipoPessoaOptions = [
  { value: "PASSAGEIRO", label: "Passageiro" },
  { value: "MOTORISTA", label: "Motorista" },
  { value: "MECANICO", label: "Mecanico" },
];

const toInputDate = (value?: string) => {
  if (!value) return "";
  const date = new Date(value);
  return Number.isNaN(date.getTime()) ? "" : date.toISOString().slice(0, 10);
};

export default function Pessoas() {
  const [searchTerm, setSearchTerm] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [editingItem, setEditingItem] = useState<Pessoa | null>(null);
  const [formData, setFormData] = useState({
    cpf: "",
    nome: "",
    email: "",
    telefone: "",
    dataNascimento: "",
    ativo: true,
    tipoPessoa: "",
    cnh: "",
    validadeCnh: "",
    categoriaCnh: "",
    numeroCarteirinha: "",
  });

  const queryClient = useQueryClient();

  const { data: pessoas = [] } = useQuery({
    queryKey: ["pessoas"],
    queryFn: () => api.get("/pessoas", { page: 0, size: 1000 }),
  });

  const createMutation = useMutation({
    mutationFn: (data: any) =>
      api.post("/pessoas", {
        ...data,
        dataNascimento: data.dataNascimento ? `${data.dataNascimento}T00:00:00` : null,
        validadeCnh: data.validadeCnh ? `${data.validadeCnh}T00:00:00` : null,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pessoas"] });
      toast({ title: "Pessoa criada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao criar pessoa", variant: "destructive" }),
  });

  const updateMutation = useMutation({
    mutationFn: (data: any) =>
      api.put(`/pessoas/${data.id}`, {
        ...data,
        dataNascimento: data.dataNascimento ? `${data.dataNascimento}T00:00:00` : null,
        validadeCnh: data.validadeCnh ? `${data.validadeCnh}T00:00:00` : null,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pessoas"] });
      toast({ title: "Pessoa atualizada com sucesso!" });
      closeDialog();
    },
    onError: () => toast({ title: "Erro ao atualizar pessoa", variant: "destructive" }),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.delete(`/pessoas/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["pessoas"] });
      toast({ title: "Pessoa excluida com sucesso!" });
    },
    onError: () => toast({ title: "Erro ao excluir pessoa", variant: "destructive" }),
  });

  const normalizedPessoas = pessoas.map((pessoa: Pessoa) => ({
    ...pessoa,
    nome: pessoa.nome ?? "",
    cpf: pessoa.cpf ?? "",
    email: pessoa.email ?? "",
    telefone: pessoa.telefone ?? "",
    tipoPessoa: pessoa.tipoPessoa ?? "",
    dataNascimento: pessoa.dataNascimento ?? "",
    validadeCnh: pessoa.validadeCnh ?? "",
    cnh: pessoa.cnh ?? "",
    categoriaCnh: pessoa.categoriaCnh ?? "",
    numeroCarteirinha: pessoa.numeroCarteirinha ?? "",
    ativo: pessoa.ativo ?? true,
  }));

  const filteredPessoas = normalizedPessoas
    .filter((pessoa: Pessoa) => {
      const term = searchTerm.toLowerCase();
      return (
        pessoa.nome.toLowerCase().includes(term) ||
        pessoa.cpf.toLowerCase().includes(term) ||
        pessoa.email.toLowerCase().includes(term)
      );
    })
    .sort((a, b) => (a.id ?? 0) - (b.id ?? 0));

  const openDialog = (item?: Pessoa) => {
    if (item) {
      setEditingItem(item);
      setFormData({
        cpf: item.cpf ?? "",
        nome: item.nome ?? "",
        email: item.email ?? "",
        telefone: item.telefone ?? "",
        dataNascimento: toInputDate(item.dataNascimento),
        ativo: item.ativo ?? true,
        tipoPessoa: item.tipoPessoa ?? "",
        cnh: item.cnh ?? "",
        validadeCnh: toInputDate(item.validadeCnh),
        categoriaCnh: item.categoriaCnh ?? "",
        numeroCarteirinha: item.numeroCarteirinha ?? "",
      });
    } else {
      setEditingItem(null);
      setFormData({
        cpf: "",
        nome: "",
        email: "",
        telefone: "",
        dataNascimento: "",
        ativo: true,
        tipoPessoa: "",
        cnh: "",
        validadeCnh: "",
        categoriaCnh: "",
        numeroCarteirinha: "",
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
      { key: "nome", label: "Nome" },
      { key: "cpf", label: "CPF" },
      { key: "tipoPessoa", label: "Tipo" },
      { key: "telefone", label: "Telefone" },
      { key: "email", label: "Email" },
      {
        key: "dataNascimento",
        label: "Nascimento",
        render: (val: string) => (val ? new Date(val).toLocaleDateString("pt-BR") : "-"),
      },
      { key: "ativo", label: "Status", render: (val: boolean) => <ActiveBadge ativo={val} /> },
    ],
    []
  );

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold">Pessoas</h1>
          <p className="text-muted-foreground mt-1">Cadastre passageiros, motoristas e mecanicos</p>
        </div>
        <Button onClick={() => openDialog()} className="gap-2">
          <Plus className="h-4 w-4" />
          Nova pessoa
        </Button>
      </div>

      <div className="flex items-center gap-2 max-w-sm">
        <Search className="h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Buscar por nome, CPF ou email..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <DataTable
        data={filteredPessoas}
        columns={columns}
        onEdit={openDialog}
        onDelete={(item) => deleteMutation.mutate(item.id)}
      />

      <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
        <DialogContent className="max-w-3xl">
          <DialogHeader>
            <DialogTitle>{editingItem ? "Editar pessoa" : "Nova pessoa"}</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
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
                <Label htmlFor="cpf">CPF</Label>
                <Input
                  id="cpf"
                  value={formData.cpf}
                  onChange={(e) => setFormData({ ...formData, cpf: e.target.value })}
                  required
                />
              </div>
              <div>
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="telefone">Telefone</Label>
                <Input
                  id="telefone"
                  value={formData.telefone}
                  onChange={(e) => setFormData({ ...formData, telefone: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="dataNascimento">Data de nascimento</Label>
                <Input
                  id="dataNascimento"
                  type="date"
                  value={formData.dataNascimento}
                  onChange={(e) => setFormData({ ...formData, dataNascimento: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="tipoPessoa">Tipo da pessoa</Label>
                <Select value={formData.tipoPessoa} onValueChange={(val) => setFormData({ ...formData, tipoPessoa: val })}>
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione o tipo" />
                  </SelectTrigger>
                  <SelectContent>
                    {tipoPessoaOptions.map((opt) => (
                      <SelectItem key={opt.value} value={opt.value}>
                        {opt.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="flex items-center space-x-2 pt-6">
                <Switch
                  id="ativo"
                  checked={formData.ativo}
                  onCheckedChange={(checked) => setFormData({ ...formData, ativo: checked })}
                />
                <Label htmlFor="ativo">Ativo</Label>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="cnh">CNH (para motoristas)</Label>
                <Input
                  id="cnh"
                  value={formData.cnh}
                  onChange={(e) => setFormData({ ...formData, cnh: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="validadeCnh">Validade CNH</Label>
                <Input
                  id="validadeCnh"
                  type="date"
                  value={formData.validadeCnh}
                  onChange={(e) => setFormData({ ...formData, validadeCnh: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="categoriaCnh">Categoria CNH</Label>
                <Input
                  id="categoriaCnh"
                  value={formData.categoriaCnh}
                  onChange={(e) => setFormData({ ...formData, categoriaCnh: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="numeroCarteirinha">Numero da carteirinha</Label>
                <Input
                  id="numeroCarteirinha"
                  value={formData.numeroCarteirinha}
                  onChange={(e) => setFormData({ ...formData, numeroCarteirinha: e.target.value })}
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
