package bar.cocktailpick.bartender.webclient.service;

import bar.cocktailpick.bartender.api.slackhook.SlackHook;
import bar.cocktailpick.bartender.api.slackhook.dto.AttachmentRequest;
import bar.cocktailpick.bartender.util.MarkdownUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmService {
    private final SlackHook slackHook;

    public void lunch() {
        AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color("#D00000")
                .pretext(MarkdownUtils.toChannel() + "\n점심 시간 알림입니다.")
                .title("점심 메뉴 추천 베스트 10가지")
                .title_link("https://jhbliss.tistory.com/entry/%EC%A0%90%EC%8B%AC%EB%A9%94%EB%89%B4-%EC%B6%94%EC%B2%9C-%EB%B2%A0%EC%8A%A4%ED%8A%B8-10%EA%B0%80%EC%A7%80")
                .text("점심 먹으러 가즈아아아!!! :runner: :woman-running:")
                .footer("바텐더 봇")
                .thumb_url("https://newsimg.sedaily.com/2018/11/01/1S70RJ621K_1.jpg")
                .build();

        slackHook.send(attachmentRequest.toHookRequest());
    }

    public void checkIn() {
        AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color("#D00000")
                .pretext(MarkdownUtils.toChannel() + "\n출근 시간 알림입니다.")
                .title("안녕하세요, 여러분.")
                .text("좋은 아침이군요." + MarkdownUtils.bold("6rinkers") + "🤖")
                .footer("바텐더 봇")
                .thumb_url("https://images.mypetlife.co.kr/content/uploads/2020/05/15111507/zach-reiner-YVpr0OdSUe0-unsplash-610x407.jpg")
                .build();

        slackHook.send(attachmentRequest.toHookRequest());
    }

    public void dinner() {
        AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color("#D00000")
                .pretext(MarkdownUtils.toChannel() + "\n저녁 시간 알림입니다.")
                .title("저녁 메뉴 추천 20가지 레시피 모음")
                .title_link("http://blog.naver.com/PostView.nhn?blogId=reeyoung82&logNo=221016589862&categoryNo=12&parentCategoryNo=-1&viewDate=&currentPage=&postListTopCurrentPage=&isAfterWrite=true")
                .text("18시입니다. 모두 저녁을 먹으러 가세요. :male-cook:")
                .footer("바텐더 봇")
                .thumb_url("http://cdn.kormedi.com/wp-content/uploads/2020/03/gettyimages-a11229272-580x387.jpg")
                .build();

        slackHook.send(attachmentRequest.toHookRequest());
    }

    public void stretch() {
        AttachmentRequest attachmentRequest = AttachmentRequest.builder()
                .color("#D00000")
                .pretext(MarkdownUtils.toChannel() + "\n스트레칭 시간 알림입니다.")
                .title("Youtube: 오피스 요가")
                .title_link("https://www.youtube.com/results?search_query=%EC%98%A4%ED%94%BC%EC%8A%A4+%EC%9A%94%EA%B0%80")
                .text("3시다아!! 스트레칭하자~ 💃🕺")
                .footer("바텐더 봇")
                .thumb_url("http://img.khan.co.kr/news/2020/04/12/l_2020041201001494300115341.jpg")
                .build();

        slackHook.send(attachmentRequest.toHookRequest());
    }
}
