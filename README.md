# rn-mediawesome

Android playlist-based media player for React Native

## Adding it to your project

Run `npm i -S rn-mediawesome` or `yarn add rn-mediawesome`

## Installation

Run `react-native link` to link the rn-mediawesome library.

Or if you have trouble, make the following additions to the given files manually:

**android/settings.gradle**

```gradle
include ':rn-mediawesome'
project(':rn-mediawesome').projectDir = new File(rootProject.projectDir, '../node_modules/rn-mediawesome/android')
```

**android/app/build.gradle**

```gradle
dependencies {
   ...
   compile project(':rn-mediawesome')
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

## Usage

Just import,
```javascript
import { MediawesomePlayer, MediawesomeController } from 'rn-mediawesome'
```

render,
```javascript
<MediawesomePlayer width={1920} height={1080} alpha={0} />
```

create playlists by passing a list of locally available files,
```javascript
this.uid = await MediawesomeController.createPlaylist(['/absolute/path/to/file.mp4'])
```

and start playback of a playlist
```javascript
MediawesomeController.startPlaylist(this.uid)
```

Other available controls are:
```javascript
MediawesomeController.showScreen()
MediawesomeController.hideScreen()
MediawesomeController.stopPlayback()
MediawesomeController.isPlaying()
MediawesomeController.getCurrentPlaylist() // returns the current playlist uid
MediawesomeController.getAllPlaylists() // returns a list of uid's
MediawesomeController.getPlaylist(uid) // returns a list of absolute paths, or null
```

All `MediawesomeController` methods are promise-based


## TODO

[ ] tests
[ ] iOs? maybe? wanna help?


**MIT Licensed**
