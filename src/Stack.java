import java.util.EmptyStackException;

public class Stack <Thing>{
    private Node<Thing> top ;
    public int size = 0;

    public Stack(){
        this.top = null;
        this.size = 0;
    }
    public Stack(Thing value){
        this.push(value);
    }
    public Stack(Stack s){
        this.top = s.top;
        this.size = s.size;
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }

    public void push(Thing value){
        Node<Thing> newNode = new Node<Thing>(value);
        newNode.setNext(this.top);
        this.top = newNode;
        this.size++;
    }

    public Thing peek(){
        if(this.isEmpty())
            throw new EmptyStackException();
        return this.top.getValue();
    }

    public Thing pop(){
        if(this.isEmpty())
            throw new EmptyStackException();
        Node<Thing> topNode = this.top;
        this.top = this.top.getNext();
        this.size--;
        return topNode.getValue();
    }
    public int size(){
        return this.size;
    }

    public boolean contains(Thing value){
        Node n = this.top;
        while(n != null){
            if(value.equals(n.getValue())){
                return true;
            }
            n = n.getNext();
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
	public void print() {
    	Node<Thing> n = this.top;
    	System.out.print("{");
    	while(n != null) {
    		System.out.print(n.getValue() + " ");
    		n = n.getNext();
    	}
    	System.out.println( "}");
    	
    }

}
