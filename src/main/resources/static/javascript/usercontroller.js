var app = angular.module( "Demo", [] );
app.controller("createUserController",['$scope', '$http', function($scope, $http){
	$scope.username="";
	$scope.password="";
	$scope.account="";
			$scope.createUser = function (){
				
				if($scope.username.length == 0 || $scope.password.length == 0)
					alert("Fields cannot be empty");
				
				else{
				    $http({
					method:"post",
					url:"/shop/createuser",
					data: {
						"name" : $scope.username,
						"password" : $scope.password,
				}
					}).then(function(response){
						$scope.account=response.data;
					}, function errorCallback(response) { });
				
				}
			}
			
			$scope.validateUser = function(){
				$http({
					method:"get",
					url:"/shop/validateuser",
					params: {
						"name" : $scope.username
				}
					}).then(function(response){
						if(response.data=="" || response.data==null)
							$scope.createUser();
						else
							alert ("User already exists");					
					}, function errorCallback(response) { });
				
				}
			
   }]);