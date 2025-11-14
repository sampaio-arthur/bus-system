import { Linha, Parada, PontoTuristico, Cronograma, Viagem, Itinerario, Veiculo, Manutencao, Peca, Pessoa, TipoPassagem, Compra, Cidade } from "@/types/transit";

export const mockLinhas: Linha[] = [
  { id: 1, nome: "Centro - Morro dos Conventos", codigo: "L101", cor: "#0066CC", ativo: true },
  { id: 2, nome: "Centro - Ilha dos Golfinhos", codigo: "L102", cor: "#00AA66", ativo: true },
  { id: 3, nome: "Centro - Ilhas", codigo: "L103", cor: "#FF6600", ativo: true },
  { id: 4, nome: "Centro - Aeroporto Regional", codigo: "L104", cor: "#9933CC", ativo: true },
  { id: 5, nome: "Circular Centro", codigo: "L201", cor: "#CC3366", ativo: true },
];

const mockCidade: Cidade = { id: 1, nome: "Araranguá", estado: "SC" };

export const mockParadas: Parada[] = [
  { id: 1, nome: "Terminal Central", latitude: "-28.9347", longitude: "-49.4861", ativo: true, cidade: mockCidade },
  { id: 2, nome: "Praça Hercílio Luz", latitude: "-28.9367", longitude: "-49.4881", ativo: true, cidade: mockCidade },
  { id: 3, nome: "Morro dos Conventos", latitude: "-28.9647", longitude: "-49.5261", ativo: true, cidade: mockCidade },
  { id: 4, nome: "Praia Ilhas", latitude: "-28.9547", longitude: "-49.5161", ativo: true, cidade: mockCidade },
  { id: 5, nome: "Hospital Regional", latitude: "-28.9247", longitude: "-49.4761", ativo: true, cidade: mockCidade },
  { id: 6, nome: "Shopping Della Giustina", latitude: "-28.9447", longitude: "-49.4961", ativo: true, cidade: mockCidade },
];

export const mockPontosTuristicos: PontoTuristico[] = [
  {
    id: 1,
    nome: "Morro dos Conventos",
    descricao: "Ponto turístico histórico com farol e vista panorâmica do oceano. Um dos principais cartões postais da região.",
    ativo: true
  },
  {
    id: 2,
    nome: "Praia das Ilhas",
    descricao: "Praia tranquila e preservada, ideal para caminhadas e contemplação da natureza.",
    ativo: true
  },
  {
    id: 3,
    nome: "Museu Histórico de Araranguá",
    descricao: "Acervo que conta a história e cultura da região através de objetos e documentos históricos.",
    ativo: true
  },
  {
    id: 4,
    nome: "Praça Hercílio Luz",
    descricao: "Praça central histórica com eventos culturais, fair de artesanato e gastronomia local.",
    ativo: true
  },
];

export const mockCronogramas: Cronograma[] = [
  { id: 1, linha: mockLinhas[0], horaPartida: "06:00", tipoDia: 1 },
  { id: 2, linha: mockLinhas[0], horaPartida: "07:30", tipoDia: 1 },
  { id: 3, linha: mockLinhas[0], horaPartida: "09:00", tipoDia: 1 },
  { id: 4, linha: mockLinhas[1], horaPartida: "06:30", tipoDia: 1 },
  { id: 5, linha: mockLinhas[1], horaPartida: "08:00", tipoDia: 1 },
  { id: 6, linha: mockLinhas[2], horaPartida: "07:00", tipoDia: 1 },
];

export const mockViagens: Viagem[] = [
  {
    id: 1,
    linha: mockLinhas[0],
    dataPartidaPrevista: new Date().toISOString(),
    dataChegadaPrevista: new Date(Date.now() + 2 * 60 * 60 * 1000).toISOString(),
    status: 1
  },
  {
    id: 2,
    linha: mockLinhas[1],
    dataPartidaPrevista: new Date().toISOString(),
    dataChegadaPrevista: new Date(Date.now() + 1.5 * 60 * 60 * 1000).toISOString(),
    status: 1
  },
  {
    id: 3,
    linha: mockLinhas[4],
    dataPartidaPrevista: new Date().toISOString(),
    dataChegadaPrevista: new Date(Date.now() + 1 * 60 * 60 * 1000).toISOString(),
    status: 1
  },
];

export const mockItinerarios: Itinerario[] = [
  { id: 1, linha: mockLinhas[0], pontoParada: mockParadas[0], ordem: 1 },
  { id: 2, linha: mockLinhas[0], pontoParada: mockParadas[1], ordem: 2 },
  { id: 3, linha: mockLinhas[0], pontoParada: mockParadas[5], ordem: 3 },
  { id: 4, linha: mockLinhas[0], pontoParada: mockParadas[2], ordem: 4 },
  { id: 5, linha: mockLinhas[1], pontoParada: mockParadas[0], ordem: 1 },
  { id: 6, linha: mockLinhas[1], pontoParada: mockParadas[3], ordem: 2 },
  { id: 7, linha: mockLinhas[1], pontoParada: mockParadas[4], ordem: 3 },
];

