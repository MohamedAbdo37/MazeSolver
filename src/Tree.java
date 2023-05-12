public class Tree <Thing> {
    private Vertex<Thing> root;
    private int numVertex = 0;

    public Tree(Thing value){
        this.root = new Vertex<Thing>(value);
        this.numVertex++;
    }

    public Vertex<Thing> getRoot(){
        return  this.root;
    }
    
    public static void addAdjecant(Vertex p, Vertex c) {
		c.setParent(p);
		p.children.enqueue(c);;
    }


}
