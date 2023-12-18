document.addEventListener("DOMContentLoaded", function () {
  const contentUl = document.getElementById("contentul");
  const username = localStorage.getItem("username");
  const welcomeMessage = document.getElementById("welcome-message");
  welcomeMessage.style.color = "white";

  if (username) {
    welcomeMessage.textContent = "Hoş geldin, " + `${username}!`;
  }

  fetch("http://localhost:8080/home/AllPost")
    .then((response) => response.json())
    .then((data) => {
      data.forEach((post) => {
        const username = post.user.username;
        const id = post.id;
        const title = post.title;
        const text = post.text;
        const date = post.creationTime;

        const p = document.createElement("p");
        p.style.color = "red";
        const li = document.createElement("li");
        li.style.listStyleType = "none";
        const hr = document.createElement("hr");
        hr.style.color = "black";
        const commentli = document.createElement("li");
        commentli.className = "commentli";

        const postLink = document.createElement("a");
        postLink.classList.add("post-link");
        postLink.style.textDecoration = "none";
        postLink.style.color = "black";
        postLink.style.cursor = "pointer";

        const titleLabel = document.createElement("label");
        titleLabel.style.fontSize = "25px";
        titleLabel.style.padding = "10px";
        titleLabel.style.color = "white";
        titleLabel.style.fontWeight = "bolder";
        titleLabel.style.height = "5%";
        titleLabel.style.width = "100%";
        titleLabel.style.display = "block";
        titleLabel.textContent = `${title}`;

        const textLabel = document.createElement("label");
        textLabel.style.fontStyle = "bold";
        textLabel.style.fontSize = "20px";
        textLabel.style.color = "white";
        textLabel.textContent = `${text}`;

        const usernameLabel = document.createElement("label");
        usernameLabel.style.fontStyle = "italic";
        usernameLabel.style.fontSize = "20px";
        usernameLabel.textContent = `Owner; ${username}`;

        const idLabel = document.createElement("label");
        idLabel.style.fontStyle = "italic";
        idLabel.style.fontSize = "17px";
        idLabel.textContent = ` Post Url : ${id}`;

        const datelabel = document.createElement("label");
        var publishedDate = date;
        var dateObject = new Date(publishedDate);
        var formattedDate =
          dateObject.getFullYear() +
          "-" +
          ("0" + (dateObject.getMonth() + 1)).slice(-2) +
          "-" +
          ("0" + dateObject.getDate()).slice(-2);

        var formattedTime =
          ("0" + dateObject.getHours()).slice(-2) +
          ":" +
          ("0" + dateObject.getMinutes()).slice(-2);

        var formattedDateTime = formattedDate + " " + formattedTime;

        datelabel.textContent = `Published : ${formattedDateTime}`;
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "ｘ";
        deleteButton.style.backgroundColor = "#0088cc";
        deleteButton.style.color = "white";
        deleteButton.style.width = "3%";
        deleteButton.style.height = "2rem";
        deleteButton.style.border = "10px solid rgba(0, 0, 0, 0);";
        deleteButton.style.border = "none";
        deleteButton.style.fontWeight = "bold";
        deleteButton.style.fontFamily = "JetBrains Mono, monospace";
        deleteButton.style.fontSize = "25px";
        deleteButton.addEventListener("mouseover", function () {
          deleteButton.style.backgroundColor = "red"; // Hover durumunda arkaplan rengini değiştirin
        });
        deleteButton.addEventListener("mouseout", function () {
          deleteButton.style.backgroundColor = "#0088cc"; // Mouse ayrıldığında arkaplan rengini geri alın
        });
        deleteButton.addEventListener("click", () => {
          // Post silme işlemini gerçekleştirmek için bir fonksiyon çağır
          deletePost(post.id, post.user.username);
        });

        postLink.appendChild(titleLabel);
        postLink.appendChild(textLabel);

        postLink.appendChild(hr);

        li.appendChild(postLink);

        li.appendChild(document.createElement("br"));
        li.appendChild(document.createElement("br"));
        li.appendChild(usernameLabel);

        li.appendChild(document.createElement("br"));
        li.appendChild(idLabel);
        li.appendChild(document.createElement("br"));
        li.appendChild(datelabel);
        li.appendChild(document.createElement("br"));
        li.appendChild(document.createElement("br"));
        li.appendChild(deleteButton);

        li.appendChild(hr);

        contentUl.appendChild(li);

        postLink.addEventListener("click", () => {
          createNewPage(id, text, title);
        });
      });
    })
    .catch((error) => {
      console.error("Veriler çekilirken bir hata oluştu: " + error);
    });

  checkSession();
});

function checkSession() {
  const username = localStorage.getItem("username");

  if (username) {
    showLogoutButton();
    clickLogoutButton();
  }
}

function showLogoutButton() {
  const logoutButton = document.getElementById("oturumkapatbutton");
  const loginButton = document.getElementById("loginbutton");
  const signupButton = document.getElementById("signupbutton");

  logoutButton.style.display = "inline";
  loginButton.style.display = "none";
  signupButton.style.display = "none";
}

document.addEventListener("click", function (event) {
  if (event.target.classList.contains("post-link")) {
  }
});

function clickLogoutButton() {
  const logoutButton = document.getElementById("oturumkapatbutton");

  logoutButton.addEventListener("click", function () {
    localStorage.removeItem("username");
    localStorage.removeItem("password");
    localStorage.removeItem("token");
    alert("Çıkış başarılı.");
    window.location.href = "http://127.0.0.1:5500/index.html";
  });
}

function deletePost(postId, postUsername) {
  const loggedInUsername = localStorage.getItem("username");

  // Eğer oturum açmış kullanıcı yoksa veya postun sahibi ile oturum açmış kullanıcı aynı değilse yetkilendirme hatası döndür
  if (!loggedInUsername || loggedInUsername !== postUsername) {
    alert("Bu postu silme izniniz yok.");
    return;
  }

  // Silme işlemini gerçekleştir
  fetch(`http://localhost:8080/home/delete/${postId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      // Silme işlemi başarılı ise, sayfayı yeniden yükle veya arayüzü güncelle
      window.location.reload();
    })
    .catch((error) => console.error("Error:", error));
}
