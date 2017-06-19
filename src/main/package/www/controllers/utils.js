jtsModule.controller('UtilsAboutController', function(alertService,
$scope, $filter, UtilsFactory, $uibModal, $stateParams, displayRestError) {

  //GUI page settings
  $scope.headerStringList = "About";

  //Ping Status
  $scope.pingStatus = UtilsFactory.about();

});
