 var app = angular.module( "myModule4", ['myModule3'] );

   app.controller("testController",['$scope', '$http','userService', 
                                    function($scope, $http, userService){
 
	   $scope.orders = [];
	   
	   $scope.getOrders = function () {
		    $http({
				method:"get",
				url:"/shop/getorders",
				
				}).then(function(response){
					
					console.log(response.data.length)
					var o = response.data.length;
					for(var i=0; i<o;i++){
						$scope.orders[i] = response.data[i];
					}
			
				}, function errorCallback(response) { });
	   }
	
   }]);