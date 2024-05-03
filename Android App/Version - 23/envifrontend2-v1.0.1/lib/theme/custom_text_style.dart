import 'package:flutter/material.dart';
import 'package:envifrontend2/core/utils/size_utils.dart';
import 'package:envifrontend2/theme/theme_helper.dart';

/// A collection of pre-defined text styles for customizing text appearance,
/// categorized by different font families and weights.
/// Additionally, this class includes extensions on [TextStyle] to easily apply specific font families to text.

class CustomTextStyles {
  // Headline text style
  static get headlineSmallRobotoWhiteA700 =>
      theme.textTheme.headlineSmall!.roboto.copyWith(
        color: appTheme.whiteA700,
        fontWeight: FontWeight.w800,
      );
  // Title text style
  static get titleMediumInterSecondaryContainer =>
      theme.textTheme.titleMedium!.inter.copyWith(
        color: theme.colorScheme.secondaryContainer,
      );
}

extension on TextStyle {
  TextStyle get roboto {
    return copyWith(
      fontFamily: 'Roboto',
    );
  }

  TextStyle get inter {
    return copyWith(
      fontFamily: 'Inter',
    );
  }
}
