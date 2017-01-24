var myModule = angular.module('myModule3', []);
myModule.service('userService',['$http', function($http) {
    var userSession;
	var myDataPromise;
	
	  this.getSessionPromise= function (){
		    myDataPromise = this.getSession();
			myDataPromise.then(function(result) {  			       
			       console.log("data.name "+ result);
			       userSession = result;
			    });
		}

  
   this.getSession=function (){
	    $http({
		method:"get",
		url:"getusersession"
		}).then(function(response){
			console.log(response.data);
			return response.data;
			   
		}, function errorCallback(response) { });	
	}
  
   return userSession;

}]);