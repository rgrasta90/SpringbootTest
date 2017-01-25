var myModule = angular.module('myModule3', []);
myModule.service('userService',['$http','$q', function($http,$q) {
	// var defered = $q.defer();
     //var promise = defered.promise;
	var posts;
	this.getPosts = function() {
		  
	        var deferred = $q.defer();
	 
	        $http.get('/shop/getusersession')
	          .then(function(result) {
	            posts = result.data;
	            deferred.resolve(posts);
	          }, function(error) {
	            posts = error;
	            deferred.reject(error);
	          });
	 
	        posts = deferred.promise;
	      
	       return $q.when(posts);
	    };
  
}]);