document.addEventListener("DOMContentLoaded", function () {
  const headers = {
    "Content-Type": "application/json",
    Origin: "http://127.0.0.1:5500",
    "Access-Control-Allow-Methods": "POST",
    "Access-Control-Allow-Headers": "Content-Type, Authorization , Origin",
  };
  var loginForm = document.getElementById("login-formm");

  loginForm.addEventListener("submit", function (event) {
    event.preventDefault();

    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    fetch("http://localhost:8080/user/login", {
      headers: headers,
      method: "POST",
      body: JSON.stringify({ username: username, password: password }),
    })
      .then(function (response) {
        if (response.status === 200) {
          console.log(response);
          return response.json();
        } else {
          alert("Kullanıcı adı veya şifre yanlış.");
          throw new Error("Authentication failed");
        }
      })
      .then(function (userData) {
        var token = userData.token;
        localStorage.setItem("username", username);
        localStorage.setItem("token", token);

        window.location = "http://127.0.0.1:5500/loginsucces.html";
      })
      .catch(function (error) {
        console.error("İstek hatası:", error);
      });
  });
});
