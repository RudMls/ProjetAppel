
function loadPlanning(event) {
    event.preventDefault()

    let date = document.getElementById("date")

    const xhr = new XMLHttpRequest()
    xhr.open("POST", "planning", true)

    xhr.onload = () => {

        if (xhr.status === 200) {

        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    xhr.send()
}


document.getElementById("filter").addEventListener("submit", loadPlanning)

document.addEventListener("DOMContentLoaded", () => {
    console.log("test");
    //document.getElementById("zone").addEventListener("keyup", existeMot)
});
