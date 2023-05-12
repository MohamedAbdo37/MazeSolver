class Node <Thing> {
    private Node<Thing> next = null;
    private Thing value = null;
    
    public Node(){
        this.value = null;
    }
    public Node(Thing value){
        this.setValue(value);
    }

    public void setNext(Node next){
        this.next = next;
    }
    public void setValue(Thing value){
        this.value = value;
    }
    public Node getNext(){
        return this.next;
    }
    public Thing getValue(){
        return this.value;
    }
    
}
