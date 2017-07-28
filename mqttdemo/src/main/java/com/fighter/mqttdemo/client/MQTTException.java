package com.fighter.mqttdemo.client;

import com.fighter.mqttdemo.codec.CONNACK;

import java.io.IOException;


public class MQTTException extends IOException {
  public final CONNACK connack;

  public MQTTException(String msg, CONNACK connack) {
    super(msg);
    this.connack = connack;
  }
}
