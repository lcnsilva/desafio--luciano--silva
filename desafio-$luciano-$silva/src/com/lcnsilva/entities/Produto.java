package com.lcnsilva.entities;

public class Produto {
	private String codigo;
	private double valor;
	private int quantidade;

	public Produto(String codigo, double valor) {
		this.codigo = codigo;
		this.valor = valor;
	}

	public Produto(String codigo2) {

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void menu() {
		System.out.println("Escolha um item do cardápio: ");
		System.out.println("1 - Café - R$ 3,50");
		System.out.println("2 - Chantily (Extra do Café) - R$ 1,50");
		System.out.println("3 - Suco Natural - R$ 6,20");
		System.out.println("4 - Sanduíche - R$ 6,50");
		System.out.println("5 - Queijo (Extra do Sanduíche) - R$ 2,00");
		System.out.println("6 - Salgado - R$ 7,25");
		System.out.println("7 - 1 Suco e 1 Sanduíche - R$ 9,50");
		System.out.println("8 - 1 Café e 1 Sanduíche - R$ 7,50");
	}
	

}
