import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text("Flutter Page"),
            centerTitle: true,
          ),
          body: PageWidget()),
      theme: ThemeData.light(),
      routes: <String, WidgetBuilder>{},
    );
  }
}

/// Stateful Widget
class PageWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _PageState();
  }
}

/// State
class _PageState extends State<PageWidget> {
  MethodChannel platform;

  @override
  void initState() {
    super.initState();
    platform = new MethodChannel('com.manu.startMainActivity');

    // 监听Android调用Flutter方法
    platform.setMethodCallHandler(platformCallHandler);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      margin: EdgeInsets.fromLTRB(8, 8, 8, 0),
      child: RaisedButton(
        onPressed: () {
          _startMainActivity();
        },
        child: Text("Flutter to Android"),
      ),
    );
  }

  /// 跳转到原生Activity
  void _startMainActivity() {
    platform.invokeMethod('startMainActivity', 'flutter message').then((value) {
      // 接收返回的数据
      print("value:$value");
    }).catchError((e) {
      print(e.message);
    });
  }

  /// Android调用Dart方法
  Future<dynamic> platformCallHandler(MethodCall call) async{
    switch(call.method){
      case "getName":
        return "name from flutter";
        break;
    }
  }
}




