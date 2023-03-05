package eu.ciechanowiec.movie2;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

enum Choice {
    CREATE(1),
    PRINT(2),
    UPDATE(3),
    DELETE(4),
    UNDEFINED(400);
    @Getter(AccessLevel.PRIVATE)
    private final int choiceNumber;

    Choice(int choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    static Choice byNumber(int searchedChoiceNumber) {
        Choice[] allChoices = Choice.values();
        Optional<Choice> first = Stream.of(allChoices)
                .filter(choice -> choice.getChoiceNumber() == searchedChoiceNumber)
                .findFirst();
        return first.orElse(Choice.UNDEFINED);
    }
}
