const $fixedCheckboxList = document.querySelector('.fixed-checkbox-list');
const $customExtensionSizeSpan = document.querySelector('.custom-extension-size > span');
const $customExtensionList = document.querySelector('.custom-extension-list');
const fixedExtensions = ['bat', 'cmd', 'com', 'cpl', 'exe', 'scr', 'js'];

const extensions = await requestAllExtensions();
const checkedFixedExtensions = extensions
    .filter((extension) => fixedExtensions.includes((extension.name)))
    .map((extension) => extension.name);
const notCheckedFixedExtensions = extensions
    .filter((extension) => !fixedExtensions.includes((extension.name)))
    .map((extension) => extension.name);

// 커스텀 확장자 개수
$customExtensionSizeSpan.textContent = notCheckedFixedExtensions.length;

// 고정 확장자 노출
fixedExtensions.forEach((extension) => {
    const liElement = document.createElement('li');
    const inputElement = document.createElement('input')
    const spanElement = document.createElement('span');

    inputElement.type = 'checkbox';
    spanElement.textContent = extension;
    console.log(extension);

    if (checkedFixedExtensions.includes(extension)) {
        inputElement.checked = true;
    }

    inputElement.addEventListener('click', handleClickFixedExtension(extension));

    liElement.classList.add('fixed-checkbox-item')
    liElement.append(inputElement, spanElement);
    $fixedCheckboxList.appendChild(liElement);
});

// 커스텀 확장자 노출
notCheckedFixedExtensions.forEach((extension) => {
    const liElement = document.createElement('li');
    const spanElement = document.createElement('span');
    const buttonElement = document.createElement('button');

    spanElement.textContent = extension;
    buttonElement.textContent = 'X';

    liElement.classList.add('custom-extension-item')
    liElement.append(spanElement, buttonElement);
    $customExtensionList.appendChild(liElement);
});

async function requestAllExtensions() {
    return await fetch('/extensions')
        .then((res) => res.json())
        .then((data) => data.result);
}

function handleClickFixedExtension(extension) {
    return async (e) => {
        console.log(e.target.checked);
        console.log(extension)
        if (!e.target.checked) {
            await requestFetch('DELETE', { name: extension });
            return;
        }

        await requestFetch('POST', { name: extension });
    }
}

async function requestFetch(method, body) {
    await fetch('/extensions', {
        method,
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then((res) => res.json())
        .then((data) => alert(data.message))
        .catch((error) => alert(error.message));
}
