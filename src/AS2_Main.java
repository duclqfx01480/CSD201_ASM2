/*
 * Subject      : CSD201x Data Structures and Algorithms
 * Assignment   : 2
 * Student      : Le Quang Duc - duclqfx01480@funix.edu.vn
 * Student ID   : FX01480
 * Project name : Product Management
 */

import java.util.Scanner;

public class AS2_Main {
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        
        // Tạo một Instance của lớp ProductManagement để quản lý sản phẩm
        // * ProductManagement là lớp chính để quản lý sản phẩm
        ProductManagement productManagement = new ProductManagement();
        
        while(true){
            // Lấy lựa chọn của người dùng
            choice = getChoice();
            
            // Nếu choice == 0 thì xử lý lưu file & thoát chương trình
            if(choice==0){
                // Lấy trạng thái đã lưu file (đã lưu hay chưa lưu)
                boolean isProductListSaved = productManagement.getIsProductListSaved();
                // Nếu productList chưa được lưu
                if(!isProductListSaved){
                    
                    // Hỏi người dùng có muốn lưu file hay không
                    System.out.println("The product list has not been saved. Do you want to save it?");
                    System.out.print("Press \"1\" for Yes, any number key for No: ");
                    int save = sc.nextInt();
                    // Nếu đồng ý lưu file, lưu file và thông báo lưu thành công
                    if(save==1){
                        productManagement.saveProductListToFile(Constants.FILE_PATH);
                        System.out.println(Constants.SAVE_PRODUCT_LIST_TO_FILE_SUCCESSFULLY);
                    }
                }
                
                // Đóng Scanner trước khi kết thúc chương trình
                sc.close();
                
                // Thông báo kết thúc chương trình
                System.out.println(" > System has been closed. Thank you!");
                
                break;
            }
            
            switch(choice){
                // 1.  Load dữ liệu từ file, đọc vào list và hiển thị
                case 1:
                    // Clear list trước khi đọc lên, tránh duplicate dữ liệu
                    productManagement.clearListData();
                    // Đọc dữ liệu từ file
                    productManagement.readProductFromFileToList(Constants.FILE_PATH);
                    // Hiển thị danh sách sản phẩm
                    productManagement.printMyList();
                    
                    break;
                    
                // 2.  Tạo sản phẩm mới và thêm vào cuối list
                case 2:
                    productManagement.addNewProduct();
                    break;
                    
                // 3.  Hiển thị danh sách sản phẩm trong list
                case 3:
                    productManagement.printMyList();
                    break;
                    
                // 4.  Lưu danh sách sản phẩm (product list)
                case 4:
                    // Lưu file và lấy kết quả lưu (thành công hay không thành công)
                    int saveResult = productManagement.saveProductListToFile(Constants.FILE_PATH);
                    // Nếu lưu thành công, in thông báo lưu file thành công
                    if(saveResult==1){
                        System.out.println(Constants.SAVE_PRODUCT_LIST_TO_FILE_SUCCESSFULLY);
                    }
                    // Ngược lại, in thông báo lưu file không thành công
                    else{
                        System.out.println(Constants.ERROR_SAVE_PRODUCT_LIST_TO_FILE_UNSUCCESSFULLY);
                    }
                    break;
                    
                // 5.  Tìm kiếm sản phẩm theo id
                case 5:
                    Node<Product> productFound = productManagement.searchProductById();
                    // Nếu tìm thấy sản phẩm
                    if(productFound!=null){
                        // In ra thông tin sản phẩm (header + thông tin chi tiết)
                        System.out.println(String.format(Constants.STRING_FORMAT_PRODUCT_HEADER, "ID", "NAME", "PRICE", "QUANTITY"));
                        System.out.println(productFound);
                    }
                    // Ngược lại, in thông báo không tìm thấy sản phẩm
                    else{
                        System.out.println(Constants.PRODUCT_NOT_FOUND);
                    }
                    break;
                    
                // 6.  Xóa sản phẩm theo id
                case 6:
                    // Xóa và lấy kết quả xóa (thành công hay không thành công)
                    int deletionResult = productManagement.deleteProductById();
                    // In ra kết quả xóa
                    if(deletionResult==-1){
                        System.out.println(Constants.DELETE_PRODUCT_UNSUCCESSFULLY);
                    }
                    else{
                        System.out.println(Constants.DELETE_PRODUCT_SUCCESSFULLY);
                    }
                    break;
                    
                // 7.  Sắp xếp sản phẩm theo id
                case 7:
                    productManagement.sortProductById();
                    System.out.println("Sorting done");
                    break;
                    
                // 8.  Chuyển đổi số lượng của sản phẩm đầu tiên trong danh sách về dạng nhị phân
                case 8:
                    productManagement.showQuantityOfFirstProductInBinary();
                    break;
                    
                // 9.  Đọc dữ liệu từ file vào Stack và hiển thị
                case 9:
                    // Xóa dữ liệu trong Stack trước, tránh duplicate dữ liệu mỗi lần load dữ liệu lên
                    productManagement.clearStackData();
                    
                    // Đọc dữ liệu từ file vào Stack
                    productManagement.readProductFromFileToStack(Constants.FILE_PATH);
                    // Lấy Stack
                    MyStack<Product> productStack = productManagement.getMyStack();
                    // Hiển thị Stack
                    if(!productStack.isEmpty()){
                        productManagement.printMyStack();
                    }
                    else{
                        System.out.println(Constants.PRODUCT_STACK_EMPTY);
                    }
                    
                    break;
                    
                // 10. Đọc dữ liệu từ file vào Queue và hiển thị
                case 10:
                    // Xóa dữ liệu trong Queue trước, tránh duplicate dữ liệu mỗi lần load dữ liệu lên
                    productManagement.clearQueueData();
                    
                    // Đọc dữ liệu từ file vào Queue
                    productManagement.readProductFromFileToQueue(Constants.FILE_PATH);
                    // Lấy Queue
                    MyQueue<Product> productQueue = productManagement.getMyQueue();
                    // Hiển thị Queue
                    if(!productQueue.isEmpty()){
                        productManagement.printMyQueue();
                    }
                    else{
                        System.out.println(Constants.PRODUCT_QUEUE_EMPTY);
                    }
                    break;
                    
            } // End Switch
            
        } // End While Loop
        
    }
    
    // Hiển thị Menu và cho phép người dùng nhập lựa chọn
    // trả về giá trị int là lựa chọn của người dùng
    private static int getChoice(){
        int choice;
        System.out.println("---------------PRODUCT MANAGEMENT---------------");
        System.out.println("1.  Load products from file and display");
        System.out.println("2.  Create new product & add to the end of list");
        System.out.println("3.  Display all products (data in the list)");
        System.out.println("4.  Save product list to file");
        System.out.println("5.  Search product by ID");
        System.out.println("6.  Delete product by ID");
        System.out.println("7.  Sort product by ID");
        System.out.println("8.  Convert quantity of first product to Binary");
        System.out.println("9.  Load to stack and display");
        System.out.println("10. Load to queue and display");
        System.out.println("0.  Exit");
        System.out.print("Please select your choice [0-10]: ");
        choice = sc.nextInt();
        
        // Validate lựa chọn, nếu không thỏa thì yêu cầu nhập lại
        while(choice<0 || choice>10){
            System.out.print("**Incorrect input. Please select your choice [0-10]: ");
            choice = sc.nextInt();
        }
        return choice;
    }
    
}
