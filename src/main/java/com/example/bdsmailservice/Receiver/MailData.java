package com.example.bdsmailservice.Receiver;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailData   {
    String nameUser;
    String email;
    String[] listLink;

}