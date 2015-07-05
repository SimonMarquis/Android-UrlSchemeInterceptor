# Android-UrlScheme
Test and debug URL scheme on Android

### Known issues

 - UserInfo contained in Uri is Android specific (user ids vs web credentials)

### Test

#### adb
```shell
adb shell am start -a android.intent.action.VIEW -d 'test://path/host?param1=value1#hash' -e key1 value1
```

### References

- https://developer.chrome.com/multidevice/android/intents
- https://paul.kinlan.me/deep-app-linking-on-android-and-chrome/
- http://solvedstack.com/questions/how-to-fall-back-to-marketplace-when-android-custom-url-scheme-not-handled
- http://fokkezb.nl/2013/08/26/url-schemes-for-ios-and-android-1/
- http://fokkezb.nl/2013/09/20/url-schemes-for-ios-and-android-2/