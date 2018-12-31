package github.banana.generic;

import lombok.Data;

@Data
public class Pair<T> {
    private T first;
    private T last;

    public Pair() {

    }

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public static <K> Pair<K> create(K first, K last) {
        return new Pair<>(first, last);
    }
}
