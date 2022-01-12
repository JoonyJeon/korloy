/**
 * @author jhjang@softhills.net
 */

const CLIPPING_MODES = {
    X: 'X',
    Y: 'Y',
    Z: 'Z'
};

const RENDER_MODES = {
    Wireframe: 'Wireframe',
    Smooth: 'Smooth',
    SmoothEdge: 'SmoothEdge',
    Flat: 'Flat',
    HiddenLine: 'HiddenLine',
    HiddenLine_Elimination: 'HiddenLine_Elimination',
    Xray: 'Xray'
};

const RENDER_MODES_TEXT = {
    Wireframe: '와이어프레임',
    Smooth: '부드러운음영',
    Flat: '거친음영'
};

const EVENT_TYPES = {
    View: {
        Init: 'View.Init',
        Loading_Complete: 'View.Loading_Complete'
    },
    Model: {
        Select: 'Model.Select'
    },
    Data: {
        Selected: 'Data.Selected',
        Deselect_All:'Data.Deselect_All'
    },
    Toolbar: {
        Show: 'Toolbar.Show',
        Hide: 'Toolbar.Hide'
    },
    Progress: {
        Percentage: 'Progress.Percentage'
    },
    Control: {
        Changed: 'Control.Changed'
    },
    Pivot: {
        DrawRender: 'Pivot.DrawRender'
    },
    Keyboard: {
        Down: 'Keyboard.Down'
    },
    Keyboard_SystemKey: {
        Down: 'Keyboard.Down.SystemKey'
    }
};

const MEASURE_UNIT = {
    mm: 'mm',
    cm: 'cm',
    inch: 'inch'
};

const PROGRESS_TYPES = {
    File_Downloading: 0,
    Data_Loading: 1,
    Edge_Loading: 2
};

const CAMERA_DIRECTIONS = {
    PlusX: '+X',
    MinusX: '-X',
    PlusY: '+Y',
    MinusY: '-Y',
    PlusZ: '+Z',
    MinusZ: '-Z',
    PlusISO: '+ISO',
    MinusISO: '-ISO'
};

const PROJECTION_MODES = {
    Orthographic: 0,
    Perspective: 1
};

const ENTITY_TYPES = {
    EntUnknown : 0,
    EntFileHeader : 100,

    EntAssembly : 500,
    EntPart : 501,
    EntCurveBSpline: 502,
    EntSurfNurbs: 503,
    EntRefNode: 504,
    EntSurfGeneric: 505,
    EntInstCurve: 506,
    EntBody: 507,
    EntCurveLine: 508,
    EntCurveCircle: 509,
    EntMeshBlock: 510,
    EntBinaryBlock: 511,
    EntCFLBody: 512,

    EntFolder: 513,
    EntPoint: 514,
    EntPolyline: 515,
    EntCircle: 516,

    BST_STYLING_MODEL : 800,	
    BST_DRAWING_MODEL : 900,	

    BST_ANALYSIS : 1000,	
    BST_INSPECTION : 2000,
    EntEdgeBlock : 3000,
};

const SELECT_UNIT = {
    Part: 0,
    Body: 1
};

const REVIEW_TYPES = {
    NONE: 0,
    RK_MEASURE_POS: 1,
    RK_MEASURE_DISTANCE: 2,
    RK_MEASURE_ANGLE: 3,
    RK_SURFACE_NOTE: 4,
    RK_2D_NOTE: 5,
    RK_3D_NOTE: 6
};

const BROWSER_TYPES = {
    Unknown:0,
    Internet_Explorer: 1,
    Edge: 2,
    Chrome: 3,
    Firefox: 4,
    Safari: 5,
    Opera: 6
};

const PLATFORM_TYPES = {
    PC: 0,
    Mobile: 1
};

const THEMA_TYPES = {
    Basic: 0,
    Splitter: 1
};

const DRAWING_TYPES = {
    NONE  : 0,
    FREE: 1,
    LINE : 2,
    QUADRANGLE: 3,
    CIRCLE: 4
};

const PROCESS_TYPES = {
    NONE: 0,
    MOVE: 1,
};