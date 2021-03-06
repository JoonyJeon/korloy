<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String mode = request.getParameter("mode");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <title>JK-Web3D :: JIKYUNG SOLUTEC CO., LTD.</title>
    <script type="text/javascript">
        var agent = navigator.userAgent, match;

        if ((match = agent.match(/MSIE ([0-9]+)/)) || (match = agent.match(/Trident.*rv:([0-9]+)/))) app = 1;
        else if (match = agent.match(/Edge\/([0-9]+)/)) app = 2;
        else if (match = agent.match(/Chrome\/([0-9]+)/)) app = 3;
        else if (match = agent.match(/Firefox\/([0-9]+)/)) app = 4;
        else if (match = agent.match(/Safari\/([0-9]+)/)) app = 5;
        else if ((match = agent.match(/OPR\/([0-9]+)/)) || (match = agent.match(/Opera\/([0-9]+)/))) app = 6;
        else app = 0;

        if (app === 1)
            document.write('<link rel="stylesheet" href="VIZWeb3D/CSS/VIZWeb3D_IE.css">');
        else
            document.write('<link rel="stylesheet" href="VIZWeb3D/CSS/VIZWeb3D.css?ver3">');
    </script>
    <script src="VIZWeb3D/ThirdParty/jquery/jquery-3.5.0.js"></script>
    <link rel="stylesheet" href="VIZWeb3D/ThirdParty/jstree/themes/default/style.css">
    <link rel="stylesheet" href="VIZWeb3D/ThirdParty/jquery/jquery.minicolors.css">
    <script src="VIZWeb3D/ThirdParty/jquery/jquery.minicolors.js"></script>

    <script type="text/javascript">
        function onSelectEvent(event) {}
        function onInitEvent() {
            var files = [];
            var url_string = window.location.href;
            var url = new URL(url_string);
            var path = url.searchParams.get("path");

            files.push(path);
            $("img[alt=imgfile]:last").remove();
            SOFTHILLS.VIZCore.View.Control.Model.Add(files);
            
            setTimeout(function () {
                // 환경 설정
                //SOFTHILLS.VIZCore.View.Toolbar.Option.Visible = false;     	// 툴바 보이기, 숨기기
                //SOFTHILLS.VIZCore.View.Tree.Option.Visible = true;         	// 모델트리 보이기, 숨기기

                // API 호출
                SOFTHILLS.VIZCore.View.AdditionalReview.SetAxisFactor(1); 	// 참조축 크기 비율 설정
                SOFTHILLS.VIZCore.View.AdditionalReview.ShowAxis(false); 	// 참조축 보이기, 숨기기

                // 좌표축 그리기 위치 및 크기 정보 수정 BottomLeft 기준
                SOFTHILLS.VIZCore.View.Coordinate.SetScreenPosition(10, 80, 80, 80);

                // 참조축 그리기 비율 설정
                SOFTHILLS.VIZCore.View.AdditionalReview.SetAxisFactor(2);
                
				// 엣지 보이기, 숨기기
                SOFTHILLS.VIZCore.View.Control.Edge.Visible = true;
            }, 2000);
        }
    </script>
    
    <!-- ThirdParty Script 참조 시작 -->
    <script src="VIZWeb3D/ThirdParty/Three/three.min.js"></script>
    <script src="VIZWeb3D/ThirdParty/jquery/jquery.binarytransport.js"></script>
    <script src="VIZWeb3D/ThirdParty/jstree/jstree.js"></script>

    <!-- ThirdParty Script 참조 끝 -->
    <!-- VIZWeb3D Main Script 참조 시작 -->
    <script data-main="main.js" type="text/javascript" src="VIZWeb3D/ThirdParty/Require/require.js"></script>
