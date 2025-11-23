package br.com.bus.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoa")
public class Pessoa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CPF não pode ser vazio")
    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @NotBlank(message = "Nome não pode ser vazio")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Email(message = "Email deve ser válido")
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @Column(name = "tipo_pessoa", length = 100)
    private String tipoPessoa;

    @Column(name = "cnh", length = 11, unique = true)
    private String cnh;

    @Column(name = "validade_cnh")
    private LocalDateTime validadeCnh;

    @Column(name = "categoria_cnh", length = 10)
    private String categoriaCnh;

    @Column(name = "numero_carteirinha", length = 20, unique = true)
    private String numeroCarteirinha;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private int version;

    @OneToMany(mappedBy = "motorista")
    private List<Viagem> viagens = new ArrayList<>();

    @OneToMany(mappedBy = "pessoa")
    private List<Passagem> passagens = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = normalizeBlankToNull(cnh);
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setCategoriaCnh(String categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public String getNumeroCarteirinha() {
        return numeroCarteirinha;
    }

    public void setNumeroCarteirinha(String numeroCarteirinha) {
        this.numeroCarteirinha = normalizeBlankToNull(numeroCarteirinha);
    }

    public LocalDateTime getValidadeCnh() {
        return validadeCnh;
    }

    public void setValidadeCnh(LocalDateTime validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

    public List<Passagem> getPassagens() {
        return passagens;
    }


    private String normalizeBlankToNull(String value) {
        if (value == null) {
            return null;
        }

        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pessoa))
            return false;
        Pessoa pessoa = (Pessoa) o;
        return id != null && id.equals(pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
