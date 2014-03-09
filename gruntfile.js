module.exports = function(grunt) {

    basedir = 'src/main/webapp/';

    grunt.initConfig({
        karma: {
            unit: {
                configFile: basedir + 'config/karma.conf.js',
                background: true
            }
        },
        travis: {
            configFile: basedir + 'config/karma.conf.js',
            singleRun: true,
            browsers: ['PhantomJS']
        },
        watch: {
            karma: {
                files: [basedir + 'app/**/*.js', basedir + 'test/unit/**/*.js'],
                tasks: ['karma:unit:run']
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-karma');

    grunt.registerTask('devmode', ['karma:unit', 'watch']);

    grunt.registerTask('test', ['karma:travis']);
};