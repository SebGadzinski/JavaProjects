import javafx.geometry.Point2D;

public class PartImage {
    private boolean[][]     pixels;
    private boolean[][]     visited;
    private int             rows;
    private int             cols;
    public PartImage(int r, int c) {
        rows = r;
        cols = c;
        visited = new boolean[r][c];
        pixels = new boolean[r][c];
    }

    public PartImage(int rw, int cl, byte[][] data) {
        this(rw,cl);
        for (int r=0; r<10; r++) {
            for (int c=0; c<10; c++) {
                if (data[r][c] == 1)
                    pixels[r][c] = true;
                else
                    pixels[r][c]= false;
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public boolean getPixel(int r, int c) { return pixels[r][c]; }

    // You will re-write the 5 methods below
    public void print() {
        int r = getRows();
        int c = getCols();
        for(int i = 0; i < r; i ++){
            for (int j = 0; j <c; j++){
                if (pixels[i][j])
                    System.out.print("*");
                else
                    System.out.print("-");
            }
            System.out.println();
        }
    }
    public Point2D findStart() {
        int r = getRows();
        int c = getCols();
        double y = 0;
        double x = 0;
        for(int i = 0; i < r; i ++){
            for (int j = 0; j <c; j++) {

                if (pixels[i][j]){
                    return new Point2D(x, y);
            }
                y++;
            }
            y =0;
            x++;
        }
        return null;
    }

    public int partSize() {
        int r = getRows();
        int c = getCols();
        int x = 0;
        for(int i = 0; i < r; i ++){
            for (int j = 0; j <c; j++) {
                if (pixels[i][j]){
                    x++;
                }
            }
        }
        return x; }
    private void expandFrom(int r, int c) {

            if (pixels[r][c]){
                // set current one to false so it doesnt not go back
                pixels[r][c] = false;
                // if its within the range check the next one
                if (r > 0){expandFrom(r-1, c);}
                if (r < rows -1 ){expandFrom(r+1, c);};
                if(c < cols -1 ){expandFrom(r, c+1);}
                if (c > 0){expandFrom(r, c-1);}
            }
        }

    private int perimeterOf(int r, int c) {

        int  collect = 0;
        // if its withing the range and if its not been visited or is a white pixel collect stuff and go to the next one
        if (c < 0 || r < 0 || c >= cols || r >= rows){
            return 0;
        }
        if (visited[r][c] || !pixels[r][c]){
            return 0;
        }

        /////////////////////////

        if (c < 9) {
            if (!pixels[r][c + 1] )
                collect++;
        }
        if (c  == 9 ){
            collect++;
        }

        ////////////////////////

        if (c > 0) {
            if (!pixels[r][c - 1])
                collect++;
        }
        if (c == 0){
            collect++;
        }

        ////////////////////////

        if (r < 9) {
            if (!pixels[r + 1][c])
                collect++;
        }
        if(r == 9){
            collect++;
        }

        /////////////////////////

        if (r >0) {
            if (!pixels[r - 1][c])
                collect++;
        }
        if (r == 0){
            collect++;
        }
        visited[r][c]= true;
        return (collect + perimeterOf(r-1,c ) + perimeterOf(r + 1,c) + perimeterOf(r, c+1) + perimeterOf(r, c-1));
        }

    public boolean isBroken(){
        Point2D p = findStart();
        expandFrom((int)p.getX(), (int)p.getY());
        return (partSize() != 0);
    }

    public int perimeter() {
        Point2D p = findStart();
        return perimeterOf((int)p.getX(), (int)p.getY());
    }

    public static PartImage exampleA() {
        byte[][] pix = {{0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,0,0,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,1,1,1,1,1,0,0,0},
                {0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleB() {
        byte[][] pix = {{1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,0,1,0,1,0,1,0,0,0},
                {1,0,1,0,1,0,1,1,1,1},
                {1,1,1,1,1,1,1,0,0,0},
                {0,1,0,1,0,0,1,1,1,1},
                {0,1,0,1,0,0,1,0,0,0},
                {0,1,0,1,0,0,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleC() {
        byte[][] pix = {{1,1,1,0,0,0,1,0,0,0},
                {1,1,1,1,0,0,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1},
                {0,1,1,1,0,0,1,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {1,0,0,0,1,1,0,1,1,1},
                {1,1,0,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,1,1,0,1,1,1,1,1},
                {0,0,1,0,0,0,1,1,0,0}};
        return new PartImage(10,10, pix);
    }
    public static PartImage exampleD() {
        byte[][] pix = {{1,0,1,0,1,0,1,1,0,0},
                {1,0,1,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,1,1},
                {1,0,1,1,1,1,1,1,1,0},
                {1,0,0,1,0,0,1,0,0,0},
                {1,1,0,0,0,1,1,0,0,1},
                {0,1,0,0,0,0,0,0,1,1},
                {0,1,0,1,0,0,0,0,0,0},
                {0,0,0,1,1,1,0,0,0,0},
                {0,0,0,0,0,1,1,0,0,0}};
        return new PartImage(10,10, pix);
    }
}