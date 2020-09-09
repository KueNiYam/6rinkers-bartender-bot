package bar.cocktailpick.bartender.webserver.member;

import bar.cocktailpick.bartender.webserver.member.dto.MemberRequest;
import bar.cocktailpick.bartender.webserver.member.dto.MemberResponse;
import bar.cocktailpick.bartender.webserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid MemberRequest memberRequest) {
        Long createdId = memberService.add(memberRequest);
        return ResponseEntity.created(URI.create("/api/members/" + createdId)).build();
    }
}
