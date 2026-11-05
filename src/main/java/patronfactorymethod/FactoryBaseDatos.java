package patronfactorymethod;

import implementacion.AdaptadorMysql;
import implementacion.AdaptadorPostgresql;
import utilidades.UtilidadesAcceso;

import java.util.Properties;

public class FactoryBaseDatos {
    private static final String DB_FACTORY_PROPERTY_URL =
            "Propiedades/ConfiguracionFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";

    public static IntAdaptador getDBadapter(TiposBD dbType) {
        return switch (dbType) {
            case MySQL -> new AdaptadorMysql();
            case Postgresql -> new AdaptadorPostgresql();
        };
    }

    public static IntAdaptador getDefaultDBAdapter() {
        Properties properties = UtilidadesAcceso.loadProperty(DB_FACTORY_PROPERTY_URL);
        String adapterClass = properties.getProperty(DEFAULT_DB_CLASS_PROP);

        try {
            return (IntAdaptador) Class.forName(adapterClass)
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (ReflectiveOperationException | ClassCastException e) {
            throw new IllegalStateException(
                    "No se pudo crear el adaptador de base de datos: " + adapterClass, e);
        }
    }
}
