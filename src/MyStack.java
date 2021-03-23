public class MyStack<T> {
    Node<T> top;
    
    public MyStack(){
        top = null;
    }
    
    // Thêm node mới vào stack
    public void push(T data){
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(top);
        top = newNode;        
    }
    
    // Trả về node ở top và xóa node đó đi
    public Node<T> pop(){
        if(!isEmpty()){
            Node<T> temp = top;
            top = top.getNextNode();
            return temp;
        }
        return null;        
    }
    
    // Trả về node ở top
    public Node<T> peek(){
        if(!isEmpty()){
            return top;
        }
        else{
            return null;
        }
        
    }
    
    // Kiểm tra xem Stack có rỗng hay không
    public boolean isEmpty(){
        return top==null;
    }
    
    public void setTop(Node<T> top){
        this.top = top;
    }
    
    // Lấy dữ liệu của Stack dạng String
    @Override
    public String toString(){
        String header = String.format(Constants.STRING_FORMAT_PRODUCT_HEADER, "ID", "NAME", "PRICE", "QUANTITY");
        String stackData = header + "\n";
        
        Node<T> current = this.top;
        while(current!=null){
            stackData += current.toString() + "\n";
            current = current.getNextNode();
        }
        return stackData;
    }
       
    
}
