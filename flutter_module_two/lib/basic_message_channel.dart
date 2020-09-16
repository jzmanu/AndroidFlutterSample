import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BasicMessageChannelPage extends StatefulWidget {
  static final routeName = "/BasicMessageChannelPage";

  @override
  State<StatefulWidget> createState() {
    return BasicMessageState();
  }
}

/// State
class BasicMessageState extends State<BasicMessageChannelPage> {
  BasicMessageChannel _basicMessageChannel;
  MethodChannel _methodChannel;
  String _stringMessage;

  @override
  void initState() {
    super.initState();
    _basicMessageChannel = BasicMessageChannel<ByteData>("com.manu.image", BinaryCodec());
    _methodChannel = MethodChannel("com.manu.startBasicMessageChannelActivity");

    // 获取assets中的图片
    rootBundle.load('images/miao.jpg').then((value) => {
      _sendStringMessage(value)
    });
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
                _startBasicMessageChannelActivity();
              },
              child: Text("Flutter send image to Android"),
            ),
          ),

          Center(
            child: Text(_stringMessage == null ? "default" : _stringMessage),
          )
        ],
      )
    );
  }

  _startBasicMessageChannelActivity(){
    _methodChannel.invokeMethod("startBasicMessageChannelActivity");
  }

  _sendStringMessage(ByteData byteData) async {
    await _basicMessageChannel.send(byteData);
  }
}