export const mockVeiculos: Veiculo[] = [
  { id: 101, placa: "ABC-1234", modelo: "Mercedes-Benz OF-1721", chassi: "9BM384125EB123456", anoFabricacao: 2018, capacidade: 44, ativo: true },
  { id: 102, placa: "DEF-5678", modelo: "Volkswagen 17.230", chassi: "9532852X2ER123457", anoFabricacao: 2020, capacidade: 42, ativo: true },
  { id: 103, placa: "GHI-9012", modelo: "Scania K270", chassi: "9BSL4X209EB123458", anoFabricacao: 2019, capacidade: 48, ativo: true },
  { id: 104, placa: "JKL-3456", modelo: "Volvo B270F", chassi: "YV1MH2A00LA123459", anoFabricacao: 2021, capacidade: 46, ativo: true },
  { id: 105, placa: "MNO-7890", modelo: "Mercedes-Benz OH-1628", chassi: "9BM388126FB123460", anoFabricacao: 2017, capacidade: 40, ativo: true },
];

export const mockManutencoes: Manutencao[] = [
  { id: 1, veiculo: mockVeiculos[2], tipo: "Preventiva", descricao: "Troca de óleo e filtros", status: 1, dataInicio: "2024-10-28", custo: 850.00 },
  { id: 2, veiculo: mockVeiculos[4], tipo: "Corretiva", descricao: "Reparo do sistema de freios", status: 1, dataInicio: "2024-10-30", custo: 1200.00 },
  { id: 3, veiculo: mockVeiculos[0], tipo: "Preventiva", descricao: "Revisão geral", status: 2, dataInicio: "2024-10-15", dataFim: "2024-10-16", custo: 650.00 },
  { id: 4, veiculo: mockVeiculos[1], tipo: "Corretiva", descricao: "Troca de pneus", status: 2, dataInicio: "2024-10-20", dataFim: "2024-10-21", custo: 2400.00 },
];

export const mockPecas: Peca[] = [
  { id: 1, nome: "Óleo de Motor 15W40", codigo: "OL-001", quantidadeEstoque: 45, precoUnitario: 35.90, fabricante: "Petrobrás", ativo: true },
  { id: 2, nome: "Filtro de Óleo", codigo: "FL-002", quantidadeEstoque: 32, precoUnitario: 28.50, fabricante: "Tecfil", ativo: true },
  { id: 3, nome: "Pastilha de Freio", codigo: "FR-003", quantidadeEstoque: 18, precoUnitario: 120.00, fabricante: "Frasle", ativo: true },
  { id: 4, nome: "Disco de Freio", codigo: "FR-004", quantidadeEstoque: 12, precoUnitario: 280.00, fabricante: "Frasle", ativo: true },
  { id: 5, nome: "Pneu 275/80R22.5", codigo: "PN-005", quantidadeEstoque: 8, precoUnitario: 1250.00, fabricante: "Pirelli", ativo: true },
  { id: 6, nome: "Bateria 150Ah", codigo: "BT-006", quantidadeEstoque: 6, precoUnitario: 850.00, fabricante: "Moura", ativo: true },
  { id: 7, nome: "Lâmpada LED H4", codigo: "IL-007", quantidadeEstoque: 24, precoUnitario: 45.00, fabricante: "Philips", ativo: true },
];

export const mockPessoas: Pessoa[] = [
  { id: 1, nome: "João Silva", cpf: "123.456.789-00", email: "joao@email.com", telefone: "(48) 99999-0001", tipo: 1, dataCadastro: "2023-01-15", ativo: true, cnh: "12345678900", categoriaCnh: "D" },
  { id: 2, nome: "Maria Santos", cpf: "234.567.890-11", email: "maria@email.com", telefone: "(48) 99999-0002", tipo: 1, dataCadastro: "2023-02-20", ativo: true, cnh: "23456789011", categoriaCnh: "D" },
  { id: 3, nome: "Pedro Oliveira", cpf: "345.678.901-22", email: "pedro@email.com", telefone: "(48) 99999-0003", tipo: 2, dataCadastro: "2023-03-10", ativo: true },
  { id: 4, nome: "Ana Costa", cpf: "456.789.012-33", email: "ana@email.com", telefone: "(48) 99999-0004", tipo: 2, dataCadastro: "2023-03-25", ativo: true },
  { id: 5, nome: "Carlos Souza", cpf: "567.890.123-44", email: "carlos@email.com", telefone: "(48) 99999-0005", tipo: 0, dataCadastro: "2024-01-05", ativo: true },
];

export const mockTiposPassagem: TipoPassagem[] = [
  { id: 1, descricao: "Passagem Única - Válida para uma viagem" },
  { id: 2, descricao: "Cartão Mensal - Viagens ilimitadas por 30 dias" },
  { id: 3, descricao: "Estudante - Meia passagem para estudantes" },
  { id: 4, descricao: "Idoso - Gratuidade para maiores de 60 anos" },
];

export const mockCompras: Compra[] = [
  { id: 1, passageiro: mockPessoas[4], tipoPassagem: mockTiposPassagem[0], valorTotal: 5.50, status: 1, dataCompra: "2024-10-30T10:30:00" },
  { id: 2, passageiro: mockPessoas[4], tipoPassagem: mockTiposPassagem[2], valorTotal: 2.75, status: 1, dataCompra: "2024-10-29T14:20:00" },
];
