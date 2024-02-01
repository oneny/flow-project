const $fixedCheckboxList = document.querySelector('.fixed-checkbox-list');
const $customExtensionSizeSpan = document.querySelector('.custom-extension-size > span');
const $customExtensionList = document.querySelector('.custom-extension-list');
const fixedExtensions = ['bat', 'cmd', 'com', 'cpl', 'exe', 'scr', 'js'];
const $extensionCustomInput = document.querySelector('.extension-custom-input');
const $extensionAddBtn = document.querySelector('.extension-add-btn');

const extensions = await requestAllExtensions();
const checkedFixedExtensions = extensions
    .filter((extension) => fixedExtensions.includes((extension.name)))
    .map((extension) => extension.name);
let notCheckedFixedExtensions = extensions
    .filter((extension) => !fixedExtensions.includes((extension.name)))
    .map((extension) => extension.name);

// 커스텀 확장자 개수
$customExtensionSizeSpan.textContent = notCheckedFixedExtensions.length;
$extensionAddBtn.addEventListener('click', handleAddCustomExtension);

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

    inputElement.addEventListener('click', handleClickFixedExtension(extension));

    liElement.classList.add('fixed-checkbox-item')
    liElement.append(inputElement, spanElement);
    $fixedCheckboxList.appendChild(liElement);
});

// 커스텀 확장자 노출
const renderNotCheckedFixedExtensions = () => {
    while ($customExtensionList.hasChildNodes()) {
        $customExtensionList.removeChild($customExtensionList.firstChild);
    }

    notCheckedFixedExtensions.forEach((extension) => {
        const liElement = document.createElement('li');
        const spanElement = document.createElement('span');
        const buttonElement = document.createElement('button');

        spanElement.textContent = extension;
        buttonElement.textContent = 'X';
        buttonElement.addEventListener('click', handleDeleteCustomExtension(extension));

        liElement.classList.add('custom-extension-item')
        liElement.append(spanElement, buttonElement);
        $customExtensionList.appendChild(liElement);
    });
}
renderNotCheckedFixedExtensions();

async function requestAllExtensions() {
    return await fetch('/extensions')
        .then((res) => res.json())
        .then((data) => data.result);
}

function handleClickFixedExtension(extension) {
    return async (e) => {
        if (!e.target.checked) {
            await requestFetch('DELETE', {name: extension});
            return;
        }

        await requestFetch('POST', {name: extension});
    }
}

async function handleAddCustomExtension() {
    const extension = $extensionCustomInput.value;

    if (!extension || !extension.length) {
        alert('확장자를 입력해주세요');
        return;
    }

    if (fixedExtensions.includes(extension)) {
        alert('고정 확장자에서 파일 확장자를 설정해주세요.');
        return;
    }

    if (notCheckedFixedExtensions.includes(extension)) {
        alert('이미 커스텀 확장자에 추가된 확장자입니다.');
        return;
    }

    if (notCheckedFixedExtensions.length >= 200) {
        alert('커스텀 확장자는 최대 200까지만 등록이 가능합니다.');
        return;
    }

    await requestFetch('POST', {name: extension});
    notCheckedFixedExtensions = [...notCheckedFixedExtensions, extension];
    $customExtensionSizeSpan.textContent = notCheckedFixedExtensions.length;
    renderNotCheckedFixedExtensions();
}

function handleDeleteCustomExtension(extension) {
    return async () => {
        await requestFetch('DELETE', {name: extension});
        notCheckedFixedExtensions = notCheckedFixedExtensions.filter((e) => e !== extension);
        $customExtensionSizeSpan.textContent = notCheckedFixedExtensions.length;
        renderNotCheckedFixedExtensions();
    };
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
