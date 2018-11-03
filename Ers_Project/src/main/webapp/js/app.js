window.onload = function() {
	loadHome();
}


function loadHome(){
	console.log('In Home()');
	
	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'welcome_page.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadHomeInfo();
		}
	}
}

function loadHomeInfo(){
	console.log('in loadHomeInfo()')
	//document.getElementById('login').addEventListener('click', loadLogin);
	document.getElementById('create_account').addEventListener('click', loadCreateAccount);
}

function loadCreateAccountInfo() {
	console.log('In loadCreateAccountInfo()');
	document.getElementById('createAcc-mess').style.display = 'none';
	document.getElementById('email').addEventListener('blur', validateEmail);
	document.getElementById('username').addEventListener('blur',
			validateUsername);
	document.getElementById('createAccBtn').disabled = false;
	document.getElementById('createAccBtn').addEventListener('click',
			createAccount);
}

function loadCreateAccount() {
	console.log('In createAccount()');

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('Get', 'create_account.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadCreateAccountInfo();
		}
	}
}

function createAccount() {
	let user = {
		id : 0,
		username : document.getElementById('username').value,
		password : document.getElementById('password').value,
		firstname : document.getElementById('firstname').value,
		lastname : document.getElementById('lastname').value,
		email : document.getElementById('email').value,
		role_id : 2
	}

	let userJSON = JSON.stringify(user);

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('POST', 'create_account', true);

	// Send the request
	xhr.send(userJSON);

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(userJSON);
			loadLogin();
		}
	}
}

function loadLoginInfo() {
	console.log('in loadLoginInfo()')
	document.getElementById('login_err').style.display = 'none';
	document.getElementById('login').addEventListener('click', login);
}

function loadLogin() {
	console.log('In loadLogin()');

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'login.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadLoginInfo();
		}
	}
}

function login() {
	console.log('Logging in');

	let loginCredJSON = [ document.getElementById('username').value,
			document.getElementById('password').value ]

	let loginCred = JSON.stringify(loginCredJSON);

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('POST', 'login', true);

	// Send the request
	xhr.send(loginCred);

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			if (user) {
				if (user.role_id == 1) {
					loadAdminLogin()
				} else if (user.role_id == 2) {
					loadUserLogin()
				} else {
					document.getElementById('login_err').style.display = 'block';
					document.getElementById('login_err').innerText = 'Invalid credentials! Please enter a valid username/or password';
				}
			}
		}
	}
}

function loadUserLogin() {
	console.log('In userLogin()');

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'user_login.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadUserLoginInfo();
		}
	}
}

function loadUserLoginInfo(){
	console.log('loadUserLoginInfo()')
	document.getElementById('new_ticket').addEventListener('click', loadNewTicket);
	//document.getElementById('all_tickets').addEventListener('click', loadNewTicket);
	document.getElementById('logout').addEventListener('click', logout);

}

function loadNewTicket(){
	console.log('In loadNewTicket()');


	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'new_ticket.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadCreateNewTicket();
		}
	}
}
function loadCreateNewTicket(){
	console.log('In createNewTicket()')
	document.getElementById('addTicket').addEventListener('click', createNewTicket);
	document.getElementById('logout').addEventListener('click', logout);
}

function createNewTicket(){
	console.log('In createNewTicket()')
	let newTicket = [
		document.getElementById('amount').value,
		document.getElementById('description').value,
		'1',//status id - pending
		document.getElementById('type').selectedIndex + 1
	];
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('POST', 'new_ticket', true);
	let newTicketJSON = JSON.stringify(newTicket);
	// Send the request
	xhr.send(newTicketJSON);

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log('success')
			loadUserLogin();
		}
	}
}

function updateTicket(){
	console.log('In updateTicket()')
	
	//Information to be changed in the ticket's database.
}



function loadAdminLogin() {
	console.log('In adminLogin()');
	//document.getElementById('logout').addEventListener('click', logout);
	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'admin_login.view', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
		}
	}
}

function validateUsername() {
	console.log('In validateUsername()');

	// hide message if they begin typing again
	document.getElementById('createAccBtn').disabled = false;
	document.getElementById('createAcc-mess').style.display = 'none';

	let username = document.getElementById('username').value;
	let usernameJSON = JSON.stringify(username);

	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('POST', 'username.validate', true);

	xhr.setRequestHeader('Content-type', 'application/json');
	// Send the request
	xhr.send(usernameJSON);
	console.log(xhr);
	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let username = JSON.parse(xhr.responseText);

			if (!username) {
				document.getElementById('createAcc-mess').style.display = 'block';
				document.getElementById('createAcc-mess').innerText = 'Sorry, username is already in use. Please try another.';
				document.getElementById('createAccBtn').disabled = false;
			}
		}
	}
}

function validateEmail() {
	console.log('In validateEmail()');

	// hide message if they begin typing again
	document.getElementById('createAccBtn').disabled = false;
	document.getElementById('createAcc-mess').style.display = 'none'

	let email = document.getElementById('email').value;
	let emailJSON = JSON.stringify(email);

	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('POST', 'email.validate', true);

	xhr.setRequestHeader('Content-type', 'application/json');

	// Send the request
	xhr.send(emailJSON);

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let email = JSON.parse(xhr.responseText);
			if (!email) {
				document.getElementById('createAcc-mess').style.display = 'block';
				document.getElementById('createAcc-mess').innerHtml = 'Sorry, email is already in use. Please try another.';
				document.getElementById('createAccBtn').disabled = false;
			}
		}
	}
}

function logout() {
	console.log('In logout()');

	// Create new xhr request
	let xhr = new XMLHttpRequest();

	// Open xhr request
	xhr.open('GET', 'logout', true);

	// Send the request
	xhr.send();

	// Wait for the response then perform this action when response is received
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			loadHome();
		}
	}
}