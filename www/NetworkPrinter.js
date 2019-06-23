cordova.define("com.smk.networkprinter.NetworkPrinter", function (require, exports, module) {
    var exec = cordova.require('cordova/exec');

    var NetworkPrinter = {
        print: function (option, onSuccess, onError) {
            var errorCallback = function (obj) {
                onError(obj);
            };

            var successCallback = function (obj) {
                onSuccess(obj);
            };

            exec(successCallback, errorCallback, 'NetworkPrinter', 'print', [option]);
        }
    };
    module.exports = NetworkPrinter;
});
