module.exports = function (grunt) {
    /**
     * Load required Grunt tasks. These are installed based on the versions listed
     * in `package.json` when you do `npm install` in this directory.
     */
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-html2js');

    /**
     * Load in our build configuration file.
     */
    var userConfig = require("./build.config.js");

    /**
     * This is the configuration object Grunt uses to give each plugin its
     * instructions.
     */
    var taskConfig = {
        /**
         * The directories to delete when `grunt clean` is executed.
         */
        clean: {
            build: {
                src: '<%= build_dir %>'
            },
            options: {
                force: true
            }
        },

        /**
         * The `copy` task just copies files from A to B. We use it here to copy
         * our project resources (images, fonts, etc.)
         */
        copy: {
            build_app_assets: {
                files: [
                    {
                        src: ['**'],
                        dest: '<%= build_dir %>/',
                        cwd: 'resources',
                        expand: true
                    }
                ]
            },
            build_appjs: {
                files: [
                    {
                        src: ['<%= app_files.js %>'],
                        dest: '<%= build_dir %>/',
                        cwd: '.',
                        expand: true
                    }
                ]
            },
            build_vendorjs: {
                files: [
                    {
                        src: ['<%= vendor_files.js %>'],
                        dest: '<%= build_dir %>/js/',
                        cwd: '.',
                        expand: true,
                        flatten: true
                    }
                ]
            },
            build_vendorcss: {
                files: [
                    {
                        src: ['<%= vendor_files.css %>'],
                        dest: '<%= build_dir %>/css/',
                        cwd: '.',
                        expand: true,
                        flatten: true
                    }
                ]
            },
            build_html: {
                files: [
                    {
                        src: ['<%= app_files.html %>'],
                        dest: '<%= build_dir %>/',
                        cwd: 'src',
                        expand: true
                    }
                ]
            }
        },

        html2js: {
            /**
             * These are the templates from `src/app`.
             */
            app: {
                options: {
                    base: 'src/app'
                },
                src: ['<%= app_files.atpl %>'],
                dest: '<%= build_dir %>/js/templates-app.js'
            }
        }
    };

    grunt.initConfig(grunt.util._.extend(taskConfig, userConfig));
    grunt.registerTask('default', ['build']);

    /**
     * The `build` task gets your app ready to run for development and testing.
     */
    grunt.registerTask('build', [
        'clean', 'html2js', 'copy:build_app_assets', 'copy:build_appjs', 'copy:build_vendorjs',
        'copy:build_vendorcss', 'copy:build_html',]);
};