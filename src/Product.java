//public class Product implements Comparable<Product>{
public class Product{
    
    // Các thuộc tính: Mã sản phẩm, tên sản phẩm, đơn giá, số lượng
    private String id;
    private String name;
    private double price;
    private int quantity;
    
    // Constructor
    public Product(String id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;        
    }
    
    // Getter
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    // Setter
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    // Lấy dữ liệu của product dạng String
    @Override
    public String toString(){
        return String.format(Constants.STRING_FORMAT_PRODUCT_DETAIL, id, name, price, quantity);
    }
    
//    @Override
//    public int compareTo(Product anotherProduct){
//        return id.compareTo(anotherProduct.getId());
//    }
    

}
