package bar.cocktailpick.bartender.rollingRole.domain;

public enum Member {
    TONEY("토니"),
    DOO("두강"),
    KUENI("그니"),
    BEAR("작곰"),
    LATTE("라테");

    private final String name;

    Member(String name) {
        this.name = name;
    }

    public String getMemberName() {
        return name;
    }
}
