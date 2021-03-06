'use strict';

describe('Controller Tests', function() {

    describe('DoubleRandomResult Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDoubleRandomResult, MockDoubleRandom, MockManager;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDoubleRandomResult = jasmine.createSpy('MockDoubleRandomResult');
            MockDoubleRandom = jasmine.createSpy('MockDoubleRandom');
            MockManager = jasmine.createSpy('MockManager');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DoubleRandomResult': MockDoubleRandomResult,
                'DoubleRandom': MockDoubleRandom,
                'Manager': MockManager
            };
            createController = function() {
                $injector.get('$controller')("DoubleRandomResultDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'drApp:doubleRandomResultUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
