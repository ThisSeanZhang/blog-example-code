package stream;

import java.util.function.*;

public class MyStream<T> {

    private T head;

    private Supplier<MyStream<T>> nextStream;

    private boolean isEnd = false;

    private MyStream() {
        this.isEnd = true;
    }

    public MyStream(T head,Supplier<MyStream<T>> nextStream) {
        this.head = head;
        this.nextStream = nextStream;
    }

    public MyStream<T> filter(Predicate<? super T> predicate) {
        if (head == null) return MyStream.empty();
        return predicate.test(head)
                ? new MyStream<>(head, () -> this.nextStream.get().filter(predicate))
                : this.nextStream.get().filter(predicate);
    }

    public <R> MyStream<R> map(Function<? super T, ? extends R> mapper) {
        if (head == null) return MyStream.empty();
        return new MyStream<>(mapper.apply(head), () -> this.nextStream.get().map(mapper));
    }


    public MyStream<T> peek(Consumer<? super T> action) {
        if (head == null) return MyStream.empty();
        action.accept(head);
        return new MyStream<>(head, () -> this.nextStream.get().peek(action));
    }

    public MyStream<T> limit(long maxSize) {
        if (head == null || maxSize <= 0) return MyStream.empty();
        return new MyStream<>(head, () -> this.nextStream.get().limit(maxSize - 1));
    }

    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        R resultA = supplier.get();
        if (isEnd) return resultA;
        accumulator.accept(resultA, head);
        combiner.accept(resultA, (R) this.nextStream.get().collect(supplier, accumulator, combiner));
        return resultA;
    }

    static public<T> MyStream<T> empty() {
        return new MyStream<>();
    }

    public T getHead() {
        return head;
    }

    public MyStream<T> getNextStream() {
        return this.nextStream.get();
    }
}
