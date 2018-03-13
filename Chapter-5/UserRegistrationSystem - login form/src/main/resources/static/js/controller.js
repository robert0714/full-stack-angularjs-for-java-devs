app.controller('homeController', function($rootScope, $scope, 
		$http, $location, $route){	
	
	 if ($rootScope.authenticated) {
         $location.path("/");
         $scope.error = false;
       } else {
         $location.path("/login");
         $scope.error = true;
       }
});

app.controller('loginController', function($rootScope, $scope, 
		$http, $location, $route){		
	$scope.credentials = {};
	
	$scope.resetForm = function() {
		$scope.credentials = null;
	}

	var authenticate = function(credentials, callback) {
		var headers = $scope.credentials ? {
			authorization : "Basic "
					+ btoa($scope.credentials.username + ":"
							+ $scope.credentials.password)
			} : {};
			
		$http.get('user', {
			headers : headers
		}).then(function(response) {
			if (response.data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}, function() {
			$rootScope.authenticated = false;
			callback && callback();
		});
	}
	
	authenticate();
	
	$scope.loginUser = function() {
	      authenticate($scope.credentials, function() {
	          if ($rootScope.authenticated) {
	            $location.path("/");
	            $scope.error = false;
	          } else {
	            $location.path("/login");
	            $scope.error = true;
	          }
	     });
	};
});

app.controller('logoutController', function($rootScope, $scope, 
		$http, $location, $route){	
	$http.post('logout', {}).finally(function() {
	    $rootScope.authenticated = false;
	    $location.path("/");
	});
});

app.controller('listUserController', function($scope, $http, $location, $route) {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/api/user/'
		}).then(function(response) {
			$scope.users = response.data;
		});

		$scope.editUser = function(userId) {
			$location.path("/update-user/" + userId);
		}

		$scope.deleteUser = function(userId) {
			$http({
				method : 'DELETE',
				url : 'http://localhost:8080/api/user/' + userId
			})
					.then(
							function(response) {
								$location.path("/list-all-users");
								$route.reload();
							});
		}
});

app.controller('registerUserController', function($scope, $http, $location,
		$route) {

	$scope.submitUserForm = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/api/user/',
			data : $scope.user,
		}).then(function(response) {
			$location.path("/list-all-users");
			$route.reload();
		}, function(errResponse) {
			$scope.errorMessage = errResponse.data.errorMessage;
		});
	}

	$scope.resetForm = function() {
		$scope.user = null;
	};
});

app.controller('usersDetailsController',function($scope, $http, $location, $routeParams, $route) {

	$scope.userId = $routeParams.id;

	$http({
		method : 'GET',
		url : 'http://localhost:8080/api/user/' + $scope.userId
	}).then(function(response) {
		$scope.user = response.data;
	});

	$scope.submitUserForm = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/api/user/',
			data : $scope.user,
		})
				.then(
						function(response) {
							$location.path("/list-all-users");
							$route.reload();
						},
						function(errResponse) {
							$scope.errorMessage = "Error while updating User - Error Message: '"
									+ errResponse.data.errorMessage;
						});
	}
});