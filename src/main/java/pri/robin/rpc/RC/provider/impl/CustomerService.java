package pri.robin.rpc.RC.provider.impl;

import pri.robin.rpc.RC.provider.Service;

public class CustomerService implements Service {
    @Override
    public String onReceive(String content) {
        System.out.println("message: " + content);
        return content;
    }
}
