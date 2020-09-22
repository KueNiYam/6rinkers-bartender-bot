package bar.cocktailpick.bartender.domain.rolemembers;

import bar.cocktailpick.bartender.domain.rolemembers.dto.RoleMemberCountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static bar.cocktailpick.bartender.domain.rolemembers.QRoleMember.roleMember;

@RequiredArgsConstructor
@Repository
public class RoleMemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<RoleMemberCountDto> countGroupByRoleAndMemberOrderByRoleAscAndMemberCountDesc() {
        return queryFactory
                .selectFrom(roleMember)
                .groupBy(roleMember.role, roleMember.member)
                .select(Projections.constructor(RoleMemberCountDto.class,
                        roleMember.role, roleMember.member, roleMember.member.count()))
                .orderBy(roleMember.role.asc(), roleMember.member.count().desc())
                .fetch();
    }
}
