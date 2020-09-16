package bar.cocktailpick.bartender.webserver.adminbot.dto;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.util.MarkdownUtils;
import bar.cocktailpick.bartender.webserver.adminbot.service.AdminBotService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class AdminBotResponse {
    private final String text;

    public static AdminBotResponse ofHelp(List<String> triggers) {
        String message = MarkdownUtils.code("관리자 명령 목록") + "입니다."
                + System.lineSeparator() + System.lineSeparator();

        String list = String.join(", ", MarkdownUtils.codes(triggers));

        return new AdminBotResponse(message + MarkdownUtils.blockQuote(list));
    }

    public static AdminBotResponse ofAllMembers(List<Member> allMembers) {
        String head = "요청하신 회원 목록입니다." + System.lineSeparator() + System.lineSeparator();

        List<String> memberResponses = allMembers.stream()
                .map(MemberResponse::of)
                .map(MemberResponse::toCode)
                .collect(Collectors.toList());

        String content = String.join(System.lineSeparator(), memberResponses);

        return new AdminBotResponse(head + content);
    }

    public static AdminBotResponse ofAddMemberHelp() {
        return new AdminBotResponse("올바른 명령 형식이 아닙니다." + System.lineSeparator() + "사용 방법 - " +
                MarkdownUtils.code(AdminBotService.Command.ADD_MEMBER.getTrigger() + " 이름"));
    }

    public static AdminBotResponse ofMemberExistError() {
        return new AdminBotResponse("같은 이름의 팀원이 이미 존재합니다.");
    }

    public static AdminBotResponse ofAddMember(Member saved) {
        return new AdminBotResponse("성공적으로 저장했습니다." + System.lineSeparator() +
                MemberResponse.of(saved).toCode());
    }

    public static AdminBotResponse ofAllRoles(List<Role> roles) {
        String head = "요청하신 역할 목록입니다." + System.lineSeparator() + System.lineSeparator();

        List<String> roleResponses = roles.stream()
                .map(RoleResponse::of)
                .map(RoleResponse::toCode)
                .collect(Collectors.toList());

        String content = String.join(System.lineSeparator(), roleResponses);

        return new AdminBotResponse(head + content);
    }

    public static AdminBotResponse ofAddRoleHelp() {
        return new AdminBotResponse("올바른 명령 형식이 아닙니다." + System.lineSeparator() + "사용 방법 - " +
                MarkdownUtils.code(AdminBotService.Command.ADD_ROLE.getTrigger() + " 역할") +
                System.lineSeparator() + "앞에 이모티콘을 붙여주시면 좋아요!!");
    }

    public static AdminBotResponse ofRoleExistError() {
        return new AdminBotResponse("같은 이름의 역할이 이미 존재합니다.");
    }

    public static AdminBotResponse ofAddRole(Role saved) {
        return new AdminBotResponse("성공적으로 저장했습니다." + System.lineSeparator() +
                RoleResponse.of(saved).toCode());
    }
}
