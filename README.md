## react-native-mediawesome

A stable Media Player for React Native. Android only for now

Requires react-native >= 0.40.0, for RN support of 0.19.0 - 0.39.0 please use a pre 1.0 version.

### Add it to your project

Run `npm i -S react-native-mediawesome` ou `yarn add react-native-mediawesome`

#### Android

Run `react-native link` to link the react-native-mediawesome library.

Or if you have trouble, make the following additions to the given files manually:

**android/settings.gradle**

```gradle
include ':react-native-mediawesome'
project(':react-native-mediawesome').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-mediawesome/android')
```

**android/app/build.gradle**

```gradle
dependencies {
   ...
   compile project(':react-native-mediawesome')
}
```

**MainApplication.java**

On top, where imports are:

```java
import br.com.universomw8.rnmediawseome.RNMediawesomePackage;
```

Add the `RNMediawesomePackage` class to your list of exported packages.

```java
@Override
protected List<ReactPackage> getPackages() {
    return Arrays.asList(
            new MainReactPackage(),
            new RNMediawesomePackage()
    );
}
```

## TODOS

- [ ] iOS and Windows

---

**MIT Licensed**
