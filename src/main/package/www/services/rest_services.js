'use strict';

//Utils Services
jtsModule.factory('UtilsFactory', function ($resource, $http, $base64) {
  return $resource('/api/:type', {}, {
    about:   { method: 'GET', isArray: false, params: {type: 'ping'} },
  })
});

//Tests Services
jtsModule.factory('TestsFactory', function ($resource) {
  return $resource('/api/tests/:testType', {}, {
    simpleTests: { method: 'POST',  params: {testType: 'simple'} },
  })
});
