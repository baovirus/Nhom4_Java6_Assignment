app.controller("authority-ctrl", function($scope, $http, $location) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initialize = function() {
		$http.get("/rest/roles").then(resp => {
			$scope.roles = resp.data;
			console.log($scope.roles);
		})

		$http.get("/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
			console.log($scope.admins);
		})

		$http.get("/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
			console.log($scope.authorities);
		}).catch(error => {
			$location.path("/unauthorized")
		})
	}
	
	$scope.initialize()
	
	// Kiểm tra tài khoản có role này không
	$scope.authrity_of = function(acc, role) {
	    return $scope.authorities.some(auth =>
	        auth.account.username === acc.username && auth.role.id === role.id);
	};

	// Xử lý bật/tắt quyền khi click
	$scope.authrity_change = function(acc, role) {
	    let authority = {
	        account: acc,
	        role: role
	    };

	    // Tìm quyền hiện có
	    let index = $scope.authorities.findIndex(auth =>
	        auth.account.username === acc.username && auth.role.id === role.id);

	    if (index >= 0) {
	        // Nếu có rồi => xóa quyền
	        $http.delete(`/rest/authorities/${$scope.authorities[index].id}`).then(resp => {
	            $scope.authorities.splice(index, 1);
				alert("Xóa quyền thành công!");
	        }).catch(error => {
	            alert("Lỗi xóa quyền!");
	            console.log("Delete Error", error);
	        });
	    } else {
	        // Nếu chưa có => thêm quyền
	        $http.post("/rest/authorities", authority).then(resp => {
	            $scope.authorities.push(resp.data);
				alert("Cấp quyền thành công!");
	        }).catch(error => {
	            alert("Lỗi cấp quyền!");
	            console.log("Post Error", error);
	        });
	    }
	};

});
