// const form = document.querySelector('form');
//
// // add event listener
// form.addEventListener('submit', e => {
//
//     // disable default action
//     e.preventDefault();
//
//     // collect files
//     const files = document.querySelector('[name=file]').files;
//     const formData = new FormData();
//     formData.append('fichier', files[0]);
//
//     // post form data
//     const xhr = new XMLHttpRequest();
//     xhr.responseType = 'json';
//
//     xhr.open("POST", "compte/profile", true);
//     // log response
//     xhr.onload = () => {
//
//         if (xhr.status === 200) {
//
//
//         }
//         console.log(xhr.response);
//     };
//
//
//     xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//     xhr.send(formData);
//
// });