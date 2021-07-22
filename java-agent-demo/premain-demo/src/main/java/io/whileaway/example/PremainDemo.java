package io.whileaway.example;

import io.whileaway.example.test.PremainTarget;

import java.lang.instrument.Instrumentation;

public class PremainDemo {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception{
        System.out.println("use Premain agent");

        // 注册一个 Transformer，该 Transformer在类加载时被调用
        inst.addTransformer(new Transformer(), true);
        inst.retransformClasses(PremainTarget.class);
        System.out.println("premain done");

    }

    public static void premain(String agentArgs) {

    }
}
