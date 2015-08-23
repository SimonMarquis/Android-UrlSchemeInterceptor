# Android-UrlScheme
Test and debug url scheme on Android

## Demo
What's gonna happen?  
✔ App installed → Start the application  
✘ App not installed → Redirect to Play Store  

[Click here](https://simonmarquis.github.io/Android-UrlScheme) to trigger the url scheme!

## How-to
This application can be launched without any modification.  
Use this `adb` command to trigger the default url scheme with some parameters:
```shell
adb shell am start \
    -a android.intent.action.VIEW \
    -d 'test://example.com:8042/over/there/index.html?query=something#refresh' \
    --es string abc \
    --ez boolean true \
    --ei int 123 \
    --el long 123456 \
    --ef float 123.456 \
    --eu uri http://www.google.com \
    --esa stringArray a,b,c
```

If you want to test your custom url scheme, simply add the corresponding entry in [AndroidManifest.xml](app/src/main/AndroidManifest.xml)
```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />

    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />

    <data android:scheme="test" />
    <!--Add your custom scheme here-->
    <!--<data android:scheme="custom" />-->
</intent-filter>
```

## Chrome
The default Chrome implementation is done through [intents](https://developer.chrome.com/multidevice/android/intents).  
Simply wrap the following *intent* into an `<a href="intent">` tag.
```
intent://example.com:8042/over/there/index.html?query=something#refresh#Intent;scheme=test;package=fr.smarquis.urlscheme;action=android.intent.action.VIEW;S.string=abc;B.boolean=true;i.int=123;l.long=123456;f.float=123.456;end
```

The `package=x.y.z;` is mandatory to prompt the user to install the app if it is not already on the device.
