import java.util.Random;
import java.util.Scanner;

class Monster {
    private String image = VisualKeys.MONSTER.getImage();
    private final int x, y;
    private boolean defeated;
    protected final Random r = new Random();

    Monster(int sizeBoard) {
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    String getImage() {
        return image;
    }

    int getY() {
        return y;
    }

    int getX() {
        return x;
    }

    boolean conflictPerson(int perX, int perY) {
        return !defeated && perY - 1 == this.y && perX - 1 == this.x;
    }

    void defeat() {
        defeated = true;
    }

    boolean taskMonster(int difficultGame, Scanner sc) {
        System.out.println("Решите задачу:");
        int maxNumber = 20 * difficultGame;
        int a = r.nextInt(maxNumber + 1);
        int b = r.nextInt(maxNumber + 1);
        int trueAnswer = a + b;
        System.out.println("Реши пример: " + a + " + " + b + " = ?");
        int ans = inputAnswer(sc);
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        } else {
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }

    protected int inputAnswer(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Ответ должен быть целым числом. Попробуй еще раз:");
        }
        int answer = sc.nextInt();
        sc.nextLine();
        return answer;
    }
}
