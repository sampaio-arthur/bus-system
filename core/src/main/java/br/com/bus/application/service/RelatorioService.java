package br.com.bus.application.service;

import br.com.bus.application.dto.GastoManutencaoPorVeiculoDTO;
import br.com.bus.application.dto.MediaPassageirosPorLinhaDTO;
import br.com.bus.application.dto.PontosTuristicosPorCidadeDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RelatorioService {

    @Inject
    EntityManager em;

    public List<GastoManutencaoPorVeiculoDTO> gastosManutencaoPorVeiculo(int meses) {
        LocalDateTime limite = LocalDateTime.now().minusMonths(meses);
        String sql = "select v.placa, sum(mp.quantidade_utilizada * mp.valor_unitario) as total " +
                "from veiculo v " +
                "join manutencao m on v.id = m.id_veiculo " +
                "join manutencao_peca mp on m.id_manutencao = mp.id_manutencao " +
                "where m.data_fim >= :limite " +
                "group by v.placa " +
                "order by total desc";

        Query q = em.createNativeQuery(sql);
        q.setParameter("limite", limite);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<GastoManutencaoPorVeiculoDTO> result = new ArrayList<>();
        for (Object[] r : rows) {
            String placa = (String) r[0];
            BigDecimal total = r[1] == null ? BigDecimal.ZERO : (r[1] instanceof BigDecimal ? (BigDecimal) r[1] : new BigDecimal(r[1].toString()));
            result.add(new GastoManutencaoPorVeiculoDTO(placa, total));
        }
        return result;
    }

    public List<PontosTuristicosPorCidadeDTO> pontosTuristicosPorCidade() {
        String sql = "select c.nome, c.uf, count(distinct pt.id) as quantidade " +
                "from cidade c " +
                "join ponto_parada pp on c.id = pp.cidade_id " +
                "join ponto_parada_turistico ppt on pp.id = ppt.id_ponto_parada " +
                "join ponto_turistico pt on ppt.id_ponto_turistico = pt.id " +
                "group by c.nome, c.uf " +
                "order by quantidade desc";
        Query q = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<PontosTuristicosPorCidadeDTO> result = new ArrayList<>();
        for (Object[] r : rows) {
            String cidade = (String) r[0];
            String uf = (String) r[1];
            Long qtd = r[2] == null ? 0L : (r[2] instanceof Number ? ((Number) r[2]).longValue() : Long.parseLong(r[2].toString()));
            result.add(new PontosTuristicosPorCidadeDTO(cidade, uf, qtd));
        }
        return result;
    }

    public List<MediaPassageirosPorLinhaDTO> mediaPassageirosPorLinha(int meses) {
        LocalDateTime limite = LocalDateTime.now().minusMonths(meses);
        String sql = "with passageiros_por_viagem as (" +
                "  select v.id as id_viagem, v.linha_id, count(*) as numero_passageiros " +
                "  from viagem v " +
                "  join passagem p on v.id = p.viagem_id " +
                "  where v.data_partida_real >= :limite " +
                "  group by v.id, v.linha_id" +
                ") " +
                "select l.nome, round(avg(ppv.numero_passageiros)::numeric, 2) as media " +
                "from linha l " +
                "join passageiros_por_viagem ppv on l.id = ppv.linha_id " +
                "group by l.nome";
        Query q = em.createNativeQuery(sql);
        q.setParameter("limite", limite);
        @SuppressWarnings("unchecked")
        List<Object[]> rows = q.getResultList();
        List<MediaPassageirosPorLinhaDTO> result = new ArrayList<>();
        for (Object[] r : rows) {
            String linha = (String) r[0];
            BigDecimal media = r[1] == null ? BigDecimal.ZERO : (r[1] instanceof BigDecimal ? (BigDecimal) r[1] : new BigDecimal(r[1].toString()));
            result.add(new MediaPassageirosPorLinhaDTO(linha, media));
        }
        return result;
    }
}

