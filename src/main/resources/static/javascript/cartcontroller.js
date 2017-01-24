 var app = angular.module( "myModule2", ['ngCookies', 'myModule3'] );

   app.controller("testController",['$scope', '$http','$cookieStore','userService', 
                                    function($scope, $http, $cookieStore, userService){
	   $scope.list = {};
	   $scope.cart = {};
	   $scope.totalprice;
	   $scope.usersession;
				
	   $scope.getUserSession= function(){
		   $scope.usersession=userService.getSessionPromise(); 
	   }
	$scope.init = function (){
				$scope.getUserSession();
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