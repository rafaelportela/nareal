'use strict';

/* Controllers */

angular.module('narealApp', []).controller('FeedlistController', ['$scope', function($scope) {
    $scope.activities = [
        {
            'title': 'First Activity',
            'content' : 'I created my first activity, and I am so excited! ;)'
        },
        {
            'title': 'Second Activity',
            'content' : 'Yeah.. now I am quite familiar with that..'
        }
    ];
}]);