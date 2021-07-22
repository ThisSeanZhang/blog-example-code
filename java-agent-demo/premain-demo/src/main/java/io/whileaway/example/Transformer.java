package io.whileaway.example;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

class Transformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader l, String className,
                            Class<?> c, ProtectionDomain pd, byte[] b)  {
        // 只修改PremainTarget的定义
        if (!c.getSimpleName().equals("PremainTarget")) {
            return null;
        }
        try {
            // 读取 PremainTarget.class这个 class文件，作为 PremainTarget 类的新定义
            return Transformer.class.getResourceAsStream("/PremainTarget.class").readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
