package io.whileaway.example;

import java.lang.instrument.Instrumentation;

public class AgentDemo {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("use agent");
    }

    public static void agentmain(String agentArgs) {

    }
}
