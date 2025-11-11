package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    private final F first;
    private final S second;
    private Pair(F first, S second){
        this.first = first;
        this.second = second;
    }
    public static <F, S> Pair<F, S> of(F first, S second){
        return new Pair<F, S>(first, second);
    }
    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pair<?, ?> another = (Pair<?, ?>)obj;
        return Objects.equals(this.first, another.first) && Objects.equals(this.second, another.second);
    }
    public int hashCode(){
        return Objects.hash(first, second);
    }
    public void ifPresent(BiConsumer<? super F, ? super S> operation){
        if(first != null && second != null){
            operation.accept(first, second);
        }
    }
    // TODO напишите реализацию
}
