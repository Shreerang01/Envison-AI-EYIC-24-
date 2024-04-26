import 'package:flutter/material.dart';
import '../../../core/app_export.dart';
import '../models/crimedata_model.dart';

/// A provider class for the CrimedataScreen.
///
/// This provider manages the state of the CrimedataScreen, including the
/// current crimedataModelObj
class CrimedataProvider extends ChangeNotifier {
  CrimedataModel crimedataModelObj = CrimedataModel();

  @override
  void dispose() {
    super.dispose();
  }
}
