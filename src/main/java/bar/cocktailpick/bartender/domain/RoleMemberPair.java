package bar.cocktailpick.bartender.domain;

public class RoleMemberPair {
    private final Member member;
    private final Role role;

    public RoleMemberPair(Role role, Member member) {
        this.role = role;
        this.member = member;
    }

    public boolean is(Role role) {
        return this.role == role;
    }

    public String getMemberName() {
        return member.getMemberName();
    }

    public String getRoleName() {
        return role.getRoleName();
    }
}
