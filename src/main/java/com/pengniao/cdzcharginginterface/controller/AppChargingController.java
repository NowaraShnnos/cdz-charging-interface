package com.pengniao.cdzcharginginterface.controller;

import com.alibaba.fastjson.JSON;
import com.pengniao.cdzcharginginterface.service.ChargingService;
import com.pengniao.cdzcharginginterface.service.impl.ChargingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: cdz-charging-interface
 * @description: APP移动端使用（WebSocket）
 * @author: lj
 * @create: 2020-12-25 10:47
 **/

@ServerEndpoint("/webSocket/{sid}")
@Component
public class AppChargingController {

    private static  ChargingService chargingService;
    @Autowired
    public void setChargingService(ChargingService chargingService) {
        AppChargingController.chargingService = chargingService;
    }

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sid") String userName){
        sessionPools.put(userName, session);
        addOnlineCount();
        System.out.println(userName + "加入webSocket！当前人数为" + onlineNum);
//        try {
//            sendMessage(session, "欢迎" + userName + "加入连接！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //发送消息
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
//                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        try {
            sendMessage(session, message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException, InterruptedException {
        //message = "客户端：" + message + ",已收到";
        System.out.println("客户端：" + message + ",已收到");
        String status = message.split("\\|")[0]; //  接收客户端的请求类型 startCharging：请求充电
        String phoneNo = message.split("\\|")[1]; //  电话号码
        String content =  message.split("\\|")[2]; // 接收客户端的请求数据
        System.out.println("status="+status);

        if(status.equals("startCharging")){
//      startCharging|13080903732|{"connectorId":"03710001","platform":1,"detail":{"ConnectorID":"03710001","PhoneNo":"13080903732","DoWay":2,"Platform":1}}
            // TODO 启动充电
            String startChargeSeq = chargingService.saveStartUpChargring(content);
            // 给客户端返回订单编号
            String sendMessage = "1|"+startChargeSeq;  // 1：请求启动充电返回
            sendInfo(phoneNo, sendMessage);
        }else if(status.equals("startChargingRet")){
            // TODO 获取启动充电结果
            while (true){
                System.out.println("phoneNo="+phoneNo);
                Object res = chargingService.getStartUpChargringResult(content);
                System.out.println(res);
                if(res != null){
                    String sendMessage = "2|"+res.toString();  // 2：请求启动充电返回
                    sendInfo(phoneNo,sendMessage);

                }
                break;
               // Thread.sleep(3000);

            }
        }else if(status.equals("chargingDataRet")){
            // TODO 获取充电实时数据
            while (true){
                System.out.println("phoneNo="+phoneNo);
                Object res = chargingService.getChargeData(content);
                //System.out.println(res);
                if(res != null){
                    System.out.println("发送实时数据");
                    String sendMessage = "3|"+res.toString();  // 3：获取充电实时数据返回
                    sendInfo(phoneNo,sendMessage);
                }
                Thread.sleep(3000);
            }
        }else if(status.equals("stopCharging")){
            // TODO 停止充电
            System.out.println("phoneNo="+phoneNo);
            chargingService.saveFinishCharging(content);
            // 给客户端返回订单编号
            String sendMessage = "4|"+"已请求停止充电";  // 4：请求停止充电返回
            sendInfo(phoneNo, sendMessage);
        }
        else if(status.equals("stopChargingRet")){
            // TODO 请求停止充电结果
            System.out.println("phoneNo="+phoneNo);
            while (true){
                Object res = chargingService.getStopChargringResult(content);
                if(res != null){
                    // 给客户端返回停止充电的结果
                    String sendMessage = "5|"+res.toString();  // 5：请求停止充电的结果返回
                    sendInfo(phoneNo, sendMessage);
                    break;
                }
                Thread.sleep(3000);
            }

        }
//        System.out.println(message);
//        System.out.println(phoneNo);
//        for (Session session: sessionPools.values()) {
//            try {
//                sendInfo(phoneNo, message);
////                sendMessage(session, message);
//            } catch(Exception e){
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    //关闭连接时调用
    @OnClose
    public void onClose(@PathParam(value = "sid") String userName){
        System.out.println("###################################################");
        System.out.println("###################################################");
        System.out.println("###################################################");
        System.out.println("###################################################");
        sessionPools.remove(userName);
        subOnlineCount();
        System.out.println(userName + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }

}
