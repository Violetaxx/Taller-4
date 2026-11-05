package implementacion;

import patronfactorymethod.IntAdaptador;
import utilidades.UtilidadesAcceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AdaptadorMysql implements IntAdaptador {
    private static final String DB_PROPERTIES = "Propiedades/DatosMySQL.properties";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = UtilidadesAcceso.loadProperty(DB_PROPERTIES);
        String url = "jdbc:mysql://" + properties.getProperty("host") + ":"
                + properties.getProperty("port") + "/"
                + properties.getProperty("dbname")
                + "?allowPublicKeyRetrieval=true&useSSL=false";
        return DriverManager.getConnection(
                url,
                properties.getProperty("user"),
                properties.getProperty("password"));
    }
}
