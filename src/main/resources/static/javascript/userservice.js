var myModule = angular.module('myModule3', []);
myModule.service('userService',['$http','$q', function($http,$q) {
	// var defered = $q.defer();
     //var promise = defered.promise;
	var posts;
	var cart;
	this.getPosts = function() {
		  
	       // var deferred = $q.defer();
	 
	       return  $http.get('/shop/getusersession')
	          .then(function(result) {
	            return  result.data;	            
	            //deferred.resolve(posts);
	          }, function(error) {
	            posts = error;
	         //   deferred.reject(error);
	          });
	 
	       // posts = deferred.promise;
	      
	       //return $q.when(posts);
	    };
	    
	    this.getCart = function(usernamev) {
			  
	        var deferred = $q.defer();
	 
	        $http.get('/shop/getcart',{
	        	params:{username: usernamev}
	        })
	          .then(function(result) {
	            cart = result.data;
	            deferred.resolve(cart);
	          }, function(error) {
	            cart = error;
	            deferred.reject(error);
	          });
	 
	        cart = deferred.promise;
	      
	       return $q.when(cart);
	    };
  
}]);
