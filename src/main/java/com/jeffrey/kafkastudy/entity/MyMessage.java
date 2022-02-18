package com.jeffrey.kafkastudy.entity;

import lombok.Data;

import java.util.Date;

/**
 * @version 1.0
 * @Aythor jeffrey 王吉慧
 * @date 2022/2/18 18:11
 * @description
 */

@Data
public class MyMessage {
    private Long id;    //id
    private String msg; //消息
    private Date sendTime;  //时间戳
}
