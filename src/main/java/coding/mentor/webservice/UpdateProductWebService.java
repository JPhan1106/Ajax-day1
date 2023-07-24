package coding.mentor.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import coding.mentor.dto.Product;
import coding.mentor.dto.ProductService;

/**
 * Servlet implementation class UpdateProductWebService
 */
@WebServlet("/updateProduct")
public class UpdateProductWebService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductWebService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonData = br.readLine();
		
		

		Product product = parseJsonToProduct(jsonData);
		
		ProductService productService = new ProductService();
		
		try {
			productService.updateProduct(product);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Product parseJsonToProduct(String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Convert JSON string to Product object
			return objectMapper.readValue(jsonData, Product.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // or handle the error appropriately
		}
	}
}