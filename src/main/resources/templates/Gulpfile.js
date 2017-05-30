var gulp = require('gulp');
var sass = require('gulp-sass');
var concat = require('gulp-concat');
var browserSync = require('browser-sync').create();

gulp.task('serve', function() {
  browserSync.init({
      proxy: 'fdwl.dev/src/main/resources/templates'
  });

    gulp.watch('components/**/*.scss', ['styles']);
    gulp.watch('**/*.html').on('change', browserSync.reload);
    gulp.watch('*.html').on('change', browserSync.reload);
    // gulp.watch('../public/*.js').on('change', browserSync.reload)
});

gulp.task('styles', function() {
    gulp.src('components/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(concat('style.css'))
        .pipe(gulp.dest('../public/css'))
        .pipe(browserSync.stream());
});

gulp.task('default', ['serve']);
