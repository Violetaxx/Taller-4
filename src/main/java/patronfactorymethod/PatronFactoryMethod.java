package patronfactorymethod;

import dao.DAOarticulos;
import entidades.articulos;

import java.sql.SQLException;
import java.util.List;

public class PatronFactoryMethod {
    public static void main(String[] args) throws SQLException {
        DAOarticulos daoarticulos = new DAOarticulos();

        List<articulos> articulosExistentes = daoarticulos.findAllProducts();
        long siguienteId = articulosExistentes.stream()
                .mapToLong(articulos::getIdarticulo)
                .max()
                .orElse(0) + 1;

        daoarticulos.saveProduct(
                new articulos(siguienteId, "Articulo A" + siguienteId, 120));
        siguienteId++;
        daoarticulos.saveProduct(
                new articulos(siguienteId, "Articulo B" + siguienteId, 130));

        List<articulos> articulos = daoarticulos.findAllProducts();
        System.out.println("Cantidad Articulos ==> " + articulos.size());
        for (articulos articulo : articulos) {
            System.out.println(articulo);
        }
    }
}
