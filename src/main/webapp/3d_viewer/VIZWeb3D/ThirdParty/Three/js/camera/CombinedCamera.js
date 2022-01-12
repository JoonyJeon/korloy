THREE.CombinedCamera = function (width, height, fov, near, far, orthoNear, orthoFar, view) {

    THREE.Camera.call(this);
    this.view = view;
    this.fov = fov;

    this.left = -width / 2;
    this.right = width / 2;
    this.top = height / 2;
    this.bottom = -height / 2;
    this.width = width;
    this.height = height;

    //    this.left = -width * offset;
    //    this.right = width * offset;
    //    this.top = height * offset;
    //    this.bottom = -height * offset;

    this.offset = 0;

    // We could also handle the projectionMatrix internally, but just wanted to test nested camera objects

    this.cameraO = new THREE.OrthographicCamera(width / -2, width / 2, height / 2, height / -2, orthoNear, orthoFar);
    //this.cameraO = new THREE.OrthographicCamera(-width, width, height, -height, orthoNear, orthoFar);
    this.cameraP = new THREE.PerspectiveCamera(fov, width / height, near, far);
    //this.cameraP = new THREE.PerspectiveCamera(65, window.innerWidth / window.innerHeight, 1, 10000000);

    this.zoom = 1;
    this.factor = 1;
    this.vPosition = new THREE.Vector3(0, 0, 0);
    this.vCenter = new THREE.Vector3(0, 0, 0);
    this.target = new THREE.Vector3(0, 0, 0);
    this.distance = 0;

    //this.toPerspective();
    this.toOrthographic();

    this.m_fModelRadius = 0;
};

THREE.CombinedCamera.prototype = Object.create( THREE.Camera.prototype );
THREE.CombinedCamera.prototype.constructor = THREE.CombinedCamera;

//THREE.CombinedCamera.prototype.setViewVolumn = function () {
//    var fov = this.fov;
//    var aspect = this.cameraP.aspect;
//    var near = this.cameraP.near;
//    var far = this.cameraP.far;

//    // The size that we set is the mid plane of the viewing frustum
//    this.vPosition = this.position.clone();
//    // 현재 카메라와 모델 중심과의 거리 계산
//    var vCamera = this.vPosition.clone();
//    var vTarget = this.vCenter.clone();

//    this.offset = vCamera.distanceTo(vTarget);
//    this.distance = 0;
//    //this.offset = distance;

//    // The size that we set is the mid plane of the viewing frustum
//    //var hyperfocus = this.offset + this.distance;
//    var hyperfocus = this.offset;

//    var halfHeight = Math.tan(fov * Math.PI / 180 / 2) * hyperfocus;
//    var planeHeight = 2 * halfHeight;
//    var planeWidth = planeHeight * aspect;
//    var halfWidth = planeWidth / 2;

//    halfHeight /= this.zoom;
//    halfWidth /= this.zoom;

//    this.cameraO.left = -halfWidth;
//    this.cameraO.right = halfWidth;
//    this.cameraO.top = halfHeight;
//    this.cameraO.bottom = -halfHeight;

//    this.cameraO.updateProjectionMatrix();

//    this.near = this.cameraO.near;
//    this.far = this.cameraO.far;
//    this.projectionMatrix = this.cameraO.projectionMatrix;

//    this.isPerspectiveCamera = false;
//    this.isOrthographicCamera = true;
//}

THREE.CombinedCamera.prototype.toPerspective = function () {

	// Switches to the Perspective Camera
    var width = this.m_fModelRadius * 1.5 * this.factor;
    var height = this.m_fModelRadius * 1.5 * this.factor;

    var fAspect = this.cameraP.aspect;
    if (fAspect > 1.0)
        width *= fAspect;
    else
        height /= fAspect;

    width /= 2.0;
    height /= 2.0;

    this.cameraP.projectionMatrix.makePerspective(-width, width, height, -height, this.cameraP.near, this.cameraP.far);

    this.cameraP.updateProjectionMatrix();

    this.near = this.cameraP.near;
    this.far = this.cameraP.far;

	this.projectionMatrix = this.cameraP.projectionMatrix;

    this.isPerspectiveCamera = true;
    this.isOrthographicCamera = false;
};

