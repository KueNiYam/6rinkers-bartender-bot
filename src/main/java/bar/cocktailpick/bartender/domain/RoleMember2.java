package bar.cocktailpick.bartender.domain;

public class RoleMember2 {
    private final Member2 member;
    private final Role2 role;

    public RoleMember2(Role2 role, Member2 member) {
        this.role = role;
        this.member = member;
    }

    public boolean is(Role2 role) {
        return this.role == role;
    }

    public String getMemberName() {
        return member.getMemberName();
    }

    public String getRoleName() {
        return role.getRoleName();
    }
}
