'use strict';

/* jasmine specs for controllers go here */

describe('controllers', function() {

  describe('FeedlistController', function() {
    var scope,
        controller,
        $httpBackend;

    beforeEach(module('narealApp'));

    beforeEach(inject(function(_$httpBackend_, $rootScope, $controller) {
      $httpBackend = _$httpBackend_;
      $httpBackend.expectGET('/services/activities').respond(
        [
          {
            "title" : "first title",
            "description" : "first description"
          }
        ]
      );

      scope = $rootScope.$new();
      controller = $controller('FeedlistController', {$scope: scope});
    }));

    it('should fetch activities', function() {
      expect(scope.activities).toBeUndefined();
      $httpBackend.flush();

      expect(scope.activities).toEqual(
        [
          {
            "title": "first title",
            "description": "first description"
          }
        ]
      );
    });
  });

});
