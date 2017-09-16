package com.github.davinkevin.mkpbackend.model;

import io.vavr.collection.List;
import io.vavr.collection.Set;

import static io.vavr.API.*;

/**
 * Created by kevin on 16/09/2017
 */
public enum Action {
    D, G, A;

    public static List<Action> asList(String s) {
        return List(s.split(""))
                .flatMap(v -> Try(() -> Action.valueOf(v)));
    }


}