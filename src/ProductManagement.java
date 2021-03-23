import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class ProductManagement {
    // List, Stack và Queue
    private MyList<Product> productList;
    private MyStack<Product> productStack;
    private MyQueue<Product> productQueue;
    
    // Trạng thái lưu xuống file của list (true: đã lưu, false: chưa lưu)
    private boolean isProductListSaved;
    
    // Constructor
    public ProductManagement(){
        productList = new MyList<>();
        productStack = new MyStack<>();
        productQueue = new MyQueue<>();
        isProductListSaved = true;
    }
    
    // Lấy danh sách sản phẩm (productList)
    public MyList<Product> getMyList(){
        return productList;
    }
    
    // Đọc file và tạo danh sách sản phẩm
    public void readProductFromFileToList(String filePath){
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while((line=bufferedReader.readLine())!=null){
                
                // Lấy mảng String chứa dữ liệu từng sản phẩm (theo dòng), tách theo dấu phân cách đã quy định
                String[] productInfo = line.split(Constants.PRODUCT_FIELD_SEPARATOR);
                // Lấy ra các dữ liệu liên quan: id, name, price, quantity
                String id = productInfo[0];
                String name = productInfo[1];
                double price = Double.parseDouble(productInfo[2]);
                int quantity = Integer.parseInt(productInfo[3]);
                
                // Tạo một sản phẩm mới
                Product newProduct = new Product(id, name, price, quantity);
                
                // Thêm sản phẩm mới vào cuối list
                productList.addAtTail(newProduct);
            }
            // Chỉ mới đọc dữ liệu lên, chưa thao tác gì, nên set trạng thái đã lưu là true
            isProductListSaved = true;
            fileReader.close();
        }
        catch(IOException e){
            System.out.println(Constants.ERROR_READ_PRODUCT_FROM_FILE_UNSUCCESSFULLY);
        }
    }
    
    // Xóa hết dữ liệu của danh sách sản phẩm
    public void clearListData(){
        productList.setHead(null);
    }
    
    // In ra dữ liệu trong danh sách sản phẩm
    public void printMyList(){
        System.out.println(" > Product List");
        System.out.println(productList.toString());
    }
    
    // Thêm sản phẩm mới vào list
    public void addNewProduct(){
        Scanner sc = new Scanner(System.in);
        String id, name;
        double price;
        int quantity;
        
        System.out.println(" > Add new product:");
        
        // Bắt đầu nhập các dữ liệu: id, name, price, quantity
        System.out.print("ID: ");
        id = sc.nextLine();
        // Validate ID
        // Nếu id đã tồn tại thì yêu cầu người dùng nhập id khác
        while(isIdExist(id)){
            System.out.print("ID " + id + " already exists. Please enter the new ID: ");
            id = sc.nextLine();
        }
        
        System.out.print("Product name: ");
        name = sc.nextLine();
        
        System.out.print("Price: ");
        price = sc.nextDouble();
        
        System.out.print("Quantity: ");
        quantity = sc.nextInt();
        
        // Tạo mới một đối tượng Product
        Product newProduct = new Product(id, name, price, quantity);
        
        // Thêm sản phẩm mới vào list
        productList.addAtTail(newProduct);
        
        System.out.println("New product is added successfully");
        
        // Vì thêm sản phẩm mới, chuyển trạng thái lưu thành false
        isProductListSaved = false;
    }
    
    // Lưu dữ liệu của list vào file
    public int saveProductListToFile(String filePath){
         File file = new File(filePath);
         try{
             // output- append là false (không cho phép ghi thêm vào file, chỉ cho ghi đè)
             FileOutputStream output = new FileOutputStream(file, false);
             // Lấy dữ liệu của product list, không bao gồm header (tiêu đề các cột) để chuẩn bị ghi vào file
             String content = getListDataForWritingToFile();
             byte[] buff = content.getBytes();
             output.write(buff, 0, buff.length);
             output.close();
             
             // Chuyển trạng thái lưu của list thành true (đã lưu xuống file)
             isProductListSaved = true;
             // Trả về kết quả (1 : lưu thành công)
             return 1;
         }
         catch(IOException e){
             // Trả về kết quả (0: lưu không thành công)
             return -1;
         }
    }
    
    // Tìm kiếm sản phẩm theo id
    public Node<Product> searchProductById(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID to search: ");
        String id = sc.nextLine();
        Node<Product> productFound = new Node<Product>();
        
        // Gọi đến phương thức tìm sản phẩm theo id của list
        productFound = productList.searchNodeById(id);
        
        // Trả về sản phẩm tìm thấy
        return productFound;
    }
    
    
    // Xóa sản phẩm theo id
    public int deleteProductById(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID to be deleted: ");
        String id = sc.nextLine();
        int deletionResult = productList.deleteById(id);
        
        // Nếu xóa thành công, chuyển trạng thái lưu thành false (list có thay đổi và chưa lưu)
        if(deletionResult!=-1){
            isProductListSaved = false;
        }
        
        return deletionResult;
    }
    
    // Sắp xếp sản phẩm theo id
    public void sortProductById(){
        productList.sortById();
        // Sau khi sắp xếp, chuyển trạng thái lưu thành false
        isProductListSaved = false;
    }
    
    
    
    // Hiển thị số lượng của sản phẩm đầu tiên dưới dạng nhị phân
    public void showQuantityOfFirstProductInBinary(){
        Node<Product> firstProduct = productList.getHead();
        System.out.print("Quantity of first product: " + firstProduct.get().getQuantity() + "(decimal) = ");
        System.out.println(convertDecimalToBinary(firstProduct.get().getQuantity()) + "(binary)");
    }
    
    // Chuyển đổi số hệ mười sang hệ nhị phân
    // * input: int
    // * output: số hệ nhị phân (dạng String)
    private String convertDecimalToBinary(int number){
        if(number>0){
            return convertDecimalToBinary(number/2) + number%2;
        }
        return "";
    }
    
    
    // Đọc dữ liệu vào Stack
    public void readProductFromFileToStack(String filePath){
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine())!=null){
                
                // Lấy mảng String chứa dữ liệu từng sản phẩm (theo dòng), tách theo dấu phân cách đã quy định
                String[] productInfo = line.split(Constants.PRODUCT_FIELD_SEPARATOR);
                
                // Lấy ra các dữ liệu liên quan: id, name, price, quantity
                String id = productInfo[0];
                String name = productInfo[1];
                double price = Double.parseDouble(productInfo[2]);
                int quantity = Integer.parseInt(productInfo[3]);
                
                // Tạo sản phẩm mới
                Product newProduct = new Product(id, name, price, quantity);
                
                // Thêm sản phẩm mới vào Stack (push)
                productStack.push(newProduct);
            }
            fileReader.close();
        }
        catch(IOException e){
            System.out.println(Constants.ERROR_READ_PRODUCT_FROM_FILE_UNSUCCESSFULLY);
        }
    }
    
    // Xóa dữ liệu của Stack
    public void clearStackData(){
        productStack.setTop(null);
    }
    
    // Lấy Stack
    public MyStack<Product> getMyStack(){
        return productStack;
    }
    
    // In dữ liệu trong Stack
    public void printMyStack(){
        System.out.println(" > Product Stack");
        System.out.println(productStack.toString());
    }
    
    
    // Đọc dữ liệu vào Queue
    public void readProductFromFileToQueue(String filePath){
        try{
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line=bufferedReader.readLine())!=null){
                // Lấy mảng String chứa dữ liệu từng sản phẩm (theo dòng), tách theo dấu phân cách đã quy định
                String[] productInfo = line.split(Constants.PRODUCT_FIELD_SEPARATOR);
                
                // Lấy ra các dữ liệu liên quan: id, name, price, quantity
                String id = productInfo[0];
                String name = productInfo[1];
                double price = Double.parseDouble(productInfo[2]);
                int quantity = Integer.parseInt(productInfo[3]);
                
                // Lấy ra các dữ liệu liên quan: id, name, price, quantity
                Product newProduct = new Product(id, name, price, quantity);
                
                // Thêm sản phẩm mới vào Stack (enqueue)
                productQueue.enqueue(newProduct);
            }
            fileReader.close();
        }
        catch(IOException e){
            System.out.println(Constants.ERROR_READ_PRODUCT_FROM_FILE_UNSUCCESSFULLY);
        }
    }
    
    
    // Xóa dữ liệu của Queue
    public void clearQueueData(){
        productQueue.setHead(null);
        productQueue.setTail(null);
    }
    
    // Lấy Queue
    public MyQueue<Product> getMyQueue(){
        System.out.println(" > Product Queue");
        return productQueue;
    }
    
    // In dữ liệu trong Queue
    public void printMyQueue(){
        System.out.println(productQueue.toString());
    }
    
    // Đặt trạng thái lưu của product list (true: đã lưu, false: chưa lưu)
    public void setProductListSaveStatus(boolean isProductListSaved){
        this.isProductListSaved = isProductListSaved;
    }
    
    // Lấy trạng thái lưu của product list
    public boolean getIsProductListSaved(){
        return this.isProductListSaved;
    }
    
    
    // ---- Các phương thức viết thêm để support các phương thức khác
    
    // Validate xem id đã có trong danh sách sản phẩm chưa
    private boolean isIdExist(String id){
        return productList.searchNodeById(id)!=null;
    }
    
    
    // Lấy dữ liệu của list (dùng cho việc ghi vào file)
    // * Dữ liệu trả về không bao gồm header của các cột ("ID", "NAME", "PRICE", "QUANTITY")
    private String getListDataForWritingToFile(){
        String listData = "";
        Node<Product> current = productList.getHead();
        while(current != null){
            
            // Lấy các dữ liệu trong từng node (product)- bao gồm id, name, price và quantity
            String id = current.get().getId();
            String name = current.get().getName();
            double price = current.get().getPrice();
            int quantity = current.get().getQuantity();
            
            // Dấu phân cách giữa các field dữ liệu khi lưu xuống file
            String separator = Constants.PRODUCT_FIELD_SEPARATOR;
            
            // String trả về là các trường id, name, price, quantity, phân cách bởi dấu phân cách
            listData += id + separator + name + separator + price + separator + quantity + "\n";
            
            current = current.getNextNode();
        }
        return listData;
    }
    
    

}
