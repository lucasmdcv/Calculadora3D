public class Calculadora3D {
    // Variáveis (Atributos)
    private double precoFilamentoKg;
    private double precoKwhEnergia;
    private int potenciaImpressoraWatts;

    // Construtor: Onde a gente define os preços base
    public Calculadora3D(double precoFilamento, double precoEnergia, int potenciaWatts) {
        this.precoFilamentoKg = precoFilamento;
        this.precoKwhEnergia = precoEnergia;
        this.potenciaImpressoraWatts = potenciaWatts;
    }

    // Método 1: Calcular custo do material
    public double calcularCustoMaterial(double pesoPecaGramas) {
        // Preço do grama = Preço do KG / 1000
        double precoPorGrama = this.precoFilamentoKg / 1000.0;
        return pesoPecaGramas * precoPorGrama;
    }

    // Método 2: Calcular custo de energia
    public double calcularCustoEnergia(double horasImpressao) {
        // Converte Watts para Kilowatts (dividir por 1000)
        double consumoKw = this.potenciaImpressoraWatts / 1000.0;
        // KWh consumidos = Potência(kW) * Tempo(h)
        double totalKwh = consumoKw * horasImpressao;
        return totalKwh * this.precoKwhEnergia;
    }

    // Método 3: Calcular custo total
    public double calcularTotal(double pesoGramas, double horas) {
        return calcularCustoMaterial(pesoGramas) + calcularCustoEnergia(horas);
    }
}