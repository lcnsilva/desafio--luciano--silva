package com.lcnsilva.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lcnsilva.entities.Produto;

public class Application {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Produto> cardapio = new ArrayList<>();
		List<Produto> carrinho = new ArrayList<>();
		String[] formasDePagamentoDisponiveis = { "dinheiro", "debito", "credito" };
		String metodoDePagamento = null;
		inicializarProdutos(cardapio);
		double valorTotal = 0;
		for (Produto listarProduto : cardapio) {
			if (listarProduto.getCodigo().equals("chantily")) {
				System.out.printf("ID: %s (Extra do Café) \t\tValor: %.2f %n", listarProduto.getCodigo(),
						listarProduto.getValor());
			} else if (listarProduto.getCodigo().equals("queijo")) {
				System.out.printf("ID: %s (Extra do sanduiche) \tValor: %.2f %n", listarProduto.getCodigo(),
						listarProduto.getValor());
			} else if (listarProduto.getCodigo().equals("combo1")) {
				System.out.printf("ID: %s 1 Suco e 1 Sanduíche \tValor: %.2f %n", listarProduto.getCodigo(),
						listarProduto.getValor());
			} else if (listarProduto.getCodigo().equals("combo2")) {
				System.out.printf("ID: %s 1 Café e 1 Sanduíche \tValor: %.2f %n", listarProduto.getCodigo(),
						listarProduto.getValor());
			} else {
				System.out.printf("ID: %s \t\t\t\tValor: %.2f %n", listarProduto.getCodigo(), listarProduto.getValor());
			}
		}
		valorTotal = adicionarProdutosNoCarrinho(sc, cardapio, carrinho, valorTotal);
		if (valorTotal == 0) {
			System.out.println("Não há itens no carrinho de compra!");
		}
		if (valorTotal != 0 && valorTotal > 0) {
			System.out.println();
			System.out.println("Itens no carrinho:");
			System.out.println();
		}

