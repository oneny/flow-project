<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="/css/style.css">
    <title>Extension Application</title>
</head>
    <body>
        <div class="extension-container">
            <div class="extension-wrapper">
                <div class="extension-header">
                    파일 확장자 차단
                </div>

                <ul class="extension-content">
                    <li class="extension-item">
                        <strong class="extension-item-title">고정 확장자</strong>
                        <ul class="fixed-checkbox-list"></ul>
                    </li>
                    <li class="extension-item">
                        <strong class="extension-item-title">커스텀 확장자</strong>
                        <ul class="custom-input-list">
                            <li>
                                <input class="extension-custom-input" type="text" placeholder="확장자 입력" />
                                <button class="extension-add-btn">+ 추가</button>
                            </li>
                            <li class="custom-input-item">
                                <div><span>0</span> / 200</div>
                                <ul></ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>

        <script src="/js/main.js" type="application/javascript"></script>
    </body>
</html>