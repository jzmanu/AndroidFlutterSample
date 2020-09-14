import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BasicMessageWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return BasicMessageState();
  }
}

/// State
class BasicMessageState extends State<BasicMessageWidget> {
  BasicMessageChannel _basicMessageChannel;
  Uint8List _imageBytes;

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
        child: Image.memory(_imageBytes),
      ),
    );
  }

  _getImage() async {
    Uint8List imageByte = await _basicMessageChannel.send("image");
    setState(() {
      _imageBytes = imageByte;
    });
  }
}
