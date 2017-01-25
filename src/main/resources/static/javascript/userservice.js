var myModule = angular.module('myModule3', []);
myModule.service('userService',['$http','$q', function($http,$q) {
	// var defered = $q.defer();
     //var promise = defered.promise;
	var user;
    return {
    	getsession : function(){
    	    $http({
    			method:"get",
    			url:"getusersession"
    			}).then(function(response){
    				console.log(response.data);
    				return response.data;
    				   
    			}, function errorCallback(response) { 
    				 //defered.reject(response);
    			});	
        }
    }


	    
	
  


}]);