const VEHICLES = [
  {
    title: "Lamborghini Urus",
    subtitle: "SUV de luxo 2024",
    price: "R$ 4.041.375,00",
    img: "fl_progressive,f_webp,q_70.webp",
    specs: ["650cv", "0-100km/h em 3.6s", "Velocidade Máxima: 305km/h"]
  },
  {
    title: "Ferrari SF90",
    subtitle: "Híbrido superesportivo",
    price: "R$ 6.500.000,00",
    img: "1717754-3840x2160-desktop-4k-ferrari-sf90-wallpaper-image.webp",
    specs: ["1000cv", "0-100km/h em 2.5s", "Velocidade Máxima: 340km/h"]
  },
  {
    title: "Rolls-Royce Phantom",
    subtitle: "Sedan de ultra luxo",
    price: "R$ 5.200.000,00",
    img: "geneva-the-rolls-royce-phantom-drophead-convertible-on-display-at-the-77th-international-motor.webp",
    specs: ["V12 563cv", "Interior artesanal", "Conforto inigualável"]
  },
   {
    title: "Koenigsegg Jesko",
    subtitle: "Superesportivo (S)",
    price: "R$ 13.000.000,00",
    img: "koenigsegg-jesko-track-stance-run1gfloysa504l3.webp",
    specs: ["1300cv", "0-100km/h em 2.5s", "Velocidade Máxima: 531km/h"]
  },
   {
    title: "Lamborghini Aventador",
    subtitle: "Esportivo",
    price: "R$ 7.000.000,00",
    img: "398005.webp",
    specs: ["700cv", "0-100km/h em 2.9s", "Velocidade Máxima: 350km/h"]
  },
  {
    title: "Bugatti Tourbillion",
    subtitle: "Superesportivo (S)",
    price: "R$ 4.041.375,00",
    img: "BUGATTI-World-Premiere-Presskit-Images-01a.webp",
    specs: ["650cv", "0-100km/h em 2.0s", "Velocidade Máxima: 445km/h"]
  }
];

const grid = document.getElementById("cardGrid");

function renderCards(list) {
  grid.innerHTML = "";
  list.forEach((car, i) => {
    const card = document.createElement("div");
    card.className = "card";
    card.innerHTML = `
      <img src="${car.img}" alt="${car.title}" />
      <div class="card-body">
        <h4>${car.title}</h4>
        <p>${car.subtitle}</p>
        <div class="price">${car.price}</div>
      </div>
    `;
    card.addEventListener("click", () => openModal(car));
    grid.appendChild(card);
  });
}
renderCards(VEHICLES);

const modal = document.getElementById("modal");
const closeModalBtn = document.getElementById("closeModal");

function openModal(car) {
  document.getElementById("modalImg").src = car.img;
  document.getElementById("modalTitle").textContent = car.title;
  document.getElementById("modalSubtitle").textContent = car.subtitle;
  document.getElementById("modalPrice").textContent = car.price;
  document.getElementById("modalSpecs").innerHTML = car.specs.map(s => `<div>• ${s}</div>`).join("");
  modal.style.display = "flex";
}

closeModalBtn.addEventListener("click", () => {
  modal.style.display = "none";
});
window.addEventListener("click", (e) => {
  if(e.target === modal) modal.style.display = "none";
});

document.getElementById("searchBtn").addEventListener("click", () => {
  const term = document.getElementById("searchInput").value.toLowerCase();
  const filtered = VEHICLES.filter(v =>
    v.title.toLowerCase().includes(term) ||
    v.subtitle.toLowerCase().includes(term)
  );
  renderCards(filtered);
});

const cardsObserver = new IntersectionObserver((entries) => {
  entries.forEach((entry, i) => {
    if (entry.isIntersecting) {
      setTimeout(() => {
        entry.target.classList.add("visible");
      }, i * 150);
    }
  });
}, { threshold: 0.2 });

document.querySelectorAll(".card").forEach(card => {
  cardsObserver.observe(card);
});


document.getElementById("year").textContent = new Date().getFullYear();

const PHONE = "+5581981727505";
const IG_URL = "https://instagram.com/dev.givaldojr";
const EMAIL = "givaldojunior154@gmail.com";

const waLink = `https://wa.me/${PHONE.replace(/\D/g,"")}?text=${encodeURIComponent("Olá, tenho interesse em um veículo de luxo!")}`;
const igLink = IG_URL;
const mailLink = `mailto:${EMAIL}`;

["whBtn","floatWA","modalWA"].forEach(id=>{
  const el = document.getElementById(id);
  if(el) el.href = waLink;
});
["igBtn","modalIG"].forEach(id=>{
  const el = document.getElementById(id);
  if(el) el.href = igLink;
});
["mailBtn","modalEmail"].forEach(id=>{
  const el = document.getElementById(id);
  if(el) el.href = mailLink;
});

const aboutSection = document.querySelector(".about");

window.addEventListener("scroll", () => {
  const rect = aboutSection.getBoundingClientRect();
  if (rect.top < window.innerHeight - 100) {
    aboutSection.classList.add("visible");
  }
});

const contactSection = document.querySelector(".contact");

window.addEventListener("scroll", () => {
  const rect = contactSection.getBoundingClientRect();
  if (rect.top < window.innerHeight - 100) {
    contactSection.classList.add("visible");
  }
});

["contactWA","contactIG","contactMail"].forEach(id=>{
  const el = document.getElementById(id);
  if(el){
    if(id==="contactWA") el.href = waLink;
    if(id==="contactIG") el.href = igLink;
    if(id==="contactMail") el.href = mailLink;
  }
});