THREE.CombinedCamera.prototype.toOrthographic = function () {

    // 현재 카메라와 모델 중심과의 거리 계산
    var vCamera = this.vPosition.clone();
    var vTarget = this.vCenter.clone();

    //var depht_s = Math.tan(this.fov / 2.0 * Math.PI / 180.0) * 2.0;
    //var eye = this.cameraP.position.clone();
    //var aspect = this.cameraP.aspect;
    //var size_y = depht_s * eye.z;
    //var size_x = depht_s * eye.z * aspect; 

    //this.offset = distance;

    var width = this.m_fModelRadius * 1.5 * this.factor;
    var height = this.m_fModelRadius * 1.5 * this.factor;
    //var width = this.m_fModelRadius * 1.5 / this.zoom;
    //var height = this.m_fModelRadius * 1.5 / this.zoom;

    var fAspect = this.cameraP.aspect;
    if (fAspect > 1.0)
        width *= fAspect;
    else
        height /= fAspect;

    width /= 2.0;
    height /= 2.0;

    this.cameraO.left = -width;
    this.cameraO.right = width;
    this.cameraO.top = height;
    this.cameraO.bottom = -height;

    this.cameraO.updateProjectionMatrix();

    this.near = this.cameraO.near;
    this.far = this.cameraO.far;
    this.projectionMatrix = this.cameraO.projectionMatrix;

    //this.inPerspectiveMode = false;
    //this.inOrthographicMode = true;
    this.isPerspectiveCamera = false;
    this.isOrthographicCamera = true;

};

THREE.CombinedCamera.prototype.setCameraMode = function (mode) {

    if (mode === PROJECTION_MODES.Orthographic) {
        this.isPerspectiveCamera = false;
        this.isOrthographicCamera = true;
    } else {
        this.isPerspectiveCamera = true;
        this.isOrthographicCamera = false;
    }
};
            
THREE.CombinedCamera.prototype.addLength = function (distance) {
    var fov = this.fov;
    var aspect = this.cameraP.aspect;
    var near = this.cameraP.near;
    var far = this.cameraP.far;

    var offsetHeight = Math.tan(fov * Math.PI / 180 / 2) * distance;
    var planeHeight = 2 * offsetHeight;
    var planeWidth = (planeHeight * aspect);
    var offsetWidth = planeWidth / 2;

    this.cameraO.left += -offsetWidth;
    this.cameraO.right += offsetWidth;
    this.cameraO.top += offsetHeight;
    this.cameraO.bottom += -offsetHeight;

    this.cameraO.updateProjectionMatrix();

    this.near = this.cameraO.near;
    this.far = this.cameraO.far;
    this.projectionMatrix = this.cameraO.projectionMatrix;

    //this.inPerspectiveMode = false;
    //this.inOrthographicMode = true;
    this.isPerspectiveCamera = false;
    this.isOrthographicCamera = true;
};

THREE.CombinedCamera.prototype.setSize = function( width, height ) {

    this.cameraP.aspect = width / height;

	this.left = - width / 2;
	this.right = width / 2;
	this.top = height / 2;
	this.bottom = - height / 2;

};


THREE.CombinedCamera.prototype.setFov = function( fov ) {

	this.fov = fov;

	//if ( this.inPerspectiveMode ) {
    if (this.isPerspectiveCamera) {
        this.toPerspective();
    } else {
		this.toOrthographic();
	}

};

// For maintaining similar API with PerspectiveCamera

THREE.CombinedCamera.prototype.updateProjectionMatrix = function() {

	//if ( this.inPerspectiveMode ) {
    if (this.isPerspectiveCamera) {
		this.toPerspective();

	} else {
		//this.toPerspective();
		this.toOrthographic();
	}

};

