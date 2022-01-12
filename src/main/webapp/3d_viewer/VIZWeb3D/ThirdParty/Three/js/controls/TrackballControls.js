/**
 * @author Eberhard Graether / http://egraether.com/
 * @author Mark Lundin 	/ http://mark-lundin.com
 * @author Simone Manini / http://daron1337.github.io
 * @author Luca Antiga 	/ http://lantiga.github.io
 */

THREE.TrackballControls = function (object, domElement, view) {

    var _this = this;
    var STATE = { NONE: -1, ROTATE: 0, ZOOM: 1, PAN: 2, TOUCH_ROTATE: 3, TOUCH_ZOOM_PAN: 4 };
    var VIEWROTATE = { HORIZONTAL: 0, VERTICAL: 1 };
    this.object = object;
    this.domElement = (domElement !== undefined) ? domElement : document;
    this.view = view;

    // API

    this.enabled = true;

    this.screen = { left: 0, top: 0, width: 0, height: 0 };

    this.rotateSpeed = 1.0;
    this.zoomSpeed = 1.2;
    this.panSpeed = 0.3;

    this.noRotate = false;
    this.noZoom = false;
    this.noPan = false;
    this.noViewRotate = true;
    var ViewRotateType = VIEWROTATE.HORIZONTAL;

    this.staticMoving = false;
    this.dynamicDampingFactor = 0.2;

    this.minDistance = 0;
    this.maxDistance = Infinity;

    this.keys = [65 /*A*/, 83 /*S*/, 68 /*D*/];

    // internals

    this.target = new THREE.Vector3();
    this.center = new THREE.Vector3();
    this.Pivot = new THREE.Vector3(0, 0, 0);
    this.ZoomPos = undefined;
    this.ZoomSpace = undefined;
    this.EventCnt = 0;
    this.bPivot = false;
    this.PivotProcess = 0;
    var EPS = 0.000001;

    var lastPosition = new THREE.Vector3();

    var _state = STATE.NONE,
        _prevState = STATE.NONE,

        _eye = new THREE.Vector3(),

        _movePrev = new THREE.Vector2(),
        _moveCurr = new THREE.Vector2(),

        _lastAxis = new THREE.Vector3(),
        _lastAngle = 0,

        _zoomStart = new THREE.Vector2(),
        _zoomEnd = new THREE.Vector2(),

        _touchZoomDistanceStart = 0,
        _touchZoomDistanceEnd = 0,
        _touchZoomDistanceOffset = 10;
    _touchZoomDistanceStartTmp = 0;

	_panStart = new THREE.Vector2(),
    _panEnd = new THREE.Vector2();
    _panS = new THREE.Vector2(),
    _panE = new THREE.Vector2();

    // for reset

    this.target0 = this.target.clone();
    this.position0 = this.object.position.clone();
    this.up0 = this.object.up.clone();

    // events

    var changeEvent = { type: 'change' };
    var startEvent = { type: 'start' };
    var endEvent = { type: 'end' };


    // methods

    this.handleResize = function () {

        if (this.domElement === document) {

            this.screen.left = 0;
            this.screen.top = 0;
            this.screen.width = window.innerWidth;
            this.screen.height = window.innerHeight;

        } else {

            var box = this.domElement.getBoundingClientRect();
            // adjustments come from similar code in the jquery offset() function
            var d = this.domElement.ownerDocument.documentElement;
            this.screen.left = box.left + window.pageXOffset - d.clientLeft;
            this.screen.top = box.top + window.pageYOffset - d.clientTop;
            this.screen.width = box.width;
            this.screen.height = box.height;

        }

    };

    this.handleEvent = function (event) {

        if (typeof this[event.type] == 'function') {

            this[event.type](event);

        }

    };

    var getMousePosition = (function (x, y) {
        var vector = new THREE.Vector2();
            vector.set(
                2 * ((x - _this.screen.left) / _this.screen.width) - 1,
                1 - 2 * ((y - _this.screen.top) / _this.screen.height)
            );
        return vector;
    });

    var getScreenOnMouse = (function () {

        var vector = new THREE.Vector2();

        return function (x, y) {

            vector.set(
                (x * _this.screen.width) + _this.screen.left, //(pageX - _this.screen.left) / _this.screen.width,
                (y * _this.screen.height) + _this.screen.top//(pageY - _this.screen.top) / _this.screen.height
            );

            return vector;

        };

    }());

    var getMouseOnScreen = (function () {

        var vector = new THREE.Vector2();

        return function (pageX, pageY) {

            vector.set(
				(pageX - _this.screen.left) / _this.screen.width,
				(pageY - _this.screen.top) / _this.screen.height
			);

            return vector;

        };

    } ());

    var getMouseOnCircle = (function () {

        var vector = new THREE.Vector2();

        return function (pageX, pageY) {

            vector.set(
				((pageX - _this.screen.width * 0.5 - _this.screen.left) / (_this.screen.width * 0.5)),
				((_this.screen.height + 2 * (_this.screen.top - pageY)) / _this.screen.width) // screen.width intentional
			);

            return vector;
        };

    } ());

    this.rotateCamera = (function () {

        var axis = new THREE.Vector3(),
			quaternion = new THREE.Quaternion(),
			eyeDirection = new THREE.Vector3(),
			objectUpDirection = new THREE.Vector3(),
			objectSidewaysDirection = new THREE.Vector3(),
			moveDirection = new THREE.Vector3(),
			angle;

        return function () {

            moveDirection.set(_moveCurr.x - _movePrev.x, _moveCurr.y - _movePrev.y, 0);
            angle = moveDirection.length();

            if (angle) {

                _eye.copy(_this.object.position).sub(_this.target);

                eyeDirection.copy(_eye).normalize();
                objectUpDirection.copy(_this.object.up).normalize();
                objectSidewaysDirection.crossVectors(objectUpDirection, eyeDirection).normalize();

                objectUpDirection.setLength(_moveCurr.y - _movePrev.y);
                objectSidewaysDirection.setLength(_moveCurr.x - _movePrev.x);

                moveDirection.copy(objectUpDirection.add(objectSidewaysDirection));

                axis.crossVectors(moveDirection, _eye).normalize();

                angle *= _this.rotateSpeed;
                quaternion.setFromAxisAngle(axis, angle);

                _eye.applyQuaternion(quaternion);
                
                //_this.object.up.applyQuaternion(quaternion);
                _this.object.up.applyQuaternion(quaternion);

                //var pivotPos = new THREE.Vector3(24699.90, 2875.0, 8685.0);

                var targetPos = _this.target.clone();
                var targetOffetPos = _this.target.clone();
                if (_this.bPivot) {
                    //targetOffetPos.sub(pivotPos);
                    targetOffetPos.sub(_this.Pivot);
                }

                var newTargetOffetPos = targetOffetPos.clone();
                newTargetOffetPos.applyQuaternion(quaternion);

                var fixPivotOffset = targetOffetPos.clone();
                fixPivotOffset.sub(newTargetOffetPos);
                if (_this.bPivot) {
                    _this.target.sub(fixPivotOffset);
                }
            }

            else if (!_this.staticMoving && _lastAngle) {

                _lastAngle *= Math.sqrt(1.0 - _this.dynamicDampingFactor);
                _eye.copy(_this.object.position).sub(_this.target);
                quaternion.setFromAxisAngle(_lastAxis, _lastAngle);
                _eye.applyQuaternion(quaternion);
                _this.object.up.applyQuaternion(quaternion);

            }

            _movePrev.copy(_moveCurr);

        };

    } ());

    this.viewCamera = (function () {

        var axis = new THREE.Vector3(),
			quaternion = new THREE.Quaternion(),
			eyeDirection = new THREE.Vector3(),
			objectUpDirection = new THREE.Vector3(),
			objectSidewaysDirection = new THREE.Vector3(),
			moveDirection = new THREE.Vector3(),
			angle;

        return function () {

            if (ViewRotateType == VIEWROTATE.HORIZONTAL) {
                _moveCurr.y = 0; _movePrev.y = 0;
            }
            else {
                _moveCurr.x = 0; _movePrev.x = 0;
            }
            moveDirection.set(_moveCurr.x - _movePrev.x, _moveCurr.y - _movePrev.y, 0);
            angle = moveDirection.length();

            if (angle) {

                _eye.copy(_this.object.position).sub(_this.target);

                eyeDirection.copy(_eye).normalize();
                objectUpDirection.copy(_this.object.up).normalize();
                objectSidewaysDirection.crossVectors(objectUpDirection, eyeDirection).normalize();

                objectUpDirection.setLength(_moveCurr.y - _movePrev.y);
                objectSidewaysDirection.setLength(_moveCurr.x - _movePrev.x);

                moveDirection.copy(objectUpDirection.add(objectSidewaysDirection));

                axis.crossVectors(moveDirection, _eye).normalize();

                angle *= _this.rotateSpeed;
                quaternion.setFromAxisAngle(axis, angle);

                _eye.applyQuaternion(quaternion);
                _this.object.up.applyQuaternion(quaternion);

                _lastAxis.copy(axis);
                _lastAngle = angle;

            }

            else if (!_this.staticMoving && _lastAngle) {

                _lastAngle *= Math.sqrt(1.0 - _this.dynamicDampingFactor);
                _eye.copy(_this.object.position).sub(_this.target);
                quaternion.setFromAxisAngle(_lastAxis, _lastAngle);
                _eye.applyQuaternion(quaternion);
                _this.object.up.applyQuaternion(quaternion);

            }

            _movePrev.copy(_moveCurr);

        };

    } ());

    this.zoomCameraCustom = function (delta) {

        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        _zoomStart.y += delta * 0.01;
        _this.dispatchEvent(startEvent);
        _this.dispatchEvent(endEvent);

    };

    this.zoomCamera = function () {

        var factor;

        if (_state === STATE.TOUCH_ZOOM_PAN) {
            
            factor = _touchZoomDistanceStart / _touchZoomDistanceEnd;
            _touchZoomDistanceStart = _touchZoomDistanceEnd;

            if (_this.object.inPerspectiveMode) {
                _eye.multiplyScalar(factor);
                _this.object.toPerspective();
                //_eye.multiplyScalar(factor);
            } else {
                if (factor !== 1.0 && factor > 0.0) {
                    var eyePos = _this.object.position.clone();
                    var eyeOffetPos = eyePos.clone();
                    if (!_this.bPivot)
                        _this.Pivot = _this.target.clone();

                    
                    eyeOffetPos.sub(_this.Pivot);
                    eyeOffetPos.multiplyScalar(-(1.0 - factor));

                    _this.target.add(eyeOffetPos);

                    var vTmp = new THREE.Vector3(0, 0, 0);
                    var distance = vTmp.distanceTo(eyeOffetPos);
                    if (factor > 1) {
                        _this.object.distance += distance;
                    }
                    else {
                        _this.object.distance -= distance;
                    }

                    _this.object.factor *= factor;

                    if (_this.object.inPerspectiveMode) {
                    } else {
                        _this.object.updateProjectionMatrix();
                    }

                    if (_this.staticMoving) {
                        _zoomStart.copy(_zoomEnd);
                    } else {
                        _zoomStart.y += (_zoomEnd.y - _zoomStart.y) * this.dynamicDampingFactor;
                    }
                }
            }
        } else {

            factor = 1.0 + (_zoomEnd.y - _zoomStart.y) * _this.zoomSpeed;

            if (factor !== 1.0 && factor > 0.0) {
                //_eye.multiplyScalar(factor);
                var eyePos = _this.object.position.clone();
                var eyeOffetPos = eyePos.clone();

                if (!_this.bPivot)
                    _this.Pivot = _this.target.clone();
                
                eyeOffetPos.sub(_this.Pivot);
                eyeOffetPos.multiplyScalar(-(1.0 - factor));

                //_eye.add(eyeOffetPos);
                _this.target.add(eyeOffetPos);

                //if (!_this.object.inPerspectiveMode)
                _this.object.factor *= factor;
                //console.log("Factor : " + _this.object.factor);

                _this.object.updateProjectionMatrix();
                

                if (_this.staticMoving) {
                    _zoomStart.copy(_zoomEnd);
                } else {
                    _zoomStart.y += (_zoomEnd.y - _zoomStart.y) * this.dynamicDampingFactor;
                }
            }

        }

    };

    this.panCamera = (function () {

        var mouseChange = new THREE.Vector2(),
			objectUp = new THREE.Vector3(),
			pan = new THREE.Vector3();

        return function () {

            mouseChange.copy(_panEnd).sub(_panStart);

            var distanceX = _panE.x - _panS.x;
            var distanceY = _panE.y - _panS.y;

            if (Math.abs(distanceX) < 0.00001 && Math.abs(distanceY) < 0.00001)
                _panS.copy(_panE);

            if (mouseChange.lengthSq()) {

                var mouseS = new THREE.Vector2();
                var mouseE = new THREE.Vector2();
                mouseS.set(
                    //_panS.x / window.innerWidth * 2 - 1,
                    //-(_panS.y / window.innerHeight) * 2 + 1
                    _panS.x / this.screen.width * 2 - 1,
                    -(_panS.y / this.screen.height) * 2 + 1
                );
                mouseE.set(
                    //_panE.x / window.innerWidth * 2 - 1,
                    //-(_panE.y / window.innerHeight) * 2 + 1
                    _panE.x / this.screen.width * 2 - 1,
                    -(_panE.y / this.screen.height) * 2 + 1
                );

                var vStart = new THREE.Vector3();
                var vEnd = new THREE.Vector3();
                var vector = new THREE.Vector3();
                if (camera.isPerspectiveCamera) {
                    mouseChange.multiplyScalar(_eye.length() * _this.panSpeed * _this.object.factor * 1.5);
                    pan.copy(_eye).cross(_this.object.up).setLength(mouseChange.x);
                    pan.add(objectUp.copy(_this.object.up).setLength(mouseChange.y));

                    _this.object.position.add(pan);
                    _this.target.add(pan);

                    //var multiply = function (m_mat, a) {
                    //    var ret = new THREE.Vector3(0, 0, 0);

                    //    ret.x = a.x * m_mat.elements[0] + a.y * m_mat.elements[4] + a.z * m_mat.elements[8] + m_mat.elements[12];
                    //    ret.y = a.x * m_mat.elements[1] + a.y * m_mat.elements[5] + a.z * m_mat.elements[9] + m_mat.elements[13];
                    //    ret.z = a.x * m_mat.elements[2] + a.y * m_mat.elements[6] + a.z * m_mat.elements[10] + m_mat.elements[14];
                    //    var w = a.x * m_mat.elements[3] + a.y * m_mat.elements[7] + a.z * m_mat.elements[11] + m_mat.elements[15];

                    //    if (Math.abs(w) > 0.0000001) {
                    //        ret.x /= w;
                    //        ret.y /= w;
                    //        ret.z /= w;
                    //    }
                    //    return ret;
                    //};
                    ////var tmp = multiply(this.cameraP.matrixWorld, vPivot);


                    //vStart = new THREE.Vector3(mouseS.x, mouseS.y, -1);
                    //vStart = vStart.unproject(camera.cameraO);
                    //vEnd = new THREE.Vector3(mouseE.x, mouseE.y, -1);
                    //vEnd = vEnd.unproject(camera.cameraO);
                    //vector = vStart.sub(vEnd);

                    //_this.object.position.add(vector);
                    //_this.target.add(vector);
                }
                else
                {
                    vStart = new THREE.Vector3(mouseS.x, mouseS.y, -1);
                    vStart = vStart.unproject(camera);
                    vEnd = new THREE.Vector3(mouseE.x, mouseE.y, -1);
                    vEnd = vEnd.unproject(camera);
                    vector = vStart.sub(vEnd);
                    
                    _this.object.position.add(vector);
                    _this.target.add(vector);
                }

                //mouseChange.multiplyScalar(_eye.length() * _this.panSpeed * off);
                //pan.copy(_eye).cross(_this.object.up).setLength(mouseChange.x);
                //pan.add(objectUp.copy(_this.object.up).setLength(mouseChange.y));

                //_this.object.position.add(pan);
                //_this.target.add(pan);

                if (_this.staticMoving) {

                    _panStart.copy(_panEnd);
                    _panS.copy(_panE);

                } else {
                    //_panStart.add(mouseChange.subVectors(_panEnd, _panStart).multiplyScalar(_this.dynamicDampingFactor));
                    //_panS.add(mouseChange.subVectors(_panE, _panS).multiplyScalar(_this.dynamicDampingFactor));
                    _panStart.copy(_panEnd);
                    _panS.copy(_panE);
                }

            }
        };

    } ());

    this.checkDistances = function () {

        if (!_this.noZoom || !_this.noPan) {

            if (_eye.lengthSq() > _this.maxDistance * _this.maxDistance) {

                _this.object.position.addVectors(_this.target, _eye.setLength(_this.maxDistance));

            }

            if (_eye.lengthSq() < _this.minDistance * _this.minDistance) {

                _this.object.position.addVectors(_this.target, _eye.setLength(_this.minDistance));

            }

        }

    };

    this.update = function () {
        
        _eye.subVectors(_this.object.position, _this.target);

        _this.object.vPosition = _this.object.position.clone();

        if (!_this.noRotate) {
            _this.rotateCamera();
        }

        if (!_this.noViewRotate) {

            _this.viewCamera();

        }

        if (!_this.noZoom) {
            _this.zoomCamera();
        }

        if (!_this.noPan) {
            _this.panCamera();
        }

        if (_state === STATE.ZOOM && !_this.object.inPerspectiveMode) {

        }
        else
        {
            _this.object.position.addVectors(_this.target, _eye);
        }

        _this.checkDistances();

        _this.object.lookAt(_this.target);
        
        if (lastPosition.distanceToSquared(_this.object.position) > EPS) {
            _this.dispatchEvent(changeEvent);

            lastPosition.copy(_this.object.position);

        }
    };

    this.reset = function () {

        _state = STATE.NONE;
        _prevState = STATE.NONE;

        _this.target.copy(_this.target0);
        _this.object.position.copy(_this.position0);
        _this.object.up.copy(_this.up0);

        _eye.subVectors(_this.object.position, _this.target);

        _this.object.lookAt(_this.target);

        _this.dispatchEvent(changeEvent);

        lastPosition.copy(_this.object.position);

    };

    this.setViewRotateType = function (value) {
        if (value == 0)
            ViewRotateType = VIEWROTATE.HORIZONTAL;
        else
            ViewRotateType = VIEWROTATE.VERTICAL;
    };

    // listeners

    function keydown(event) {

        if (_this.enabled === false) return;

        window.removeEventListener('keydown', keydown);

        _prevState = _state;

        if (_state !== STATE.NONE) {

            return;

        } else if (event.keyCode === _this.keys[STATE.ROTATE] && !_this.noRotate) {

            _state = STATE.ROTATE;

        }
        else if (event.keyCode === _this.keys[STATE.ZOOM] && !_this.noZoom) {

            _state = STATE.ZOOM;

        } else if (event.keyCode === _this.keys[STATE.PAN] && !_this.noPan) {

            _state = STATE.PAN;

        }

    }

    function keyup(event) {

        if (_this.enabled === false) return;

        _state = _prevState;

        window.addEventListener('keydown', keydown, false);

    }

    function mousedown(event) {

        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        if (_state === STATE.NONE) {
            _state = event.button;
            // wheel down
            if (_state === 1) {
                _state = STATE.ROTATE;
                _wheelDown = true;
                if (_this.PivotProcess === 1) {
                    var intersects = _this.view.Picking.PickInfo(event);
                    if (intersects !== undefined && intersects.length > 0) {
                        _this.Pivot.copy(intersects[0].point);
                    }
                }
            }
        }

        /// 화면 축 회전 확인
        var rect = _this.domElement.getBoundingClientRect();
        var hminx = rect.width / 2 - 100 + rect.left;
        var hmaxx = rect.width / 2 + 100 + rect.left;
        var hminy = rect.top; //rect.height - 50 + rect.top;
        var hmaxy = rect.top + 50; //rect.height + rect.top;

        var vminx = rect.width - 50 + rect.left;
        var vmaxx = rect.width + rect.left;
        var vminy = rect.height / 2 - 100 + rect.top;
        var vmaxy = rect.height / 2 + 100 + rect.top;

        var bCheckPosition = false;
        if (hminx < event.pageX && event.pageX < hmaxx && hminy < event.pageY && event.pageY < hmaxy) {
            bCheckPosition = true;
            ViewRotateType = VIEWROTATE.HORIZONTAL;
        }

        if (vminx < event.pageX && event.pageX < vmaxx && vminy < event.pageY && event.pageY < vmaxy) {
            bCheckPosition = true;
            ViewRotateType = VIEWROTATE.VERTICAL;
        }

        if (bCheckPosition) {
            _this.noRotate = true;
            _this.noViewRotate = false;
        }
        else {
            _this.noViewRotate = true;
            _this.noRotate = false;
        }


        if (_state === STATE.ROTATE && !_this.noRotate) {

            _moveCurr.copy(getMouseOnCircle(event.pageX, event.pageY));
            _movePrev.copy(_moveCurr);
            

        } else if (_state === STATE.ROTATE && !_this.noViewRotate) {

            _moveCurr.copy(getMouseOnCircle(event.pageX, event.pageY));
            _movePrev.copy(_moveCurr);

        } else if (_state === STATE.ZOOM && !_this.noZoom) {

            _zoomStart.copy(getMouseOnScreen(event.pageX, event.pageY));
            _zoomEnd.copy(_zoomStart);

        } else if (_state === STATE.PAN && !_this.noPan) {

            _panStart.copy(getMouseOnScreen(event.pageX, event.pageY));
            _panEnd.copy(_panStart);
            _panS.x = event.pageX;
            _panS.y = event.pageY;
            _panE.copy(_panS);
        }

        document.addEventListener('mousemove', mousemove, false);
        document.addEventListener('mouseup', mouseup, false);

        _this.dispatchEvent(startEvent);

    }

    function mousemove(event) {
        
        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        if (_state === STATE.ROTATE && !_this.noRotate) {

            _movePrev.copy(_moveCurr);
            _moveCurr.copy(getMouseOnCircle(event.pageX, event.pageY));
        } else if (_state === STATE.ROTATE && !_this.noViewRotate) {

            _movePrev.copy(_moveCurr);
            _moveCurr.copy(getMouseOnCircle(event.pageX, event.pageY));

        } else if (_state === STATE.ZOOM && !_this.noZoom) {

            _zoomEnd.copy(getMouseOnScreen(event.pageX, event.pageY));

        } else if (_state === STATE.PAN && !_this.noPan) {

            _panEnd.copy(getMouseOnScreen(event.pageX, event.pageY));
            _panE.x = event.pageX;
            _panE.y= event.pageY;
        }
        _this.update();
    }

    function mouseup(event) {

        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        _state = STATE.NONE;
        _wheelDown = false;

        document.removeEventListener('mousemove', mousemove);
        document.removeEventListener('mouseup', mouseup);
        _this.dispatchEvent(endEvent);

    }
    var timeBegan = null
        , timeStopped = null
        , stoppedDuration = 0
        , started = null;

    function start() {
        if (timeBegan === null) {
            timeBegan = new Date();
        }

        if (timeStopped !== null) {
            stoppedDuration += (new Date() - timeStopped);
        }
        console.log(stoppedDuration);

        started = setInterval(clockRunning, 10);
    }

    function stop() {
        timeStopped = new Date();
        clearInterval(started);
    }

    function reset() {
        clearInterval(started);
        stoppedDuration = 0;
        timeBegan = null;
        timeStopped = null;
    }

    function clockRunning() {
        var currentTime = new Date()
            , timeElapsed = new Date(currentTime - timeBegan - stoppedDuration)
            , hour = timeElapsed.getUTCHours()
            , min = timeElapsed.getUTCMinutes()
            , sec = timeElapsed.getUTCSeconds()
            , ms = timeElapsed.getUTCMilliseconds();

        console.log( (hour > 9 ? hour : "0" + hour) + ":" + (min > 9 ? min : "0" + min) + ":" + (sec > 9 ? sec : "0" + sec) + "." + (ms > 99 ? ms : ms > 9 ? "0" + ms : "00" + ms));
    };

    function mousewheel(event) {

        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        var delta = 0;

        if (event.wheelDelta) { // WebKit / Opera / Explorer 9

            delta = event.wheelDelta / 40;

        } else if (event.detail) { // Firefox

            delta = -event.detail / 3;

        }

        _zoomStart.y += delta * 0.01;

        //if (_this.ZoomSpace === undefined) {
        //    var intersects = _this.view.Picking.PickInfo(event);
        //    _this.ZoomSpace = true;
        //}

        if (_this.PivotProcess === 0) {
            if (_this.ZoomSpace === undefined) {
                var intersects = _this.view.Picking.PickInfo(event);
                if (intersects !== undefined && intersects.length > 0) {
                    if (_this.ZoomSpace === undefined) {
                        _this.ZoomPos = new THREE.Vector3(0, 0, 0);
                        _this.ZoomPos.copy(intersects[0].point);
                        _this.Pivot.copy(_this.ZoomPos);
                        _this.ZoomSpace = false;
                    }
                }
                else {
                    if (_this.ZoomSpace === undefined) {
                        _this.ZoomPos = undefined;
                        _this.ZoomSpace = true;
                    }
                }
            }
        }
        else {
            var mouse = view.Data.GetMousePos(event);
            var view2D = new THREE.Vector3(mouse.x, mouse.y, 0.5);
            var pivot2D = new THREE.Vector3().copy(view2D.unproject(_this.object));
            _this.ZoomPos = new THREE.Vector3(0, 0, 0);
            _this.ZoomPos.copy(pivot2D);
            _this.Pivot.copy(_this.ZoomPos);
            _this.ZoomSpace = false;
        }
        
        _this.EventCnt++;
        setTimeout(function () {
            _this.EventCnt--;
            if (_this.EventCnt === 0) {
                _this.ZoomSpace = undefined;
            }
        }, 500);

        _this.dispatchEvent(startEvent);
        _this.dispatchEvent(endEvent);
    }

    function touchstart(event) {

        _this.noRotate = false;

        if (_this.enabled === false) return;

        switch (event.touches.length) {

            case 1:

                /// 화면 축 회전 확인
                var rect = _this.domElement.getBoundingClientRect();
                var hminx = rect.width / 2 - 100 + rect.left;
                var hmaxx = rect.width / 2 + 100 + rect.left;
                var hminy = rect.top; //rect.height - 50 + rect.top;
                var hmaxy = rect.top + 50; //rect.height + rect.top;

                var vminx = rect.width - 50 + rect.left;
                var vmaxx = rect.width + rect.left;
                var vminy = rect.height / 2 - 100 + rect.top;
                var vmaxy = rect.height / 2 + 100 + rect.top;

                var bCheckPosition = false;
                if (hminx < event.touches[0].pageX && event.touches[0].pageX < hmaxx && hminy < event.touches[0].pageY && event.touches[0].pageY < hmaxy) {
                    bCheckPosition = true;
                    ViewRotateType = VIEWROTATE.HORIZONTAL;
                }

                if (vminx < event.touches[0].pageX && event.touches[0].pageX < vmaxx && vminy < event.touches[0].pageY && event.touches[0].pageY < vmaxy) {
                    bCheckPosition = true;
                    ViewRotateType = VIEWROTATE.VERTICAL;
                }

                if (bCheckPosition) {
                    _this.noRotate = true;
                    _this.noViewRotate = false;
                }
                else {
                    _this.noViewRotate = true;
                    _this.noRotate = false;
                }

                _state = STATE.TOUCH_ROTATE;
                _moveCurr.copy(getMouseOnCircle(event.touches[0].pageX, event.touches[0].pageY));
                _movePrev.copy(_moveCurr);
                break;

            case 2:
                _state = STATE.TOUCH_ZOOM_PAN;
                var dx = event.touches[0].pageX - event.touches[1].pageX;
                var dy = event.touches[0].pageY - event.touches[1].pageY;

                _touchZoomDistanceEnd = _touchZoomDistanceStart = Math.sqrt(dx * dx + dy * dy);
                //_zoomStart.y = Math.sqrt(dx * dx + dy * dy) / 100;
                //_zoomEnd.y = Math.sqrt(dx * dx + dy * dy) / 100;

                var x = (event.touches[0].pageX + event.touches[1].pageX) / 2;
                var y = (event.touches[0].pageY + event.touches[1].pageY) / 2;
                _panStart.copy(getMouseOnScreen(x, y));
                _panS.x = x;
                _panS.y = y;
                _panEnd.copy(_panStart);
                _panE.copy(_panS);
                break;

            default:
                _state = STATE.NONE;

        }
        _this.dispatchEvent(startEvent);


    }

    function touchmove(event) {

        if (_this.enabled === false) return;

        event.preventDefault();
        event.stopPropagation();

        switch (event.touches.length) {

            case 1:
                _movePrev.copy(_moveCurr);
                _moveCurr.copy(getMouseOnCircle(event.touches[0].pageX, event.touches[0].pageY));
                break;

            case 2:
                var dx = event.touches[0].pageX - event.touches[1].pageX;
                var dy = event.touches[0].pageY - event.touches[1].pageY;
                _touchZoomDistanceEnd = Math.sqrt(dx * dx + dy * dy);
                //_zoomEnd.y = Math.sqrt(dx * dx + dy * dy)/100;

                var x = (event.touches[0].pageX + event.touches[1].pageX) / 2;
                var y = (event.touches[0].pageY + event.touches[1].pageY) / 2;
                _panEnd.copy(getMouseOnScreen(x, y));
                _panE.x = x;
                _panE.y = y;
                break;

            default:
                _state = STATE.NONE;

        }

    }

    function touchend(event) {

        if (_this.enabled === false) return;
        switch (event.touches.length) {

            case 1:
                //_moveCurr.x = 0; _movePrev.x = 0;
                //_moveCurr.y = 0; _movePrev.y = 0;
                _movePrev.copy(_moveCurr);
                _moveCurr.copy(getMouseOnCircle(event.touches[0].pageX, event.touches[0].pageY));
                break;

            case 2:
                //_moveCurr.x = 0; _movePrev.x = 0;
                //_moveCurr.y = 0; _movePrev.y = 0;
                _touchZoomDistanceStart = _touchZoomDistanceEnd = 0;

                var x = (event.touches[0].pageX + event.touches[1].pageX) / 2;
                var y = (event.touches[0].pageY + event.touches[1].pageY) / 2;
                _panEnd.copy(getMouseOnScreen(x, y));
                _panStart.copy(_panEnd);
                _panE.x = x;
                _panE.y = y;
                _panS.copy(_panE);
                break;

        }
        _this.noRotate = true;
        _state = STATE.NONE;
        _this.dispatchEvent(endEvent);

    }

    this.domElement.addEventListener('contextmenu', function (event) { event.preventDefault(); }, false);

    this.domElement.addEventListener('mousedown', mousedown, false);

    this.domElement.addEventListener('mousewheel', mousewheel, false);
    this.domElement.addEventListener('DOMMouseScroll', mousewheel, false); // firefox

    this.domElement.addEventListener('touchstart', touchstart, false);
    this.domElement.addEventListener('touchend', touchend, false);
    this.domElement.addEventListener('touchmove', touchmove, false);

    window.addEventListener('keydown', keydown, false);
    window.addEventListener('keyup', keyup, false);

    this.handleResize();

    // force an update at start
    this.update();

};

THREE.TrackballControls.prototype = Object.create( THREE.EventDispatcher.prototype );
THREE.TrackballControls.prototype.constructor = THREE.TrackballControls;
