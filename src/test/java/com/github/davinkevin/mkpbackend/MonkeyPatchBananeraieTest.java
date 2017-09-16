package com.github.davinkevin.mkpbackend;

import com.github.davinkevin.mkpbackend.model.Action;
import com.github.davinkevin.mkpbackend.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.stream.Stream;

import static com.github.davinkevin.mkpbackend.model.Action.*;
import static com.github.davinkevin.mkpbackend.model.Orientation.*;
import static com.github.davinkevin.mkpbackend.model.Orientation.S;
import static io.vavr.API.List;
import static io.vavr.API.Tuple;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kevin on 16/09/2017
 */
class MonkeyPatchBananeraieTest {

    @Test
    void should_read_position_and_list_of_actions() {
        /* GIVEN */
        String position =   "(2, 1, E)\n" +
                            "AGAADDADA\n";
        ByteArrayInputStream in = asStream(position);

        /* WHEN  */
        MonkeyPatchBananeraie init = new MonkeyPatchBananeraie(in);
        MonkeyPatchBananeraie end = init.executeActions();

        /* THEN  */
        assertThat(init.getPosition()).isEqualToComparingFieldByField(Position.of(2, 1, E));
        assertThat(init.getActions()).isEqualTo(List(A, G, A, A, D, D, A, D, A));
        assertThat(end.getPosition()).isEqualToComparingFieldByField(Position.of(2, 2, O));
    }

    @Test
    void should_produce_expected_output() {
        /* GIVEN */
        String position =   "(5, 6, N)\n" +
                            "AAADAGAAGADA\n";
        ByteArrayInputStream in = asStream(position);

        /* WHEN  */
        MonkeyPatchBananeraie end = new MonkeyPatchBananeraie(in).executeActions();

        /* THEN  */
        assertThat(end.getPosition()).isEqualToComparingFieldByField(Position.of(5, 12, N));
    }

    private static ByteArrayInputStream asStream(String position) {
        return new ByteArrayInputStream(Charset.forName("UTF-8").encode(position).array());
    }
}