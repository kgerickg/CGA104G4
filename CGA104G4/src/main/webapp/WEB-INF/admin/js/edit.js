(() => {
  const btn1 = document.querySelector('#btn1');
  const admAcc = document.querySelector('#admAcc');
  const admName = document.querySelector('#admName');
  const oAdmPwd = document.querySelector('#oAdmPwd');
  init();

  function init() {
    btn1.addEventListener('click', edit);

    fetch('getInfo', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
    })
      .then(resp => resp.json())
      .then(body => {
        admAcc.value = body['admAcc'];
        admName.value = body['admName'];
      });

    oAdmPwd.addEventListener('blur', checkOldPassword);
  }
  const data = { admPwd: 'kgerickg' };
  function checkOldPassword() {
    fetch('checkPassword', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    })
      .then(resp => resp.json()
      )
      .then(body => {
        btn1.disabled = !body['successful']
      });

  }

  const msg = document.querySelector('#msg');
  const nAdmPwd = document.querySelector('#nAdmPwd');
  const confirmPassword = document.querySelector('#confirmPassword');
  const currentUser = document.querySelector('#currentUser');

  function edit() {
    if (nAdmPwd.value && confirmPassword.value) {
      if (nAdmPwd.value.length < 6 || nAdmPwd.value.length > 12) {
        msg.textContent = '密碼長度須介於6~12字元';
        return;
      }

      if (confirmPassword.value !== nAdmPwd.value) {
        msg.textContent = '密碼與確認密碼不相符';
        return;
      }
    }

    const admNameLength = admName.value.length;
    if (admNameLength < 1 || admNameLength > 20) {
      msg.textContent = '暱稱長度須介於1~20字元';
      return;
    }

    msg.textContent = '';

    fetch('edit', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        admPwd: nAdmPwd.value,
        admName: admName.value
      }),
    })
      .then(resp => resp.json())
      .then(body => {
        const { successful, message, admName: admNameValue } = body;
        if (successful) {
          msg.className = 'info';
          sessionStorage.setItem('admName', admNameValue);
          currentUser.textContent = admNameValue;
          oAdmPwd.value = '';
          nAdmPwd.value = '';
          confirmPassword.value = '';
          admName.value = admNameValue;
          btn1.disabled = true;
        } else {
          msg.className = 'error';
        }
        msg.textContent = message;
      });
  }
})();