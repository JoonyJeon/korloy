VIZWeb3D['Ground']=function(a,b,c){var d=this,e=null;this['Option']={get 'Visible'(){return e['visible'];},set 'Visible'(g){e['visible']=g;}},f();function f(){e=new THREE['Object3D']();var g=2.2,h=b['max']['x']-b['min']['x'],i=b['max']['y']-b['min']['y'],j=(b['max']['z']-b['min']['z'])/0x2,k=null;h=h+h*g,i=i+i*g;if(h>i)k=h;else k=i;var l=new THREE['Mesh'](new THREE['PlaneBufferGeometry'](k,k),new THREE['MeshPhongMaterial']({'color':0x999999,'depthWrite':![],'side':THREE['DoubleSide']})),m=new THREE['Vector3'](c['x'],c['y'],c['z']-j);l['position']['copy'](m),l['receiveShadow']=!![],e['add'](l);var n=new THREE['GridHelper'](k,0x14,0x0,0x0);n['material']['opacity']=0.2,n['side']=THREE['DoubleSide'],n['rotation']['x']=-Math['PI']/0x2,n['position']['copy'](m),e['add'](n),e['visible']=visible_ground,a['add'](e);}};