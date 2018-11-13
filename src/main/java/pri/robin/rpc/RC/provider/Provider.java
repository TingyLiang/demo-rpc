package pri.robin.rpc.RC.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import pri.robin.rpc.RC.provider.impl.CustomerService;

public class Provider {

    public static void main(String[] args) {
        Service customerService = new CustomerService();
        // Application Info
        ApplicationConfig application = new ApplicationConfig();
        application.setName("my-rpc");

        // Registry Info, 这里需要设置zk的地址 由于没有zk，此处不能正常运行
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("172.17.171.104:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");
        registry.setTimeout(1000);

        // Protocol
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(12345);
        protocol.setThreads(200);

        // NOTES: ServiceConfig holds the serversocket instance and keeps connections to registry, please cache it for performance.
        // Exporting
        // In case of memory leak, please cache.
        ServiceConfig<Service> services = new ServiceConfig<Service>();
        services.setApplication(application);
        // Use setRegistries() for multi-registry case
        services.setRegistry(registry);
        // Use setProtocols() for multi-protocol case
        services.setProtocol(protocol);
        services.setInterface(Service.class);
        services.setRef(customerService);
        services.setVersion("1.0.0");

        // Local export and register
        services.export();
    }

}
