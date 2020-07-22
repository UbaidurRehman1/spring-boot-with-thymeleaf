function searchByEmployees() {
    // Declare variables
    let i, tds, txtValue;
    let nameValue, departmentValue, emailValue;

    //get the inputs from the user (name, department and email) (in search section)
    let table = document.getElementById("table1");
    let tr = table.getElementsByTagName("tr");
    let name = document.getElementById("name").value.toUpperCase();
    let department = document.getElementById("department").value.toUpperCase();
    let email = document.getElementById("email").value.toUpperCase();


    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        tds = tr[i].getElementsByTagName("td");
        if (tds.length > 0) {
            nameValue = tds[0].innerText;
            departmentValue = tds[2].innerText;
            emailValue = tds[4].innerText;
            txtValue = nameValue + emailValue;
            txtValue = txtValue.toUpperCase();
            if (department.includes("ALL")) {
                if (txtValue.includes(name) && txtValue.includes(email)) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            } else {
                if (txtValue.includes(name) && txtValue.includes(email) && department.includes(departmentValue.toUpperCase())) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}


function searchByAdmin() {
    // Declare variables
    let i, tds, txtValue;
    let loginValue, nameValue, manOfMonthValue;

    //get the user input from the search section (login, name, manOfMonth)
    let table = document.getElementById("table2");
    let tr = table.getElementsByTagName("tr");
    let login = document.getElementById("login").value.toUpperCase();
    let name = document.getElementById("name").value.toUpperCase();
    let manOfMonth = document.getElementById("manOfMonth").value.toUpperCase();


    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        tds = tr[i].getElementsByTagName("td");
        if (tds.length > 0) {
            loginValue = tds[0].innerText;
            nameValue = tds[1].innerText;
            manOfMonthValue = tds[3].innerText;
            txtValue = loginValue + nameValue;
            txtValue = txtValue.toUpperCase();
            if (manOfMonth.includes("ALL")) {
                if (txtValue.includes(login) && txtValue.includes(name)) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            } else {
                if (txtValue.includes(login) && txtValue.includes(name) && manOfMonthValue.includes("YES")) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
}