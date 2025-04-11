app.controller("category-ctrl", function ($scope, $http) {
	$scope.items = [];   // Danh sách danh mục
	$scope.form = {};    // Danh mục đang chỉnh sửa

	// Hàm khởi tạo
	$scope.initialize = function () {
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
		});
	};

	// Gọi hàm khởi tạo
	$scope.initialize();

	// Reset form
	$scope.reset = function () {
		$scope.form = {
			name: ""
		};
	};

	// Hiển thị form chỉnh sửa
	$scope.edit = function (item) {
		$scope.form = angular.copy(item);
		var editTab = new bootstrap.Tab(document.querySelector('a[data-bs-target="#edit"]'));
		editTab.show();
	};

	// Thêm mới
	$scope.create = function () {
		let item = angular.copy($scope.form);
		$http.post("/rest/categories", item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm danh mục thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Thêm danh mục thất bại!");
		});
	};

	// Cập nhật
	$scope.update = function () {
		let item = angular.copy($scope.form);
		$http.put(`/rest/categories/${item.id}`, item).then(resp => {
			let index = $scope.items.findIndex(c => c.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật danh mục thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Cập nhật danh mục thất bại!");
		});
	};

	// Xóa
	$scope.delete = function (item) {
		if (confirm("Bạn có chắc muốn xóa danh mục này không?")) {
			$http.delete(`/rest/categories/${item.id}`).then(resp => {
				let index = $scope.items.findIndex(c => c.id == item.id);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xóa danh mục thành công!");
			}).catch(error => {
				console.log("Error", error);
				alert("Xóa danh mục thất bại!");
			});
		}
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
			if (this.page > 0) this.page--;
		},
		next() {
			if (this.page < this.count - 1) this.page++;
		},
		last() {
			this.page = this.count - 1;
		}
	};
});
