package dao;

import entidades.articulos;
import patronfactorymethod.FactoryBaseDatos;
import patronfactorymethod.IntAdaptador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOarticulos {
    private final IntAdaptador dbAdapter;

    public DAOarticulos() {
        dbAdapter = FactoryBaseDatos.getDefaultDBAdapter();
    }

    public List<articulos> findAllProducts() throws SQLException {
        String sql = "SELECT idarticulo, nombrearticulo, valor FROM articulos";
        List<articulos> productList = new ArrayList<>();

        try (Connection connection = dbAdapter.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                productList.add(new articulos(
                        results.getLong("idarticulo"),
                        results.getString("nombrearticulo"),
                        results.getDouble("valor")));
            }
        }

        return productList;
    }

    public boolean saveProduct(articulos articulo) throws SQLException {
        String sql = "INSERT INTO articulos (idarticulo, nombrearticulo, valor) VALUES (?, ?, ?)";

        try (Connection connection = dbAdapter.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, articulo.getIdarticulo());
            statement.setString(2, articulo.getNombrearticulo());
            statement.setDouble(3, articulo.getValor());
            return statement.executeUpdate() == 1;
        }
    }
}