/*
* Uses Focal Length (in mm) to estimate and set FOV
* 35mm (fullframe) camera is used if frame size is not specified;
* Formula based on http://www.bobatkins.com/photography/technical/field_of_view.html
*/
THREE.CombinedCamera.prototype.setLens = function ( focalLength, frameHeight ) {

	if ( frameHeight === undefined ) frameHeight = 24;

	var fov = 2 * THREE.Math.radToDeg( Math.atan( frameHeight / ( focalLength * 2 ) ) );

	this.setFov( fov );

	return fov;

};


THREE.CombinedCamera.prototype.setZoom = function( zoom ) {

	this.zoom = zoom;

	//if ( this.inPerspectiveMode ) {
    if (this.isPerspectiveCamera) {

		this.toPerspective();

	} else {

		this.toOrthographic();

	}

};

THREE.CombinedCamera.prototype.toFrontView = function() {

	this.rotation.x = 0;
	this.rotation.y = 0;
	this.rotation.z = 0;

	// should we be modifing the matrix instead?

	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.toBackView = function() {

	this.rotation.x = 0;
	this.rotation.y = Math.PI;
	this.rotation.z = 0;
	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.toLeftView = function() {

	this.rotation.x = 0;
	this.rotation.y = - Math.PI / 2;
	this.rotation.z = 0;
	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.toRightView = function() {

	this.rotation.x = 0;
	this.rotation.y = Math.PI / 2;
	this.rotation.z = 0;
	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.toTopView = function() {

	this.rotation.x = - Math.PI / 2;
	this.rotation.y = 0;
	this.rotation.z = 0;
	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.toBottomView = function() {

	this.rotation.x = Math.PI / 2;
	this.rotation.y = 0;
	this.rotation.z = 0;
	this.rotationAutoUpdate = false;

};

THREE.CombinedCamera.prototype.World2Screen = function (position, camera) {
    

    var pos = position.clone();
    //var projScreenMat = new THREE.Matrix4();
    //projScreenMat.multiplyMatrices(camera.projectionMatrix, camera.matrixWorldInverse);
    //projScreenMat.multiplyMatrices(camera.projectionMatrix, camera.matrixWorld);
    //pos.applyProjection(projScreenMat);
    pos.project(camera);

    //var rect = container.getBoundingClientRect();

    //return {
    //    x: ((pos.x + 1) * (rect.width + container.offsetLeft) / 2) + container.offsetLeft, // - rect.left,
    //    y: ((-pos.y + 1) * (rect.height + container.offsetTop) / 2) + container.offsetTop, // + rect.top,
    //};

    //return { x: (pos.x + 1) * window.innerWidth / 2,
    //    y: (-pos.y + 1) * window.innerHeight / 2
    //};

    //return new THREE.Vector2((pos.x + 1) * window.innerWidth / 2, (-pos.y + 1) * window.innerHeight / 2);
    //return new THREE.Vector3((pos.x + 1) * window.innerWidth / 2, (-pos.y + 1) * window.innerHeight / 2, position.z);
    return new THREE.Vector3((pos.x + 1) * window.innerWidth / 2, (-pos.y + 1) * window.innerHeight / 2, pos.z);

};

THREE.CombinedCamera.prototype.Screen2World = function (position, camera) {
    var mouse = new THREE.Vector2();
    mouse.set(
        (( position.x / this.width ) * 2 ) - 1,
        1 - (( position.y / this.height) * 2 )

        //[2 * ((x - rect.left) / rect.width) - 1, 1 - 2 * ((y - rect.top) / rect.height)];
    );

    if (this.isPerspectiveCamera) {
        var vector = new THREE.Vector3(mouse.x, mouse.y, 0.5);
        vector = vector.unproject(camera);
        //vector.z = position.z;
        return vector;
    }
    else {
        var origin = new THREE.Vector3(mouse.x, mouse.y, 0.5);
        origin = origin.unproject(camera);
        //origin.z = position.z;
        return origin;
    }
};

THREE.CombinedCamera.prototype.projectionMatrixInverse = function () {
    if (this.isPerspectiveCamera)
        return this.cameraP.projectionMatrixInverse;
    else
        return this.cameraO.projectionMatrixInverse;
};

