module.exports = {
    build_dir: "../server/src/main/resources/public",
    app_files: {
        js: ['src/**/*.js'],
        atpl: ['src/app/**/*.tpl.html'],
        html: ['index.html']
    },
    vendor_files: {
        js: [
            'bower_components/angular/angular.min.js',
            'bower_components/angular-material/angular-material.min.js',
            'bower_components/angular-ui-router/release/angular-ui-router.min.js',
            'bower_components/angular-route/angular-route.min.js',
            'bower_components/angular-aria/angular-aria.min.js',
            'bower_components/angular-animate/angular-animate.min.js'
        ],
        css: [
            'bower_components/angular-material/angular-material.css'
        ]
    }
};