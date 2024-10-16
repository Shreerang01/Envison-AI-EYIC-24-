import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:envifrontend2/core/utils/pref_utils.dart';

class ThemeProvider extends ChangeNotifier {
  themeChange(String themeType) async {
    PrefUtils().setThemeData(themeType);
    notifyListeners();
  }
}
