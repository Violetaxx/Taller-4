package patronfactorymethod;

import java.sql.Connection;
import java.sql.SQLException;

public interface IntAdaptador {
    Connection getConnection() throws SQLException;
}
