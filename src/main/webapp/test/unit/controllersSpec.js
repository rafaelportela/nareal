'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function() {
  beforeEach(module('narealApp'));

  it('should return activities', inject(function($controller) {
      var scope = {},
          controller = $controller('FeedlistController', {$scope: scope});

      expect(scope.activities.length).toBe(2);
  }));
});
