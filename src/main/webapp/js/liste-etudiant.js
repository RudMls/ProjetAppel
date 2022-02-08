let btnEtudiant = document.querySelectorAll(".btn-etudiant");

btnEtudiant.forEach(element => element.addEventListener("click",evt => {
    let etudiantId = evt.target.value;

    if (etudiantId !== null){
       let xhr = new XMLHttpRequest()

        xhr.open("post", "/compte/profile-etudiant-cherche", true)
        console.log(xhr);
        xhr.onload = function(){
           if(xhr.status == 200){

           }
            xhr.send(evt);
        }
    }
}) )