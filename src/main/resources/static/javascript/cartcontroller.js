 var app = angular.module( "Demo", ['ngCookies'] );

   app.controller("testController",['$scope', '$http',  '$cookieStore', function($scope, $http, $cookieStore){
	   $scope.list = {};
	   $scope.cart = {};
	   $scope.totalprice;
	   
	$scope.init = function (){
				$scope.cart = $cookieStore.get('myFavorite');
				$scope.totalprice =  $cookieStore.get('totalPrice'); 
			    $http({
				method:"get",
				url:"getproducts"
				}).then(function(response){
					$scope.list = response.data;
					   
				}, function errorCallback(response) { });
			
			}

		$scope.addtocart = function (game){			       		       
				    $http({
					method:"post",
					url:"/shop/addtocart",
					data: {
						"name" : game.name,
						"id" : game.id,
						"description" : game.description,
						"price" : game.price
				}
					}).then(function(response){
						$scope.cart = response.data.products;
						$scope.totalprice = response.data.totalPrice;
						$cookieStore.put('myFavorite',$scope.cart);
						$cookieStore.put('totalPrice',$scope.totalprice);
				
					}, function errorCallback(response) { });
				
				}
		$scope.removefromcart = function (game){			       		       
		    $http({
			method:"post",
			url:"/shop/removefromcart",
			data: {
				"name" : game.name,
				"id" : game.id,
				"description" : game.description,
				"price" : game.price
		}
			}).then(function(response){
				$scope.cart = response.data.products;
				$scope.totalprice = response.data.totalPrice;
				$cookieStore.put('myFavorite',$scope.cart);
				$cookieStore.put('totalPrice',$scope.totalprice);

			}, function errorCallback(response) { });
		
		}    

	
	$scope.init();	
   }]);