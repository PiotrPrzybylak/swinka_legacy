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
    gulp.watch('../public/*.js', ['scripts']);
});

gulp.task('styles', function() {
    gulp.src('components/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(concat('style.css'))
        .pipe(gulp.dest('../public'))
        .pipe(browserSync.stream());

    gulp.src('components/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(concat('style.css'))
        .pipe(gulp.dest('../../../../resources'))
        .pipe(browserSync.stream());
});

gulp.task('scripts', function(){
  gulp.src('../public/*.js').pipe(gulp.dest('../../../../resources'));
})

gulp.task('default', ['serve']);