</head>
<body>
    <!-- VIZWeb3D Main Script 참조 끝 -->
    <!-- VIZWeb3D 가시화 영역 선언 시작 -->
    <div id="VIZWeb3D" />
    <!-- VIZWeb3D 가시화 영역 선언 끝 -->
    <!-- ProgressPage 시작 -->
    <div id="ProgressPage" class="ProgressPage">
        <div class="progress-bar">
            <canvas id="inactiveProgress" class="progress-inactive" width="200px" height="200px"></canvas>
            <canvas id="activeProgress" class="progress-active" width="200px" height="200px"></canvas>
            <p>0%</p>
            <pText></pText>
            <pTextFile></pTextFile>
        </div>
    </div>
    <!-- ProgressPage 끝 -->
    <!-- VIZWeb3D Toolbar 시작 -->
    <!-- 
    <div id="VIZWeb3D_Tree" class="uitree">
        <div id="ui_tree_expand" class="uitxt uialone_simple">
            <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABBklEQVRYhe2X7Q2DIBCGX5sO4CZ1g3aEbqAjdQS6gRu0I9hN2OAaE2gU+fIE8UffhERR7h6OywEVEaGkTkW9HxFAAKDMTUwdmjmwV0JU+uEc+iGxFhM8fBI+ALwBNKUARsfXrBBjEk6alu5riEiqPqnezTFrmmk/CJAaggWQEoINEAPREdE9J4APQkSMTQJgg+hpruwAJoQpMyIDEdUu+9xKOKjaEKOLt4YwIyDsk1+M1bqljEAHoGVGbiEOgPR8e6415tqOfepTbtdbt2OhErIuBdAGszwgzhLY9OIO3BqBmKT7qGWyynUozX0m/NkvfiZ05cBu9zUzAqsLCUMzH//LaVkAAF+SmIJjGqwbeAAAAABJRU5ErkJggg==" width="32px">
        </div>
    </div>
 	-->    
    <div id="VIZWeb3D_Toolbar" class="uicontainer">
        <div id="uibox_toolbar" class="uibox" style="visibility:hidden">
            <div id="ui_edge_visible" class="uitxt uistart">
                <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABDklEQVRYhe2XvQ2DMBCFH1EKurSUjEJJyQjOBhmBEbJByAaMwDiU0NERXRQixzhwln9QFJ5Eg+X7nn0G30XjOGJLHTalGxgQADoAo6OnmwJzUkDwm9Uy9Yro7ZELH4YBVVWhbVtjUpIkEEIgjuPZ2FIKnMMpDteAFzjF4RjwBtfFUQ0EhZPkQ/hx2pumeQZI09QITnOKomDBVQNXeSDPcyOwKu4Oyik4WREV1XXNSt/sP1CWpRV4mq/75HT6mbtgN7Ab2A04Ed0RmxmYLqiX+qAGNBXRJZgBDfwM4F2ZeDWwBietFaVW8CzLFuFeDSj1hBZOCnEIv8JJcmPio0lchJPkHbg7BPccOOnPu2MAD6hsnTXTpKWfAAAAAElFTkSuQmCC" width="24px">
            </div>
            <div id="ui_rendermode" class="uitxt uicenter_line">
                <img id="ui_rendermode_text_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACCElEQVRYhe1Xy4rqQBA9iqiIEh8gKKJx40436p+49l7wR+4nzCfMXfsJrsStbtSFiGAGRFc+gg98gH2pJvE2mfQQRwYHJgeaSleaqpPq6lS1hzGGZ8L7VO8AfA7WRAH8NqQqjJxl3RsATRgbAK+GlIO2QDJUxtgLY2zDPo+NYUOV+bHLAfq6PwB+icrFYoHT6YTlcnmbi0ilUnyWSCQQCARucwF/DbuaqLQjQCFT6OF8PmMwGGA8HmO73TrYrf+IRCIoFAooFovw+/2mXje28kMCXNHr9bhzIvEIyDmRKJfLN5+iOekpIAKPOocRRbIlgzQCtNdm+B+BuQ2UG3YRkBIwsdvt0O12oWma44hQ2FVVRaVSQTgctr52RkDXdSiK8s44EaKEFE+EmfmUeDYO0e/3USqV7iPQ6XQQDAaRyWTsjpQjtFotNJtNTKdT/mxH4MM/4Wq1wmw2w36/RzQaxfF45NLr9SKZTPJQX69XHhHKlfV6jeFwyNe12214PB7EYjHE43GpDye/Yo75fI7D4cAdkbQOIinqnOLpxcgl4BJwCbgEXAJSArlcDj6f41IhBZXoer0ufW/ngRpHJZvNIp1OYzKZYDQa3VVgCFTGa7UaGo2G2CPo1nV3teVEgqri5XLhpKjsUmNCklov0ufzeYRCIVSrVatd27b8W15MrPjSq9kPvx0D+AducOedPh2ShAAAAABJRU5ErkJggg==" width="24px">
                <div class="ddbox">
                    <div id="ui_rendermode_smooth" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACCElEQVRYhe1Xy4rqQBA9iqiIEh8gKKJx40436p+49l7wR+4nzCfMXfsJrsStbtSFiGAGRFc+gg98gH2pJvE2mfQQRwYHJgeaSleaqpPq6lS1hzGGZ8L7VO8AfA7WRAH8NqQqjJxl3RsATRgbAK+GlIO2QDJUxtgLY2zDPo+NYUOV+bHLAfq6PwB+icrFYoHT6YTlcnmbi0ilUnyWSCQQCARucwF/DbuaqLQjQCFT6OF8PmMwGGA8HmO73TrYrf+IRCIoFAooFovw+/2mXje28kMCXNHr9bhzIvEIyDmRKJfLN5+iOekpIAKPOocRRbIlgzQCtNdm+B+BuQ2UG3YRkBIwsdvt0O12oWma44hQ2FVVRaVSQTgctr52RkDXdSiK8s44EaKEFE+EmfmUeDYO0e/3USqV7iPQ6XQQDAaRyWTsjpQjtFotNJtNTKdT/mxH4MM/4Wq1wmw2w36/RzQaxfF45NLr9SKZTPJQX69XHhHKlfV6jeFwyNe12214PB7EYjHE43GpDye/Yo75fI7D4cAdkbQOIinqnOLpxcgl4BJwCbgEXAJSArlcDj6f41IhBZXoer0ufW/ngRpHJZvNIp1OYzKZYDQa3VVgCFTGa7UaGo2G2CPo1nV3teVEgqri5XLhpKjsUmNCklov0ufzeYRCIVSrVatd27b8W15MrPjSq9kPvx0D+AducOedPh2ShAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_rendermode_smoothedge" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACPElEQVRYhe1Xu67aQBA9iRAgBCKCCgowDQ8hSCSgT4Ho+AAaciW+gD+gpbufkHwJEZXFQ0qAAqi4EoKK90MCir2alW0ZYnN9caI0PtLK3vF65uzs7O7MB8YY/ic+mrT9SWoPw2bQyJP0FFQtfDPuN4A1gF+q50/pXR+0BDpNYIzVGGNr9jjo32dJl6YdrRig2dUAfFML5/M5TqcTFouF0lcjEAjwnt/vh8PhUPoq/JD0TtRCLQKK4Hw+o9/vYzweY7fbvWuZPR4PotEoUqkU7Hb7lU1DBLrdLjdOJMyAjBOJTCajSUB3FxABs8YheZF06UHXA7TWsvvNQF4Gig0tD9yNAcJ+v0en08FkMjHsEXK7IAjIZrNwu923n40R2Gw28Hq9fygnQhSQ6h0hRz4FnoZB9Ho9pNNpTQJae5Oj2WyyVqvF1uvHjwFRFFmpVGL5fF4tvrJ39yRcLpd8v5MnaLaNRgNOpxMul4vPNpfL8XHtdpvHymq1wmAwQLVaRSwWgyiKGI1G8Pl8ujaMHMUctP7T6RTH41FpMoF6vY7D4aDIt9utUbWmLyPTsAhYBCwCFgGLgC6BcDgMm83wVaELurTK5bLud10LoVAIwWCQXzKPolAooFKpaOYI9whE5LScPCAnJZR0FItFzGYzXC4XDIdD5YdkMslTL5JHIhHE43EuTyQSar1yWn6NNwqT579QmNTeW5jcgkqyrwC+SO/y8/PNuBep6JAblWTf3yrNzFbHcmF6v/77hwTMAcAra3srrUnjkSwAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_rendermode_flat" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAB+ElEQVRYhe1XvW7CMBD+WhAwAI2EhIClWQITavl5gL5JO/EcPEIfoX0G3oEJJrLAFC+AkJCoYAAWV2fFqZXEyCVqWfJJ1sXO6e7z+efOd5xz3BL3N/UOIGugYwF486WttMeQHgPgKW0H4MOXetASaJrNOX/nnO/49dj5Nmydn7g9QLMbAnhVB1erFU6nE7bbbdBXUa/XRa9SqSCfzwd9BZ++XU8djCNAIXugj/P5jNlshsVigf1+b7BaPyiVSmg2m2i328jlcnL8y1/KiwTEwHQ6Fc6JRBKQcyLR6/UCn6o57SkgAkmdw48i2dJBGwFaaxn+JJDLQHsjLgJaAhKHwwGTyQSe5xlHhMJu2zb6/T6KxWL4txkB2nS0kcIgQvRPPRFy55N+jENxYpRTYUaA1o0MVqtVWJYV1jECRc11XSyXSwwGg1gCF29Cmik113WZvPlqtRoKhUIgKQqkM5/PRXRIEhzHYeVyOXxbRmByFQvQbFQpMRqNYvUdxzGye/NklBJICaQEUgIpAS2BRqOBTCaT2AHVBko5FoE2GVH+plS82WzAGDNOWhKWZWU7nU64KI3AuCxnjOF4PGK9Xou+zIrj8VhIOctWqyXqiG63G7YbW5b/x8Nk+NuHSSSaAF4APPvfUj6F9K56miV9Hcta7fL77w8JJAOAbzbJuJe/JwQYAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_rendermode_wireframe" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABwElEQVRYhe1X0W2EMAx1q/4fG5QNjhHYoGxQNmhH4DZgBG6DdANGyG1AN6ATpIr0orqWzQXUEx+9J0WGxHHsxHachxAC7YnHXVcnoqcMnoKIWtCStWfB90lEE2szEQ2gNuIRGK0MIfQhhDlsxwwZpbWO1hmZB2PJKLBDq5Xx2O8MpQdNEU0BPjl+eyGoZ7zaIrF/xL9X5P1aT3PCA+gJ5+3E+BsRNaLvDPpKRO+s30HGScj+gbIDCem/Y9Z4ZkkleL2wNB2JJdfcgQQP70+Y8f8FSwbBXxPRRbG0hSwdCzsgMWK8VcbS3Cojaq46YcJoCJiURRz4pwXlVytACDUZBWvgIcP0gWuZcIQnH3G+0cMrZMVIX8D3gXOeQXvMcZBhIicVcycchcB0k8mwXE6/DLtfRncF7gqsiYIC6ZaHYYITYVhkS907EW1NxSPSr4R1D2xKxRbWFCQashXwuPk6RUgreHkJ1zEFOvDyY1x1Ga0pSLjVf1KQdMKbZ+R8qyBpjIKkgKzsKLCK0lSQNAvnWor5m4rSihWZB1yrqd+BXsScGla2eJgkHNlunEXuMHeAW3Pzh0nO4/SmT7N//jomom+zn4VK106VRgAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_rendermode_hiddenline" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACWElEQVRYhe1XsW7bMBA9F9mjVVOVlYv1B1IHz8niWc3iuf0C6v7As5dGs5dk9hDlD6zFa51JKwN0NVRccRKuKknJVoEseQAh6UTeHXnHx+OsaRp4T3yaaDvgdjGuRhr5ys9ItM+9fhUAGADYi2fJ725QCBwtapomP51Ov5oLwWPXrMtqx5YDNLscADIprOvahGH4wjMjlEVRPNNLlmVfACBleVzXdRKGYT80Bes9SqEtBD/F+9vhcNjvdrvEGFNpre9kx+Ox01Vy+4PNZlMGQZAsFosXpVQMANc8IWozqcOXhEirsd1uS2P8YbSBxtBYXlF09fM5kA8m0EhfWJcVzl2AiBTr9VTriEg76JvW+jwHAGAOAD8m2rdbHekAZXwivhNEpC3zypnchQcRHwVP9PnBpquDbRu2ghkiphyG+dBMPJOg5d9LvbKDl4q11pTFj/xJTEf7/Z6zuhBdn1j2nftULC/ZuBNjqLiFYYc6IGLGjt715KN3z9TDaDI+HPhw4JxdEDAvxEw6cfsDER+YnNo2ukoaIqKYicjKYiNQMRGVLiJyOoCILvqUVHzLsqchKtZat7r+csAXgr5xUpJKAZ8NNiIq++eIy4gvCStBu5cCWUflGu9cAa11zLNxFhNjoLWmBH0QOTB6BfKpNT8j8FVEPgeomDgul8s0CM73g8bQWE5YZ2FiC8GNKMuvlVKJUorK8jkfzV1ZHkVROyaVZflqtZqHYQi95CusKzFwMVn/h4tJfu7F5J/V5NlJBgwsVdJrjw0NJ5+3Nph6O26T4+Ly/X2v5wDwG0Iprsun+rgfAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_rendermode_hiddenline_elimination" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABd0lEQVRYhe1X0U3EMAz1If7JBtwG1w04NmADOgIjdISOABvABPQ2CBuUDXoTGEV6Qa5x01xyEj99klUrTRwndpyXHTPTf+Kmcm4HKcZt5iQtvnsh96rfFxFNROTFd4C+jBCCBdkzc8fME5cjjO1hy5zHyoGwuo6InlX7SayMsDqJI/QGu/Wg/r/B7jhrNbySmLALiyso2MFZv5QDYbArmFiLgy3TASsEsWFXnNo2TLupY+iR/bVoRd78xUoOBIzM3F4YDocxo2EvOwQnI5MDvpHJ8kQ0ok7o+qBtzUOb2IGgH5nZV9SBgZkbw+6vrFXCcNbfieiASveiqmGsFR+qAvYYMyTjn1mKIyaj+EQHnoy+Wai9jKqxObA5sDmwObA5kHKgq+X8gIMtGxmk1IurNXV1SxnQ7tdI6SW0/Kyu16B/Qn9UtDzod2p8Ni2XtLq/wsMkSetzHqcOK4q0K34Pql+kaqOgbK9r3KD2dRyTNJuAXNuBOhDRD0NxSrpv5ToQAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_rendermode_xray" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAClElEQVRYhe1XzaraQBQ+SfxJjN66E13o1YVuhLp2VfAFCn2AduemD3EfoYs+wO0DFHwBoUs3QuvChaJUBSXiL2gSo8mUMzfaXMn0GhXuhd4PhjM5mTnfN2Rmcg5HCIHnBH8hd9RuZ8N3Iskn297aDftvj8b9AoAFAPx02B92nw38BIwWJYTcWZa1JmfCnvuFEHLL4nHbA7jCOwD46HQahgGWZcFut6PPm82G2ul0Sm0kEqFWkiQQBOHw7MA3O+5vp9NNwMFhmiaMx2MaEPtuGA6Hrv5AIADBYBAymQz4fI++NHeSgNFoBIqiUOJEIsH8hCwB+xh+vx/S6TRks1lXAcxTgIFZq/aC7XYLrVaLOePSY3gxmAJ0Xb8ayXK5NFjvmPfAfD4HjuNUWZYtVVXDoVDIEynOn8/na1wIIUT2LAAe7ojQarWCarV68MmyrKCNRqM+v9+/a7fbeDR9s9mMnk9d12O4d/AEpFIpJvFJAtywXq9j8GDp20aj4TXEI7zcTfgq4FXAfyPA8z1gmubKNE2VEMJrmmYuFgvMAQSe501BEGSe58NXE0AIMRRFMbvdroRXq42w3Sg6nY7r3Hw+D7FYTLu5uRE4jgt4FtDr9YzJZEInOshPRq/Xw+tYwvHJZBJ/Rq4imHtgT34N9Pt9ZqyXewoKhQKIongxAf7Gi8Ui8/0/k1LMfGu1GlQqFVBV1TVAvV539cfjcSiXy1AqlWim7OR8SsA7Xdc/i6L4Ye/QNA0GgwFt2MdUfDKZ0H6z2aQpOCauSIr9XC5Hmyz/TQc0TfsuSdJXu1hxLJddmGAxcX9uUYLYbreaHcNTYXIMLFTe2+VYwWHfHI1b2uUYOEqy+6dKs0ur432duCf2jOctzwHgDwTm7JImQfRFAAAAAElFTkSuQmCC" width="24px">
                    </div>
                </div>
            </div>
            <div id="ui_projection" class="uitxt uicenter">
                <img id="ui_projection_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABhUlEQVRYhcWXzY3CQAyFzVaQDmiBAjhQAjeuOVAIN64UsAc6gApogRSQGwdWQiuttBIXJN4q0gwyw0zGdsjG0oj8jMef4jdjMwJAQ9rHoNE7AkyI6JuI7kRUE9HatEqTAsMokbYzgE8AY8m62uAFgC0LvQewBHAEcIsgXQHsAEzfATABULHFV5E5UxfwGoG5OdCFBWAO4Mct1PzOBD5jl4pzBOZXA7BhjpVLg0U3hwAiC1AEn3xjDBzqRgQwCz753Bg8ppssAJ9UuUUswVO6SQIUblt523bId5tukgAX9vLQId853SQBYqY51aS6yQJYTjWNbrIA/l5yqi0NuhED8NF2qnkrhToxAYRjDaBmPpqt+hSHd0T+YqSp5kqfpoc4cp//7IhKFvz0eGpMgcYn1kM8hNo3QLaH6BNA1EP0BSDuIWKL1W6bWQDUPQS/+cKr5WoBBzD1EOGDhbIWcHHxTy4+mNpeSmpBaOoeQjrxnbXABBAOXwvuLt/Wtu2pFgxiw/47JqI/Uf1aiMFmIFYAAAAASUVORK5CYII=" width="24px">
            </div>
            <div id="ui_camera_fitall" class="uitxt uicenter_line">
                <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA9UlEQVRYhe1XgQ2DIBA8GwdgJDboCh3BkXCDjtBRHKEbXKN5UkJAQaNfYy/5hPj4fzzHow1JaOKmmv3yBDwBB4BK5kYRqqqwDcbNwbmnhZ/2FNhIO06e1YNfYMEMyY7kwDwGmWMK4k0oIWBJukRKnyxHysm7mwnEeGYCW/HFmI3brti1O4C3jF+BJh7iq0NFBXLbkCt/UdwaAktCjAW4C4F4zz1SmiiKG7biXCec86/14fSdUIVAB8AUzDMytwwVIgyPmE0ILdcxd23FqXGK6CYC3tQuo5KqLK12Ux/YC7/RB8LbUOXjdKxAr5FY0P//DS9OAMAHOyeKM88P4B8AAAAASUVORK5CYII=" width="24px">
            </div>
            <div id="ui_camera_set" class="uitxt uicenter">
                <img id="ui_camera_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACB0lEQVRYheVX7XGCQBBdM/kfOggdxA6kg5gKpAQ62NsKQgdiB6YD0oHpgHRAKtjMOnvkRA7Ow8QfeTOMyu3te7df4IKZ4Za4uyn7DAEZAFQA0OpV6b3LISkIvFJmNszcsB+N2qShfqcMEmbOmbkeIfWh1r1JjIA1M1fM3EYQ99Gqr/WUAAlbORHiuWiUo0vRUBEm8TU9iXPfTgQKzZfN++GKJz849ZAo11kKLCRfS723nFELrceXhVeAha1icFSH1EfjRBNGumhSgOvQ7e2sdxKLStdCZ0awAB+JjYp7Wp+4qwmwODjpgciiPe51n4Yxj8XF3L3uHPiMcBKLjssVkALACwC8/yLxu3Kk3R2bC2NMYYxJnCoO6f+Q+rHzIFWeRLjs3ntH3atcRLQDgBIRcx2d8lkAwOOFp5Uwl/a9gYiW+n2j67J2IsBCDDZEJOGqELFU40yFPE8Qv6l9LT+IKNdDrIaMhwRYyIYVERlVLmLWmr9CnVp8qY0QN0QkNkZtRiPXtSERhbTSToXUQ4tElCnpZmjdBSIupiIwBJueD62TCn7CLFF5utBf9EupEG2d39sY8r6A2w4iRPyzQaRcR5zUACLuAWDvVLFU/cNMUukQ8WsQsekvDhahGuZEdJVBhIitz2i0C3TjcRBpiwUPIl+rXiSgJ0Yc1poe7yAaCvMY/vm/YwD4Bmagap1CrIglAAAAAElFTkSuQmCC" width="24px">
                <div class="ddbox">
                    <div id="ui_camera_plusx" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABHElEQVRYhe2W4Q2CMBBGP4z/dRRGYAQ30BHc4LgNHEE3cISO4CgwAaaGNFhb7hAo0XAJEJLm3msL18uapsGSsVmU/oVACaAZeV0HCTBzzsznwVMLxw3ACUCuErBwAAbAfmK4EQU68N0McJczKJAKHhRICf8QSA23sfXeCwCXwDjjPaWo2jzihOashBI8sze3Asxc9iQzRGTaFSoEsGmvg2Yru1tAisSFYhyg36rfOwtWgVXg/wT8UiyF5v8eVLY1AjWAeyeptsioxkoCFl4Q0aNtyzSd0atsM3OsbFdE5A68PoEu3DaSRwXcSUTKdu1LxT7CMfBYuJySQDJ4SCAp3BdIDvcFpoZXEtzGW0s2EZyJqK+7igssEcueBQCeKn+ZIQl35yYAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_camera_minusx" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA0klEQVRYhe2Wyw3CMBBEJ4g7rVACJVACnUzcQVqgE5dAKUkFRrYMskhgc7B3D3ikVRQp2vfsyJ8hhADLHEzpAI57PnLOnQF4AKcKzDvJ2+tFnIEW8NxTFmgI96JAY/i756aAFnxTQBO+EtCGx3wuwwuAqQJ8JjntGVCznVCCkxzis5yBsRLb57ru+ZWlACsJoFznUszPgi7QBbrA3woslgJLPvRMBBKc5MNCYAXXFNiEawl8hWsI/IS3FpgleEq8kuWqlbHoKVZ5I0p3NO3YbsUAnvWizoBpwhvvAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_camera_plusy" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABGklEQVRYhe2X4Q2DIBCFn03/t6M4giN0g3YENzhvA0doN2GEjqIT2GDUIBE4ouKP+v4Qkwvf85AHZl3X4UhdDqUDuMYUM3MFgFYyP0T0Gh8kHcgBlCuhMzgz51IDulABuG8MVxIDY+FtB/g0p8tAErjLQDL4koGkcC17GxYA6oU6ZY0hNURUh+BauyVhCE5EmR7NDlSe+fREipmLoUs+KSLS9Q/JUpoGQgmnBrgkCaVLdfxZcBo4DZwGom5Ewv0dFdtmFLsyuR0C6CuyGKlQByY4M5fCm1EfxZ7Y7g+q8cFnwIS/ATwj3s0V261tyvURroG71M9JRLOlXDKQDL5kICncNpAcbhvYGt6E4FrmLtgSDnOr+fTnf8cAfnFajtF7hmIoAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_camera_minusy" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAAy0lEQVRYhe2X2wnDMAxFb0r/u0pG6AgdoZuo2iArdBOP0FGSCVwMTQiNW5lgSR/x/TAYjM9Bxq8uxgjPnFzpAM4lg5i5BxAAXCown0R0nztiBZTgfZGAIjyIAsrwZc6sgBU8K2AJ3whYw1O+t+EVwFABPhLRIMFTNE9CCd6lZqkAMz8qgQMRJfCtZCnXS0CVBLDe51Lc74Im0ASawGEFJk+B6XPpuQjM8JeHwAZuKZCFWwn8hFsI/IVrC4wSPKXoa7YzRW/Lg/+OAbwB1ntMfOwzPIsAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_camera_plusz" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA4ElEQVRYhe2X0Q3CIBCGf43vrsIIjuAIbnKwQUeom3SEjtJOcOYaq20tpZorROVPeODlvo8jBNgxM1Jmn5T+hoAB0ABghVGOKssWBIZh5oZ1UgrLWmt6ZqgDsvIKwPHjHj9zBXBxzvU1uywJbAl/1PQJRIH7BKLB5wSiwiWHyfwEoFCAy5EtQvA5AQ14lzXwkYBzziqxKyIS8HnNVg47QEoCGJ7zUL7mLsgCWSAL/JxAm1KgvV96SQQ6OBHVKQRe4DEFZuGxBLzwGAKL8K0FmhBcMn2SqYWIVj3v/vx3DOAG2P7IEccdVA8AAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_camera_minusz" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABHklEQVRYhe2X4Q2DIBCFn43/21E6giN0g3YENzhvA0ewmzBCR9EJaDBqkKJAUPxRLyHE5OR7cPLATEqJI+NyKB1AHpLMzBUAimS+ieg1PjhXgJnvzFxGQmdwNaaXgCFRALhtDBdOAVridQf4NKZVQCq4VUBK+I+A1HAV5jYsANSWPGH0rmiJqPaZ0G5O6IITUab6XHuhWhlPEJFg5mJYpbXocwE8fEqpl8DlcGKA+zihb6mOPwtOAaeAU0DQjchzfwfZ9mTFzLzkyZ0yICL6+KoMCdcK6PDS82YkNNe02XarH3hrAnR4A+AZMLEl2+5MUUsfYQx8KUb4rJQ2AcngNgFJ4aaA5HBTwNbw1gXvQ/mA1hoZH5Ux5mr7879jAF+ZzP6b+DXT6gAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_camera_plusiso" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACB0lEQVRYheVX7XGCQBBdM/kfOggdxA6kg5gKpAQ62NsKQgdiB6YD0oHpgHRAKtjMOnvkRA7Ow8QfeTOMyu3te7df4IKZ4Za4uyn7DAEZAFQA0OpV6b3LISkIvFJmNszcsB+N2qShfqcMEmbOmbkeIfWh1r1JjIA1M1fM3EYQ99Gqr/WUAAlbORHiuWiUo0vRUBEm8TU9iXPfTgQKzZfN++GKJz849ZAo11kKLCRfS723nFELrceXhVeAha1icFSH1EfjRBNGumhSgOvQ7e2sdxKLStdCZ0awAB+JjYp7Wp+4qwmwODjpgciiPe51n4Yxj8XF3L3uHPiMcBKLjssVkALACwC8/yLxu3Kk3R2bC2NMYYxJnCoO6f+Q+rHzIFWeRLjs3ntH3atcRLQDgBIRcx2d8lkAwOOFp5Uwl/a9gYiW+n2j67J2IsBCDDZEJOGqELFU40yFPE8Qv6l9LT+IKNdDrIaMhwRYyIYVERlVLmLWmr9CnVp8qY0QN0QkNkZtRiPXtSERhbTSToXUQ4tElCnpZmjdBSIupiIwBJueD62TCn7CLFF5utBf9EupEG2d39sY8r6A2w4iRPyzQaRcR5zUACLuAWDvVLFU/cNMUukQ8WsQsekvDhahGuZEdJVBhIitz2i0C3TjcRBpiwUPIl+rXiSgJ0Yc1poe7yAaCvMY/vm/YwD4Bmagap1CrIglAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_camera_minusiso" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAACIElEQVRYheVX223jMBCcHO4/6uBUgjs4dxB3cEoFcQfkdpAOzqkgTgdKB04HcgdKBTxsMBQohpIoxoE/boCFQYrcGXIfkm+cc7gmflyVHcDPtRtEpAawB9AYYyrO9QAOAB6NMd23CBCRLYnvEo9vATyoicgLhbRfFiAiesIdAAvgV6ZWFXgnImfuOxpj+lUCeM2W5LeZxDFU8F+9DRE5qr9UeEYCRGTHa/5dSJqCHuCPmoi8MjxHv26oAhFRdc8XJo+hvp/JNRawIsaXwMBV2gfeANwH43vOrcbaPvCk9R6XmDFGe8CBpdow5lnIEfCuiUNijV3NsRJVXNPz+ce8iFg+3y9V0fAuEJH4pfBKpweOU43ohr/h3hcKbOm3oZhRchtjPvambuCJpXLiCZuSRgTgzNo/MjwbHmAcHr0BNWvt3lpbcVw75w7Oud7Nw++fQ09fNXkq5fJ7EThR2znn2gWHIXIEhGjJMXCG3wNdQS9I5UAOzkzmkYCSL5NSAcPeqzcirIyjJtOW6zVh9zSfvFuuyc6fHAGaxdZn8QxJKK7mnrkqWhSgGdtEp+0yTtZFt9JMVNakAD3JhnObzH6QQj/ha1KAV11R9amAdAon+qyC2/wkQOP2WHjaXPTk8PmULMPJD8gL4LPvqBWHLbk09jF8LuxSXFMCvFUzWbyENoj7JMeSgDhH7EIpdlHPWLQ1AkLbBiHqoya0yv7zf8cA/gG9Pcxsu3JUYAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                </div>
            </div>

            <div id="ui_clipping" class="uitxt uicenter_line ddinui">
                <img id="ui_clipping_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABeUlEQVRYheVXy3GDMBTcZMKV5Mgt0AFnTinBqcAugRJSgktwCS7BJXDlFFIAM3YF8jwszYhnWSB4mEN2RqNBfPZ9peVFKYU18boq+woG5ABOAPZm4W0F8vfeKtXAE0aulDqrG876uuP1RaAE8CHkfak9vwD4AlANRaBU8uh5PhSBzvO2bbsxBXEcI0kS+82fnuca3iIk8rqug+mJPMsyc3nR5HvXs+JdQORFUSCKIjhzziC6D3jI08UN8JBvAPzqeRkDBsKes1nWgNCcixowkvwI4E/Pd5jcBQGeyxfh3LDPMmACORVfA4kinOg5td/n7DaUDHuwATPJKzb3MNgFAp5T+2W6DsIMMKeaQNid5ARvCug8F8r57tGNMRsRP1hMOx3Zfm+qvGK73gHAVouce03gkGO2gFRantF6ykRaY73TsHupXt85vuOVZLZ05kqG8vjNImCwYREwOT9YwtapiB557hSQSwz7o6dnk9PgXSC2w43FP/87BnAFKZ1vLjbYOEMAAAAASUVORK5CYII=" width="24px">
                <div class="ddbox">
                    <div id="ui_clipping_yz" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABB0lEQVRYhe2X3Q2CMBSFP4zvmrCAPvGKGzAKI7CJbKAjOIIbqI8MQYITYEpaUwtYRLQx9iQ3JDTlfL389BDUdY1LzZy6/wpAClRAPVFV+sVtz4Aw3729zLYCdcbWgVQeL8BGTny11sC1z2BuAdgDRyA3W/eCVsBCQizMad94DVeykwfgpHyfAWTAcgLjSnZOlzK7A5i3QJhvJzDXZUI8yARoVl6WZVNjFYZhU0M62fkQCvOiKEYDRFGkAKzyn2IP4AE8wF8BxC4BYpkrMMPJtwByLZRk+oAtEU0pYZ4AZxcASd9AJ4DYSsWWOlZDt+IugIrHQPGurEHWBFDx6VOZsCX/c+oWALgBX4JJVxZHKrIAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_clipping_zx" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABS0lEQVRYhe2Xz22DMBjFX6qcERIXLkjdoN6gGaGdoNmgI7TdoCOQDbJBO4J75BbBApQjHBy5+kxRZPyHxnAoT/qQLT3ze7KNsDdCCCypm0XpEwPEAD4BiJGqAbCB/518D9cIoOD3jv4cwDO1mc7gE0DB76h/ALDRlPRxgj9Z3yo3oUPFQggufpVbxuTK2batar7qvEHhZVmKoiiMAWxLoJv2vcHfT3tVVeCcW1fAFCA4XGrrCP8iwI76NW20P8FNAfYDOKj9ceF5BHAk7yS41NgSxJZx3wBO1L6Vj6ZpvOGmAEpvlm+9V9d13nCXAMG1BlgDrAGCB4iiaLkAjDGkaaq69awBJDzLMtVVP7N5Amjgu9lmwAd+9QAX8IMNLjV2HvBWkiQ/NYCbTk+9QuwBZ7iULcCL4+1nEtwU4EinHhepg4k3XOqf344BnAH5oC9/LqtvRAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_clipping_xy" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABNUlEQVRYhe2XMRKCMBBFv460yFFS0+gR9AZ6MvUEegRtqG3oPQKhpYizmuiKCYIELOTP7AyZwL7PJiwwUkrhlxr/lP73BgYDTQysAGQAlKc4m8STmvDN9/doVVTXwANeFAWSJEGe541pYRgijmMEQUBDSrAwc1WNiE7adwCf8SVwGRAATnR9l3CSbRN2DV/puKlcgT7u3ABHZQMPOA3SNIWUsjGcoEKIqrI7DdBzPm1MdMu65mUDfA9cPMKlA/4m3geERwMuvTF4BSLeoVrKlofgR3383Fy0B3ScdaBlbHWeiOURSqlM3ZXp8W2Ow4zaGjCaf4JTdP06NmWf6rLP63TC3uAk3gdens8Wsr1clgAOtpR9fBGtXXCS7XvA548CwbdVJ/AK7DyCZR04afgzGgz8uQEAVxbyWWEoTMxnAAAAAElFTkSuQmCC" width="24px">
                    </div>

                    <div id="ui_clipping_inverse" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABeUlEQVRYhe1XPW6DMBT+qMrCQCsxcwdmpvQAnKHpDXqEHqE3KDmDL9CJmTswR0oYYGCgeghXjutH7ECCqvaTHBHxnr/362e8vu+xJu5WZXc0IAfQL7RyualLCpbOlUc/965aQohZrFmWnfyfSsEWwCeA9zmEQRAgiiL4vm98z0WAyD/mEBPCMESapgN5URTY7/c/ZEwRUMmPAF7H511VVReRd12Htm2NcroBOvkGQCnflWUJG+jk5H3TNEZNNQV62N8API5GDKBcngORJklyQl7XNaultuEBwIOVixbgyJUuGNpQTcFi5ARK15TnEqYu8DhhIcTZw0h6SBGwwe+aBVRcaxrwHMfxqgZcBf8GrG6A831An+cEGlLcnKDOmSxeOorHJQFm5T2P3LDPpuf1vuXVWSAf2JPQEnKfp/FCM4lb1EAydbNyroELydlBd80I6OS5UcqhCG2XCVtO9xY18MJ6z9TAkh8gk+QENQK7BYmPNuSEP/51DOALKtpYq+Rs0BsAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_clipping_disable" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA90lEQVRYheVXgQ0CIQw8jQP8SI7wG/gjMAIjOAJu4khsUPOxKJLn/6E1jfGSJiQPvaOFlj8QESxxNGUHcMrGZ7YZEcBVmcsBGHh8ZwPmFLB5+kTIvkktFL598reWgguAoLDzwL4WkQuIXxBRI39zFaEtQ5XQk45dvroXapDXBEhFNK1Vc9S7RnM3XVHTymd3yjQOlejQalyrbvJWAXtFNNWMVgFbIpoLlnk7/qkUmB5C02toWohMS7FpMzJtx6YPEtMnmVMk3xLh0py8FA8LhfIGYBIU2ol9lHhxrfUCKfmWiCeycI1EFNm8IOw185n/Mc35879jAA+wuUSQpzKIaAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                </div>
            </div>

            <div id="ui_measure" class="uitxt uicenter_line">
                <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA+UlEQVRYheVX0Q3CIBB9Nf7LKN3AEVzBEdhAN7FO4gqOohNgMLS5Eto8W/CIvuT+Dt6De3eBxjkHTWxU2SMBHQBHRpdLgCzBp7VocgjYLtg4q2nUPZC6gRgm1NxoCbAADiXIPVImTHnAihs4zeQVEyDB5lGoahBVK6AF8BATUkXALjdxD9aEreiC20xeMQESv9UFzCREVIKvCzgCuJQg92BKcAfw1BZggumk8djXUyqGF9WaLliLNw9rwhSWtuHoAKwAW6oL4EsQogeiOLs04jw2RjyMB+In2X4ijz6zXK8xikfrqxzFbJtlaUd5A9ccG5IYuP78dwzgBeKcwYP65kn7AAAAAElFTkSuQmCC" width="24px">
                <div class="ddbox">
                    <div id="ui_measure_coordinate" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAB7UlEQVRYhe1XwVHDMBBcmPxxB/jDm7x48UgBzGAqwB3gDjAduISU4HSQdOAS7DcfU4EYwV64CEnBQzTmwc3c6KQ7SXvynU4+M8ZgTjqfdfe/AGDh9CsArwCuAIwAGjWesW/bEkAPYM1+5djntHGpo67Zj9sYIJfmk1bGmIZyyb6ltbLdciw3xtSUa6Vf0sZyR/2o1irFFs6iHeXMGNNzkrSZspWFWmWDALe0LajvuNc3AK4XhfmiwrP4VunLwOayRqPG5MQ++rEgXCl56dFXbAfGgks5x62+Dm0SAmA3fAKwAbAD8OwB0bHtA2vYzS8YjGMIgD6unt9LvtPIIMt5ZHbsjnKlPtvWiYtKyS5JTOxjZuEgtp7eAmjpoXj3wBO4Yb9l+6Jsco/OJWtzf6BTJ5DR63UkoisnoNw0js0F9QcZlboWZCpYt+RDOoL4t1yqGMh9a6WuBeL9JpQtKQHYe+SachMySglAitHg/faJAdh0e6Qc9D4lAPH+LXBNJwcgwddGr2FLc6ReLA0Lposh9xybQnL8u0ih8p5AESggJvAe8PFy6hy3GkrVK8jynIq9eNy7for9HkAWQB47lRjVPwWQIguGY7mvSd4DIydeOs8nkQdV709LJw7CyexOKFQwSjAl29zy/8/pvAAAvAMVTClC0jNpPgAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_measure_2posdistance" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA+UlEQVRYheVX0Q3CIBB9Nf7LKN3AEVzBEdhAN7FO4gqOohNgMLS5Eto8W/CIvuT+Dt6De3eBxjkHTWxU2SMBHQBHRpdLgCzBp7VocgjYLtg4q2nUPZC6gRgm1NxoCbAADiXIPVImTHnAihs4zeQVEyDB5lGoahBVK6AF8BATUkXALjdxD9aEreiC20xeMQESv9UFzCREVIKvCzgCuJQg92BKcAfw1BZggumk8djXUyqGF9WaLliLNw9rwhSWtuHoAKwAW6oL4EsQogeiOLs04jw2RjyMB+In2X4ijz6zXK8xikfrqxzFbJtlaUd5A9ccG5IYuP78dwzgBeKcwYP65kn7AAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_measure_angle" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABmElEQVRYheVX21GEQBDss+5fMpAMxAgkBDKQEMhAzOBC4DI4M8AMMIMzA4xgrNVZ7dva41G7wIdTRbGPorp3pmd22IkItrSbTdFnEEgAFAAOADoAouNgmxqCM4A7z/oulMAUD6QDe/kaBMzpMwAPWxEw1gOodfwJ4DUWARgNTHhy+bNKRDIR6UWknPj91WeuCD9GNDHbpoSgogwoY4IbG/NAoqe/BfCuYoxqYx6oFdzYPYAmNoEhgaQkvJ7GTajw+BnabAk8UWBr9dIECgLrNO3M+onWsyUJnOXSrBcSCke7FIGaoA80toAlraWxCfAJT7pWEWCuaz0RjErACq13TmdDYjPAeiY4DDzJBlReETFXpEEE9lQSuMMpnJsu0bcpSi3NofO51tiixqV47e70u5vaezZe9F3qJfTmnPJZ30e9J6A35JPzPdSLj3qLNs73P0bxcGPaXtGDmxFw+gVfSrfXcDZvy4c8UKrq3ZLbaHlOnPrReS4qX+d0geMTYXCrPXZmxtk8BL4sWDUd2QPHFXF/sf753zGAL7fxpb+NFqZ/AAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_measure_delete" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABT0lEQVRYheWWjW0CMQyFX6oOcBuUTlBGYARGYATYgA06EiPABi0TlE7gylIiGXNOzCXhR/0kdCix4xfn4jOeCiLaUpltlz0R0coRPLHqIWAXF98T0WxkfhbnmF0PAcXdySzVBpuLHbeA15p7gy8bBtYsdbyggg8AvgDw8wRgA+C7Kp0Avy+fYs33EMLJ2v1aKPalzEE80sRaerwq9zfxf1/7LhkMcvhF2fz2iKgYT79GpGwxNRL7lq6lzoAkKb0oOleQfM1d5wQctAC+JUT0wz+109Fx4XuAgX4JPQyGjTWeJZeBC6z7a97r1gJ6kBOQKuBHRdzka1bTnIBjfE46W+V7tAwe+gj+hwAT2RdIG6u05mzH+oCEpxS3YFIpvgl3F+D6FvDHRpTbjWF2Nh7buzpqegJPL8Dc/QhCbrKk3h0kBDNOKQNmI3EFLdboBIA/pZfr7YD6/XYAAAAASUVORK5CYII=" width="24px">
                    </div>
                </div>
            </div>


            <div id="ui_drawing" class="uitxt uicenter_line">
                <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABHklEQVRYhcWXgQ2DIBBFr53EERyhI3QERugojEA3cARH6AiM0E5AYwLJeb1D1LvyE6IxJO8doMglpQQ9c+1KNxbwADDnq5xlCgxaSOsEiWENj+ielbCEh8ozEwE67OOGmKoAhS95VyS8pgCtbsxwTqKsiVlLQBpaTkJ9BKqLi0gkqa8VHEuI8KMCrfCmvl3hewXU4XsETOCtAmbwFgFT+JaAObwm4P8BlwSGtI6zgksCj/QbTuI0XBLAOxb+ljttOCeAv913ZkNxmnBOoCy+SKQ2d7Wjjf6Wu3yd0LMBAF6k3xP1PRdkfkfV3fJoRKZyr1E5NwUTAyuJ+e0YNOFLw2dDekj8AEDIjU6BXpBNWWhTng7VSltGoEv6no4B4Atmox1oRStHCAAAAABJRU5ErkJggg==" width="24px">
                <div class="ddbox">
                    <div id="ui_drawing_free" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABiklEQVRYheVXXY3DMAz2ne79wuAKoRAKoRAGoRDK4AYhEA5CIQxCx6AMfKpkS5aVtLazaQ+LZG1REueL/z73AxHhlePzpbcDwFfwXA8Ao5ivALDQr2/sLnBKxvq4IeKEiMmq03v5JK5eEXEh2RSkfT5bgHgBLHTBUlgbC9Y5BRIFMB/s6RDxqqyy/788AgC/cDXs7QoW2R/QtwAYhbLBeKYXluMxRwEAvR4p4r3g2S1rCwBphcl5NtGZoQWADMaNfB3VEwbQCXOWUvLpAEAVpaO0fBqAXf4EiD6io5WOExHQNwDciaQ2j4JWOt4EK/4AwOzW0OgClmugQJliYDAqTNECVVvohULO93yS87JAFYnHCiAV+L1YxwvCBcpshZrZpT87ulSCulXS7iL2mCrkGQCpJKm8Rwo+3Wy4grG2wK/NFYA6PrgP1NYLA5hPTJlU6umxtWaBDMSz9kt3PaunLB8tsmIr21lrRhUApxA3DEdx8DApfRn9qnkOsUSAC7Q/c5RiPfLmX8cA8A+cL544UukukAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_drawing_line" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAAsUlEQVRYhe3XvQ2AIBCG4U9j7yqO4IiOxAiOohOcMRpjIio/9x2FklAAxfMWNFeJCEquuqj+tYAOgAMwnC8bY7y9vKyfkLw7EZlkW9N+PsyiODvgFWcGBOGsgGCcERCFawdE45oBSbhWQDKuEZCF5wZk4zkBKnhqgBqeEqCKxwao4zEBFDw0gIaHBFDxtwA6/hRggt8FmOG+AFPcF+As8XX7BpMZQA9gtBgY/uG0bACABTOX7K5VtYdhAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_drawing_quadrangle" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAAY0lEQVRYhe3XMQrAIAyF4ad4/zPoSdO12FQcmmbwDzgIYj4lgilmpsyoqdmPB7wBuiQLGn1O5hVhdFWW+6TtLvwg3IPxCgAAAAAAAAAAANIBqz/hLy2TdwMjMN9jb3rDwwGSLtr1JKQL7XbcAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_drawing_circle" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABIUlEQVRYhe1XgQ2DIBC8doJu4Aiu8COwSTuK3cARHMERdAOchKbJf0Lwq6A2kOglhChw3L//PN6cc8iJe9bdTy9giwADoAVgAbigWR4zSYzfIIxo5JyzLh6W16xyx2RBA+DpPY8AOgB9MI/Y+tp79wbw2uOB1rN5iLSKeK6gXZq/RNQEJI/IzwWe64tvUgVQrAUJHlS992uhBNyQaLnmCfkcNlaAWVOd2HxvmnCtdg5IHo9KpG9Bz1zQzghNAHHfHbC5QLgoHNAEVNwfYb1AuKpw4KqGRQqYuJ8FzA5QwL0oQAImrawuQ7hmga0JkJSpD/ICeRVyntolHsVFFCPkLseaBX+5kBR/JfOtynYp9WG4kVJYJvZKl1JJr3/DkwsA8AFMUJ57izEdBQAAAABJRU5ErkJggg==" width="24px">
                    </div>
                    <div id="ui_drawing_delete" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABT0lEQVRYheWWjW0CMQyFX6oOcBuUTlBGYARGYATYgA06EiPABi0TlE7gylIiGXNOzCXhR/0kdCix4xfn4jOeCiLaUpltlz0R0coRPLHqIWAXF98T0WxkfhbnmF0PAcXdySzVBpuLHbeA15p7gy8bBtYsdbyggg8AvgDw8wRgA+C7Kp0Avy+fYs33EMLJ2v1aKPalzEE80sRaerwq9zfxf1/7LhkMcvhF2fz2iKgYT79GpGwxNRL7lq6lzoAkKb0oOleQfM1d5wQctAC+JUT0wz+109Fx4XuAgX4JPQyGjTWeJZeBC6z7a97r1gJ6kBOQKuBHRdzka1bTnIBjfE46W+V7tAwe+gj+hwAT2RdIG6u05mzH+oCEpxS3YFIpvgl3F+D6FvDHRpTbjWF2Nh7buzpqegJPL8Dc/QhCbrKk3h0kBDNOKQNmI3EFLdboBIA/pZfr7YD6/XYAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_drawing_close" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA90lEQVRYheVXgQ0CIQw8jQP8SI7wG/gjMAIjOAJu4khsUPOxKJLn/6E1jfGSJiQPvaOFlj8QESxxNGUHcMrGZ7YZEcBVmcsBGHh8ZwPmFLB5+kTIvkktFL598reWgguAoLDzwL4WkQuIXxBRI39zFaEtQ5XQk45dvroXapDXBEhFNK1Vc9S7RnM3XVHTymd3yjQOlejQalyrbvJWAXtFNNWMVgFbIpoLlnk7/qkUmB5C02toWohMS7FpMzJtx6YPEtMnmVMk3xLh0py8FA8LhfIGYBIU2ol9lHhxrfUCKfmWiCeycI1EFNm8IOw185n/Mc35879jAA+wuUSQpzKIaAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                </div>
            </div>
            <!-- 점, 모서리, 참조축 -->
            <div id="ui_additional_set" class="uitxt uicenter_line">
                <img id="ui_additional_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABTUlEQVRYhe1X223DMAy8FBnAG7SjeARlg4zQETRCR/AIHsEjeAR3g2xwhQEaUAlKFhUD/kgO0I8exyNFi/SFJM7Ex6nWX17AMwJ6AAMAyhhkzo81CStHR/Kb5MI8FtnT1fLWbOpJDobJzVhO1CBnmwSsHtxJzg7inNBZuMyoWMZ/SD4Mb2NlaDvZq6PyEO5dARqT506VkMngcwuwQh+Uh4vMla7CLSAWki+HXDLGFgF9Ekor02fxOhjJqj/H/hkBOtM3hGQ+OM79W/O+hFPFnsVDeG14PH8BfAKIyVxM1lwCvFegw60RMmcOu4IVI4CbeJtG5SZrLrRcwSZilEq44quR590RFQV0B9rJc+3UgrGQ2chltvpiRsXZXI6tTscizT3d1eUYSUOy1+mkAkqdk6shsR6Rvap4eEuWi8opTWlNVKq8tcb73/DFBQD4Awpr0s2Lf3McAAAAAElFTkSuQmCC" width="24px">
                <div class="ddbox">
                    <div id="ui_additional_dot" class="uitxt uistart">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABTUlEQVRYhe1X223DMAy8FBnAG7SjeARlg4zQETRCR/AIHsEjeAR3g2xwhQEaUAlKFhUD/kgO0I8exyNFi/SFJM7Ex6nWX17AMwJ6AAMAyhhkzo81CStHR/Kb5MI8FtnT1fLWbOpJDobJzVhO1CBnmwSsHtxJzg7inNBZuMyoWMZ/SD4Mb2NlaDvZq6PyEO5dARqT506VkMngcwuwQh+Uh4vMla7CLSAWki+HXDLGFgF9Ekor02fxOhjJqj/H/hkBOtM3hGQ+OM79W/O+hFPFnsVDeG14PH8BfAKIyVxM1lwCvFegw60RMmcOu4IVI4CbeJtG5SZrLrRcwSZilEq44quR590RFQV0B9rJc+3UgrGQ2chltvpiRsXZXI6tTscizT3d1eUYSUOy1+mkAkqdk6shsR6Rvap4eEuWi8opTWlNVKq8tcb73/DFBQD4Awpr0s2Lf3McAAAAAElFTkSuQmCC" width="24px">
                    </div>
                    <div id="ui_additional_edge" class="uitxt uicenter">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABEElEQVRYhe2X0Q2DMAxE3U6QkTICI3SEjsAIHSEjdARGYARGYIOrkFwJuXFiA1IqwUn5iYz94iSOuQGglro3jX56gD0AkYgSEYFH4jm/lkNoHAHAE8AEXRPbBKtfi1EEkDIhv8E0qMTfbgJYVvAAMDoca6Aj+8pmJRf8BWDOrLY3pjawrczKzL6rAFKDZ08FyJDx5wZw7WlhK9wAfeXwBbHa0mHstwBEo3MLZNwD4Elv6YYcArBOuVTpoKoAW0vxbJyr6nqOL4C/BggHxtF9Vd6CN4BOudtSOZuOfUCrA57nWNZ/DUAr3ebnmFYNSanTkSp1Tq6GxFv/Ne1qybSsNGlKLVmxNis/4/o3PDkAEX0A7gbHA+P5s6oAAAAASUVORK5CYII=" width="24px">
                    </div>
                    <div id="ui_additional_axis" class="uitxt uiend">
                        <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABXUlEQVRYhe1X0VGEQAx95/jvlkAJdKB2QAmUoB1QgnZACdeB2AHXwZaAFcRhLtFMLjISPBhneDN77C3Z5O0S3oYDEWFL3Gwa/b8TKAFkAAP3YxhzINASEfX0jYGIioivKAEdXNAzsasTeHKCC45z/UVy4GFB3lwgqgNC4o2vzwB6bsMaBAQy+RFAF3GwC9FOIEqgYhkWtDw2HwEhqiaEqFpDCbOS3oqbSHNeg4C3Wr0rV5fiP8VtwNk7gHsAjRpr1L15mLFdJRF1Ewko6Ng2lAOJj9tkxlonyNEcw5Zc6/ixvi8I9MqhnP2Dcjr264kV1eotEfuG78ki+p8IFGYF2fxvflnxJLad8lV6BOwkvb2Req8wj0njxSNgWZI2XNC8hX0Jli5IvMrkA0Ba+N6PFdKdM34Yf7QQvQI4GSNv4lxYHyeOdWZBfkk21nwF99uFBGq+Zq9s2z9OtyUA4BNKKRIF8q4GZAAAAABJRU5ErkJggg==" width="24px">
                    </div>
                </div>
            </div>

            <div id="ui_snapshot" class="uitxt uicenter_line">
                <img id="ui_projection_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABY0lEQVRYhe1X0U3DQAx9QfyTDRo26AhhA0ZghG5AskFGCBtkA8IkMMIxgZElH3JPycUOVUIFT7Jy6b3k3tnn17YgIuyJm11XvzYBAwAyxmB9qfUMVADeHWIZ9wA+lkjWDNRqXCzE1DMXE/Bm4EbO0fRmLgER9bQ9eM3vM7CXGRRpCWJ9W7lvDTX3xoNe8NcZUSORO8ElgB5AUJ4Q5LPSrUAO4RxOMh/jSEQhww/CQSZqxcdtoqdVY95Vl+ycHe5OcUcZc8aeZW6QFgxrMpBTflK8emJe7yzN3BzPdQgf5fqidq4xypzmLmJNF+T8fdH7U1zV74G4u6eZditlDp5MeAT0cj1M9Hz0hkPCXYajCzi6pOdHCe0NnccHvAI4mowRNYbnzwSk34aFMXGVtFosQxADstSeTes1rrdWwE9wJiC14mYDAZW+iRkIyuO3wieXMGag9tjnhTDoDOyG/79mf1wAgC/iIXfBvM74GgAAAABJRU5ErkJggg==" width="24px">
            </div>

        </div>
        <div id="uibox_toolbar_visible" class="uibox" style="visibility:hidden">
            <div id="ui_toolbar_visible" class="uitxt uialone_line">
                <img id="ui_toolbar_visible_img" alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsSAAALEgHS3X78AAAAOklEQVQ4jWMYBYMB/P////x/CDiP7B5ixRlBLJgkIyMjI5JCosSZGBgYLkD5FxhQAanio2CIAgYGBgCZdDOgxD75RwAAAABJRU5ErkJggg==" width="24px">
            </div>
        </div>
    </div>
    <!-- VIZWeb3D Toolbar 끝 -->
    <!-- VIZWeb3D Model Tree 시작
    <div id="uinav_drag">
        <div id="uinav_dragheader">
            <div id="ui_tree_collapse" class="uitxt uialone_simple">
                <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABDUlEQVRYhe2X7w2CMBDFH+p32URGYAETNtCRHKGM4AQ6Am6CE5whaRM8vNJ/Fj7wSy6hUK+P62tOCiLCkuwWXX2NAhQA+nOo8YLcA7kMUZiLw9yExExecPUmtHmiySHgYnlW5hAg0XI3hyKdgoKNx9wjys/zB1WgBlAFCpjgI2B48zeAI4BnMhHDFozCYO4ZlB5XRNTre70e8xy24PlnBTREdGVzYkR4C5BCEqFoSkdEpZQ/9Bh22owunjhZ/RJYAdftMNSpKyBV4uabIFaA0nHWIryR2rErplfsQ3vD9p8wdgsMj9AfxlagdZjz0qflJ3PtODVJ2nFSJA9k+17jFXDZ01i+1tg+TpcVAOADM7fwtCtN2n0AAAAASUVORK5CYII=" width="32px">
            </div>Structure
        </div>
        <div id="uinav">
            <div class="grid-container">
                <nav id="ui_tree">
                </nav>
            </div>
        </div>
    </div>
    -->
    <!-- VIZWeb3D Model Tree 끝 -->
    <!-- VIZWeb3D Property 시작 -->
    <div id="ui_property_drag">
        <div id="ui_property_dragheader">Property</div>
        <div id="uinav_property">
            <div class="grid-container">
                <nav id="ui_property">
                </nav>
            </div>
        </div>
    </div>
    <div id="ui_message">
        <label id="ui_message_text"></label>
    </div>
    <!-- VIZWeb3D Message 끝 -->
    <!-- VIZWeb3D Configuration 시작 -->
    <div id="ui_configuration_div">
        <div id="ui-bar">
            <div class="ui-bar ui-header-color">
                <button id="btn_tab_home" class="ui-bar-item ui-button">
                    <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABLElEQVRYhe2X0Q2CQAyGi3EARmAERmAER2AERmAER8ANHOFGcARHYIMakzY5mnpXCoSoXNIXy/X/2h71KBAR9lynXdUXAtQAEMhqd5R3CxzW4nSNiHjxxFoqPpLxarcGiMWfiFiTuSHmiF8jkQcilpGvot94dWsDDAlxtlJADGsBWMTdEClnKcRNGdG+u3XfkkwaRAxkjbdyXnE5Bz6d/ixETrxXgnaRX84B7fT3KYj4Qcv7LDOqlFcwV7GRtCYAOXF5IGUmWttkuVUIdobIoYnnglsgGYITDRJgUhoyS3ktbZKtDjRZk3NAtsU8XpWDKhPLzgFY+i+n9Hw2AC9tyFitieKoe1J3QnYU7tuOIc5X3wkPgAPgNwDOhmc2/XhMVeC2os7HWH/+dQwAL88c/wtf5ofAAAAAAElFTkSuQmCC" width="16px" style="vertical-align:sub">
                    Home
                </button>
                <button id="btn_tab_measure" class="ui-bar-item ui-button">
                    <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA+UlEQVRYheVX0Q3CIBB9Nf7LKN3AEVzBEdhAN7FO4gqOohNgMLS5Eto8W/CIvuT+Dt6De3eBxjkHTWxU2SMBHQBHRpdLgCzBp7VocgjYLtg4q2nUPZC6gRgm1NxoCbAADiXIPVImTHnAihs4zeQVEyDB5lGoahBVK6AF8BATUkXALjdxD9aEreiC20xeMQESv9UFzCREVIKvCzgCuJQg92BKcAfw1BZggumk8djXUyqGF9WaLliLNw9rwhSWtuHoAKwAW6oL4EsQogeiOLs04jw2RjyMB+In2X4ijz6zXK8xikfrqxzFbJtlaUd5A9ccG5IYuP78dwzgBeKcwYP65kn7AAAAAElFTkSuQmCC" width="16px" style="vertical-align:sub">
                    Measure
                </button>
                <button id="btn_tab_note" class="ui-bar-item ui-button">
                    <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA7UlEQVRYhe1XgQ3CIBC8GgdwEzuCI3QER3IE3MBRdIOOUCd48wk1BHxAKKAJl5A0Dfwdzx1pByJCS+yasv+iAAWACg9lEtoeqGWIYX3YhyZsDGeD3YR/ISAnGSpUvHkHpBiWTsG7fjehdBGZOAAYE+vfASy+CTEe4CLHRAEPS7xTP6YDFwDnRAHBGPYUbC0gdGs6iDkCNtEkEN60Se31Pjx1svQKInOsMN/NJGMW1ktYiGg018SkYAp0IBa885PVsc1TIB3BR3JGjRSI5DUEeMlLCwiSMyQT5n6eR5Ez7A5cM4m/Imf0n9O2AgC8AELN4BKhzL0QAAAAAElFTkSuQmCC" width="16px" style="vertical-align:sub">
                    Note
                </button>
                <button id="btn_tab_drawing" class="ui-bar-item ui-button">
                    <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAABHklEQVRYhcWXgQ2DIBBFr53EERyhI3QERugojEA3cARH6AiM0E5AYwLJeb1D1LvyE6IxJO8doMglpQQ9c+1KNxbwADDnq5xlCgxaSOsEiWENj+ielbCEh8ozEwE67OOGmKoAhS95VyS8pgCtbsxwTqKsiVlLQBpaTkJ9BKqLi0gkqa8VHEuI8KMCrfCmvl3hewXU4XsETOCtAmbwFgFT+JaAObwm4P8BlwSGtI6zgksCj/QbTuI0XBLAOxb+ljttOCeAv913ZkNxmnBOoCy+SKQ2d7Wjjf6Wu3yd0LMBAF6k3xP1PRdkfkfV3fJoRKZyr1E5NwUTAyuJ+e0YNOFLw2dDekj8AEDIjU6BXpBNWWhTng7VSltGoEv6no4B4Atmox1oRStHCAAAAABJRU5ErkJggg==" width="16px" style="vertical-align:sub">
                    Drawing
                </button>
                <div id="ui_btn_config_cancel" class="ui-btn-cancel uialone_simple" style="margin:6px 0px 0px calc(100% - 23px)">
                    <img alt="imgfile" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAABYlAAAWJQFJUiTwAAAA90lEQVRYheVXgQ0CIQw8jQP8SI7wG/gjMAIjOAJu4khsUPOxKJLn/6E1jfGSJiQPvaOFlj8QESxxNGUHcMrGZ7YZEcBVmcsBGHh8ZwPmFLB5+kTIvkktFL598reWgguAoLDzwL4WkQuIXxBRI39zFaEtQ5XQk45dvroXapDXBEhFNK1Vc9S7RnM3XVHTymd3yjQOlejQalyrbvJWAXtFNNWMVgFbIpoLlnk7/qkUmB5C02toWohMS7FpMzJtx6YPEtMnmVMk3xLh0py8FA8LhfIGYBIU2ol9lHhxrfUCKfmWiCeycI1EFNm8IOw185n/Mc35879jAA+wuUSQpzKIaAAAAABJRU5ErkJggg==" width="16px">
                </div>
            </div>
        </div>
        <nav style="height: calc(100% - 10px); width: calc(100% - 2px)">
            <div id="ui_config_home" class="ui-container ui-white">
                <h5 style="margin-block-end:0.5em">View</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Ground</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <input id="ui_check_ground" class="ui-check" type="checkbox" style="left:50%">
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Coordinate</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <input id="ui_check_coordinate" class="ui-check" type="checkbox" style="left:50%">
                    </div>
                </div>
                <h5 style="margin-block-end:0.5em">Model</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Outline</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <input id="ui_check_model_outline" class="ui-check" type="checkbox" style="left:50%">
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Unit</label>
                    </div>
                    <div class="ui-container ui-cell" style="vertical-align:middle">
                        <select id="ui_select_unit" class="ui-select" name="option">
                            <option value="0">Part</option>
                            <option value="1">Body</option>
                        </select>
                    </div>
                </div>
                <h5 style="">Color</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Selection</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_model_selection" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5" value="rgba(52, 64, 158, 1)">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Selection Line</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_model_selectionedge" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5" value="rgba(52, 64, 158, 1)">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Edge</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_model_line" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5" value="rgba(52, 64, 158, 1)">
                        </div>
                    </div>
                </div>
            </div>
            <div id="ui_config_measure" class="ui-container ui-white" style="display:none">
                <h5 style="margin-block-end:0.5em">Option</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Unit</label>
                    </div>
                    <div class="ui-container ui-cell" style="vertical-align:middle">
                        <select id="ui_measure_unit" class="ui-select" name="option">
                            <option value="mm">mm</option>
                            <option value="cm">cm</option>
                            <option value="inch">inch</option>
                        </select>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Positional Num.</label>
                    </div>
                    <div class="ui-container ui-cell" style="vertical-align:middle">
                        <span id="ui_measure_stepper" class="ui-stepper">
                            <button id="btn_measure_stepper_minus">◀</button>
                            <input type="number" id="ui_measure_pointnum" value="2" min="0" max="5" step="1" readonly>
                            <button id="btn_measure_stepper_plus">▶</button>
                        </span>
                    </div>
                </div>
                <h5 style="margin-block-end:0.5em">Color</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Line</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_line" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Point</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_point" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Pick</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_pick" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Back</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_back" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Border</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_border" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Text</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_measure_text" class="color_picker" data-position="top right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="ui_config_drawing" class="ui-container ui-white" style="display:none">
                <h5 style="margin-block-end:0.5em">Option</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Line Width</label>
                    </div>
                    <div class="ui-container ui-cell" style="vertical-align:middle">
                        <span id="ui_drawing_stepper" class="ui-stepper">
                            <button id="btn_drawing_stepper_minus">◀</button>
                            <input type="number" id="ui_drawing_linewidth" value="10" min="1" max="100" step="1" readonly>
                            <button id="btn_drawing_stepper_plus">▶</button>
                        </span>
                    </div>
                </div>
                <h5 style="margin-block-end:0.5em">Color</h5>
                <div class="ui-cell-row">
                    <div class="ui-container ui-cell ui-60">
                        <label>Line</label>
                    </div>
                    <div class="ui-container ui-cell">
                        <div style="padding-left:calc(50% + 8px)">
                            <input type="hidden" id="ui_color_drawing_line" class="color_picker" data-position="bottom right" data-format="rgb" data-opacity=".5">
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <!-- VIZWeb3D Configuration 끝 -->
</body>
</html>
