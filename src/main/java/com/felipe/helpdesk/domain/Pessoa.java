package com.felipe.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.felipe.helpdesk.domain.enums.Perfil;

public abstract class Pessoa {
	
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	// Lista de perfis - Tipo Set<Perfil>
	// Inicia com new HashSet<>(), pois evitamos a questão de exceção de ponteiro nulo
	// O Set não permite que tenhamos 2 valores iguais dentro da nossa lista
	
	// Aqui temos uma questão que será alterada, quando formos no perfil ele é composto por (índice, descricao), não quero fazer uma lista com todas essas informações
	// Logo vou ter que substituir protected Set<Perfil> perfis = new HashSet<>(); por Set<Integer> perfis = new HashSet<>(); afim de pegar somente o índice.
	// Porém essa simples alteração resultará em erros pelo meu código no qual terei que ajustar.
	protected LocalDate dataCriacao = LocalDate.now();
	
	// Uma lógica interessante a ser implementada no momento de criação de um objeto Pessoa será ele começar tendo um Perfil de cliente logo instancio nos construtores
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);
		// TODO Auto-generated constructor stub
	}
	
	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// Método ajustado
	// public Set<Perfil> getPerfis() {
	//	 return perfis;
	// }
	// Para:
	public Set<Perfil> getPerfis() {
		return perfis.stream()// Criação de um fluxo, onde vai ser pego elemento por elemento da lista
				.map(x -> Perfil.toEnum(x))// Aplica uma função lambda onde cada elemento irá passar pelo método toEnum presente na classe Perfil, onde retorna o perfil
				.collect(Collectors.toSet());// Pega os elementos que passaram pelo map e armazena em outra lista
		// Processo detalhado no caderno
	}
	
	// Método ajustado
	// public void setPerfis(Set<Perfil> perfis){
	// 		this.perfis = perfis;
	// }
	// Para:
	public void addPerfil(Perfil perfil) {// Aqui alteramos o nome do método e invés de receber uma lista de perfis, receberá somente um perfil por vez
		this.perfis.add(perfil.getCodigo());// Como perfis é uma lista Integer, para receber um valor tem que ter o método add(elemento) e o número inteiro basta tirar do método getCodigo()
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
}
