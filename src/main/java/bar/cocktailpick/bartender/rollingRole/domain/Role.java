package bar.cocktailpick.bartender.rollingRole.domain;

public enum Role {
    COOK("👩‍🍳 취사병"),
    WRITER("👩‍💻 서기"),
    LEADER("👩‍🏫 회의진행자"),
    MASTER("👩‍🎨 스크럼 마스터"),
    WIZARD("🕵️‍♀️ 회의실 예약");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }
}
