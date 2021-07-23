package com.example.bdsmailservice.Receiver;

import com.example.bdsmailservice.Service.MailService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@AllArgsConstructor
public class Receiver {

    private final  MailService mailService;

    public void receiveMessage(byte[] message) throws IOException {

        JSONObject json = new JSONObject(new String(message));
        JSONArray arr = json.getJSONArray("listId");
        String[] listId = new String[arr.length()];
        for(int i = 0; i < json.getJSONArray("listId").length(); i++){
            //listId.add(arr.getLong(i));
            //listId.add(arr.getLong(i));
            listId[i] = "http://localhost:3000/productdetail/" + arr.getLong(i);
        }

        MailData mailData = new MailData(
                json.getString("nameUser"),
                json.getString("email"),
                listId
        );

        mailService.sendMail(mailData);

    }

}