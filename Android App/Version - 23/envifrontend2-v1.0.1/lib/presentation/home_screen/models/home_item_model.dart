import '../../../core/app_export.dart';

/// This class is used in the [home_item_widget] screen.
class HomeItemModel {
  HomeItemModel({
    this.natureOfCrime,
    this.riskPercentage,
    this.latitude,
    this.longitudeCounter,
    this.id,
  }) {
    natureOfCrime = natureOfCrime ?? "Nature of Crime : Assualt";
    riskPercentage = riskPercentage ?? "Risk Percentage: 90";
    latitude = latitude ?? "Latitude:  78.3434";
    longitudeCounter = longitudeCounter ?? "Longitude: 72.3434";
    id = id ?? "";
  }

  String? natureOfCrime;

  String? riskPercentage;

  String? latitude;

  String? longitudeCounter;

  String? id;
}
