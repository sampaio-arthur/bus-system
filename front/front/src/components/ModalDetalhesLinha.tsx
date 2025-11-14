import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Badge } from "@/components/ui/badge";
import { MapPin, Check, Clock } from "lucide-react";
import { Linha } from "@/types/transit";
import { mockItinerarios } from "@/data/mockData";

interface ModalDetalhesLinhaProps {
  linha: Linha | null;
  open: boolean;
  onOpenChange: (open: boolean) => void;
}

const ModalDetalhesLinha = ({ linha, open, onOpenChange }: ModalDetalhesLinhaProps) => {
  if (!linha) return null;

  const itinerariosLinha = mockItinerarios
    .filter(i => i.linha?.id === linha.id)
    .sort((a, b) => a.ordem - b.ordem);

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
        <DialogHeader>
          <div className="flex items-center gap-3">
            <div className="p-2 rounded-lg" style={{ backgroundColor: linha.cor }}>
              <MapPin className="h-5 w-5 text-white" />
            </div>
            <div>
              <DialogTitle>{linha.nome}</DialogTitle>
              <Badge variant="secondary" className="mt-1">{linha.codigo}</Badge>
            </div>
          </div>
        </DialogHeader>

        <div className="space-y-4 py-4">
          <h3 className="font-semibold flex items-center gap-2">
            <Clock className="h-4 w-4" />
            Percurso da Linha
          </h3>

          {itinerariosLinha.length > 0 ? (
            <div className="space-y-3">
              {itinerariosLinha.map((itinerario, index) => {
                const parada = itinerario.pontoParada;
                const isFirst = index === 0;
                const isLast = index === itinerariosLinha.length - 1;

                return (
                  <div key={itinerario.id} className="flex gap-3">
                    <div className="flex flex-col items-center">
                      <div className={`rounded-full p-1.5 ${isFirst || isLast ? "bg-primary" : "bg-muted-foreground"}`}>
                        {isFirst || isLast ? <Check className="h-3 w-3 text-primary-foreground" /> : <div className="h-3 w-3" />}
                      </div>
                      {!isLast && <div className="w-0.5 h-8 bg-muted-foreground/30 my-1" />}
                    </div>
                    <div className="flex-1 pb-2">
                      <p className="font-medium">{parada?.nome}</p>
                      <p className="text-sm text-muted-foreground">Parada {index + 1} de {itinerariosLinha.length}</p>
                    </div>
                  </div>
                );
              })}
            </div>
          ) : (
            <p className="text-muted-foreground text-center py-4">Nenhum itinerário cadastrado para esta linha</p>
          )}
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default ModalDetalhesLinha;
