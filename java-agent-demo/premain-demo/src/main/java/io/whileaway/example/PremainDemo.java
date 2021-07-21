package io.whileaway.example;

import java.lang.instrument.Instrumentation;

public class PremainDemo {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("use Premain agent");
    }

    public static void premain(String agentArgs) {

    }
}
