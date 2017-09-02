import React, { PropTypes, Component } from 'react'
import { requireNativeComponent, View, NativeModules } from 'react-native';

class MediawesomePlayer extends Component {
  constructor(props) {
    super(props)
  }

  setNativeProps(nativeProps) {
    this.root.setNativeProps(nativeProps);
  }

  assignRoot(component) {
    this.root = component;
  }

  init(args) {
    this.setNativeProps({init: args})
  }

  render() {
    return (
      <RCTMediawesomePlayer
        ref={this.assignRoot}
        {...this.props}
      />
    )
  }
}

MediawesomePlayer.propTypes = {
  alpha: PropTypes.number,
  ...View.propTypes,
}

const MediawesomeController = NativeModules.MediawesomeController;
const RCTMediawesomePlayer = requireNativeComponent('RCTMediawesomePlayer', MediawesomePlayer, {
  nativeOnly: {
  },
});

module.exports = {
  init: function() {
    return MediawesomeController.init();
  },

  // createPlaylist: function(filePaths) {
  //   return MediawesomeController.createPlaylist(filePaths);
  // },

  // getPlaylist: function(id) {
  //   return MediawesomeController.getPlaylist(id);
  // },

  // destroyPlaylist: function(id) {
  //   return MediawesomeController.destroyPlaylist(id);
  // },

  // getAllPlaylists: function() {
  //   return MediawesomeController.getAllPlaylists();
  // },

  // startPlaylist: function(id) {
  //   return MediawesomeController.startPlaylists(id);
  // },

  // isPlaying: function() {
  //   return MediawesomeController.isPlaying();
  // },

  // hideScreen: function() {
  //   return MediawesomeController.hideScreen()
  // },

  // showScreen: function() {
  //   return MediawesomeController.showScreen();
  // },

  // stopPlayback: function() {
  //   return MediawesomeController.stopPlayback();
  // },

  // getCurrentPlaylist: function() {
  //   return MediawesomeController.getCurrentPlaylist();
  // },

  MediawesomePlayer: MediawesomePlayer,

  MediawesomeController: MediawesomeController
};
