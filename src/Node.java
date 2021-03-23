public class Node<T>{
    private T data;
    private Node nextNode;
    
    public Node(){}
    public Node(T data){
        this.data = data;
    }
    
    // Getter
    public T get(){
        return data;
    }
    
    public Node getNextNode(){
        return nextNode;
    }
    
    // Setter
    public void set(T data){
        this.data = data;
    }
    
    public void setNextNode(Node nextNode){
        this.nextNode = nextNode;
    }
    
    @Override
    public String toString(){
        return data.toString();
    }


}
