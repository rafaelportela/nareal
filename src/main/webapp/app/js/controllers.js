'use strict';

/* Controllers */

angular.module('narealApp', []).controller('FeedlistController', ['$scope', '$http',
  function($scope, $http) {
    $http.get('/services/activities').success(function(data) {
      $scope.activities = data;
    });
  }
]);