		for (int i = 0; i < carrinho.size(); i++) {
			Produto itemAtual = carrinho.get(i);
			boolean primeiraOcorrencia = true;

			for (int j = 0; j < i; j++) {
				if (carrinho.get(j).getCodigo().equals(itemAtual.getCodigo())) {
					primeiraOcorrencia = false;
					break;
				}
			}

			if (primeiraOcorrencia) {
				System.out.printf("Quantidade: %d Unidade(s) | Codigo: %s %n", itemAtual.getQuantidade(),
						itemAtual.getCodigo());
			}
		}
		if (valorTotal != 0 && valorTotal > 0) {
			System.out.println();
			System.out.printf("O valor total é: R$%.2f %n %n", valorTotal);
			System.out.println("Escolha a forma de pagamento: ");
			System.out.println("dinheiro, debito ou credito");
			System.out.print("Opção: ");
			boolean condicaoPagamento = false;
			while (!condicaoPagamento) {
				metodoDePagamento = sc.nextLine();
				if (metodoDePagamento.equals(formasDePagamentoDisponiveis[0])) {
					condicaoPagamento = true;
				} else if (metodoDePagamento.equals(formasDePagamentoDisponiveis[1])) {
					condicaoPagamento = true;
				} else if (metodoDePagamento.equals(formasDePagamentoDisponiveis[2])) {
					condicaoPagamento = true;
				} else {
					System.out.println("Forma de pagamento inválida! Digite novamente.");
					System.out.print("Opção: ");
				}
			}
			calcularValorDaCompra(metodoDePagamento, carrinho, valorTotal);
		}
	}

	public static void calcularValorDaCompra(String metodoDePagamento, List<Produto> itens, double valorTotal) {
		if (metodoDePagamento.equals("dinheiro")) {
			valorTotal *= 0.95;
			System.out.printf("Pagamento efetuado no valor de: R$ %.2f", valorTotal);
		}
		if (metodoDePagamento.equals("debito")) {
			System.out.printf("Pagamento efetuado no valor de: R$ %.2f", valorTotal);

		}
		if (metodoDePagamento.equals("credito")) {
			valorTotal *= 1.03;
			System.out.printf("Pagamento efetuado no valor de: R$ %.2f", valorTotal);

		}
	}

	public static double adicionarProdutosNoCarrinho(Scanner sc, List<Produto> cardapio, List<Produto> carrinho,
			double valorTotal) {
		String[] codigos = { "cafe", "chantily", "suco", "sanduiche", "queijo", "salgado", "combo1", "combo2" };
		System.out.println();
		System.out.println("Para sair, digite 'sair'");
		System.out.print("Digite o código do produto que você deseja: ");
		String opcao = sc.nextLine();
		boolean codigoEncontrado = false;
		boolean chantilyLiberado = false;
		boolean queijoLiberado = false;
		if (opcao.equals("sair")) {
			return valorTotal;
		}
		for (int i = 0; i < codigos.length; i++) {
			if (opcao.equals(codigos[i])) {
				codigoEncontrado = true;
				break;
			}
		}
		if (opcao.equals("chantily")) {
			for (Produto checagemCarrinho : carrinho) {
				if (checagemCarrinho.getCodigo().equals("cafe")) {
					chantilyLiberado = true;
				} else if (checagemCarrinho.getCodigo().equals("combo2")) {
					chantilyLiberado = true;
					queijoLiberado = true;
				}
			}
		}

		if (opcao.equals("queijo")) {
			for (Produto checagemCarrinho : carrinho) {
				if (checagemCarrinho.getCodigo().equals("sanduiche")) {
					queijoLiberado = true;
				} else if (checagemCarrinho.getCodigo().equals("combo1")) {
					queijoLiberado = true;
				} else if (checagemCarrinho.getCodigo().equals("combo2")) {
					queijoLiberado = true;
					chantilyLiberado = true;
				}
			}
		}

		if (opcao.equals("queijo") && queijoLiberado == false) {
			System.out.println("Não é possível escolher Queijo sem escolher Sanduíche.");
			return adicionarProdutosNoCarrinho(sc, cardapio, carrinho, valorTotal);
		}
		if (opcao.equals("chantily") && chantilyLiberado == false) {
			System.out.println("Não é possível escolher Chantily sem escolher Café.");
			return adicionarProdutosNoCarrinho(sc, cardapio, carrinho, valorTotal);
		}
		if (codigoEncontrado) {
			for (Produto adicionarProduto : cardapio) {
				if (adicionarProduto.getCodigo().equals(opcao)) {
					int quantidade = adicionarProduto.getQuantidade();
					quantidade++;
					adicionarProduto.setQuantidade(quantidade);
					carrinho.add(adicionarProduto);
					valorTotal = valorTotal + adicionarProduto.getValor();
				}
			}
			System.out.println();
			System.out.println("Deseja adicionar um outro item? (s/n)");
			System.out.print("Opção: ");
			String adicionarItem = sc.nextLine();
			if (adicionarItem.equals("sim") || adicionarItem.equals("Sim") || adicionarItem.equals("s")
					|| adicionarItem.equals("S")) {
				return adicionarProdutosNoCarrinho(sc, cardapio, carrinho, valorTotal);
			} else {
				return valorTotal;
			}
		} else {
			System.out.println();
			System.out.println("Ortografia incorreta ou código não encontrado! Tente novamente.");
			return adicionarProdutosNoCarrinho(sc, cardapio, carrinho, valorTotal);
		}

	}

	public static void inicializarProdutos(List<Produto> cardapio) {
		Produto produto1 = new Produto("cafe", 3);
		cardapio.add(produto1);
		Produto produto2 = new Produto("chantily", 1.5); // COLOCAR COMO OPÇÃO EXTRA AO ESCOLHER CAFÉ OU COMBO 2
		cardapio.add(produto2);
		Produto produto3 = new Produto("suco", 6.20);
		cardapio.add(produto3);
		Produto produto4 = new Produto("sanduiche", 6.50);
		cardapio.add(produto4);
		Produto produto5 = new Produto("queijo", 2.00); // COLOCAR COMO OPÇÃO EXTRA AO ESCOLHER SANDUICHE OU COMBO 1 e 2
		cardapio.add(produto5);
		Produto produto6 = new Produto("salgado", 7.25);
		cardapio.add(produto6);
		Produto produto7 = new Produto("combo1", 9.50);
		cardapio.add(produto7);
		Produto produto8 = new Produto("combo2", 7.50);
		cardapio.add(produto8);
	}

}
