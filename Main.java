import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializa o banco de dados
        GerenciadorBanco.inicializarBanco();
        
        Scanner leitor = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Novo Orçamento");
            System.out.println("2. Ver Histórico");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            // Tratamento para opção do menu
            try {
                String opcaoTexto = leitor.next();
                opcao = Integer.parseInt(opcaoTexto);
            } catch (NumberFormatException e) {
                opcao = 0;
            }
            
            leitor.nextLine(); // Limpa buffer

            if (opcao == 1) {
                System.out.println("\n--- NOVO CÁLCULO ---");
                System.out.print("Nome da Peça: ");
                String nomePeca = leitor.nextLine();

                // OBSERVE: Usamos lerNumero, NÃO nextDouble
                System.out.print("Peso (g): ");
                double pesoPeca = lerNumero(leitor);

                System.out.print("Tempo (h): ");
                double tempoPeca = lerNumero(leitor);

                System.out.print("Preço Filamento (kg): ");
                double valorRolo = lerNumero(leitor);
                
                System.out.print("Preço Energia (kWh): ");
                double valorEnergia = lerNumero(leitor);

                System.out.print("Potência (Watts): ");
                int potencia = (int) lerNumero(leitor);

                Calculadora3D calc = new Calculadora3D(valorRolo, valorEnergia, potencia);
                double custoTotal = calc.calcularTotal(pesoPeca, tempoPeca);

                System.out.printf(">>> CUSTO FINAL: R$ %.2f%n", custoTotal);
                
                GerenciadorBanco.salvarImpressao(nomePeca, pesoPeca, custoTotal);
            
            } else if (opcao == 2) {
                GerenciadorBanco.consultarHistorico();
            
            } else if (opcao == 3) {
                System.out.println("Saindo... Até logo!");
            } else {
                System.out.println("Opção inválida!");
            }
        }
        leitor.close();
    }

    // --- FUNÇÃO QUE CORRIGE A VÍRGULA AUTOMATICAMENTE ---
    public static double lerNumero(Scanner scanner) {
        // Lê como texto
        String texto = scanner.next();
        // Troca vírgula por ponto
        texto = texto.replace(",", ".");
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            System.out.println("❌ Valor inválido digitado. Considerando 0.");
            return 0.0;
        }
    }
}