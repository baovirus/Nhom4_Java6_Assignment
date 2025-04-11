app.controller("account-ctrl", function ($scope, $http) {
	$scope.items = [];     // Danh sách tài khoản
	$scope.form = {};      // Biến chứa thông tin tài khoản đang chỉnh sửa
	
	// Hàm khởi tạo dữ liệu
	$scope.initialize = function () {
		// Load tài khoản
		$http.get("/rest/accounts").then(resp => {
			$scope.items = resp.data;
		});
	};

	// Gọi hàm khởi tạo
	$scope.initialize();

	// Reset form
	$scope.reset = function () {
	    $scope.form = {
	        username: "",
	        password: "",
	        fullname: "",
	        email: "",
	        photo: "user.png"
	    };
	};


	// Hiển thị lên form khi bấm "Edit"
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		console.log("Đã chọn tài khoản:", $scope.form);

		// 👉 Chuyển tab qua "Chỉnh sửa"
		var editTab = new bootstrap.Tab(document.querySelector('a[data-bs-target="#edit"]'));
		editTab.show();
	};

	// Thêm tài khoản mới
	$scope.create = function () {
		let item = angular.copy($scope.form);
		$http.post("/rest/accounts", item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới tài khoản thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Thêm mới tài khoản thất bại!");
		});
	};

	// Cập nhật tài khoản
	$scope.update = function () {
		let item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			let index = $scope.items.findIndex(acc => acc.username == item.username);
			$scope.items[index] = item;
			alert("Cập nhật tài khoản thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Cập nhật tài khoản thất bại!");
		});
	};

	// Xóa tài khoản
	$scope.delete = function (item) {
		if (confirm("Bạn có chắc muốn xóa tài khoản này không?")) {
			$http.delete(`/rest/accounts/${item.username}`).then(resp => {
				let index = $scope.items.findIndex(acc => acc.username == item.username);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xóa tài khoản thành công!");
			}).catch(error => {
				console.log("Error", error);
				alert("Xóa tài khoản thất bại!");
			});
		}
	};

	// Xử lý thay đổi ảnh đại diện
	$scope.imageChanged = function (files) {
		let data = new FormData();
		data.append('file', files.files[0]);
		$http.post('/rest/upload/account', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload ảnh đại diện!");
			console.log("Error", error);
		});
	};

	// Phân trang
	$scope.pager = {
		page: 0,
		size: 8,

		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
		},

		prev() {
			if (this.page > 0) {
				this.page--;
			}
		},

		next() {
			if (this.page < this.count - 1) {
				this.page++;
			}
		},

		last() {
			this.page = this.count - 1;
		}
	};
});
