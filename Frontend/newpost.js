document.addEventListener("DOMContentLoaded", function () {
  const postButton = document.getElementById("sendbutton");
  const titleArea = document.getElementById("titlearea");
  const messageArea = document.getElementById("messagee");

  postButton.addEventListener("click", function () {
    const title = titleArea.value;
    const text = messageArea.value;
    const userId = getUserId();
    const token = localStorage.getItem("token");
    const alertx = document.createElement("alert");
    alertx.style.backgroundColor = "#f44336;";

    // Kullanıcının token'ını localStorage'den al
    if (!userId) {
      alert("Kayıt olup giriş yapmalısın.");
      return;
    }

    const postApiUrl = "http://localhost:8080/home/add";

    const postData = {
      title: title,
      text: text,
      user_id: userId,
    };
    console.log("postData:", postData);
    console.log("token:", token);

    fetch(postApiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(postData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Post başarıyla gönderildi.", data);
      })
      .catch((error) => {
        console.error("Post gönderme sırasında hata oluştu: " + error);
      });
  });
});

function getUserId() {
  const token = localStorage.getItem("token");

  if (!token) {
    console.error("Kullanıcı giriş yapmamış.");
    return null;
  }

  const payload = JSON.parse(atob(token.split(".")[1]));
  console.log(payload);

  const userId = payload.sub;

  return userId;
}
