package bar.cocktailpick.bartender.domain;

public class RoleMember {
    private final Member member;
    private final Role role;

    public RoleMember(Role role, Member member) {
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
