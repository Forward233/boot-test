package com.boot.demo.rpc.server;

import com.boot.demo.rpc.RpcRequest;
import com.boot.demo.rpc.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author: yhl
 * @DateTime: 2020/6/6 17:06
 * @Description:
 */
public class RpcServerHandler implements Runnable {
    private Socket clientScoket;
    private Map<String, Object> serviceMap;

    public RpcServerHandler(Socket clientScoket, Map<String, Object> serviceMap) {
        this.clientScoket = clientScoket;
        this.serviceMap = serviceMap;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        RpcResponse response = new RpcResponse();
        try {
            ois = new ObjectInputStream(clientScoket.getInputStream());
            oos = new ObjectOutputStream(clientScoket.getOutputStream());
            Object object = ois.readObject();
            if (!(object instanceof RpcRequest)) {
                response.setResult("请求参数错误");
                response.setError(new Exception("请求参数错误"));
                oos.writeObject(response);
                oos.flush();
            } else {
                RpcRequest req = (RpcRequest) object;
                Object service = serviceMap.get(req.getClassName());
                Class<?> clazz = service.getClass();
                Method method = clazz.getMethod(req.getMethodName(), req.getParamTypes());
                Object result = method.invoke(service, req.getParams());

                response.setResult(result);
                oos.writeObject(response);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            try {	// 回收资源，关闭流
                if(ois != null) ois.close();
                if(oos != null) oos.close();
                if(clientScoket != null) clientScoket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
