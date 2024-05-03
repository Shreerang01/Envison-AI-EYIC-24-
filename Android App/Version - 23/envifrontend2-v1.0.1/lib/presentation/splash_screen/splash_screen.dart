import 'models/splash_model.dart';
import 'package:flutter/material.dart';
import 'package:envifrontend2/core/app_export.dart';
import 'provider/splash_provider.dart';

class SplashScreen extends StatefulWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  SplashScreenState createState() => SplashScreenState();

  static Widget builder(BuildContext context) {
    return ChangeNotifierProvider(
        create: (context) => SplashProvider(), child: SplashScreen());
  }
}

class SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(milliseconds: 3000), () {
      NavigatorService.popAndPushNamed(
        AppRoutes.homeScreen,
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
            body: SizedBox(
                width: double.maxFinite,
                child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      SizedBox(height: 3.v),
                      Text("lbl_envision_ai".tr,
                          style: theme.textTheme.headlineSmall),
                      SizedBox(height: 13.v),
                      Text("msg_smart_crime_detection".tr,
                          style: CustomTextStyles
                              .titleMediumInterSecondaryContainer)
                    ]))));
  }
}
