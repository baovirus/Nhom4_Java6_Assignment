const app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/account", {
		templateUrl: "/admin/account/index.html",
		controller: "account-ctrl"
	})
	.when("/authorize", {
		templateUrl: "/admin/authority/index.html",
		controller: "authority-ctrl"
	})
	.when("/unauthorize", {
		templateUrl: "/admin/authority/unauthorized.html",
		controller: "authority-ctrl"
	})
	.when("/product", {
		templateUrl: "/admin/product/index.html",
		controller: "product-ctrl"
	})
	.when("/category", {
		templateUrl: "/admin/category/index.html",
		controller: "category-ctrl"
	})
	.when("/order", {
		templateUrl: "/admin/order/index.html",
		controller: "order-ctrl"
	})
	.otherwise({
		template: "<h1 class='text-center'>FPT Polytechnic Administration</h1>"
	})
})