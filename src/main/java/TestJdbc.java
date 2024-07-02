import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
    public static void main(String[] args) {
        Connection conn = null;
        try {

            // Настройки подключения
            String url       = "jdbc:postgresql://localhost:5432/compose-postgres?encoding=utf8";
            String user      = "compose-postgres";
            String password  = "compose-postgres";

            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
