package bar.cocktailpick.bartender.webserver.dice.service;

import bar.cocktailpick.bartender.domain.dice.Dice;
import org.springframework.stereotype.Component;

@Component
public class DiceGenerator {
    public Dice generate() {
        return new Dice((int) (Math.random() * Dice.MAX) + 1);
    }
}
