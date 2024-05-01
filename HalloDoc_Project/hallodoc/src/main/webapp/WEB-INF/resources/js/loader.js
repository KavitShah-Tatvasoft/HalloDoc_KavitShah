const loaderContainer = document.querySelector('.loader-container');
const body = document.querySelector('body');

function showLoader() {
  loaderContainer.classList.add('show');
  body.classList.add('loader-bg');
}

function hideLoader() {
  loaderContainer.classList.remove('show');
  body.classList.remove('loader-bg');
}

// Call showLoader() to show the loader and set the background to opaque black
// Call hideLoader() to hide the loader and remove the opaque black background
