package br.com.bus.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.bus.domain.itinerario.Itinerario;
import br.com.bus.domain.progressoViagem.ProgressoViagem;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_parada")
public class PontoParada extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto_parada")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "long", length = 255)
    private String longitude;

    @Column(name = "lat", length = 255)
    private String latitude;

    @OneToOne(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    private PontoParadaTuristico pontoParadaTuristico;

    @OneToMany(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    private Set<Itinerario> itinerarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pontoParada", fetch = FetchType.LAZY)
    private Set<ProgressoViagem> progressos = new LinkedHashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public PontoParadaTuristico getPontoParadaTuristico() {
		return pontoParadaTuristico;
	}

	public void setPontoParadaTuristico(PontoParadaTuristico pontoParadaTuristico) {
		this.pontoParadaTuristico = pontoParadaTuristico;
	}

	public Set<Itinerario> getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(Set<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}

	public Set<ProgressoViagem> getProgressos() {
		return progressos;
	}

	public void setProgressos(Set<ProgressoViagem> progressos) {
		this.progressos = progressos;
	}
    
}
