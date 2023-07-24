package coding.mentor.dto;

public class Product {
	private int id;
	private String name;
	private String author;
	private int stock;
	private int price;
	private int categoryId;
	
	
	public Product() {
		
	}
	
	public Product(int id, String name, String author, int stock, int price) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.stock = stock;
		this.price = price;
	}
	
	
	
	public Product(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
