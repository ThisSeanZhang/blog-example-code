package stream;

import java.util.ArrayList;
import java.util.function.Function;

public class UnlimitedIntegerStreamGenerator {

    private static<T> MyStream<T> getIntegerStreamInner(T start, Function<T, T> next){
        return new MyStream<>(start, () -> getIntegerStreamInner(next.apply(start), next));
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = UnlimitedIntegerStreamGenerator.getIntegerStreamInner(1, n -> n + 1)
                .peek(System.out::println)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(a);
    }
}
