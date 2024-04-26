import 'widgets/home_item_widget.dart';
import 'models/home_item_model.dart';
import 'models/home_model.dart';
import 'package:flutter/material.dart';
import 'package:envifrontend/core/app_export.dart';
import 'provider/home_provider.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  HomeScreenState createState() => HomeScreenState();

  static Widget builder(BuildContext context) {
    return ChangeNotifierProvider(
        create: (context) => HomeProvider(), child: HomeScreen());
  }
}

class HomeScreenState extends State<HomeScreen> {
  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(milliseconds: 3000), () {
      NavigatorService.popAndPushNamed(
        AppRoutes.crimedataScreen,
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
            body: Container(
                width: double.maxFinite,
                padding: EdgeInsets.symmetric(horizontal: 16.h, vertical: 22.v),
                child: Column(children: [
                  Text("lbl_envision_ai".tr,
                      style: CustomTextStyles.headlineSmallInterOnPrimary),
                  SizedBox(height: 10.v),
                  Text("msg_smart_crime_detection".tr,
                      style:
                          CustomTextStyles.titleMediumInterSecondaryContainer),
                  SizedBox(height: 26.v),
                  _buildHome(context)
                ])),
            bottomNavigationBar: _buildBottomBar(context)));
  }

  /// Section Widget
  Widget _buildHome(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(right: 4.h),
        child: Consumer<HomeProvider>(builder: (context, provider, child) {
          return ListView.separated(
              physics: NeverScrollableScrollPhysics(),
              shrinkWrap: true,
              separatorBuilder: (context, index) {
                return SizedBox(height: 10.v);
              },
              itemCount: provider.homeModelObj.homeItemList.length,
              itemBuilder: (context, index) {
                HomeItemModel model = provider.homeModelObj.homeItemList[index];
                return HomeItemWidget(model, navigateToCrimedata: () {
                  navigateToCrimedata(context);
                });
              });
        }));
  }

  /// Section Widget
  Widget _buildBottomBar(BuildContext context) {
    return Container(
        decoration: BoxDecoration(
            color: appTheme.whiteA700,
            borderRadius: BorderRadius.circular(24.h),
            boxShadow: [
              BoxShadow(
                  color: theme.colorScheme.primaryContainer,
                  spreadRadius: 2.h,
                  blurRadius: 2.h,
                  offset: Offset(0, 0))
            ]),
        child: CustomImageView(
            imagePath: ImageConstant.imgHome,
            height: 24.adaptSize,
            width: 24.adaptSize,
            margin: EdgeInsets.fromLTRB(165.h, 27.v, 186.h, 27.v)));
  }

  /// Navigates to the crimedataScreen when the action is triggered.
  navigateToCrimedata(BuildContext context) {
    NavigatorService.pushNamed(
      AppRoutes.crimedataScreen,
    );
  }
}
