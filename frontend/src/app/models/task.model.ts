export interface Task {
  id?: number;
  titulo: string;
  descricao: string;
  responsavel: string;
  prioridade: 'ALTA' | 'MEDIA' | 'BAIXA';
  deadline: string;
  situacao: 'EM_ANDAMENTO' | 'CONCLUIDA';
}