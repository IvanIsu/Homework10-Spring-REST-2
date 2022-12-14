angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:6868/app/api/v1';
    $scope.pageIndex = 1;

    $scope.loadProducts = function (pageIndex) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filters ? $scope.filters.min_price : null,
                max_price: $scope.filters ? $scope.filters.max_price : null,
                title_part: $scope.filters ? $scope.filters.title_part : null,
                page: pageIndex
            }


        }).then(function (response) {
            $scope.ProductList = response.data.content;
            $scope.pageIndex = response.data.pageable.pageNumber + 1;
            if(response.data.totalPages == $scope.pageIndex){
                $scope.pageIndex = response.data.pageable.pageNumber;
            }
        })
    };

    $scope.deleteProduct = function (productId) {
        if (confirm('Delete ' + productId) == true) {
            $http.delete(contextPath + '/products/' + productId)
                .then(function () {
                    $scope.loadProducts()
                })
        }
    };

    $scope.addToCart = function (productId){
        $http.get(contextPath + '/products/addToCart/' + productId)
            .then(function (response) {

                $scope.CartList = response.data;
                console.log($scope.CartList)

            })
    };
    $scope.deleteFromCart = function (productId){
        $http.get(contextPath + '/products/deleteFromCart/' + productId)
            .then(function (response) {

                $scope.CartList = response.data;
                console.log($scope.CartList)

            })
    };


    $scope.createProduct = function () {
        $http({
            url: contextPath + '/products',
            method: 'POST',
            data: $scope.new_product
        })
            .then(function () {
                $scope.new_product.title = null;
                $scope.new_product.price = null;
                $scope.loadProducts()
            })
    };

    // $scope.filterProduct = function () {
    //     $http({
    //         url: contextPath + '/products',
    //         method: 'GET',
    //         params: {
    //             min: $scope.filter_product.minValue,
    //             max: $scope.filter_product.maxValue
    //         }
    //     }).then(function (response) {
    //         $scope.ProductList = null;
    //         $scope.ProductList = response.data;
    //     })
    // };

    $scope.loadProducts();
});