export interface Cidade {
  id: number;
  nome: string;
  uf: string;
  pontosParada?: PontoParada[];
  version?: number;
}

export interface PontoParada {
  id: number;
  nome: string;
  latitude: number;
  longitude: number;
  ativo: boolean;
  cidade: { id: number; nome: string; uf: string };
  pontosTuristicosProximos?: PontoTuristico[];
  version?: number;
}

export interface PontoTuristico {
  id: number;
  nome: string;
  descricao: string;
  latitude: number;
  longitude: number;
  ativo: boolean;
  pontosParadaProximos?: PontoParada[];
  version?: number;
}

export interface Linha {
  id: number;
  nome: string;
  codigo: string;
  tarifa: number;
  ativo: boolean;
  tempoPercursoEstimado?: string;
  version?: number;
}

export interface Itinerario {
  ordem: number;
  idLinha: number;
  idPontoParada: number;
  nomeLinha?: string;
  nomePontoParada?: string;
}

export interface Cronograma {
  id: number;
  linha: { id: number; nome: string; codigo: string };
  horaPartida: string;
  tipoDia: number;
  version?: number;
}

export interface TipoVeiculo {
  id: number;
  descricao: string;
  ativo: boolean;
  veiculos?: Veiculo[];
  version?: number;
}

export interface Veiculo {
  id: number;
  placa: string;
  modelo: string;
  chassi: string;
  anoFabricacao: number;
  capacidade: number;
  ativo: boolean;
  tipoVeiculo: { id: number; descricao: string };
  viagens?: Viagem[];
  version?: number;
}

export interface Pessoa {
  id: number;
  cpf: string;
  nome: string;
  email: string;
  telefone: string;
  dataNascimento: string;
  ativo: boolean;
  tipoPessoa: number;
  cnh?: string;
  validadeCnh?: string;
  categoriaCnh?: string;
  numeroCarteirinha?: string;
  version?: number;
}

export interface Viagem {
  id: number;
  dataPartidaReal?: string;
  dataPartidaPrevista: string;
  dataChegadaPrevista: string;
  dataChegadaReal?: string;
  linha: { id: number; nome: string; codigo: string };
  veiculo: { id: number; placa: string };
  motorista: { id: number; nome: string };
  status: number;
  passagens?: Passagem[];
  version?: number;
}

export interface Passagem {
  id: number;
  dataEmissao: string;
  valor: number;
  ativo: boolean;
  pessoa: { id: number; nome: string };
  viagem: { id: number };
  tipoPassagem: { id: number; descricao: string };
  metodoPagamento: { id: number; descricao: string };
}

export interface TipoPassagem {
  id: number;
  descricao: string;
  porcentagemDesconto: number;
  passagens?: Passagem[];
}

export interface MetodoPagamento {
  id: number;
  descricao: string;
  passagens?: Passagem[];
}

export interface ProgressoViagem {
  data: string;
  idViagem: number;
  idPontoParada: number;
}

export interface Manutencao {
  id: number;
  veiculo: { id: number; placa: string };
  mecanico: { id: number; nome: string };
  descricao: string;
  custoTotal: number;
  dataInicio: string;
  dataFim?: string;
  manutencaoPecas?: ManutencaoPeca[];
}

export interface Peca {
  id: number;
  valorUnitario: number;
  nome: string;
  fabricante: string;
  quantidade: number;
  manutencoes?: Manutencao[];
}

export interface ManutencaoPeca {
  idManutencao: number;
  idPeca: number;
  quantidadeUtilizada: number;
  valorUnitario: number;
}

export interface RelatorioGastosManutencao {
  placa: string;
  valorTotalGasto: number;
}

export interface RelatorioPontosTuristicos {
  cidade: string;
  uf: string;
  quantidadePontosTuristicos: number;
}

export interface RelatorioMediaPassageiros {
  linha: string;
  mediaPassageiros: number;
}
