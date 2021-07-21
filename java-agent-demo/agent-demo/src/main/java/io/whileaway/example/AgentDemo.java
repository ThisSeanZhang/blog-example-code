package io.whileaway.example;

import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class AgentDemo {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        try{
//            JarFile jarFile = new JarFile("D:/projects/blog-example-code/java-agent-demo/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar");
//            inst.appendToBootstrapClassLoaderSearch(jarFile);
            System.out.println("use agent");
        } catch (Exception ignore) {}
    }

    public static void agentmain(String agentArgs) {

    }
}
