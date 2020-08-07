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
          body: PageWidget()
      ),
      routes: <String,WidgetBuilder>{

      },
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
  }
  @override
  Widget build(BuildContext context) {
    return RaisedButton(
        onPressed: () {
          _startMainActivity();
        },
        child:  Text("Flutter to Android"),
    );
  }

  void _startMainActivity(){
    platform.invokeMethod('startMainActivity').then((value) {
      print("value:startMainActivity");
    }).catchError((e) {
      print(e.message);
    });
  }

}




