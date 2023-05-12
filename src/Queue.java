import java.util.EmptyStackException;

public class Queue <Thing> {
    private Node<Thing> first = null ;
    private Node<Thing> last = first;
    private int size = 0;

    public Queue(){
        first = null ;
        this.last = this.first;
    }

    public Queue(Thing value){
        this.enqueue(value);
    }

    public void enqueue(Thing value){
        if(this.size == 0){
            this.first = new Node<Thing>(value);
            this.last  = this.first;
            this.size++;
            return;
        }
        this.last.setNext(new Node<Thing>(value));
        this.last = this.last.getNext();
        this.size++;
    }

    public Thing dequeue(){
        if(this.size == 0)
            throw new EmptyStackException();
        Node<Thing> top = this.first;
        this.first = this.first.getNext();
        this.size--;
        return top.getValue();
    }
    
    @SuppressWarnings("unchecked")
   	public void print() {
       	Node<Thing> n = this.first;
       	System.out.print("{");
       	while(n != null) {
       		System.out.print(n.getValue() + " ");
       		n = n.getNext();
       	}
       	System.out.println( "}");
       	
       }

    public boolean isEmpty(){
        if(this.size == 0)
            return true;
        return false;
    }
    public int size(){
        return this.size;
    }

    public Thing getFirst(){
        return this.first.getValue();
    }

    public Thing getLast(){
        return this.last.getValue();
    }
    
    public boolean contains(Thing value){
        Node n = this.first;
        while(n != null){
            if(value.equals(n.getValue())){
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

}
