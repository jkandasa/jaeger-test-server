'use strict';

// Declare app level module which depends on views, and components
var jtsModule = angular.module('jts',[
  'ui.router',
  'ui.bootstrap',
  'ngResource',
  'ngCookies',
  'ui.bootstrap.datetimepicker',
  'base64',
  'colorpicker.module',
  'ngFileSaver',
  'pascalprecht.translate',
  'ngSanitize',
  'nvd3',
  'patternfly',
  'patternfly.charts',
  'patternfly.select',
  'patternfly.views',
  'patternfly.filters',
  'patternfly.card',
  'patternfly.toolbars',
  'frapontillo.bootstrap-switch',
  'xeditable',
  'angularUtils.directives.dirPagination',
  'frapontillo.bootstrap-duallistbox',
  'angularMoment',
]);

jtsModule.constant("jtshelper", {
    internal:{},
    cfg:{},
    languages:{},
    user:{},
    userSettings:{},
});

jtsModule.config(function($stateProvider, $urlRouterProvider) {
  //For any unmatched url, redirect to /dashboard
  $urlRouterProvider.otherwise('/utils/about');

  $stateProvider

    /* Tests */
    .state('testsSimple', {
      url:"/tests/simple",
      templateUrl: "partials/tests/simple.html?avc=${app.gui.version}",
      controller: "TestsController",
       data: {
        requireLogin: false
      }
    }).state('utilsAbout', {
      url:"/utils/about",
      templateUrl: "partials/utils/about.html?avc=${app.gui.version}",
      controller: "UtilsAboutController",
       data: {
        requireLogin: false
      }
    })
});


//McNavCtrl
jtsModule.controller('JtsNavBarCtrl', function($scope, $location, $translate, $state, jtshelper, CommonServices) {
    $scope.isCollapsed = true;
    $scope.jtshelper = jtshelper;
    jtshelper.cfg.dateFormat = 'MMM d, y h:mm:ss a';
    $scope.$state = $state;
});

jtsModule.run(function ($rootScope, $state, $location, $http, jtshelper, $translate, editableOptions, CommonServices) {
  //Load jtshelper from cookies
  CommonServices.loadjtshelper();

  //update xeditable theme
  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'

});

jtsModule.filter('millSecondsToTimeString', function() {
  return function(millseconds) {
    var seconds = Math.floor(millseconds / 1000);
    var tmpSeconds = seconds % 60;
    var days = Math.floor(seconds / 86400);
    var hours = Math.floor((seconds % 86400) / 3600);
    var minutes = Math.floor(((seconds % 86400) % 3600) / 60);
    var timeString = '';
    if(days > 0){
      timeString += (days > 1) ? (days + " days ") : (days + " day ");
    }
    if(hours >0){
      timeString += (hours > 1) ? (hours + " hours ") : (hours + " hour ");
    }
    if(minutes > 0){
      timeString += (minutes >1) ? (minutes + " minutes ") : (minutes + " minute ");
    }
    if(tmpSeconds >= 0){
      timeString += (tmpSeconds >1) ? (tmpSeconds + " seconds ") : (tmpSeconds + " second ");
    }
    return timeString;
  }
});

jtsModule.filter('byteToMBsizeConvertor', function() {
  return function(sizeInByte) {
    if(sizeInByte < 0){
      return "n/a";
    }
    return Math.floor(sizeInByte /(1024 * 1024)) + " MB";
  }
});

jtsModule.filter('byteToFriendlyConvertor', function() {
  return function(sizeInByte) {
    if(sizeInByte < 0){
      return "n/a";
    }else if((sizeInByte /(1024 * 1024)) > 1024){
      return (sizeInByte /(1024 * 1024 * 1024)).toFixed(2) + " GB";
    }else if((sizeInByte /(1024)) > 1024){
      return (sizeInByte /(1024 * 1024)).toFixed(2) + " MB";
    }else if(sizeInByte > 1024){
    return (sizeInByte /1024).toFixed(2) + " KB";
    }
    return sizeInByte + " Bytes";
  }
});

jtsModule.filter('jtsHtml', function($sce) {
    return function(htmlText) {
       return $sce.trustAsHtml(htmlText);
       //return htmlText
    };
});

jtsModule.filter('slice', function() {
  return function(arr, start, end) {
    return (arr || []).slice(start, end);
  };
});
