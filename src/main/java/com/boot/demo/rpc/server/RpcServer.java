package com.boot.demo.rpc.server;

import com.boot.demo.rpc.RpcService;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yhl
 * @DateTime: 2020/6/5 23:31
 * @Description:
 */
public class RpcServer {

    public void start(int port, String clazz) {
        ServerSocket server;
        try {
            server = new ServerSocket(port);
            Map<String, Object> services = getServices(clazz);
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            while (true) {
                Socket client = server.accept();
                RpcServerHandler handler = new RpcServerHandler(client, services);
                executorService.submit(handler);
            }
        } catch (IOException | IllegalAccessException | InstantiationException ioException) {
            ioException.printStackTrace();
        }

    }

    private Map<String, Object> getServices(String clazz) throws IllegalAccessException, InstantiationException {
        Map<String, Object> service = new HashMap<>();
        String[] cla = clazz.split(",");
        List<Class<?>> classes = new ArrayList<>();
        for (String s : cla) {
            List<Class<?>> classList = getClasses(s);
            classes.addAll(classList);
        }

        for (Class<?> aClass : classes) {
            Object obj = aClass.newInstance();
            service.put(aClass.getAnnotation(RpcService.class).value().getName(), obj);
        }
        return service;
    }

    private List<Class<?>> getClasses(String fullClassName) {
        List<Class<?>> classes = new ArrayList<>();
        File dirctory = null;
        try {
            ClassLoader cld = Thread.currentThread().getContextClassLoader();
            if (cld == null) {
                throw new ClassNotFoundException("无法获取到ClassLoa");
            }
            String path = fullClassName.replace('.', '/');
            URL resource = cld.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("无法找打要加载的类资源");
            }

            dirctory = new File(resource.getFile());
            if (dirctory.exists()) {
                File[] files = dirctory.listFiles();
                for (File file : Objects.requireNonNull(files)) {
                    if (file.isFile() && file.getName().endsWith(".class")) {
                        String fileName = file.getName();
                        Class<?> aClass = Class.forName(fullClassName + "." + fileName.substring(0, fileName.length() - 6));
                        if (aClass.getAnnotation(RpcService.class) != null) {
                            classes.add(aClass);
                        }
                    } else if (file.isDirectory()) {
                        List<Class<?>> cla = getClasses(fullClassName + "." + file.getName());
                        if (cla != null && !cla.isEmpty()) {
                            classes.addAll(cla);
                        }
                    }
                }
            } else {
                throw new ClassNotFoundException(fullClassName + "不是一个有效的包名");
            }
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            return classes;
        }
    }
}
