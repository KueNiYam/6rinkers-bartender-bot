package bar.cocktailpick.bartender.rollingRole.domain;

public class RoleMemberPair {
    private final Member member;
    private final Role role;

    public RoleMemberPair(Role role, Member member) {
        this.role = role;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public Role getRole() {
        return role;
    }

    public String text() {
        return role.getRoleName() + " -> " + member.getMemberName() + "\n";
    }
}
