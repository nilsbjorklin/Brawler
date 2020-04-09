var app = angular.module('addressAPI', []);
app.controller('addressSearch', function($scope, $http) {

    $scope.search = function() {
            $http.get("https://papapi.se/json/?s="+$scope.street+"+1&token=" + apitokenpapi)
            .then(function(response) {
                console.log(response.data.result);
                $scope.list = response.data.result;
            });
        $scope.showscrolllist = true;
        return false;
    };
});