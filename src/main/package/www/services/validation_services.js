
jtsModule.factory('validationServices', function() {
  var validationService = {};

  //Validate isNumber
  validationService.isNumber = function (value) {
    if (isNaN(value)) {
      return false;
    }
    return true;
  };

  //Validate isString
  validationService.isString = function (value) {
    if (isNaN(value)) {
      return false;
    }
    return true;
  };

  //Validate isString
  validationService.isEmpty = function (value) {
    if (!value || value === "") {
      return false;
    }
    return true;
  };

 return validationService;

});
