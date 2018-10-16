# lib
The lib directory is used to store third-party libraries not installed through npm such
as CSS and JS that will be sourced from index.html. node_modules should not be sourced from
index.html, so any libraries which are going to be included in index.html should be installed
here. You should install the minified versions of the CSS and JS libraries. If any library has
its own dependencies, the library and its dependencies should be placed inside of their own
directory within lib. This is also a good location to place the project's custom CSS. Remember
that custom CSS should be sourced after all other CSS has been sourced. If the custom CSS
is minified, the minified version should also be kept here, and it should use the extension
.min.css.
