function pageLoad() {
    fillStatusBars();
}

function fillStatusBars() {
    var bars = document.querySelectorAll('.action-card__progress-bar');
    for (var i = 0; i < bars.length; i++) {
        var filling = bars[i].querySelector('.progress-bar__progress');
        var current = parseInt(bars[i].getAttribute('data-current'));
        var target = parseInt(bars[i].getAttribute('data-target'));
        var progress = (current / target) * 100;
        filling.style.width = progress + "%";
    }
}

document.addEventListener('DOMContentLoaded', pageLoad, false);
