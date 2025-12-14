import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.println("=== SISTEMA DE ORÇAMENTO 3D ===");
        
        // 1. Coleta de dados base (Configuração)
        System.out.print("Digite o valor do rolo de Filamento (1kg) em R$: ");
        double valorRolo = leitor.nextDouble();

        System.out.print("Digite o valor do kWh de energia (ex: 0.90): ");
        double valorEnergia = leitor.nextDouble();

        System.out.print("Digite a potência da impressora em Watts (ex: 300): ");
        int potencia = leitor.nextInt();

        // Instanciando o objeto da nossa calculadora
        Calculadora3D calc = new Calculadora3D(valorRolo, valorEnergia, potencia);

        System.out.println("\n--- DADOS DA PEÇA ---");
        
        // 2. Coleta de dados da peça específica
        System.out.print("Peso estimado da peça (gramas): ");
        double pesoPeca = leitor.nextDouble();

        System.out.print("Tempo estimado de impressão (horas): ");
        double tempoPeca = leitor.nextDouble();

        // 3. Processamento e Saída
        double custoMaterial = calc.calcularCustoMaterial(pesoPeca);
        double custoEnergia = calc.calcularCustoEnergia(tempoPeca);
        double custoTotal = calc.calcularTotal(pesoPeca, tempoPeca);

        System.out.println("\n=== RESULTADO FINAL ===");
        // O %.2f serve para formatar com 2 casas decimais
        System.out.printf("Custo de Material: R$ %.2f%n", custoMaterial);
        System.out.printf("Custo de Energia:  R$ %.2f%n", custoEnergia);
        System.out.println("---------------------------");
        System.out.printf("CUSTO TOTAL DE PRODUÇÃO: R$ %.2f%n", custoTotal);
        
        // Dica de venda: Margem de lucro sugerida (3x o custo)
        System.out.printf("Preço de Venda Sugerido (3x): R$ %.2f%n", custoTotal * 3);

        leitor.close();
    }
}