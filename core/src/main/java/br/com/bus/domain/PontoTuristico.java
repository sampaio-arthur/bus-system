package br.com.bus.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_turistico")
public class PontoTuristico extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto_turistico")
    private Integer id;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @OneToMany(mappedBy = "pontoTuristico", fetch = FetchType.LAZY)
    private Set<PontoParadaTuristico> pontosParada = new LinkedHashSet<>();

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<PontoParadaTuristico> getPontosParada() {
		return pontosParada;
	}

	public void setPontosParada(Set<PontoParadaTuristico> pontosParada) {
		this.pontosParada = pontosParada;
	}
    
}
