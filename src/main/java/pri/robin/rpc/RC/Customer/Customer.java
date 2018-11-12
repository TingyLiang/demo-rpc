package pri.robin.rpc.RC.Customer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import pri.robin.rpc.RC.provider.Service;

public class Customer {
    public static void main(String[] args) {
        // Application Info
        ApplicationConfig application = new ApplicationConfig();
        application.setName("my-rpc");

        // Registry Info
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("localhost:9090");
        registry.setUsername("aaa");
        registry.setPassword("bbb");

        // NOTES: ReferenceConfig holds the connections to registry and providers, please cache it for performance.
        // Refer remote service
        // In case of memory leak, please cache.
        ReferenceConfig<Service> reference = new ReferenceConfig<Service>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(Service.class);
        reference.setVersion("1.0.0");
        // 这里是绕过注册的方式
        reference.setUrl("dubbo://10.20.130.230:9090/pri.robin.rpc.RC.provider.Service");

        // Use xxxService just like a local bean
        // NOTES: Please cache this proxy instance.
        Service myService = reference.get();
    }
}
