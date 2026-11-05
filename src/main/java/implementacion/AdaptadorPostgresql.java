package implementacion;

import patronfactorymethod.IntAdaptador;
import utilidades.UtilidadesAcceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AdaptadorPostgresql implements IntAdaptador {
    private static final String DB_PROPERTIES = "Propiedades/DatosPOstgresql.properties";

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = UtilidadesAcceso.loadProperty(DB_PROPERTIES);
        String url = "jdbc:postgresql://" + properties.getProperty("host") + ":"
                + properties.getProperty("port") + "/"
                + properties.getProperty("service");
        return DriverManager.getConnection(
                url,
                properties.getProperty("user"),
                properties.getProperty("password"));
    }
}
