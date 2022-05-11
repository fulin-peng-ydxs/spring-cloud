package start.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import java.util.UUID;

/**
 * @author PengFuLin
 * @description Stream消息发送
 * @date 2022/5/4 12:09
 */
//设置为消息的发送通道
@EnableBinding(Source.class)
public class SendDemo{
    //消息发送通道
    @Autowired
    private MessageChannel output;

    public String send()
    {
        String serial = UUID.randomUUID().toString();
        //消息发送
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return serial;
    }
}



