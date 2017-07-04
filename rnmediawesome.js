/**
 * @providesModule react-native-mediawesome
 */

var RNMediawesome = require('react-native').NativeModules.RNMediawesome;

module.exports = {
  createPlaylist: function(filePaths, promise) {
    return RNMediawesome.createPlaylist;
  }

  getPlaylist: function(id, promise) {
    return RNMediawesome.getPlaylist;
  }

  getAllPlaylists: function(promise) {
    return RNMediawesome.getAllPlaylists;
  }

  startPlaylists: function(id, promise) {
    return RNMediawesome.startPlaylists;
  }

  stopPlaylist: function(id, promise) {
    return RNMediawesome.stopPlaylist;
  }
}