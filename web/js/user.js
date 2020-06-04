async function checkUsername(page) {
    let response = await fetch(page + document.getElementById("signup_username").value);
    let resJason = await response.json();
    document.getElementById("message_signup_username").innerHTML = resJason.msg_username;
//    document.getElementById("message_signup_username").focus();
}

async function checkPassword(page) {
    let response = await fetch(page + document.getElementById("signup_password").value);
    let resJason = await response.json();
    document.getElementById("message_signup_password").innerHTML = resJason.msg_password;
//    if (resJason.msg_password != null) {
//        document.getElementById("message_signup_password").focus();
//    }
}

// sign up

async function loginUser(action) {
    let username = password = page = "";
    if (action === "signup") {
        username = document.signupForm.username.value;
        password = document.signupForm.password.value;
        page = "ajaxuser?todo=" + action + "&username=" + username + "&password=" + password;
    } else if (action === "login") {
        username = document.loginForm.username.value;
        password = document.loginForm.password.value;
        page = "ajaxuser?todo=" + action + "&username=" + username + "&password=" + password;
    }

// method1
//    let response = await fetch(page);
//    let resJason = await response.json();
//    console.log(resJason);
//    document.getElementById("message_signup").innerHTML = resJason.msg_username;

// POST method
    const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'
        }
    };
    try {
        const response = await fetch(page, settings);
        const resJason = await response.json();
        console.log(resJason);
        // do something
        if (resJason.username != null) {
            document.getElementById("login_signup_page").innerHTML = `
        <h1>Welcome ${resJason.username}!</h1>
            <p class='content'>Thanks for visiting. Make yourself at home. Feel free to browse through 
                our book catalog. When you do, you can read samples from on our site.
                We think our catalog contains some great books, and we 
                hope you like it as much as we do.</p>
            <p class='content'>If you find an book that you like, we hope that you will use this site 
                to order it. Most of the book we carry are not available anywhere else!</p>`;
        }
        if (resJason.msg_login != null) {
            document.getElementById("message_login").innerHTML = resJason.msg_login;
        }
        if (resJason.msg_username != null) {
            document.getElementById("message_signup_username").innerHTML = resJason.msg_username;
        }
        if (resJason.msg_password != null) {
            document.getElementById("message_signup_password").innerHTML = resJason.msg_password;
        }
    } catch (e) {
        console.log(e);
    }
}

// logout
async function logoutUser() {
    let page = "ajaxuser?todo=logout";
    let response = await fetch(page);
    let resJason = await response.json();
    showPage('./account/logout.jsp');
}


export {checkUsername,checkPassword,loginUser,logoutUser};