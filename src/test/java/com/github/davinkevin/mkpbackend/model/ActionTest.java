package com.github.davinkevin.mkpbackend.model;

import io.vavr.API;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static com.github.davinkevin.mkpbackend.model.Action.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kevin on 16/09/2017
 */
class ActionTest {

    @Test
    void should_transform_a_string_to_list_of_action() {
        /* GIVEN */
        String list = "AGAADDADA";
        /* WHEN  */
        List<Action> actions = Action.asList(list);
        /* THEN  */
        assertThat(actions).isEqualTo(API.List(A, G, A, A, D, D, A, D, A));
    }

    @Test
    void should_have_an_empty_list() {
        /* GIVEN */
        String list = "";
        /* WHEN  */
        List<Action> actions = Action.asList(list);
        /* THEN  */
        assertThat(actions).isEqualTo(List.empty());
    }
}