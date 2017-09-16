package com.github.davinkevin.mkpbackend;

import static io.vavr.API.println;

/**
 * Created by kevin on 16/09/2017
 */
public class Main {

    public static void main(String[] args) {

        MonkeyPatchBananeraie mp = new MonkeyPatchBananeraie(System.in).executeActions();
        println("Final position: " + mp.getPosition());

    }

}