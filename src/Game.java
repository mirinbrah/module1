import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

class Game {
    private final String castle = VisualKeys.CASTLE.getImage();
    private final GameBoard board = new GameBoard(5);
    private final Person person = new Person(board.getSize());
    private final Random r = new Random();
    private final Scanner sc = new Scanner(System.in);
    private Monster[] arrMonster;
    private int step;

    void start() {
        generateCastle();
        generateMonsters();

        while (true) {
            System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
            String answer = sc.nextLine();
            System.out.println("Ваш ответ:\t" + answer);

            switch (answer.trim().toUpperCase(Locale.ROOT)) {
                case "ДА" -> {
                    play();
                    return;
                }
                case "НЕТ" -> {
                    System.out.println("Жаль, приходи еще!");
                    return;
                }
                default -> System.out.println("Данные введены некорректно");
            }
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
        int difficultGame = inputDifficulty();
        sc.nextLine();
        System.out.println("Выбранная сложность:\t" + difficultGame);

        while (true) {
            board.setCell(person.getX() - 1, person.getY() - 1, person.getImage());
            board.output(person.getLive());
            System.out.println("Введите координаты x и y через пробел" +
                    "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");
            int[] coordinates = inputCoordinates();

            if (makeMove(coordinates[0], coordinates[1], difficultGame)) {
                break;
            }
        }
    }

    private boolean makeMove(int x, int y, int difficultGame) {
        if (!board.isInside(x - 1, y - 1)) {
            System.out.println("Нельзя выйти за границы игрового поля");
            return false;
        }

        if (!person.moveCorrect(x, y)) {
            System.out.println("Некорректный ход");
            return false;
        }

        String next = board.getCell(x - 1, y - 1);
        if (next.equals(VisualKeys.EMPTY.getImage())) {
            movePerson(x, y);
            step++;
            System.out.println("Ход корректный; Новые координаты: " + person.getX() + ", " + person.getY() +
                    "\nХод номер: " + step);
        } else if (next.equals(castle)) {
            System.out.println("Вы прошли игру!");
            return true;
        } else {
            return fightMonster(x, y, difficultGame);
        }
        return false;
    }

    private int inputDifficulty() {
        while (true) {
            System.out.println("Выбери сложность игры(от 1 до 5):");

            if (sc.hasNextInt()) {
                int difficultGame = sc.nextInt();
                if (difficultGame >= 1 && difficultGame <= 5) {
                    return difficultGame;
                }
            } else {
                sc.next();
            }

            System.out.println("Сложность должна быть целым числом от 1 до 5");
        }
    }

    private int[] inputCoordinates() {
        while (true) {
            if (sc.hasNextInt()) {
                int x = sc.nextInt();
                if (sc.hasNextInt()) {
                    int y = sc.nextInt();
                    if (board.isInside(x - 1, y - 1)) {
                        return new int[]{x, y};
                    }
                }
            }

            sc.nextLine();
            System.out.println("Координаты должны быть целыми числами от 1 до " + board.getSize() +
                    ". Введите x и y еще раз:");
        }
    }

    private boolean fightMonster(int x, int y, int difficultGame) {
        for (Monster monster : arrMonster) {
            if (monster.conflictPerson(x, y)) {
                if (monster.taskMonster(difficultGame, sc)) {
                    monster.defeat();
                    movePerson(x, y);
                } else {
                    person.downLive();
                    if (person.getLive() == 0) {
                        System.out.println("У вас закончились жизни. Игра окончена!");
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    private void movePerson(int x, int y) {
        board.clearCell(person.getX() - 1, person.getY() - 1);
        person.move(x, y);
    }
}
