 var app = angular.module( "myModule4", ['myModule3'] );

   app.controller("testController",['$scope', '$http','userService', 
                                    function($scope, $http, userService){

	   $scope.getOrders = function () {
		    $http({
				method:"get",
				url:"/shop/getorders",
				
				}).then(function(response){
					$scope.cart = response.data.products;
					$scope.totalprice = response.data.totalPrice;
			
				}, function errorCallback(response) { });
	   }
	
   }]);