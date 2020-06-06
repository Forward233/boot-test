package com.boot.demo.rpc.client;

import com.boot.demo.rpc.RpcRequest;
import com.boot.demo.rpc.RpcResponse;

import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 12:47
 * @Description:
 */
public class RpcClient {

    public Object start(RpcRequest request, String host, int port) throws Throwable {

        ObjectInputStream oin = null;
        ObjectOutputStream oout = null;
        Socket server = null;
        try {
            server = new Socket(host, port);
            oout = new ObjectOutputStream(server.getOutputStream());
            oout.writeObject(request);
            oout.flush();

            oin = new ObjectInputStream(server.getInputStream());
            Object res = oin.readObject();
            RpcResponse response;
            if (!(res instanceof RpcResponse)) {
                throw new InvalidClassException("返回参数不正确，应当为：" + RpcResponse.class + " 类型");
            } else {
                response = (RpcResponse) res;
            }

            if (response.getError() != null) {
                throw response.getError();
            }
            return response.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(oin != null) {
                oin.close();
            }
            if(oout != null) {
                oout.close();
            }
            if(server != null) {
                server.close();
            }
        }
        return null;
    }

}
