var exec = cordova.require('cordova/exec');

var NetworkPrinter = function() {
    
};

NetworkPrinter.prototype.print = function(option, onSuccess, onError) {
    var errorCallback = function(obj) {
        onError(obj);
    };

    var successCallback = function(obj) {
        onSuccess(obj);
    };

    exec(successCallback, errorCallback, 'NetworkPrinter', 'print', [option]);
};

if (typeof module != 'undefined' && module.exports) {
    module.exports = NetworkPrinter;
}