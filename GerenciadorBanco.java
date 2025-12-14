import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class GerenciadorBanco {

    // Onde o arquivo vai ficar salvo
    private static final String URL = "jdbc:sqlite:calculadora.db";

    public static void inicializarBanco() {
        // SQL para criar a tabela se ela não existir
        String sql = "CREATE TABLE IF NOT EXISTS historico_impressoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome_peca TEXT," +
                "peso_gramas REAL," +
                "tempo_horas REAL," +
                "valor_filamento REAL," +
                "valor_energia REAL," +
                "custo_total REAL," +
                "data_calculo DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");";

        // Tenta conectar e criar
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            
            // Se chegou aqui, o arquivo .db foi criado (ou já existia)
            stmt.execute(sql);
            System.out.println("✅ SUCESSO: Banco de dados conectado e tabela verificada!");
            
        } catch (SQLException e) {
            System.out.println("❌ ERRO: Não foi possível criar o banco.");
            System.out.println(e.getMessage());
        }
    }

    // NOVO MÉTODO: Recebe os dados e salva no banco
    public static void salvarImpressao(String nome, double peso, double custo) {
        // O "?" é uma segurança (PreparedStatement) para evitar hackers (SQL Injection)
        String sql = "INSERT INTO historico_impressoes (nome_peca, peso_gramas, custo_total) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Substitui os "?" pelos valores reais
            pstmt.setString(1, nome);
            pstmt.setDouble(2, peso);
            pstmt.setDouble(3, custo);

            pstmt.executeUpdate(); // Manda bala!
            System.out.println("💾 DADOS SALVOS NO HISTÓRICO COM SUCESSO!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao salvar: " + e.getMessage());
        }
    }
        public static void consultarHistorico() {
        String sql = "SELECT * FROM historico_impressoes";

        System.out.println("\n=== HISTÓRICO DE IMPRESSÕES ===");
        
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            // Enquanto tiver uma próxima linha na tabela, ele repete
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome_peca");
                double peso = rs.getDouble("peso_gramas");
                double custo = rs.getDouble("custo_total");
                String data = rs.getString("data_calculo");

                // Mostra formatado bonitinho
                System.out.printf("[%d] %s | %g g | R$ %.2f | Data: %s%n", 
                                  id, nome, peso, custo, data);
            }
            System.out.println("===============================");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao consultar: " + e.getMessage());
        }
    }
}