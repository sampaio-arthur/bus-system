package br.com.bus.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.bus.domain.itinerario.Itinerario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "linha")
public class Linha extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha")
    private Integer id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @Column(name = "codigo", length = 255, nullable = false)
    private String codigo;

    @OneToOne(mappedBy = "linha", fetch = FetchType.LAZY)
    private Cronograma cronograma;

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    private Set<Itinerario> itinerarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "linha", fetch = FetchType.LAZY)
    private Set<Viagem> viagens = new LinkedHashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public Set<Itinerario> getItinerarios() {
		return itinerarios;
	}

	public void setItinerarios(Set<Itinerario> itinerarios) {
		this.itinerarios = itinerarios;
	}

	public Set<Viagem> getViagens() {
		return viagens;
	}

	public void setViagens(Set<Viagem> viagens) {
		this.viagens = viagens;
	}
    
}
