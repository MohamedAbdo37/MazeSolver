import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MazeSolver implements IMazeSolver{

    private int m = 0 , n=  0;
    private Point start = new Point(0,0);
    
    public static void main(String[] args) {
    	
    	Scanner scan = new Scanner(System.in);
    	MazeSolver solver = new MazeSolver();
    
    	System.out.print("Enter File Path :");
    	String filePath = scan.nextLine();
    	
    	char[][] maze = solver.mazeReader(new File(filePath));
    	
    	System.out.println("Enter 1 to DFS and 2 for BFS:");
    	int chooce = scan.nextInt();
    	
        int[][] path = null ;
         
        switch (chooce) {
			case 1:
				System.out.println("\nDFS Solution \n");
				path = solver.solveDFS(new File(filePath));
				break;
			case 2:
				System.out.println("\nBFS Solution \n");
				path = solver.solveBFS(new File(filePath));
				break;
			default:
				System.out.println("Wrong Input");
				System.exit(0);
		}
       
        // print maze
        for(int i = 0; i< solver.m;i++) {
        	for(int j = 0 ;j < solver.n;j++)
        		System.out.print(maze[i][j] + "\t");
        	System.out.println();
        }
        System.out.print("\n Path: \t");
        // Print Path
        for(int i  = 0 ; i < path.length; i++){
        	if(maze[path[i][0]][path[i][1]] == '.')
        		maze[path[i][0]][path[i][1]] = '@';
            System.out.printf("(%d,%d) ",path[i][0],path[i][1]);
        }
        System.out.println();
        for(int i = 0; i< solver.m;i++) {
        	for(int j = 0 ;j < solver.n;j++)
        		System.out.print(maze[i][j] + "\t");
        	System.out.println();
        }
        
    }

    /*
     * read maze and changed to character array
     * @param maze file
     * @return char[][]
     * 
     * */
    private char[][] mazeReader(File path){
        char[][] maze = null;
        
        try {
        	// Read From the File
            Scanner scan = new Scanner(path);
            String line = new String();
            if(scan.hasNextLine()) line = scan.nextLine();
            
            // Get the dimensions
            this.m = Integer.parseInt(line.split(" ")[0]);
            this.n = Integer.parseInt(line.split(" ")[1]);
            
            maze = new char[this.m][this.n];
            
            // Read Maze
            for(int i = 0 ; scan.hasNextLine();i++){
                maze[i] = scan.nextLine().toCharArray();
                if(String.valueOf(maze[i]).contains("S"))
                    for(int j=0;j<this.n;j++)
                        if(maze[i][j] == 'S'){
                            this.start.setLocation(i, j);
                            break;
                        }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
            System.exit(0);
            
        }
        return maze;
    }
    
    /*
     * change Point object to integer array of size 2
     * @parm Point p
     * @return  int[2]
     * 
     * */
    
    private int[] toInegerArray(Point p){
        int[] xy = {(int)p.getX(), (int)p.getY()};
        return xy;
    }

    
    public int[][] solveBFS(File maze){
    	// change maze to char[][]
        char[][] mazePoint = this.mazeReader(maze);
        
        // Data Structures used to save and find the possible paths
        Tree<Point> paths = new Tree<Point>(this.start);
        Queue<Vertex<Point>> temp = new Queue<Vertex<Point>>(paths.getRoot());
        
        // Steps needed to find the adjacency paths
        int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
        
        
        Vertex<Point> v = null;
        while(!temp.isEmpty()){
        	
            v = temp.dequeue();
            Point cell = v.getValue();
           
            // Skep the visited cells
            if(mazePoint[(int)cell.getX()][(int)cell.getY()] =='@' ){
            	continue;
            }
            // loop stop when cell equals 'E' cell (Target cell) at first time
            if(mazePoint[(int)cell.getX()][(int)cell.getY()] == 'E' ){
                break;
            }

            for(int i = 0; i < 4 ; i++){
            	Vertex<Point> t = null;
                Point NEW = new Point((int)cell.getX()+move[i][0], (int)cell.getY()+move[i][1]);
                
                // check if the cell is valid to be a path
                if((int)NEW.getX() >= 0 && (int)NEW.getY() >= 0 && (int)NEW.getX() < this.m && (int)NEW.getY() < this.n 
                		&& (mazePoint[(int)NEW.getX()][(int)NEW.getY()] == '.' ||  mazePoint[(int)NEW.getX()][(int)NEW.getY()] == 'E') ){
                			t = new Vertex<Point>(NEW);
                			Tree.addAdjecant(v, t);
                			temp.enqueue(t);
                			// mark the cell as visited
                            if(mazePoint[(int)cell.getX()][(int)cell.getY()] != 'S')
                            	mazePoint[(int)cell.getX()][(int)cell.getY()] = '@';
                 }else{
                	 t = new Vertex<Point>(null);
                	 Tree.addAdjecant(v, t);
                          
                 }
            }
            
        }
        //save the Shortest path
        Stack<Point> path = new Stack<Point>();
        while(v != null) {
        	path.push(v.getValue());
        	v = v.getParent();
        }
        
        // put path in int[][]
        int[][] result = new int[path.size()][2];
        for(int i = 0 ; i < result.length ;i++)
            result[i] = toInegerArray(path.pop());
        
        return result;
    }

    public int[][] solveDFS(File maze){
    	
    	// change maze to char[][]
        char[][] mazePoint = this.mazeReader(maze);
        
        // data Structures used to save and find the possible paths
        Stack<Point> path = new Stack<Point>();
        Stack<Point> temp = new Stack<Point>();
        
        // Steps needed to find the adjacency paths
        int[][] move = {{-1,0},{0,-1},{1,0},{0,1}};
        
        
        Point cell = new Point(this.start);
        temp.push(cell);
        while(!temp.isEmpty()){
        	
            cell = temp.pop();
            path.push(cell);
            
            // Skep the visited cells
            if(mazePoint[(int)cell.getX()][(int)cell.getY()] == '@' )
            	continue;
            
            // mark the cell as visited
            if(mazePoint[(int)cell.getX()][(int)cell.getY()] == '.' )
            	mazePoint[(int)cell.getX()][(int)cell.getY()] = '@';
            
            // loop stop when cell equals 'E' cell (Target cell) at first time
            if(mazePoint[(int)cell.getX()][(int)cell.getY()] == 'E' ){
                break;
            }
            for(int i = 0 ; i < 4; i++){
     
            	Point NEW = new Point((int)cell.getX()+move[i][0], (int)cell.getY()+move[i][1]);
            	
            	// check if the cell is valid to be a path
                if( (int)NEW.getX() >= 0 &&  (int)NEW.getY() >= 0)
                    if( (int)NEW.getX()  < this.m && (int)NEW.getY() < this.n){
                        if((mazePoint[(int)NEW.getX()][(int)NEW.getY()] == '.' || mazePoint[(int)NEW.getX()][(int)NEW.getY()] == 'E' )){
                            temp.push(NEW);
                        }
                    }
            }
        }
        
        // put path in int[][]
        int[][] result = new int[path.size()][2];
        for(int i = path.size()-1 ; i > -1 ;i--)
            result[i] = toInegerArray(path.pop());

        return result;
    }
}
