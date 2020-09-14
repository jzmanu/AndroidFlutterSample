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
  ByteData _imageBytes;

  @override
  void initState() {
    super.initState();
    _basicMessageChannel = BasicMessageChannel("com.manu.image", BinaryCodec());
    _getImage();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("BasicMessageChannel"),
        centerTitle: true,
      ),
      body: Container(
        child: _imageBytes != null ? Image.memory(_imageBytes.buffer.asUint8List()) : Text("Image"),
      ),
    );
  }

  _getImage() async {
    ByteData byteData = Uint8List(1).buffer.asByteData();
    ByteData imageByte = await _basicMessageChannel.send(byteData);
    setState(() {
      _imageBytes = imageByte;
    });
  }
}
