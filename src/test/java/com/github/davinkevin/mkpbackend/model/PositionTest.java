package com.github.davinkevin.mkpbackend.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.davinkevin.mkpbackend.model.Orientation.*;
import static io.vavr.API.Tuple;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by kevin on 16/09/2017
 */
class PositionTest {

    @Test
    void should_not_able_to_parse_invalid_format() {
        assertThatThrownBy(() -> Position.of("1,3,S"))
                .hasMessageStartingWith("Invalid position:")
                .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("should handle multiple format for creation from string")
    @ParameterizedTest(name = "{0} should give {1}")
    @CsvSource({"'(1,3,S)'", "'( 1, 3, S)'", "'(1, 3, S)'", "' (    1   ,    3   ,   S   ) '"})
    void should_handle_multiple_format_for_creation_from_string(String stringPosition) {
        /* GIVEN */ 
        /* WHEN  */
        Position p = Position.of(stringPosition);
        /* THEN  */
        assertThat(p).isEqualToComparingFieldByField(new Position(Tuple(1, 3), S));
    }

    @DisplayName("should move")
    @ParameterizedTest(name = "Action {0} should move to {1}")
    @MethodSource("allMovesAndResult")
    void should_move_(Action action, Position start, Position expected) {
        /* GIVEN */
        /* WHEN  */
        Position newPosition = start.move(action);
        /* THEN  */
        assertThat(newPosition).isEqualToComparingFieldByField(expected);
    }

    private static Stream<Arguments> allMovesAndResult() {
        return Stream.of(
                Arguments.of(Action.A, Position.of(1, 3, N), Position.of(1,4, N)),
                Arguments.of(Action.A, Position.of(1, 3, S), Position.of(1,2, S)),
                Arguments.of(Action.A, Position.of(1, 3, E), Position.of(2,3, E)),
                Arguments.of(Action.A, Position.of(1, 3, O), Position.of(0,3, O)),
                Arguments.of(Action.D, Position.of(1, 3, S), Position.of(1,3, O)),
                Arguments.of(Action.G, Position.of(1, 3, S), Position.of(1,3, E))
        );
    }
}