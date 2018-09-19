 var app = angular.module( "myModule2", ['ngCookies', 'myModule3'] );

   app.controller("testController",['$scope', '$http','$cookieStore','userService', 
                                    function($scope, $http, $cookieStore, userService){
	   $scope.list = {};
	   $scope.cart = {};
	   $scope.totalprice;
	   $scope.usersession;
					   
	$scope.init = function (){
		var prm;
		var prm2;
		 prm = userService.getPosts();
		 
		        prm.then(function(posts) {		 
		          console.log(posts + " in controller");	
		          $scope.usersession = posts.username;
		        
		          prm2 = userService.getCart($scope.usersession);	          
		         prm2.then(function(cart) {		 
		            console.log(cart + " in controller");	
		  			$scope.cart = cart.products;
		  			$scope.totalprice = cart.totalPrice;
		          });
		        });
		
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
				//$cookieStore.put('myFavorite',$scope.cart);
				//$cookieStore.put('totalPrice',$scope.totalprice);

			}, function errorCallback(response) { });
		
		}    
		
		$scope.order = function(){
			$http({
				method:"post",
				url:"/shop/submitorder"
				}).then(function(response){
				}, function errorCallback(response) { });
		}
		
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
	
	$scope.init();	
   }]);
