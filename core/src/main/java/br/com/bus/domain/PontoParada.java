package br.com.bus.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada")
public class PontoParada extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "long", precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "lat", precision = 10, scale = 6)
    private BigDecimal latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @OneToMany(mappedBy = "pontoParada")
    private List<Itinerario> itinerarios = new ArrayList<>();

    @OneToMany(mappedBy = "pontoParada")
    private List<PontoParadaTuristico> pontosTuristicos = new ArrayList<>();

    @OneToMany(mappedBy = "pontoParada")
    private List<ProgressoViagem> progressos = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }

    public List<Itinerario> getItinerarios() { return itinerarios; }
    public void setItinerarios(List<Itinerario> itinerarios) { this.itinerarios = itinerarios; }

    public List<PontoParadaTuristico> getPontosTuristicos() { return pontosTuristicos; }
    public void setPontosTuristicos(List<PontoParadaTuristico> pontosTuristicos) { this.pontosTuristicos = pontosTuristicos; }

    public List<ProgressoViagem> getProgressos() { return progressos; }
    public void setProgressos(List<ProgressoViagem> progressos) { this.progressos = progressos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PontoParada)) return false;
        PontoParada that = (PontoParada) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
