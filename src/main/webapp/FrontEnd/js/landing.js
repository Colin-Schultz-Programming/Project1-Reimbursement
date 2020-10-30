 
function closeLoginForm() {
    document.getElementById("login").style.display = "none";
}
  
  function closeRegisterForm() {
    document.getElementById("register").style.display = "none";
  }

  function openLoginForm() {
    closeRegisterForm();
    document.getElementById("login").style.display = "block";
  }

  function openRegisterForm() {
    closeLoginForm();
    document.getElementById("register").style.display = "block";
  }
