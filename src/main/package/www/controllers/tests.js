jtsModule.controller('TestsController', function ($scope, TestsFactory, $stateParams, jtshelper, $state, alertService, $filter, CommonServices, displayRestError) {
  $scope.cs = CommonServices;
  $scope.headerStringAdd = "Simple test";
  $scope.saveButtonName = "Execute";
  $scope.savingButtonName = "Executing";
  $scope.saveProgress = false;
  $scope.cancelButtonState = 'utilsAbout';
  $scope.testsResult = undefined;
  $scope.executionStatus = false;
  
  if(jtshelper.testData){
    $scope.testData = jtshelper.testData;
    $scope.testParameters = angular.toJson($scope.testData.testParameters);
  }else{
    $scope.testData = {};
    $scope.testParameters = '{ }';
    $scope.testData.config = {};
    $scope.testData.config.queryPort = 16686;
    $scope.testData.config.agentZipkinThriftPort = 5775;
    $scope.testData.config.agentCompactPort = 6831;
    $scope.testData.config.agentBinaryPort = 6832;
    $scope.testData.config.zipkinCollectorPort = 14268;
  }
  

  $scope.save = function(){
      $scope.testData.testParameters = angular.fromJson(JSON.stringify(eval('('+$scope.testParameters+')')));
      jtshelper.testData = $scope.testData;
      $scope.saveProgress = true;
      $scope.testsResult = undefined;
      $scope.executionStatus = false;
      TestsFactory.simpleTests($scope.testData,function(response) {
        $scope.executionStatus = true;
        alertService.success("Executed successfully");
        $scope.saveProgress = false;
        $scope.testsResult = response;
        $scope.testsResultString = angular.toJson(response, true);
      },function(error){
        $scope.executionStatus = false;
        $scope.testsResult = angular.toJson(error.data, true);
        displayRestError.display(error);
        $scope.saveProgress = false;
      });
    }

});
