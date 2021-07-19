package com.example.bdsmailservice.Receiver;

import com.example.bdsmailservice.Service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@AllArgsConstructor
public class Receiver {

    private final  MailService mailService;


    public void receiveMessage(byte[] message) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object>  map = mapper.readValue(new String(message), Map.class);


        RabbitMQMessage rabbitMQMessage = new RabbitMQMessage(map.get("nameUser").toString(),
                map.get("productTitle").toString(),
                map.get("address").toString(),
                Long.parseLong(map.get("price").toString()),
                map.get("email").toString(),
                map.get("phone").toString());

        System.out.println(rabbitMQMessage);
        mailService.sendMail(rabbitMQMessage);

    }

}