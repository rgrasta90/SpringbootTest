 var app = angular.module('myModule2');

   app.controller("orderController",['$scope', '$http', 
                                    function($scope, $http){
 
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
	
	   $scope.submbitOrder = function () {
		    $http({
				method:"post",
				url:"/shop/submitorder",				
				}).then(function(response){					
					console.log(response.data)
					 if(response.data){
						 alert("Order has been placed succesfully");
					 }
					}			
				, function errorCallback(response) { });
	   }
   }]);