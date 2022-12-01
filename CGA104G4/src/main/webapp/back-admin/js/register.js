(() => {
	const btn1 = document.querySelector('#btn1');
	const msg = document.querySelector('#msg');
	const admAcc = document.querySelector('#admAcc');
	const admPwd = document.querySelector('#admPwd');
	const confirmPassword = document.querySelector('#confirmPassword');
	const admName = document.querySelector('#admName');
	const inputs = document.querySelectorAll('input');
	const path = window.location.pathname;
	const webCtx = path.substring(0, path.indexOf('/', 1));
	const url = webCtx + "/admin/register";

	btn1.addEventListener('click', () => {
		const accLength = admAcc.value.length;
		if (accLength < 5 || accLength > 50) {
			msg.textContent = '帳號長度須介於5~50字元';
			return;
		}

		const pwdLength = admPwd.value.length;
		if (pwdLength < 6 || pwdLength > 12) {
			msg.textContent = '密碼長度須介於6~12字元';
			return;
		}

		if (confirmPassword.value !== admPwd.value) {
			msg.textContent = '密碼與確認密碼不相符';
			return;
		}


		const admNameLength = admName.value.length;
		if (admNameLength < 1 || admNameLength > 20) {
			msg.textContent = '暱稱長度須介於1~20字元';
			return;
		}

		msg.textContent = '';
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				admAcc: admAcc.value,
				admPwd: admPwd.value,
				admName: admName.value,
			}),
		})
			.then(resp => resp.json())
			.then(body => {
				const { successful } = body;
				if (successful) {
					for (let input of inputs) {
						input.disabled = true;
					}
					btn1.disabled = true;
					msg.className = 'info';
					msg.textContent = '新增成功';
				} else {
					msg.className = 'error';
					msg.textContent = '新增失敗';
				}
			});
	});

})();