import java.util.Scanner;

public class BigMonster extends Monster {

    private String image = VisualKeys.BIG_MONSTER.getImage();

    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int difficultGame, Scanner sc) {
        int trueAnswer = r.nextInt(2, 5 * difficultGame + 3);
        int coefficient = difficultGame == 1 ? 1 : r.nextInt(2, 2 * difficultGame + 2);
        int freeNumber = r.nextInt(1, 10 * difficultGame + 1);
        boolean subtraction = difficultGame >= 3 && r.nextBoolean();
        int rightPart;

        System.out.println("Большой монстр приготовил уравнение. Найди x:");
        if (subtraction) {
            rightPart = coefficient * trueAnswer - freeNumber;
            System.out.println(coefficient + "x - " + freeNumber + " = " + rightPart);
        } else {
            rightPart = coefficient * trueAnswer + freeNumber;
            System.out.println(coefficient + "x + " + freeNumber + " = " + rightPart);
        }

        int answer = inputAnswer(sc);
        if (answer == trueAnswer) {
            System.out.println("Верно! Ты решил уравнение и победил большого монстра");
            return true;
        } else {
            System.out.println("Неверно! Правильный ответ: x = " + trueAnswer);
            return false;
        }
    }
}
