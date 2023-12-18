// uyeler.js

document.addEventListener("DOMContentLoaded", function () {
  // Sayfa yüklendiğinde çalışacak kodlar

  // Kullanıcı listesini çekmek için API'yi çağırın
  fetch("http://localhost:8080/user/GetAll") // API endpoint'inizi güncelleyin
    .then((response) => response.json())
    .then((users) => {
      // API'den gelen kullanıcıları işleyin ve sayfaya ekleyin
      const userList = document.getElementById("userList");

      users.forEach((user) => {
        const listItem = document.createElement("li");
        listItem.textContent = user.username; // Veya kullanıcı adının gösterileceği diğer bir özellik
        userList.appendChild(listItem);
      });
    })
    .catch((error) =>
      console.error("API çağrısı sırasında bir hata oluştu:", error)
    );
});
