jtsModule.controller('UtilsAboutController', function(alertService,
$scope, $filter, RootFactory, $uibModal, $stateParams, displayRestError) {
  //Ping Status
  $scope.pingStatus = RootFactory.about();
});

jtsModule.controller('UtilsEnvironmentController', function(alertService,
$scope, $filter, UtilsFactory, $uibModal, $stateParams, displayRestError) {
  //Environment details
  $scope.jtsEnvironment = UtilsFactory.environment();
});


jtsModule.controller('UtilsJvmPropertiesController', function(alertService,
$scope, $filter, UtilsFactory, $uibModal, $stateParams, displayRestError) {
  //Properties details
  $scope.jvmProperties = UtilsFactory.properties();
});
