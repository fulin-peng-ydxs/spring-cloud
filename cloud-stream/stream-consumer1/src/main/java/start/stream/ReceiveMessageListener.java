package start.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

//设置为消息的接收通道
@EnableBinding(Sink.class)
public class ReceiveMessageListener {
    @Value("${server.port}")
    private String serverPort;

    //消息接收监听器
    @StreamListener(Sink.INPUT)
    public void input(Message message) {

        System.out.println("消费者2号，接受："+message.getPayload()+"\t port:"+serverPort);
    }

}