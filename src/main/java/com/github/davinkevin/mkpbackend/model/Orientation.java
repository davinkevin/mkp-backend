package com.github.davinkevin.mkpbackend.model;

/**
 * Created by kevin on 16/09/2017
 */
public enum Orientation {
    N, E, S, O;

    public Orientation rotateRight() {
        switch (this) {
            case N: return E;
            case E: return S;
            case S: return O;
            case O: return N;
            /* Only for compiler, never happens because all enum case are covered */
            default: throw new RuntimeException("Invalid Cardinal Orientation");
        }
    }

    public Orientation rotateLeft() {
        return this.rotateRight().rotateRight().rotateRight();
    }
}
