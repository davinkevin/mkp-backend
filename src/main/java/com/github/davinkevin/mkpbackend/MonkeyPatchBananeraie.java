package com.github.davinkevin.mkpbackend;

import com.github.davinkevin.mkpbackend.model.Action;
import com.github.davinkevin.mkpbackend.model.Position;
import io.vavr.collection.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Scanner;

import static io.vavr.API.println;

/**
 * Created by kevin on 16/09/2017
 */
@RequiredArgsConstructor
class MonkeyPatchBananeraie {

    @Getter private final Position position;
    @Getter private final List<Action> actions;

    MonkeyPatchBananeraie(InputStream inputStream) {
        Scanner in = new Scanner(inputStream);

        println("Provide position with format (x, y, o)");
        this.position = Position.of(in.nextLine());
        println("Provide actions");
        this.actions = Action.asList(in.nextLine());
    }

    MonkeyPatchBananeraie executeActions() {
        Position p = position;
        for (Action action : actions) {
            p = p.move(action);
        }

        return new MonkeyPatchBananeraie(p, List.empty());

    }
}
