package bar.cocktailpick.bartender.util;

import java.util.List;
import java.util.stream.Collectors;

public class MarkdownUtils {
    public static String code(String string) {
        return String.format("`%s`", string);
    }

    public static List<String> codes(List<String> strings) {
        return strings.stream()
                .map(MarkdownUtils::code)
                .collect(Collectors.toList());
    }

    public static String toChannel() {
        return "<!channel>";
    }
}
