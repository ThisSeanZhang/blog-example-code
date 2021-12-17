package stream;

import java.util.ArrayList;

/**
 * 整数流生成器
 */
public class IntegerStreamGenerator {

    private static MyStream<Integer> getIntegerStreamInner(int low, int high){
        if(low > high){
            return MyStream.empty();
        }
        return new MyStream<>(low, ()->getIntegerStreamInner(low+1,high));
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = IntegerStreamGenerator.getIntegerStreamInner(1, 5)
                .map(s -> s + 1)
                .filter(x -> x == 4)
                .peek(System.out::println)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(a);
        MyStream<Integer> integerStream = IntegerStreamGenerator.getIntegerStreamInner(1, 100000000);
        System.out.println(integerStream.getHead());
        System.out.println(integerStream.getNextStream().getHead());
        int sum = 0;
        int i = 0;
        while (i < 10) {
            int y = i * i;
            sum += y;
            i++;
        }
    }
}
