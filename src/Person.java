import java.util.Random;

class Person {
    private int x, y;
    private final String image = VisualKeys.PERSON.getImage();
    private int live = 3;

    Person(int sizeBoard) {
        y = sizeBoard;
        Random r = new Random();
        x = r.nextInt(sizeBoard) + 1;
    }

    Person(int x, int y){
        this.x = x;
        this.y = y;
    }
    Person(){
        this(1, 1);
    }

    int getX(){
        return x;
    }

    int getY() {
        return y;
    }

    int getLive() {
        return live;
    }

    String getImage(){
        return image;
    }

    boolean moveCorrect(int x, int y){
        return this.x == x && Math.abs(this.y - y) == 1 || this.y == y && Math.abs(this.x - x) == 1;
    }

    void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    void downLive(){
        live--;
    }
}
