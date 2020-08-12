package bar.cocktailpick.bartender.scheduler;

public class Crons {

    public static final String LUNCH_TIME = "0 11 11 * * TUE-FRI";

    public static final String CHECK_IN_TUESDAY_TO_FRIDAY = "0 0 10 * * TUE-FRI";

    public static final String CHECK_IN_MONDAY = "0 0 13 * * MON";

    public static final String DINNER_TIME = "0 0 18 * * MON-FRI";

    public static final String STRETCH_TIME = "0 0 15 * * MON-FRI";
}
