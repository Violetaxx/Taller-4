package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilidadesAcceso {
    public static Properties loadProperty(String propertiesURL) {
        Properties properties = new Properties();
        ClassLoader classLoader = UtilidadesAcceso.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(propertiesURL)) {
            if (inputStream == null) {
                throw new IllegalStateException(
                        "No se encontró el archivo de configuración: " + propertiesURL);
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo leer el archivo de configuración: " + propertiesURL, e);
        }
    }
}
