/**
 * @author jhjang@softhills.net
 */

VIZWeb3D.Configuration = function () {
    var scope = this;
    var eventHandler = new VIZWeb3D.EventDispatcher(window, container);
    this.EventHandler = eventHandler;

    this.AUTHORITY_PARAMS = {
        Data: 'https://pdm.jikyung.com/softhills-license-server'
    };

    this.Model = {
        Selection: {
            Color: { r: 255, g: 0, b: 0, a: 0.5 },
            Line: {
                Enable: true,
                Color: { r: 0, g: 255, b: 0, a: 1.0 }
            },
            Unit: SELECT_UNIT.Body,
            //Unit: SELECT_UNIT.Part
        },
        Edge: {
            Color: { r: 0, g: 0, b: 0, a: 1.0 }
        },
        LOD: {
            Enable: false
        },
        RenderMode: RENDER_MODES.Smooth
    };

    this.Ground = {
        Option: {
            Visible: false
        }
    };

    this.Toolbar = {
        Option: {
            Visible: false
        }
    };

    this.Tree = {
        Option: {
            Visible: false
        }
    };

    this.Measure = {
        Unit: MEASURE_UNIT.mm,
        PositionalNumber: 2,
        Color: {
            Line: { r: 255, g: 255, b: 255, a: 1.0 },
            Point: { r: 255, g: 0, b: 0, a: 1.0 },
            Pick: { r: 0, g: 255, b: 0, a: 1.0 },
            Back: { r: 255, g: 255, b: 255, a: 1.0 },
            //Border: { r: 76, g: 76, b: 255, a: 1.0 },
            Border: { r: 41, g: 143, b: 194, a: 1.0 },
            //Text: { r: 0, g: 0, b: 0, a: 1.0 }
            Text: { r: 0, g: 56, b: 101, a: 1.0 }
        }
    };

    this.Note = {
        Color: {
            Line: { r: 255, g: 255, b: 255, a: 1.0 },
            Point: { r: 255, g: 0, b: 0, a: 1.0 },
            Pick: { r: 0, g: 255, b: 0, a: 1.0 },
            Back: { r: 255, g: 255, b: 255, a: 1.0 },
            //Border: { r: 76, g: 76, b: 255, a: 1.0 },
            Border: { r: 0, g: 0, b: 0, a: 0.5 },
            Text: { r: 0, g: 0, b: 0, a: 1.0 }
        }
    };

    this.Drawing = {
        Color: {
            Line: { r: 255, g: 0, b: 0, a: 1.0 },
        },
        Width: 2
    };

    this.Thema = {
        //Type: THEMA_TYPES.Splitter
        Type: THEMA_TYPES.Basic
    };
};