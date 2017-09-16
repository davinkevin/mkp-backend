package com.github.davinkevin.mkpbackend.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.davinkevin.mkpbackend.model.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kevin on 16/09/2017
 */
class OrientationTest {

    @DisplayName("should rotate to the right")
    @ParameterizedTest(name = "{0} should give {1}")
    @MethodSource("allRightCombinations")
    void should_rotate_right(Orientation start, Orientation expected) {
        assertThat(start.rotateRight()).isEqualTo(expected);
    }

    @DisplayName("should rotate to the left")
    @ParameterizedTest(name = "{0} should give {1}")
    @MethodSource("allLeftCombinations")
    void should_rotate_left(Orientation start, Orientation expected) {
        assertThat(start.rotateLeft()).isEqualTo(expected);
    }

    private static Stream<Arguments> allRightCombinations() {
        return Stream.of(Arguments.of(N, E), Arguments.of(E, S), Arguments.of(S, O), Arguments.of(O, N));
    }
    private static Stream<Arguments> allLeftCombinations() {
        return Stream.of(Arguments.of(N, O), Arguments.of(E, N), Arguments.of(S, E), Arguments.of(O, S));
    }
}