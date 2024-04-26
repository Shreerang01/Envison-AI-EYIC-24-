import 'package:envifrontend/widgets/custom_elevated_button.dart';
import 'models/crimedata_model.dart';
import 'package:flutter/material.dart';
import 'package:envifrontend/core/app_export.dart';
import 'provider/crimedata_provider.dart';

class CrimedataScreen extends StatefulWidget {
  const CrimedataScreen({Key? key})
      : super(
          key: key,
        );

  @override
  CrimedataScreenState createState() => CrimedataScreenState();
  static Widget builder(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => CrimedataProvider(),
      child: CrimedataScreen(),
    );
  }
}

class CrimedataScreenState extends State<CrimedataScreen> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Container(
          width: double.maxFinite,
          padding: EdgeInsets.symmetric(
            horizontal: 17.h,
            vertical: 26.v,
          ),
          child: Column(
            children: [
              Align(
                alignment: Alignment.centerRight,
                child: Padding(
                  padding: EdgeInsets.only(right: 94.h),
                  child: Text(
                    "lbl_envision_ai".tr,
                    style: CustomTextStyles.headlineSmallInterOnPrimary,
                  ),
                ),
              ),
              SizedBox(height: 16.v),
              Container(
                height: 245.v,
                width: 338.h,
                decoration: BoxDecoration(
                  color: appTheme.lightBlue100,
                ),
              ),
              SizedBox(height: 14.v),
              Align(
                alignment: Alignment.centerLeft,
                child: Padding(
                  padding: EdgeInsets.only(left: 112.h),
                  child: Text(
                    "lbl_crime_data".tr,
                    style: theme.textTheme.titleLarge,
                  ),
                ),
              ),
              SizedBox(height: 16.v),
              _buildFrameTwo(context),
              SizedBox(height: 16.v),
              CustomElevatedButton(
                text: "lbl_approve".tr,
                margin: EdgeInsets.symmetric(horizontal: 4.h),
              ),
              SizedBox(height: 16.v),
              CustomElevatedButton(
                text: "lbl_disapprove".tr,
                margin: EdgeInsets.only(left: 6.h),
              ),
              SizedBox(height: 5.v),
            ],
          ),
        ),
        bottomNavigationBar: _buildTwo(context),
      ),
    );
  }

  /// Section Widget
  Widget _buildFrameTwo(BuildContext context) {
    return Container(
      width: 335.h,
      margin: EdgeInsets.only(left: 5.h),
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
            "msg_nature_of_crime".tr,
            style: theme.textTheme.titleMedium,
          ),
          SizedBox(height: 4.v),
          Text(
            "msg_risk_percentage".tr,
            style: theme.textTheme.titleMedium,
          ),
          SizedBox(height: 1.v),
          Text(
            "msg_latitude_78_3434".tr,
            style: theme.textTheme.titleMedium,
          ),
          SizedBox(height: 4.v),
          Text(
            "msg_longitude_72_3434".tr,
            style: theme.textTheme.titleMedium,
          ),
        ],
      ),
    );
  }

  /// Section Widget
  Widget _buildTwo(BuildContext context) {
    return Container(
      height: 24.adaptSize,
      width: 24.adaptSize,
      margin: EdgeInsets.only(
        left: 167.h,
        right: 184.h,
        bottom: 27.v,
      ),
      decoration: AppDecoration.outlinePrimaryContainer1.copyWith(
        borderRadius: BorderRadiusStyle.circleBorder24,
      ),
      child: CustomImageView(
        imagePath: ImageConstant.imgHome,
        height: 24.adaptSize,
        width: 24.adaptSize,
        alignment: Alignment.center,
      ),
    );
  }
}
