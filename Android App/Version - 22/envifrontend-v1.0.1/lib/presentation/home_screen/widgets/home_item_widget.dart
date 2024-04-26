import '../models/home_item_model.dart';
import 'package:flutter/material.dart';
import 'package:envifrontend/core/app_export.dart';

// ignore: must_be_immutable
class HomeItemWidget extends StatelessWidget {
  HomeItemWidget(
    this.homeItemModelObj, {
    Key? key,
    this.navigateToCrimedata,
  }) : super(
          key: key,
        );

  HomeItemModel homeItemModelObj;

  VoidCallback? navigateToCrimedata;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        navigateToCrimedata?.call();
      },
      child: Container(
        padding: EdgeInsets.symmetric(
          horizontal: 22.h,
          vertical: 5.v,
        ),
        decoration: AppDecoration.outlinePrimaryContainer.copyWith(
          borderRadius: BorderRadiusStyle.roundedBorder14,
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            SizedBox(height: 3.v),
            Text(
              homeItemModelObj.natureOfCrime!,
              style: theme.textTheme.titleMedium,
            ),
            SizedBox(height: 4.v),
            Text(
              homeItemModelObj.riskPercentage!,
              style: theme.textTheme.titleMedium,
            ),
            SizedBox(height: 1.v),
            Text(
              homeItemModelObj.latitude!,
              style: theme.textTheme.titleMedium,
            ),
            SizedBox(height: 4.v),
            Text(
              homeItemModelObj.longitudeCounter!,
              style: theme.textTheme.titleMedium,
            ),
          ],
        ),
      ),
    );
  }
}
