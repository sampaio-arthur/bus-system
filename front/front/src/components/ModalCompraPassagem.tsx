import { useState } from "react";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { Linha } from "@/types/transit";
import { mockTiposPassagem } from "@/data/mockData";
import { CreditCard, Smartphone, Banknote, Check } from "lucide-react";
import { useToast } from "@/hooks/use-toast";

interface ModalCompraPassagemProps {
  linha: Linha | null;
  open: boolean;
  onOpenChange: (open: boolean) => void;
}

const ModalCompraPassagem = ({ linha, open, onOpenChange }: ModalCompraPassagemProps) => {
  const [passo, setPasso] = useState(1);
  const [tipoPassagemId, setTipoPassagemId] = useState<string>("");
  const [metodoPagamento, setMetodoPagamento] = useState<string>("");
  const { toast } = useToast();

  const resetModal = () => {
    setPasso(1);
    setTipoPassagemId("");
    setMetodoPagamento("");
  };

  const handleClose = () => {
    resetModal();
    onOpenChange(false);
  };

  const handleProximo = () => {
    if (passo === 1 && !tipoPassagemId) {
      toast({
        title: "Selecione um tipo de passagem",
        variant: "destructive",
      });
      return;
    }
    if (passo === 2 && !metodoPagamento) {
      toast({
        title: "Selecione um método de pagamento",
        variant: "destructive",
      });
      return;
    }
    if (passo < 3) {
      setPasso(passo + 1);
    }
  };

  const handleFinalizar = () => {
    toast({
      title: "Compra realizada com sucesso!",
      description: "Sua passagem foi confirmada.",
    });
    handleClose();
  };

  const tipoSelecionado = mockTiposPassagem.find(t => t.id.toString() === tipoPassagemId);

  if (!linha) return null;

  return (
    <Dialog open={open} onOpenChange={handleClose}>
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>Comprar Passagem - {linha.nome}</DialogTitle>
          <div className="flex gap-2 mt-2">
            {[1, 2, 3].map((step) => (
              <div
                key={step}
                className={`h-2 flex-1 rounded-full transition-all ${
                  step <= passo ? "bg-primary" : "bg-muted"
                }`}
              />
            ))}
          </div>
        </DialogHeader>

        <div className="space-y-4 py-4">
          {passo === 1 && (
            <div className="space-y-3">
              <h3 className="font-semibold">Passo 1: Selecione o tipo de passagem</h3>
              <RadioGroup value={tipoPassagemId} onValueChange={setTipoPassagemId}>
                {mockTiposPassagem.map((tipo) => (
                  <div
                    key={tipo.id}
                    className="flex items-center space-x-3 p-3 rounded-lg border hover:bg-muted cursor-pointer"
                  >
                    <RadioGroupItem value={tipo.id.toString()} id={`tipo-${tipo.id}`} />
                    <Label htmlFor={`tipo-${tipo.id}`} className="flex-1 cursor-pointer">
                      <div className="flex justify-between items-start">
                        <div>
                          <p className="font-medium text-sm">{tipo.descricao}</p>
                        </div>
                      </div>
                    </Label>
                  </div>
                ))}
              </RadioGroup>
            </div>
          )}

          {passo === 2 && (
            <div className="space-y-3">
              <h3 className="font-semibold">Passo 2: Selecione o método de pagamento</h3>
              <RadioGroup value={metodoPagamento} onValueChange={setMetodoPagamento}>
                <div className="flex items-center space-x-3 p-3 rounded-lg border hover:bg-muted cursor-pointer">
                  <RadioGroupItem value="pix" id="pix" />
                  <Label htmlFor="pix" className="flex-1 cursor-pointer">
                    <div className="flex items-center gap-2">
                      <Smartphone className="h-5 w-5 text-primary" />
                      <span className="font-medium">PIX</span>
                    </div>
                  </Label>
                </div>

                <div className="flex items-center space-x-3 p-3 rounded-lg border hover:bg-muted cursor-pointer">
                  <RadioGroupItem value="cartao_credito" id="cartao_credito" />
                  <Label htmlFor="cartao_credito" className="flex-1 cursor-pointer">
                    <div className="flex items-center gap-2">
                      <CreditCard className="h-5 w-5 text-primary" />
                      <span className="font-medium">Cartão de Crédito</span>
                    </div>
                  </Label>
                </div>

                <div className="flex items-center space-x-3 p-3 rounded-lg border hover:bg-muted cursor-pointer">
                  <RadioGroupItem value="cartao_debito" id="cartao_debito" />
                  <Label htmlFor="cartao_debito" className="flex-1 cursor-pointer">
                    <div className="flex items-center gap-2">
                      <CreditCard className="h-5 w-5 text-secondary" />
                      <span className="font-medium">Cartão de Débito</span>
                    </div>
                  </Label>
                </div>

                <div className="flex items-center space-x-3 p-3 rounded-lg border hover:bg-muted cursor-pointer">
                  <RadioGroupItem value="dinheiro" id="dinheiro" />
                  <Label htmlFor="dinheiro" className="flex-1 cursor-pointer">
                    <div className="flex items-center gap-2">
                      <Banknote className="h-5 w-5 text-accent" />
                      <span className="font-medium">Dinheiro</span>
                    </div>
                  </Label>
                </div>
              </RadioGroup>
            </div>
          )}

          {passo === 3 && (
            <div className="space-y-4">
              <div className="flex items-center justify-center py-4">
                <div className="h-16 w-16 rounded-full bg-secondary/20 flex items-center justify-center">
                  <Check className="h-8 w-8 text-secondary" />
                </div>
              </div>
              <h3 className="font-semibold text-center">Confirme sua compra</h3>
              <div className="space-y-2 p-4 bg-muted rounded-lg">
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Linha:</span>
                  <span className="font-medium">{linha.codigo}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Tipo de Passagem:</span>
                  <span className="font-medium text-sm">{tipoSelecionado?.descricao}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Pagamento:</span>
                  <span className="font-medium capitalize">{metodoPagamento?.replace("_", " ")}</span>
                </div>
              </div>
            </div>
          )}
        </div>

        <div className="flex gap-2">
          {passo > 1 && (
            <Button variant="outline" onClick={() => setPasso(passo - 1)} className="flex-1">
              Voltar
            </Button>
          )}
          <Button
            onClick={passo === 3 ? handleFinalizar : handleProximo}
            className="flex-1"
          >
            {passo === 3 ? "Finalizar Compra" : "Próximo"}
          </Button>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default ModalCompraPassagem;
