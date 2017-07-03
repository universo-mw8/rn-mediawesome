/**
 * @providesModule Mediawesome
 * @flow
 */
'use strict';

var NativeMediawesome = require('NativeModules').Mediawesome;

/**
 * High-level docs for the Mediawesome iOS API can be written here.
 */

var Mediawesome = {
  test: function() {
    NativeMediawesome.test();
  }
};

module.exports = Mediawesome;
