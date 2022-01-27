package com.kalori.hesapla.networking;


interface Either<S, T>  {

    enum Type { LEFT, RIGHT }


    Type getType();

    S getLeft();

    T getRight();


    static <S,T> Either<S,T> left(S val) {

        return new Either<S, T>() {
            @Override
            public Type getType() {
                return Type.LEFT;
            }

            @Override
            public S getLeft() {
                return val;
            }

            @Override
            public T getRight() {
                throw new RuntimeException();
            }
        };

    }


    static <S,T> Either<S,T> right(T val) {

        return new Either<S, T>() {

            @Override
            public Type getType() {
                return Type.RIGHT;
            }

            @Override
            public S getLeft() {
                throw new RuntimeException();
            }

            @Override
            public T getRight() {
                return val;
            };
        };

    }
}
