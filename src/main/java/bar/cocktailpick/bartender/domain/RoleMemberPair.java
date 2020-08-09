package bar.cocktailpick.bartender.domain;

public class RoleMemberPair {
    private final Member member;
    private final Role role;

    public RoleMemberPair(Role role, Member member) {
        this.role = role;
        this.member = member;
    }

    public String getMemberName() {
        return member.getMemberName();
    }

    public String getRoleName() {
        return role.getRoleName();
    }

}
