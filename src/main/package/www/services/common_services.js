jtsModule.factory('CommonServices', function($filter, $cookies, jtshelper) {
  var commonService = {};

  //get jtshelper configurations
  commonService.loadjtshelper = function(){
    var jtshelperLocal = $cookies.getObject('jtshelper');
    if(jtshelperLocal){
      jtshelper.cfg = jtshelperLocal.cfg || {};
    }
    return jtshelper;
  };

  //restore store all the configurations locally
  commonService.savejtshelper = function(jtshelperRemote){
    jtshelper.cfg = jtshelperRemote.cfg;
    $cookies.putObject('jtshelper', jtshelper);
  };

  //clear local jtshelper
  commonService.clearjtshelper = function(){
    jtshelper.cfg = {};
  };

  //remove cookies
  commonService.clearCookies = function(){
    var cookies = $cookies.getAll();
    angular.forEach(cookies, function (v, k) {
      $cookies.remove(k);
    });
  };

  //validation methods
  //-----------------------

  //Number validation
  commonService.isNumber = function (value) {
    if (isNaN(value)) {
      return false;
    }
    return true;
  };

  //is contains space validation
  commonService.isContainsSpace = function (value) {
    if(value !== undefined){
      return !value.match(/\s/g);
    }
    return true;
  };

  //is valid JSON
  commonService.isJsonString = function (value) {
    try {
        JSON.stringify(eval('('+value+')'));
        return true;
    } catch(err) {
        return false;
    }
  };

  //guid helper
  var s4 = function() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  //get guid
  commonService.guid = function() {
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
  };

  //get friendly time
  commonService.getTimestampJson = function(timestamp){
    var timestampJson = {};
    if(timestamp % 31536000000  == 0){
      timestampJson.timestamp = timestamp / 31536000000;
      timestampJson.timeConstant = "31536000000";
      timestampJson.timeConstantString = $filter('translate')('YEARS');
    }else if(timestamp % 86400000  == 0){
      timestampJson.timestamp = timestamp / 86400000;
      timestampJson.timeConstant = "86400000";
      timestampJson.timeConstantString = $filter('translate')('DAYS');
    }else if(timestamp % 3600000  == 0){
      timestampJson.timestamp = timestamp / 3600000;
      timestampJson.timeConstant = "3600000";
      timestampJson.timeConstantString = $filter('translate')('Hours');
    }else if(timestamp % 60000  == 0){
      timestampJson.timestamp = timestamp / 60000;
      timestampJson.timeConstant = "60000";
      timestampJson.timeConstantString = $filter('translate')('Minutes');
    }
    return timestampJson;
  };

  //get timestamp
  commonService.getTimestamp = function(timestampJson){
    return timestampJson.timeConstant * timestampJson.timestamp;
  };

  //Update mill seconds to readable value
  commonService.updateReadable = function(milliSeconds, item){
     if(milliSeconds % 86400000  == 0){
      item.readableValue = milliSeconds / 86400000;
      item.timeConstant = "86400000";
    }else if(milliSeconds % 3600000  == 0){
      item.readableValue = milliSeconds / 3600000;
      item.timeConstant = "3600000";
    }else if(milliSeconds % 60000  == 0){
      item.readableValue = milliSeconds / 60000;
      item.timeConstant = "60000";
    }else{
      item.readableValue = milliSeconds / 1000;
      item.timeConstant = "1000";
    }
  };

  //Get readable value to milliseconds
  commonService.getMilliseconds = function(readableValue, timeConstant){
     return readableValue * timeConstant;
  };


 return commonService;

});
