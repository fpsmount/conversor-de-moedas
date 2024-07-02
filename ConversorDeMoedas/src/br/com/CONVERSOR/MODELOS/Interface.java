package br.com.CONVERSOR.MODELOS;

import java.util.Scanner;

public class Interface {
    Scanner scanner = new Scanner(System.in);
    String escolha;

    public void menu() {
        while (!(escolha == "7")) {
            System.out.println("==== CONVERSOR DE MOEDAS ====");
            System.out.println("1 = REAL X DOLAR ============");
            System.out.println("2 = REAL X EURO =============");
            System.out.println("3 = REAL X PESO =============");
            System.out.println("4 = DOLAR X EURO ============");
            System.out.println("5 = DOLAR X REAL ============");
            System.out.println("6 = DOLAR X PESO ============");
            System.out.println("7 = SAIR ====================");
            System.out.println("Digite a opção desejada: ");
            escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    this.pegarValor("BRL", "USD");
                    break;
                case "2":
                    this.pegarValor("BRL", "EUR");
                    break;
                case "3":
                    this.pegarValor("BRL", "ARS");
                    break;
                case "4":
                    this.pegarValor("USD", "EUR");
                    break;
                case "5":
                    this.pegarValor("USD", "BRL");
                    break;
                case "6":
                    this.pegarValor("USD", "ARS");
                    break;
                default:
                    if (escolha.equals("7")) {
                        System.out.println("Finalizando programa");
                    } else {
                        System.out.println("Valor digitado é inválido");
                    }
            }
        }
    }

    public void pegarValor(String m1, String m2) {
        System.out.println("Digite o valor para ser convertido: ");
        try {
            double valor = Double.parseDouble(scanner.nextLine());
            Moedas moedas = new Moedas(m1, m2, valor);
            ApiExchange api = new ApiExchange(moedas);
            System.out.println(api.buscaMoeda());
        } catch (NumberFormatException e) {
            System.out.println("Valor digitado é inválido!");
        }
    }
}