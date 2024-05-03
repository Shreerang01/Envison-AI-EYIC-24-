import 'package:flutter/material.dart';
import '../presentation/splash_screen/splash_screen.dart';
import '../presentation/home_screen/home_screen.dart';
import '../presentation/crimedata_screen/crimedata_screen.dart';
import '../presentation/app_navigation_screen/app_navigation_screen.dart';

class AppRoutes {
  static const String splashScreen = '/splash_screen';

  static const String homeScreen = '/home_screen';

  static const String crimedataScreen = '/crimedata_screen';

  static const String appNavigationScreen = '/app_navigation_screen';

  static const String initialRoute = '/initialRoute';

  static Map<String, WidgetBuilder> get routes => {
        splashScreen: SplashScreen.builder,
        homeScreen: HomeScreen.builder,
        crimedataScreen: CrimedataScreen.builder,
        appNavigationScreen: AppNavigationScreen.builder,
        initialRoute: SplashScreen.builder
      };
}
