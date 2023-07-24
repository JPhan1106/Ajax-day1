package coding.mentor.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coding.mentor.db.util.DBUtil;

public class ProductService {
	public void updateProduct(Product product) throws SQLException {
		// connect to DB
		Connection conn = null;
		PreparedStatement ps = null;

		try {
//			make connection to mySQL
			conn = DBUtil.makeConnection();
			ps = conn.prepareStatement(
					"UPDATE `product` SET `name` = ?, `author` = ?, `stock` = ?, `price` = ? WHERE `id` = ?");
			ps.setString(1, product.getName());
			ps.setString(2, product.getAuthor());
			ps.setInt(3, product.getStock());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getId());

			ps.execute();

		} finally {
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public Product getProductById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		try {
//			make connection to mySQL
			conn = DBUtil.makeConnection();
			ps = conn.prepareStatement("select * from `product` where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String author = rs.getString("author");
				int stock = rs.getInt("stock");
				int price = rs.getInt("price");

				product = new Product(id, name, author, stock, price);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return product;
	}

	public List<Product> getAllProducts() throws SQLException {
		// connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		List<Product> list = new ArrayList<Product>();
		try {
//				make connection to mySQL
			conn = DBUtil.makeConnection();
			ps = conn.prepareStatement("select * from `product`");
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				product = new Product(id, name);
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return list;
	}

	public void removeProduct(int id) throws SQLException {
		// connect to DB
		Connection conn = null;
		PreparedStatement ps = null;

		try {
//			make connection to mySQL
			conn = DBUtil.makeConnection();
			ps = conn.prepareStatement("DELETE FROM `product` WHERE `id` = ?");
			ps.setInt(1, id);
			ps.execute();

		} finally {

			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
