'use strict';

describe('Controller Tests', function() {

    describe('DoubleRandom Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDoubleRandom, MockDoubleRandomResult;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDoubleRandom = jasmine.createSpy('MockDoubleRandom');
            MockDoubleRandomResult = jasmine.createSpy('MockDoubleRandomResult');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DoubleRandom': MockDoubleRandom,
                'DoubleRandomResult': MockDoubleRandomResult
            };
            createController = function() {
                $injector.get('$controller')("DoubleRandomDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'doubleRandomApp:doubleRandomUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
