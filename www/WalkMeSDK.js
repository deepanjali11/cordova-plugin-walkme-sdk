var exec = require('cordova/exec');

exports.start = function (key, secret, success, error) {
    exec(success, error, 'WalkMeSDK', 'start', [key, secret]);
};
