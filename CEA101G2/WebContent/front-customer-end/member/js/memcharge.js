const app = document.getElementById('app');

function addDollar() {
	const span = document.createElement('SPAN');
	span.classList.add('dollar-container');
	span.style.fontSize = 20 + Math.random() * 40 + 'px';
	span.style.left = Math.random() * window.innerWidth + 'px';
	span.style.animationDuration = 1 + Math.random() * 3 + 's';
	
	const icon = document.createElement('I');
	icon.classList.add('fas','fa-dollar-sign');
	icon.style.animationDuration = 1 + Math.random() * 3 + 's';
	
	
	span.appendChild(icon);
	app.appendChild(span);
	
	setTimeout(() => {
		span.remove();
	}, 5000);
}

setInterval(addDollar, 100);