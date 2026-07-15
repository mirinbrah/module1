import java.util.Random;
import java.util.Scanner;
import java.util.Locale;

public class Game {
    private final String castle = "\uD83C\uDFF0";
    private final GameBoard board = new GameBoard(5);
    private final Person person = new Person(board.getSize());
    private final Random r = new Random();
    private final Scanner sc = new Scanner(System.in);
    private Monster[] arrMonster;
    private int step;

    public void start() {
        generateMonsters();
        generateCastle();

        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer.trim().toUpperCase(Locale.ROOT)) {
            case "ДА" -> play();
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены неккоректно");
        }
    }

    private void generateMonsters() {
        int countMonster = board.getSize() * board.getSize() - board.getSize() - 5;
        arrMonster = new Monster[countMonster + 1];
        int count = 0;

        while (count <= countMonster) {
            Monster monster;
            if (r.nextBoolean()) {
                monster = new Monster(board.getSize());
            } else {
                monster = new BigMonster(board.getSize());
            }

            if (board.isCellEmpty(monster.getX(), monster.getY())) {
                board.setCell(monster.getX(), monster.getY(), monster.getImage());
                arrMonster[count] = monster;
                count++;
            }
        }
    }

    private void generateCastle() {
        int castleX = r.nextInt(board.getSize());
        int castleY = 0;
        board.setCell(castleX, castleY, castle);
    }

    private void play() {
        System.out.println("Выбери сложность игры(от 1 до 5):");
        int difficultGame = sc.nextInt();
        System.out.println("Выбранная сложность:\t" + difficultGame);

        while (true) {
            board.setCell(person.getX() - 1, person.getY() - 1, person.getImage());
            board.output(person.getLive());
            System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)" +
                    "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + "))");
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (makeMove(x, y, difficultGame)) {
                break;
            }
        }
    }

    private boolean makeMove(int x, int y, int difficultGame) {
        if (!person.moveCorrect(x, y)) {
            System.out.println("Неккоректный ход");
            return false;
        }

        String next = board.getCell(x - 1, y - 1);
        if (next.equals("  ")) {
            movePerson(x, y);
            step++;
            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                    "\nХод номер: " + step);
        } else if (next.equals(castle)) {
            System.out.println("Вы прошли игру!");
            return true;
        } else {
            fightMonster(x, y, difficultGame);
        }
        return false;
    }

    private void fightMonster(int x, int y, int difficultGame) {
        for (Monster monster : arrMonster) {
            if (monster.conflictPerson(x, y)) {
                if (monster.taskMonster(difficultGame)) {
                    movePerson(x, y);
                } else {
                    person.downLive();
                }
                break;
            }
        }
    }

    private void movePerson(int x, int y) {
        board.clearCell(person.getX() - 1, person.getY() - 1);
        person.move(x, y);
    }
}
