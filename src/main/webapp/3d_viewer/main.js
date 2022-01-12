// VIZWeb3D Main
require({
    baseUrl: '.',
    paths: {
        VIZWeb3D: 'VIZWeb3D',
    }
}, [
        'VIZWeb3D/Softhills'
        ],
    function (Softhills) {
	
        var ViewContainer = document.getElementById("VIZWeb3D");
        if (ViewContainer) {
            SOFTHILLS = new Softhills(ViewContainer);

            // Event
            SOFTHILLS.VIZCore.View.EventHandler.addEventListener(EVENT_TYPES.View.Init, onInitEvent);
            SOFTHILLS.VIZCore.View.EventHandler.addEventListener(EVENT_TYPES.Model.Select, onSelectEvent);

            SOFTHILLS.VIZCore.View.EventHandler.addEventListener(EVENT_TYPES.Progress.Percentage,
                function (event) {
                    var str = '';
                    if (event.data.type === PROGRESS_TYPES.File_Downloading)
                        str = 'File Downloading : ';
                    else if (event.data.type === PROGRESS_TYPES.Data_Loading)
                        str = 'Data Loading : ';
                    else
                        str = 'Edge Loading : ';

                    console.log("Progress : " + str + " value : " + event.data.value);
                });

            SOFTHILLS.VIZCore.View.EventHandler.addEventListener(EVENT_TYPES.Keyboard.Down,
                function (event) {
                    console.log("KeyDown : Key : " + event.data.key + " ctrl : " + event.data.ctrl + " alt : " + event.data.alt + " shift : " + event.data.shift);

                    if (event.data.key === 'A'.charCodeAt(0)) {
                        SOFTHILLS.VIZCore.View.Control.Camera.InitPos();
                    }
                    else if (event.data.key === 27) {
                        SOFTHILLS.VIZCore.View.ClearMode();
                    }
                });

            SOFTHILLS.VIZCore.View.EventHandler.addEventListener(EVENT_TYPES.View.Loading_Complete,
                function (event) {
                    console.log("Loading Complete");
                    //SOFTHILLS.VIZCore.View.Control.Model.RenderMode = RENDER_MODES.SmoothEdge;
					
					// 카메라 사용자 지정
					SOFTHILLS.VIZCore.View.Camera_FitCustom(1.5);
                });

            SOFTHILLS.Init();
        }
    }
);

