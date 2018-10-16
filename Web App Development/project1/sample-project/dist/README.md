# dist
The dist directory is used to store the JavaScript intended for distribution. Many JavaScript
projects (especially large projects) will bundle and compile their Web App to a single, minified
JavaScript file which can be sourced in index.html. Some projects will even generate different
dist files for different target browsers. If this is true of the project, the dist files
should be outputted here, and should be sourced from index.html. In such cases, index.html
would no longer source app/app.js. However, if dist is not used, then index.html should still
source app/app.js. All dist files should use the extension .dist.js or .dist.min.js, if the
dist file has been minified.
