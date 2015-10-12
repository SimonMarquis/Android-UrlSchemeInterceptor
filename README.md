![Android-UrlScheme Interceptor](https://raw.githubusercontent.com/SimonMarquis/Android-UrlSchemeInterceptor/master/art/Play%20Store%20Feature.png "Android-UrlSchemeInterceptor")

## Test it
What's gonna happen?  
✘ App not installed → Redirect to Play Store  
✔ App installed → Start the application or the interceptor  

**<a href="intent://example.com:8042/over/there/index.html?query=something#refresh#Intent;scheme=test;package=fr.smarquis.usi.sample;action=android.intent.action.VIEW;S.string=abc;B.boolean=true;i.int=123;l.long=123456;f.float=123.456;end">Click here</a> to trigger the url scheme!**

## Integration
In your `build.gradle` file:

```groovy
dependencies {
    compile 'fr.smarquis.usi:usi:1.0.0'
}
```

In your `Application` class:

```java
public class YourApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Usi.intercept(this);
    }

}
```

If you want to intercept a custom scheme, simply override the `usi_scheme` string resource:
```xml
<resources>
    <string name="usi_scheme">your-custom-scheme</string>
</resources>
```

Or more complex, add a new IntentFilter to the InterceptorActivity:
```java
<activity android:name="fr.smarquis.usi.InterceptorActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:host="your.project.com" android:scheme="http" />
    </intent-filter>
</activity>
```

**That's it!** UrlSchemeInterceptor will automatically intercept the corresponding scheme.

## Manual trigger
This demo application can be launched without any modification.  
Use this `adb` command to trigger the default url scheme with some parameters:
```shell
adb shell am start \
    -a android.intent.action.VIEW \
    -d 'usi://example.com:8042/over/there/index.html?query=something#refresh' \
    --es extra-string abc \
    --ez extra-boolean true \
    --ei extra-int 123 \
    --el extra-long 123456 \
    --ef extra-float 123.456 \
    --eu extra-uri http://www.google.com \
    --esa extra-stringArray a,b,c
```

## Chrome Intents
The default Chrome implementation is done through [intents](https://developer.chrome.com/multidevice/android/intents).  
Simply wrap the following *intent* into an `<a href="...">` tag.
```
intent://example.com:8042/over/there/index.html?query=something#refresh#Intent;scheme=usi;package=fr.smarquis.usi.sample;action=android.intent.action.VIEW;S.string=abc;B.boolean=true;i.int=123;l.long=123456;f.float=123.456;end
```

The `package=x.y.z;` is mandatory to prompt the user to install the app if it is not already on the device.
