package dmei22.flashy.model;

public enum Interval {
    NEW(0),        // created
    ONE(1),        // 1 day
    TWO(3),        // 3 days
    THREE(7),      // 1 week
    FOUR(21),       // 3 weeks
    FIVE(60),       // 2 months
    SIX(180);         // 6 months

    private final int days;

    Interval(int days) {
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }

    public Interval next() {
        return switch (this) {
            case NEW -> ONE;
            case ONE -> TWO;
            case THREE -> FOUR;
            case FOUR -> FIVE;
            case SIX -> SIX;
            default -> NEW;
        };
    }
}
