/**
 * @providesModule react-native-mediawesome
 */

var RNMediawesome = require('react-native').NativeModules.RNMediawesome;

module.exports = {
  init: function() {
    return RNMediawesome.init();
  },

  createPlaylist: function(filePaths) {
    return RNMediawesome.createPlaylist(filePaths);
  },

  getPlaylist: function(id) {
    return RNMediawesome.getPlaylist(id);
  },

  destroyPlaylist: function(id) {
    return RNMediawesome.destroyPlaylist(id);
  },

  getAllPlaylists: function() {
    return RNMediawesome.getAllPlaylists();
  },

  startPlaylist: function(id) {
    return RNMediawesome.startPlaylists(id);
  },

  stopPlaylist: function(id) {
    return RNMediawesome.stopPlaylist(id);
  },
};