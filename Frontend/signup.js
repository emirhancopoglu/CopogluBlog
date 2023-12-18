document.addEventListener("DOMContentLoaded", function () {
  const signupForm = document.getElementById("signup-form");
  const signupButton = document.getElementById("signup-button");

  signupButton.addEventListener("click", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const user = {
      username: username,
      password: password,
    };

    fetch("http://localhost:8080/user/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Kullanıcı başarıyla kaydedildi.", data);
        window.location.href = "signupsucces.html";
      })
      .catch((error) => {
        console.error("Kullanıcı kaydı sırasında hata oluştu: " + error);
      });
  });
});
