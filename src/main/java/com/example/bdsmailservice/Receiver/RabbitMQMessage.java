package com.example.bdsmailservice.Receiver;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class RabbitMQMessage   {
    String nameUser;
    String productTitle;
    String  address;
    Long price;
    String email;
    String phone;

}