document.getElementById('next').onclick = function() {
    let slide = document.getElementById('slide');
    slide.appendChild(slide.firstElementChild);
}

document.getElementById('prev').onclick = function() {
    let slide = document.getElementById('slide');
    slide.prepend(slide.lastElementChild);
}