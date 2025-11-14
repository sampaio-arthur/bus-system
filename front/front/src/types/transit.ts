export interface Cidade {
  id: number;
  nome: string;
  estado: string;
  version?: number;
}

export interface Linha {
  id: number;
  nome: string;
  codigo: string;
  cor: string;
  ativo: boolean;
  tempoPercursoEstimado?: number;
  version?: number;
}

export interface Parada {
  id: number;
  nome: string;
  latitude: string;
  longitude: string;
  ativo: boolean;
  cidade?: Cidade;
  pontosTuristicosProximos?: PontoTuristico[];
  version?: number;
}

export interface PontoTuristico {
  id: number;
  nome: string;
  descricao: string;
  latitude?: string;
  longitude?: string;
  ativo: boolean;
  pontosParadaProximos?: Parada[];
  version?: number;
}

export interface Cronograma {
  id: number;
  linha?: Linha;
  horaPartida: string;
  tipoDia?: number;
  version?: number;
}

export interface TipoVeiculo {
  id: number;
  descricao: string;
  ativo: boolean;
  veiculos?: Veiculo[];
  version?: number;
}

export interface Viagem {
  id: number;
  dataPartidaReal?: string;
  dataPartidaPrevista: string;
  dataChegadaPrevista: string;
  dataChegadaReal?: string;
  linha?: Linha;
  veiculo?: Veiculo;
  motorista?: Pessoa;
  status?: number;
  passagens?: Compra[];
  version?: number;
}

export interface Itinerario {
  id: number;
  linha?: Linha;
  pontoParada?: Parada;
  ordem: number;
  version?: number;
}

export interface Veiculo {
  id: number;
  placa: string;
  chassi?: string;
  modelo?: string;
  anoFabricacao: number;
  capacidade: number;
  ativo: boolean;
  tipoVeiculo?: TipoVeiculo;
  viagens?: Viagem[];
  version?: number;
}

export interface ManutencaoPeca {
  id: number;
  manutencao?: Manutencao;
  peca?: Peca;
  quantidade: number;
  version?: number;
}

export interface Manutencao {
  id: number;
  veiculo?: Veiculo;
  mecanico?: Pessoa;
  tipo: string;
  descricao: string;
  status?: number;
  dataInicio: string;
  dataFim?: string;
  custo?: number;
  pecasUsadas?: ManutencaoPeca[];
  version?: number;
}

export interface Peca {
  id: number;
  nome: string;
  codigo: string;
  quantidadeEstoque: number;
  precoUnitario: number;
  fabricante: string;
  ativo: boolean;
  version?: number;
}

export interface Pessoa {
  id: number;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
  tipo: number;
  dataNascimento?: string;
  dataCadastro: string;
  ativo: boolean;
  cnh?: string;
  categoriaCnh?: string;
  createdAt?: string;
  updatedAt?: string;
  version?: number;
}

export interface MetodoPagamento {
  id: number;
  nome: string;
  ativo: boolean;
  version?: number;
}

export interface TipoPassagem {
  id: number;
  descricao: string;
  passagens?: Compra[];
  version?: number;
}

export interface Compra {
  id: number;
  passageiro?: Pessoa;
  viagem?: Viagem;
  tipoPassagem?: TipoPassagem;
  metodoPagamento?: MetodoPagamento;
  valorTotal: number;
  status?: number;
  dataCompra: string;
  version?: number;
}

export interface PontoParadaTuristico {
  idPontoParada: number;
  idPontoTuristico: number;
}

export interface ProgressoViagem {
  data: string;
  idViagem: number;
  idPontoParada: number;
}
