package bar.cocktailpick.bartender.domain.dice;

public class Dice {
    public final static int MAX = 100;

    private final int number;

    public Dice(int number) {
        this.number = number;

        if (number < 1) {
            throw new IllegalArgumentException("주사위의 숫자는 1보다 작을 수 없습니다.");
        }

        if (number > 100) {
            throw new IllegalArgumentException("주사위의 숫자는 100보다 클 수 없습니다.");
        }
    }

    public boolean isOrBelow(int max) {
        return number <= max;
    }

    public int getNumber() {
        return number;
    }
}
