package com.github.davinkevin.mkpbackend.model;

import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.davinkevin.mkpbackend.model.Orientation.*;
import static io.vavr.API.Tuple;

/**
 * Created by kevin on 16/09/2017
 */
@ToString
@RequiredArgsConstructor
public class Position {

    private final static Pattern p = Pattern.compile("^\\s*\\(\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*,\\s*([NSEW]+)\\s*\\)\\s*$");

    private final Tuple2<Integer, Integer> coordinate;
    private final Orientation orientation;

    public static Position of(String s) {
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            throw new RuntimeException("Invalid position: " + s);
        }

        return Position.of(
                Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)),
                Orientation.valueOf(m.group(3))
        );
    }

    public static Position of(Integer x, Integer y, Orientation o) {
        return new Position(Tuple(x,y), o);
    }

    public Position move(Action a) {
        switch (a) {
            case A:
                return new Position(computeNewCoordinate(coordinate), orientation);
            case D:
                return new Position(coordinate, orientation.rotateRight());
            case G:
                return new Position(coordinate, orientation.rotateLeft());
            /* Only for compiler, never happens because all enum case are covered */
            default:
                throw new RuntimeException("Invalid action");
        }
    }

    private Tuple2<Integer, Integer> computeNewCoordinate(Tuple2<Integer, Integer> coordinates) {
        if (this.orientation == N || this.orientation == S) {
            return coordinates.map2(y -> this.orientation == N ? y + 1 : y - 1);
        }

        return coordinates.map1(y -> this.orientation == E ? y + 1 : y - 1);
    }
}
