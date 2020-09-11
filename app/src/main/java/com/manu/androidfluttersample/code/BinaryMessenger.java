package com.manu.androidfluttersample.code;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.ByteBuffer;

/**
 * Flutter与Platform端通信的工具
 */
public interface BinaryMessenger {
  /**
   * 发送二进制信息到Flutter
   */
  void send(@NonNull String channel, @Nullable ByteBuffer message);

  /**
   * 发送二进制信息到Flutter，可在Flutter接收到消息回调改发送端
   */
  void send(@NonNull String channel, @Nullable ByteBuffer message, @Nullable BinaryReply callback);

  /**
   * Flutter向主机发送消息时注册要调用的消息处理器
   * 注册会覆盖相同通道标识的之前的任何注册，可以使用空处理程序取消注册
   * 如果没有为特定通道注册任何消息处理器，则该通道上的任何传入消息都将通过发送空回复进行静默处理
   */
  void setMessageHandler(@NonNull String channel, @Nullable BinaryMessageHandler handler);

  /**
   * 消息处理器接口
   */
  interface BinaryMessageHandler {
    /**
     * 处理指定的消息
     * 消息处理器必须向给定的BinaryReply回复单条消息，依次处理所有的回复消息，如果出现异常则回复null
     */
    void onMessage(@Nullable ByteBuffer message, @NonNull BinaryReply reply);
  }

  /**
   * 二进制消息回调
   */
  interface BinaryReply {
    /**
     * 处理指定的回复
     */
    void reply(@Nullable ByteBuffer reply);
  }
}
