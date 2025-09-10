package com.felipe.helpdesk.domain.enums;

public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	//Por padrão temos uma indexação em ordem começando do 0 -> ADMIN = 0, CLIENTE = 1, TECNICO = 2
	//A alteração acima deixa o código mais robusto, pois da maneira anterior: ADMIN, CLIENTE, TECNICO; estava muito "frágil"
	//Logo, como mostramos acima a implementação correta é ADMIN(Indexação fixa, descrição) quando implementarmos a autenticação e o spring security vai olhar para a descrição
	
	//Atributos
	private Integer codigo;
	private String descricao;
	
	//Construtor
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	//Getters e Setters
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Perfil inválido!");
	}
}