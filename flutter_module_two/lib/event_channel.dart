import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class EventChannelPage extends StatefulWidget {
  static final routeName = "/EventChannelPage";

  @override
  State<StatefulWidget> createState() {
    return EventState();
  }
}

/// State
class EventState extends State<EventChannelPage> {
  EventChannel _eventChannel;
  String _stringMessage;
  StreamSubscription _streamSubscription;

  @override
  void initState() {
    super.initState();
    _eventChannel = EventChannel("com.manu.event");
    // 监听Event事件
    _streamSubscription =
        _eventChannel.receiveBroadcastStream().listen((event) {
      setState(() {
        _stringMessage = event;
      });
    }, onError: (error) {
      print("event error$error");
    });
  }

  @override
  void dispose() {
    super.dispose();
    if (_streamSubscription != null) {
      _streamSubscription.cancel();
      _streamSubscription = null;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("EventChannel"),
          centerTitle: true,
        ),
        body: Center(
          child: Text(_stringMessage == null ? "default" : _stringMessage),
        ));
  }
}