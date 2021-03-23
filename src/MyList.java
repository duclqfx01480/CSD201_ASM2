public class MyList<T> {
    private Node<Product> head;
    private Node<Product> tail;
    
    // Constructor mặc định
    public MyList(){}
    
    public Node<Product> getHead(){
        return head;
    }
    
    public void setHead(Node<Product> head){
        this.head = head;
    }
    
    // Thêm node mới (product) vào vị trí đầu tiên của list
    public void addAtHead(Product product){
        Node newProduct = new Node(product);
        // Nếu head là null thì head sẽ là node mới tạo
        if(this.head==null){
           this.head = newProduct; 
        }
        // Ngược lại, nextNode của node mới sẽ trỏ đến head và cập nhật lại head là node mới tạo
        else{
            newProduct.setNextNode(this.head);
            this.head = newProduct;
        }
    }
    
    // Thêm node mới (product) vào cuối list    
    public void addAtTail(Product product){
        Node newProduct = new Node(product);
        // Nếu head là rỗng thì gán head và tail là node mới tạo
        if(this.head==null){
            this.head = newProduct;
            this.tail = newProduct;
        }
        // Ngược lại, nextNode của tail sẽ trở đến node mới tạo, và cập nhật lại tail
        else {
            this.tail.setNextNode(newProduct);
            this.tail = newProduct;
        }
        
    }
    
    
    // Tìm kiếm sản phẩm theo id
    public Node<Product> searchNodeById(String id){
        Node<Product> current = this.head;
        // Nếu node hiện tại có id = id cần tìm thì trả về node hiện tại
        while(current != null){
            if(current.get().getId().equals(id)){
                return current;
            }
            current = current.getNextNode();
        }
        
        // Nếu không tìm thấy, trả về null
        return null;
    }
    
    
    // Xóa sản phẩm theo id
    public int deleteById(String id){
        // Tìm vị trí của node có id trùng với id cần xóa
        int delPos = searchPositionById(id);
        // Thực hiện xóa
        if(delPos==-1){
            return -1;
        }
        else{
            // Nếu node cần xóa ở vị trí đầu của list
            if(delPos==0){
                deleteHead();
            }
            // Nếu node cần xóa ở cuối list
            else if(delPos==length()){
                deleteTail();
            }
            // Ngược lại, node cần xóa có vị trí ở giữa list
            else{
                deleteAtPosition(delPos);
            }
            // Trả về vị trí đã xóa
            return delPos;
        }
    }
    
    // -- Xóa ở vị trí đầu
    private void deleteHead(){
        if(isEmpty()){
            System.out.println("Sorry! The list is empty");
        }
        else{
            //Node current = this.head;
            this.head = this.head.getNextNode();
        } 
    }
    // -- Xóa ở vị trí cuối
    private void deleteTail(){
        if(isEmpty()){
            System.out.println("Sorry! The list is empty");
        }
        else{
            Node current = this.head;
            // Tìm node ở vị trí kế node cuối cùng
            while(current.getNextNode().getNextNode() != null){
                current = current.getNextNode();
            }
            // Bỏ reference đến node cuối cùng đi
            current.setNextNode(null);
        }
    }
    
    // -- Xóa ở vị trí bất kỳ
    private void deleteAtPosition(int position){
        if(isEmpty()){
            System.out.println("Sorry! The list is empty");
        }
        else{
            Node current = this.head;
            for(int i=0; i<position-1; i++){
                current = current.getNextNode();
            }
            current.setNextNode(current.getNextNode().getNextNode());
        }
    }
   
    // -- End Xóa sản phẩm theo id
    
    
    
    // Sắp xếp sản phẩm theo id
    public void sortById(){
        Node<Product> current = this.head;
        Node<Product> next = null;
        
        // Nếu list là rỗng thì kết thúc (không cần sắp xếp)
        if(isEmpty()){
            return;
        }
        
        // Nếu node hiện tại không rỗng
        while(current!=null){
            // Lấy node kế tiếp của node hiện tại
            next = current.getNextNode();
            while(next!=null){
                // So sánh id của node hiện tại và id của node kế tiếp
                // Nếu id của node hiện tại > id của node kế tiếp
                if(current.get().getId().compareTo(next.get().getId())>0){
                    // Swap (hoán vị) dữ liệu giữa hai node (các reference giữa các node vẫn giữ nguyên)
                    swap(current, next);
                }
                next = next.getNextNode();
            }
            current = current.getNextNode();
        }
        
    }
    
    // Hoán vị dữ liệu chứa trong hai node với nhau
    // (các reference giữa các node vẫn giữ nguyên)
    private void swap(Node<Product> a, Node<Product> b){
        String id, name;
        double price;
        int quantity;
        
        // Lấy dữ liệu trong node a
        id = a.get().getId();
        name = a.get().getName();
        price = a.get().getPrice();
        quantity = a.get().getQuantity();
        
        // Tạo đối tượng từ dữ liệu trong node a
        Product productA = new Product(id, name, price, quantity);
        
        // Lấy dữ liệu trong node b
        id = b.get().getId();
        name = b.get().getName();
        price = b.get().getPrice();
        quantity = b.get().getQuantity();
        
        // Tạo đối tượng từ dữ liệu trong node b
        Product productB = new Product(id, name, price, quantity);
        
        // Hoán vị dữ liệu trong hai node
        a.set(productB);
        b.set(productA);
    }
    
    // Chuyển đổi số lượng qua dạng nhị phân (Convert quantity to Binary)
    
    // Kiểm tra xem list có rỗng hay không
    private boolean isEmpty(){
        return this.head == null;
    }
    
    
    // Chiều dài của list
    public int length(){
        // Nếu head là null thì chưa có node nào trong list, 
        // trả về chiều dài là 0
        if(head==null){
            return 0;
        }
        // Ngược lại, loop qua từng node, nếu node không phải là null thì tăng biến đếm lên 1
        int length = 0;
        Node current = this.head;
        while(current != null){
            length++;
            current = current.getNextNode();
        }
        
        // Trả về chiều dài của list (size of list)
        return length;
    }
    
    @Override
    public String toString(){
        // Trả về dữ liệu của List dưới dạng String, có bao gồm header (hasHeader = true)
        return getListDataAsString(true);
    }
    
    // Lấy dữ liệu của list dưới dạng String
    // * Tham số boolean hasHeader:
    //   * true  : nếu muốn String trả về sẽ bao gồm cả tiêu đề các cột (ID, NAME, PRICE, QUANTITY) 
    //             + dữ liệu chi tiết trong từng node
    // 
    //   * false : String trả về chỉ bao gồm dữ liệu chi tiết trong từng node
    //             (không bao gồm tiêu đề các cột (ID, NAME, PRICE, QUANTITY) 
    public String getListDataAsString(boolean hasHeader){
        // Nếu list không chứa phần tử (product) nào, trả về thông báo list là rỗng
        if(isEmpty()){
            return Constants.PRODUCT_LIST_EMPTY;
        }
        
        String listData = "";
        // Nếu yêu cầu có header xuất ra, sẽ thêm header vào String xuất ra
        if(hasHeader){
            // Header của dữ liệu xuất ra
            String header = String.format(Constants.STRING_FORMAT_PRODUCT_HEADER, "ID", "NAME", "PRICE", "QUANTITY");
            listData = header + "\n";
        }
        
        // Dữ liệu chi tiết của sản phẩm (các phần tử trong list)
        Node current = this.head;
        while(current != null){
            listData += current.toString() + "\n";
            current = current.getNextNode();
        }
        return listData;
    }
    
    
    // Tìm vị trí của node theo id
    private int searchPositionById(String id){
        int position = 0;
        Node<Product> current = this.head;
        while(current != null){
            // Nếu node có id trùng với id cần tìm thì trả về vị trí tìm thấy
            if(current.get().getId().equals(id)){
                return position;
            }
            position++;
            current = current.getNextNode();
        }
        // Nếu không tìm thấy, trả về -1
        return -1;
    }
    

    
}
