angular.module('templates-app', ['home/home.tpl.html']);

angular.module("home/home.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("home/home.tpl.html",
    "Home page\n" +
    "<md-list flex>\n" +
    " <md-list-item ng-repeat=\"product in products\">\n" +
    "    <p>{{ product.name }}, {{ product.price }}</p>\n" +
    "     <md-button>Buy</md-button>\n" +
    " </md-list-item>\n" +
    "</md-list>\n" +
    "");
}]);
