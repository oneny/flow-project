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

    if (checkedFixedExtensions.includes(extension)) {
        inputElement.checked = true;
    }

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

    liElement.append(spanElement, buttonElement);
    $customExtensionList.appendChild(liElement);
});

async function requestAllExtensions() {
    return await fetch('/extensions').then(res => res.json());
}