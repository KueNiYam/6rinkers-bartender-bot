package bar.cocktailpick.bartender.domain;

public enum Role2 {
    COOK("👩‍🍳 취사병"),
    WRITER("👩‍💻 서기"),
    LEADER("👩‍🏫 회의진행자"),
    MASTER("👩‍🎨 스크럼 마스터"),
    WIZARD("🕵️‍♀️ 회의실 예약");

    private final String role;

    Role2(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
