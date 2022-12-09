angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const cartPath = 'http://' + window.location.hostname + ':5555/cart/api/v1';
    const corePath = 'http://' + window.location.hostname + ':5555/core/api/v1';

    $scope.loadCart = function () {
        if ($scope.isUserLoggedIn()) {
            $http({
                    url: cartPath +'/cart',
                    method: 'GET',
                }
            ).then(function (response) {
                $scope.cartList = response.data;
                $scope.total = 0;
                for (let i = 0; i < $scope.cartList.length; i++) {
                    $scope.cartList[i].cost = $scope.cartList[i].price * $scope.cartList[i].count;
                    $scope.total += $scope.cartList[i].cost;
                }
            }).catch(function (err) {
                console.log(err);
                if (err.status===401) {
                    alert("Пожалуйста, авторизуйтесь");
                    $scope.clearUser();
                } else alert(err.data.message)
            });
        } else $scope.cartList = null;
    };


    $scope.addToCart = function (productId, count) {
        if ($scope.isUserLoggedIn()) {

            $http({
                url: cartPath +'/cart',
                method: 'POST',
                params: {
                    productId: productId,
                    count: count
                }

            }).then(function () {
                $scope.loadCart();
            }).catch(function (err) {
                console.log(err);
                if (err.status===401) {
                    alert("Пожалуйста, авторизуйтесь");
                    $scope.clearUser();
                } else alert(err.data.message)
            });
        } else {
            alert("Пожалуйста, авторизуйтесь!");
        }
    };


    $scope.clearUser = function () {
        delete $localStorage.webMarketUser;
        $http.defaults.headers.common.Authorization = '';
        window.location.href = 'http://' + window.location.hostname + ':' + window.location.port + '/market';
    };

    $scope.createOrder = function (){
        if ($scope.isUserLoggedIn()) {

            $http({
                url: corePath + '/order/create',
                method: 'POST',
                params: {
                }

            }).then(function (response) {
                window.location.href = 'http://' + window.location.hostname + ':' + window.location.port + '/market/#!/order_info/'+response.data;
                alert('Заказ создан');
            }).catch(function (err) {

                if (err.status===401) {
                    alert("Пожалуйста, авторизуйтесь");
                    $scope.clearUser();
                } else alert(err.data.message)
            });
        } else {
            alert("Пожалуйста, авторизуйтесь!");
        }
    };


    $scope.loadCart();
});