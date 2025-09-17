
document.addEventListener("DOMContentLoaded", () => {
  

  document.querySelectorAll("a[href^='#']").forEach(link => {
    link.addEventListener("click", event => {
      event.preventDefault();
      const target = document.querySelector(link.getAttribute("href"));
      if (target) {
        target.scrollIntoView({ behavior: "smooth" });
      }
    });
  });

 
  const form = document.querySelector("form");
  if (form) {
    form.addEventListener("submit", event => {
      event.preventDefault();

      
      const msg = document.createElement("p");
      msg.textContent = "✅ Obrigado pelo contato! Logo responderemos sua mensagem.";
      msg.style.color = "#28a745";
      msg.style.marginTop = "15px";
      msg.style.fontWeight = "600";

     
      form.querySelectorAll(".msg-sucesso").forEach(el => el.remove());
      msg.classList.add("msg-sucesso");
      form.appendChild(msg);

      
      form.reset();
    });
  }
});
