var appName = angular.module('PetClinic', []);
appName.controller("petController", function($scope, $http) {
    'use strict';

    var urlBase = "http://localhost:8080";
    $scope.pet = { name: '', age: '', type: '' };
    $scope.isPetValid = false;
    $http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

    $scope.validatePet = function () {
        return $scope.isPetValid = angular.isString($scope.pet.name) && !isNaN(+$scope.pet.age) && angular.isNumber(+$scope.pet.age) && angular.isString($scope.pet.type) && $scope.pet.type.length !== 0;
    };

    $scope.addPet = function () {
        $scope.validatePet();
    };

    $scope.$on("addStory", function (event) {
        $scope.addPet();
        $scope.$emit("afterPetValidation", $scope.isPetValid);
    });
});
appName.controller("ownerController", function ($scope, $http) {
    'use strict';

    var urlBase = "http://localhost:8080";
    $scope.owner = { name: '', age: '', sex: '' };
    $scope.isOwnerValid = false;
    $http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

    $scope.validateOwner = function () {
        return $scope.isOwnerValid = angular.isString($scope.owner.name) && !isNaN(+$scope.owner.age) && angular.isNumber(+$scope.owner.age) && angular.isString($scope.owner.sex) && $scope.owner.sex.length !== 0;
    };

    $scope.addOwner = function () {
        $scope.validateOwner();
    };

    $scope.$on("addStory", function (event) {
        $scope.addOwner();
        $scope.$emit("afterOwnerValidation", $scope.isOwnerValid);
    });
});
appName.controller("vetController", function($scope, $http){
    'use strict';

    var urlBase = "http://localhost:8080";
    $scope.vet = { name: '', age: '', sex: '' };
    $scope.isVetValid = false;
    $http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

    $scope.validateVet = function () {
        return $scope.isVetValid = angular.isString($scope.vet.name) && !isNaN(+$scope.vet.age) && angular.isNumber(+$scope.vet.age) && angular.isString($scope.vet.sex) && $scope.vet.sex.length !== 0;
    };

    $scope.addVet = function () {
        $scope.validateVet();
    };

    $scope.$on("addStory", function (event) {
        $scope.addVet();
        $scope.$emit("afterVetValidation", $scope.isVetValid);
    });
});

appName.controller("manager", function ($scope) {
    $scope.isEverythingValid = false;

    $scope.addStory = function () {
        $scope.$broadcast("addStory");
        $scope.$on("afterPetValidation", function (event, arg) {
            $scope.isEverythingValid = arg;
            console.log("is pet valid?: " + arg);
        });
        $scope.$on("afterOwnerValidation", function (event, arg) {
            $scope.isEverythingValid = arg;
            console.log("is owner valid?: " + arg);
        });
        $scope.$on("afterVetValidation", function (evenet, arg) {
            $scope.isEverythingValid = arg;
            console.log("is vet valid?: " + arg);
        });
    };
});