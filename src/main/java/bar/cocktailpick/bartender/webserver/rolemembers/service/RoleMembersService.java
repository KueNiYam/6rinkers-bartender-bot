package bar.cocktailpick.bartender.webserver.rolemembers.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMember;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMemberRepository;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMembers;
import bar.cocktailpick.bartender.domain.rolemembers.RoleMembersRepository;
import bar.cocktailpick.bartender.webserver.rolemembers.dto.RoleMembersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleMembersService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final RoleMemberRepository roleMemberRepository;
    private final RoleMembersRepository roleMembersRepository;

    @Transactional
    public RoleMembersResponse create() {
        List<Member> members = memberRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        if (roles.size() > members.size()) {
            throw new RuntimeException("역할 수가 팀원 수 보다 많습니다. 바텐더 관리자 봇을 통해 팀원을 추가해주세요.");
        }

        Collections.shuffle(members);
        List<Member> toAssign = members.stream()
                .limit(roles.size())
                .collect(Collectors.toList());

        List<RoleMember> assigned = assignEach(roles, toAssign);

        RoleMembers roleMembers = toRoleMembers(assigned);

        return RoleMembersResponse.of(roleMembers);
    }

    private RoleMembers toRoleMembers(List<RoleMember> assigned) {
        RoleMembers roleMembers = new RoleMembers();
        roleMembersRepository.save(roleMembers);

        for (RoleMember roleMember : assigned) {
            roleMembers.add(roleMember);
        }
        return roleMembers;
    }

    private List<RoleMember> assignEach(List<Role> roles, List<Member> shuffledAndCut) {
        List<RoleMember> assigned = new ArrayList<>();

        for (int i = 0; i < roles.size(); i++) {
            assigned.add(new RoleMember(roles.get(i).getRole(), shuffledAndCut.get(i).getName()));
        }

        return roleMemberRepository.saveAll(assigned);
    }

    @Transactional(readOnly = true)
    public RoleMembersResponse current() {
        RoleMembers roleMembers = roleMembersRepository.findFirstByOrderByCreatedAtDesc()
                .orElseThrow(() -> new RuntimeException("현재 역할을 불러올 수 없습니다."));

        return RoleMembersResponse.of(roleMembers);
    }
}
