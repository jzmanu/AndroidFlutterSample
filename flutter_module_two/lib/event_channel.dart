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
  MethodChannel _methodChannel;
  String _stringMessage;

  @override
  void initState() {
    super.initState();
    _eventChannel = EventChannel("com.manu.event");
    _methodChannel = MethodChannel("com.manu.startEventChannelActivity");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("BasicMessageChannel"),
        centerTitle: true,
      ),
      body: Column(
        children: [
          Container(
            width: double.infinity,
            margin: EdgeInsets.fromLTRB(8, 8, 8, 0),
            child: RaisedButton(
              onPressed: () {
                _startEventChannelActivity();
              },
              child: Text("Flutter to Android"),
            ),
          ),
        ],
      )
    );
  }

  _startEventChannelActivity(){
    _methodChannel.invokeMethod("startEventChannelActivity");
  }
}
