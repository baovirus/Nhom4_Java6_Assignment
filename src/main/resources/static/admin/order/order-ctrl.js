app.controller("order-ctrl", function ($scope, $http, $location) {
	$scope.items = [];           // Danh sách sản phẩm
	$scope.categories = [];      // Danh sách danh mục
	$scope.form = {};            // Biến chứa thông tin sản phẩm đang chỉnh sửa

	// Hàm khởi tạo dữ liệu
	$scope.initialize = function () {
		// Load sản phẩm
		$http.get("/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate);
			});
		});

		// Load danh mục
		$http.get("/rest/categories").then(resp => {
			$scope.categories = resp.data;
		});
	};

	// Gọi hàm khởi tạo
	$scope.initialize();

	// Reset form
	$scope.reset = function () {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true
		};
	};

	// Hiển thị lên form khi bấm "Edit"
	$scope.edit = function(item){
	    $scope.form = angular.copy(item);
	    console.log("Đã chọn sản phẩm:", $scope.form);

	    // 👉 Chuyển tab qua "Chỉnh sửa"
	    var editTab = new bootstrap.Tab(document.querySelector('a[data-bs-target="#edit"]'));
	    editTab.show();
	};


	// Thêm sản phẩm mới
	$scope.create = function () {
		let item = angular.copy($scope.form);
		$http.post("/rest/products", item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Thêm mới sản phẩm thất bại!");
		});
	};

	// Cập nhật sản phẩm
	$scope.update = function () {
		let item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item).then(resp => {
			let index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật sản phẩm thành công!");
		}).catch(error => {
			console.log("Error", error);
			alert("Cập nhật sản phẩm thất bại!");
		});
	};

	// Xóa sản phẩm
	$scope.delete = function (item) {
		if (confirm("Bạn có chắc muốn xóa sản phẩm này không?")) {
			$http.delete(`/rest/products/${item.id}`).then(resp => {
				let index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xóa sản phẩm thành công!");
			}).catch(error => {
				console.log("Error", error);
				alert("Xóa sản phẩm thất bại!");
			});
		}
	};

	// Xử lý thay đổi hình ảnh
	$scope.imageChanged = function (files) {
		let data = new FormData();
		data.append('file', files.files[0]);
		$http.post('/rest/upload/product_image', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh!");
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
