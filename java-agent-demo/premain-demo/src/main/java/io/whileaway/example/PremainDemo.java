package io.whileaway.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class PremainDemo {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception{
        // 对增强类中更细致的处理方式
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) -> {
                // method 指定拦截的方法需要, ElementMatchers.any() 拦截所有方法
                return builder.method(ElementMatchers.any())
                        // intercept() 指明方法的处理类
                        .intercept(MethodDelegation.to(TimeKeeperInterceptor.class));
        };
        // 使用AgentBuild 定义处理的方式
        new AgentBuilder
                .Default()
                // 根据包名前缀拦截类
                .type(ElementMatchers.nameStartsWith("io.whileaway"))
                // 拦截到的类由transformer处理
                .transform(transformer)
                // 安装到 Instrumentation
                .installOn(inst);
    }

    public static void premain(String agentArgs) {

    }
}
