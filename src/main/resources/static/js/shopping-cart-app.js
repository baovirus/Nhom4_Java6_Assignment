const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function ($scope, $http) {
	$scope.cart = {
		items: [],

		// Thêm sản phẩm vào giỏ hàng
		add(id) {
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				}).catch(error => {
					console.error("Lỗi khi lấy sản phẩm:", error);
				});
			}
		},

		// Xóa sản phẩm khỏi giỏ hàng
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			if (index !== -1) {
				this.items.splice(index, 1);
				this.saveToLocalStorage();
			}
		},

		// Xóa toàn bộ giỏ hàng
		clear() {
			this.items = [];
			this.saveToLocalStorage();
		},

		// Tính tổng số lượng sản phẩm trong giỏ hàng
		get count() {
			return Array.isArray(this.items) 
				? this.items.reduce((total, item) => total + (item.qty || 0), 0)
				: 0;
		},

		// Tính tổng tiền trong giỏ hàng
		get amount() {
			return Array.isArray(this.items) 
				? this.items.reduce((total, item) => total + (item.qty * (item.price || 0)), 0)
				: 0;
		},

		// Lưu giỏ hàng vào localStorage
		saveToLocalStorage() {
			try {
				localStorage.setItem("cart", JSON.stringify(this.items));
			} catch (error) {
				console.error("Lỗi khi lưu vào localStorage:", error);
			}
		},

		// Tải giỏ hàng từ localStorage
		loadFromLocalStorage() {
			try {
				var json = localStorage.getItem("cart");
				this.items = json ? JSON.parse(json) : [];
				// Đảm bảo dữ liệu sau khi parse là một mảng
				if (!Array.isArray(this.items)) {
					this.items = [];
				}
			} catch (error) {
				console.error("Lỗi khi load từ localStorage:", error);
				this.items = [];
			}
		}
	}

	// Tải giỏ hàng khi trang load
	$scope.cart.loadFromLocalStorage();
	
	//============ THANH TOÁN ==========
	$scope.order = {
		createDate: new Date(),
		address: "",
		purchase(){
			alert("Đặt hàng")
		},
		
	}
});
