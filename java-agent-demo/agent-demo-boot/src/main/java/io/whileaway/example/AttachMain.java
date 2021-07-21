package io.whileaway.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import sun.jvmstat.monitor.*;
public class AttachMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println(AttachMain.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        getAllProcess().forEach((k, v) -> {
            System.out.println(k + ": "  +v);
        });

        VirtualMachine vm = VirtualMachine.attach("25516");
        try {
            vm.loadAgent("D:/projects/blog-example-code/java-agent-demo/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar");
//            vm.loadAgent("D:/projects/blog-example-code/java-agent-demo/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar");
        } finally {
            vm.detach();
        }
//
//        try {
//            vm.loadAgent("D:/projects/blog-example-code/java-agent-demo/agent-demo/target/agent-demo-1.0-SNAPSHOT.jar");
//        } finally {
//            vm.detach();
//        }
    }

    public static Map<Integer, String> getAllProcess() {
        Map<Integer, String> result = new HashMap<>();
        try {
           // 获取监控主机
           MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
           // 取得所有在活动的虚拟机集合
            for (Integer pid: local.activeVms()) {
                MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + pid));
                System.out.println(MonitoredVmUtil.mainArgs(vm));
                String processname = MonitoredVmUtil.mainClass(vm, true);
                result.put(pid, processname);
            }
       } catch (Exception e) {

       }
        return result;
    }
}
