public class Vertex <Thing>{
    private Vertex<Thing> parent ;
    private Thing value;
    public Queue<Vertex<Thing>> children ;

    public Vertex(){
        this.parent= null;
        this.children = new Queue<Vertex<Thing>>(null);
    }

    public Vertex(Thing value){
        this.parent= null;
        this.value= value;
        this.children = new Queue<Vertex<Thing>>(null);
    }

    public Vertex(Vertex parent,Thing value){
        this.parent= parent;
        this.value= value;
        this.children = new Queue<Vertex<Thing>>(null);
    }

    public Vertex(Vertex parent, Thing value, Vertex up, Vertex right, Vertex down, Vertex left){
        this.parent= parent;
        this.value= value;
        this.children = new Queue<Vertex<Thing>>(up);
        children.enqueue(right);
        children.enqueue(down);
        children.enqueue(left);
    }

    public void setParent(Vertex parent){
        this.parent = parent;
    }
    public void setValue(Thing value){
        this.value = value;
    }
    
    public Vertex getParent(){
        return this.parent;
    }
    public Thing getValue(){
        return this.value;
    }
    public Vertex[] getAdjVertex(){
        Vertex[] adjVertex = new Vertex[this.children.size()];
        for(int i = 0; i < this.children.size() ; i++ ){
            Vertex<Thing> v = this.children.dequeue();
            adjVertex[i] = v;
            this.children.enqueue(v);
        }
        return adjVertex;
    }

}
