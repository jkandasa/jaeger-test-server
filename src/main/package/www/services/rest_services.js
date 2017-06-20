'use strict';

//Root Service
jtsModule.factory('RootFactory', function ($resource, $http, $base64) {
  return $resource('/api/:type', {}, {
    about: { method: 'GET', isArray: false, params: {type: 'ping'} },
  })
});

//Utils Services
jtsModule.factory('UtilsFactory', function ($resource, $http, $base64) {
  return $resource('/api/utils/:type', {}, {
    environment: { method: 'GET', isArray: false, params: {type: 'environment'} },
    properties: { method: 'GET', isArray: false, params: {type: 'properties'} },
  })
});

//Tests Services
jtsModule.factory('TestsFactory', function ($resource) {
  return $resource('/api/tests/:testType', {}, {
    simpleTests: { method: 'POST',  params: {testType: 'simple'} },
  })
});
