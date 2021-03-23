public class MyQueue<T> {
    Node<T> head;
    Node<T> tail;
    
    public MyQueue(){
        head = null;
        tail = null;
    }
    
    // Thêm node mới vào queue (thêm vào ở sau tail)
    public void enqueue(T data){
        Node<T> newNode = new Node(data);
        if(tail==null){
            head = newNode;
            tail = newNode;
            return;
        }
        tail.setNextNode(newNode);
        tail = newNode;        
    }
    
    // Lấy node ra khỏi queue (lấy node ở head và xóa node đó đi)
    public Node<T> dequeue(){
        if(head==null){
            return null;
        }
        Node<T> temp = head;
        head = head.getNextNode();
        if(head==null){
            tail = null;
        }
        return temp;        
    }
    
    // Trả về node ở head
    public Node<T> peek(){
        if(!isEmpty()){
            return head;
        }
        return null;
    }
    
    public boolean isEmpty(){
        return head==null;
    }
    
    public void setHead(Node<T> head){
        this.head = head;
    }
    
    public void setTail(Node<T> tail){
        this.tail = tail;
    }
    
    // Lấy dữ liệu của Queue dạng String
    @Override
    public String toString(){
        String header = String.format(Constants.STRING_FORMAT_PRODUCT_HEADER, "ID", "NAME", "PRICE", "QUANTITY");
        String queueData = header + "\n";
        
        Node<T> current = this.head;
        while(current!=null){
            queueData += current.toString() + "\n";
            current = current.getNextNode();
        }
        return queueData;
    }
    
    
}
