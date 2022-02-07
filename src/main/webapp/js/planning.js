
function loadPlanning() {

    let date = document.getElementById("date").value
    console.log(date)
        const xhr = new XMLHttpRequest()
        xhr.open("POST", "/compte/planning", true)
        xhr.onload = () => {
            if (xhr.status === 200) {
                let responseJson = JSON.parse(xhr.responseText)
                console.log(responseJson)
            }
        }
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
        xhr.send("date=" + date)
}


document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("rechercher").addEventListener("click", loadPlanning)
});