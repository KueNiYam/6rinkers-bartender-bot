package bar.cocktailpick.bartender.domain;

import org.springframework.stereotype.Component;

@Component
public class CustomDateFactory {
    public CustomDate nowDate() {
        return CustomDate.now();
    }
}
