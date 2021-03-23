public class Constants {
    // Tên file khi đọc và ghi
    public static final String FILE_PATH = "data.txt";
    
    // String Format khi hiển thị dữ liệu
    public static final String STRING_FORMAT_PRODUCT_HEADER = "%-5s %-15s %10s %10s";
    public static final String STRING_FORMAT_PRODUCT_DETAIL = "%-5s %-15s %10.2f %10d";
    
    // Dấu phân cách giữa các field dữ liệu khi lưu file và đọc file
    public static final String PRODUCT_FIELD_SEPARATOR = ",";
    
    // Làm việc với file
    // Read file
    public static final String ERROR_READ_PRODUCT_FROM_FILE_UNSUCCESSFULLY = "**FAILED: Load product from file unsuccessfully";
    
    // Write file
    public static final String ERROR_SAVE_PRODUCT_LIST_TO_FILE_UNSUCCESSFULLY = "**FAILED: Save product list to file unsuccessfully";
    public static final String SAVE_PRODUCT_LIST_TO_FILE_SUCCESSFULLY = "SUCCESSFULLY: Product list was saved to file";
    
    // Search Product
    public static final String PRODUCT_NOT_FOUND = "**PRODUCT NOT FOUND: No product ID met your query";
    
    // Xóa sản phẩm
    public static final String DELETE_PRODUCT_UNSUCCESSFULLY = "**DELETION FAILED: No product ID matched";
    public static final String DELETE_PRODUCT_SUCCESSFULLY = "SUCCESSFULLY: Product was deleted";
    
    // List
    public static final String PRODUCT_LIST_EMPTY = "Product List is empty";
    
    // Stack
    public static final String PRODUCT_STACK_EMPTY = "Product Stack is empty";
    
    // Queue
    public static final String PRODUCT_QUEUE_EMPTY = "Product Queue is empty";

}
