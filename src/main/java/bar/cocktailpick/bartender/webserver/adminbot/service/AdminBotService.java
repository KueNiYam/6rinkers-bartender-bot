package bar.cocktailpick.bartender.webserver.adminbot.service;

import bar.cocktailpick.bartender.domain.member.Member;
import bar.cocktailpick.bartender.domain.member.MemberRepository;
import bar.cocktailpick.bartender.domain.role.Role;
import bar.cocktailpick.bartender.domain.role.RoleRepository;
import bar.cocktailpick.bartender.util.CommandUtils;
import bar.cocktailpick.bartender.webserver.adminbot.dto.AdminBotResponse;
import bar.cocktailpick.bartender.webserver.common.dto.BotRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminBotService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    public AdminBotResponse serve(BotRequest botRequest) {
        return Command.find(botRequest)
                .behavior
                .apply(this, botRequest);
    }

    private AdminBotResponse help(BotRequest botRequest) {
        return AdminBotResponse.ofHelp(Command.sortedTriggers());
    }

    @Transactional(readOnly = true)
    protected AdminBotResponse allMembers(BotRequest botRequest) {
        return AdminBotResponse.ofAllMembers(memberRepository.findAll());
    }

    @Transactional
    protected AdminBotResponse addMember(BotRequest botRequest) {
        String receive = botRequest.getText();

        List<String> split = Arrays.asList(receive.split(CommandUtils.INPUT_SEPARATOR));

        if (split.size() != 2) {
            return AdminBotResponse.ofAddMemberHelp();
        }

        String name = split.get(1).trim();

        if (StringUtils.isEmpty(name)) {
            return AdminBotResponse.ofAddMemberHelp();
        }

        if (memberRepository.findByName(name).isPresent()) {
            return AdminBotResponse.ofMemberExistError();
        }

        Member saved = memberRepository.save(Member.builder()
                .name(name)
                .build());

        return AdminBotResponse.ofAddMember(saved);
    }

    @Transactional(readOnly = true)
    protected AdminBotResponse allRoles(BotRequest botRequest) {
        return AdminBotResponse.ofAllRoles(roleRepository.findAll());
    }

    @Transactional
    protected AdminBotResponse addRole(BotRequest botRequest) {
        String receive = botRequest.getText();

        List<String> split = Arrays.asList(receive.split(CommandUtils.INPUT_SEPARATOR));

        if (split.size() != 2) {
            return AdminBotResponse.ofAddRoleHelp();
        }

        String role = split.get(1).trim();

        if (StringUtils.isEmpty(role)) {
            return AdminBotResponse.ofAddRoleHelp();
        }

        if (roleRepository.findByRole(role).isPresent()) {
            return AdminBotResponse.ofRoleExistError();
        }

        Role saved = roleRepository.save(Role.builder()
                .role(role)
                .build());

        return AdminBotResponse.ofAddRole(saved);
    }

    public enum Command {
        HELP("도움", AdminBotService::help),
        ALL_MEMBERS("팀원 목록", AdminBotService::allMembers),
        ADD_MEMBER("팀원 추가" + CommandUtils.INPUT_SEPARATOR, AdminBotService::addMember),
        ALL_ROLES("역할 목록", AdminBotService::allRoles),
        ADD_ROLE("역할 추가" + CommandUtils.INPUT_SEPARATOR, AdminBotService::addRole);

        private final String trigger;
        private final BiFunction<AdminBotService, BotRequest, AdminBotResponse> behavior;

        Command(String trigger, BiFunction<AdminBotService, BotRequest, AdminBotResponse> behavior) {
            this.trigger = trigger;
            this.behavior = behavior;
        }

        public static List<String> sortedTriggers() {
            return Arrays.stream(values())
                    .map(command -> command.trigger)
                    .sorted()
                    .collect(Collectors.toList());
        }

        public static Command find(BotRequest botRequest) {
            return Arrays.stream(values())
                    .filter(command -> botRequest.isByTrigger(command.trigger))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("처리할 수 있는 요청이 아닙니다."));
        }

        public String getTrigger() {
            return trigger;
        }
    }
}
