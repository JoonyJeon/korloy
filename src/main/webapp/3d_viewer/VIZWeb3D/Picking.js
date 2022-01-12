VIZWeb3D['Picking']=function(a,b,c,d,e){var f=this,g=a,h=c,i=b,j=d,k=new Map();this['resolution']=e!==undefined?new THREE['Vector2'](e['x'],e['y']):new THREE['Vector2'](0x100,0x100);var l=new THREE['Color'](),m={'minFilter':THREE['LinearFilter'],'magFilter':THREE['LinearFilter'],'format':THREE['RGBAFormat']};this['renderTargetDepthBuffer']=new THREE['WebGLRenderTarget'](this['resolution']['x'],this['resolution']['y'],m),this['renderTargetDepthBuffer']['texture']['name']='Picking.depth',this['renderTargetDepthBuffer']['texture']['generateMipmaps']=![],this['PickInfo']=function(n){{var o=g['Data']['GetMousePos'](n),p=[];l['copy'](j['getClearColor']()),oldClearAlpha=j['getClearAlpha'](),oldAutoClear=j['autoClear'],j['autoClear']=![],j['setClearColor'](0xffffff,0x1),k=new Map();var q=g['Control']['Model']['Object'],r={'r':0x0,'g':0x0,'b':0x1,'a':0x1},s=function(C){var D=0x1;if(C['b']+D<=0xff)C['b']+=D;else C['g']+D<=0xff?(C['g']+=D,C['b']=0x0):(C['r']+=D,C['g']=0x0,C['b']=0x0);},t=function(C,D,E){var F=this;for(var G=0x0;G<C['children']['length'];G++){if(C['children'][G]instanceof THREE['Mesh']){var H=![];for(var I=0x0;I<C['children'][G]['userData']['length'];I++){var J=C['children'][G]['userData'][I],K=J['BBox'],L=new THREE['Vector3']((K['max']['x']+K['min']['x'])/0x2,(K['max']['y']+K['min']['y'])/0x2,(K['max']['z']+K['min']['z'])/0x2),M=new THREE['Vector3'](D['x'],D['y'],0.3),N=new THREE['Vector3'](D['x'],D['y'],0.7),O=M['unproject'](F['camera']),P=N['unproject'](F['camera']),Q=new THREE['Vector3']();Q['subVectors'](L,O);var R=new THREE['Vector3']();R['subVectors'](L,P);var S=new THREE['Vector3']();S['crossVectors'](Q,R);var T=new THREE['Vector3']();T['subVectors'](O,P);var U=S['length']()/T['length'](),V=new THREE['Vector3'](K['max']['x']-K['min']['x'],K['max']['y']-K['min']['y'],K['max']['z']-K['min']['z'])['length']();V/=1.9;if(U<V){H=!![];break;}}!H?(C['children'][G]['visible']=![],w['push'](C['children'][G])):E['push'](C['children'][G]);}else t(C['children'][G],o,E);}},u=function(C,D,E){C['material']=D,C['material']['needsUpdate']=!![];for(var F=0x0;F<C['userData']['length'];F++){var G=C['userData'][F],H=function(J,K,L){for(var M=K['m_vnIdx']/0x3*0x4;M<K['m_vnIdx']/0x3*0x4+K['m_nVtx']+K['m_nVtx']/0x3;M=M+0x4){J['geometry']['attributes']['color']['array'][M]=L['r'],J['geometry']['attributes']['color']['array'][M+0x1]=L['g'],J['geometry']['attributes']['color']['array'][M+0x2]=L['b'],J['geometry']['attributes']['color']['array'][M+0x3]=L['a']*0xff;}J['geometry']['attributes']['color']['needsUpdate']=!![],s(L);},I=E['r']+(E['g']<<0x8)+(E['b']<<0x10)+(E['a']*0xff<<0x18);k['set'](I,{'bodyId':G['bodyId'],'mesh':C}),H(C,G,E);}},v=[],w=[];t(g['Control']['Model']['Object'],o,v);for(var x=0x0;x<v['length'];x++){u(v[x],g['Data']['Materials']['pick'],r);}j['autoClearDepth']=![],a['Data']['Materials']['pick']['depthTest']=!![],a['Data']['Materials']['pick']['depthWrite']=!![],j['autoClear']=!![],j['setViewport'](0x0,0x0,e['x'],e['y']);if(q!==null)q['visible']=!![];j['setRenderTarget'](this['renderTargetDepthBuffer']),j['clear'](),j['render'](i,h,this['renderTargetDepthBuffer'],!![]);var y=j['domElement']['getContext']('webgl');if(!y)y=j['domElement']['getContext']('experimental-webgl');if(!y)return;else{const C=n['clientX'],D=e['y']-n['clientY'],E=new Uint8Array(0x4);y['readPixels'](C,D,0x1,0x1,y['RGBA'],y['UNSIGNED_BYTE'],E);var z=E[0x0]+(E[0x1]<<0x8)+(E[0x2]<<0x10)+(E[0x3]<<0x18),A=k['get'](z);A!==undefined&&(p=g['Data']['GetIntersectionsByMesh'](o,A['mesh']));}for(var x=0x0;x<w['length'];x++){w[x]['visible']=!![];}var B=function(F,G,H,I){for(var J=0x0;J<F['length'];J++){if(F[J]instanceof THREE['Mesh']){F[J]['material']=H,F[J]['material']['needsUpdate']=!![];for(var K=0x0;K<F[J]['userData']['length'];K++){var L=F[J]['userData'][K],M=function(N,O,P){for(var Q=O['m_vnIdx']/0x3*0x4;Q<O['m_vnIdx']/0x3*0x4+O['m_nVtx']+O['m_nVtx']/0x3;Q=Q+0x4){N['geometry']['attributes']['color']['array'][Q]=P['r'],N['geometry']['attributes']['color']['array'][Q+0x1]=P['g'],N['geometry']['attributes']['color']['array'][Q+0x2]=P['b'],N['geometry']['attributes']['color']['array'][Q+0x3]=P['a']*0xff;}N['geometry']['attributes']['color']['needsUpdate']=!![];};M(F[J],L,{'r':L['color']['R'],'g':L['color']['G'],'b':L['color']['B'],'a':L['color']['A']/0xff});}}else f['UpdateObjectStatus'](F[J],G,H,I);}};return g['Data']['ResetObjectStatus'](v,g['Data']['Materials']['basic']),p;}},this['PickInfoByPos']=function(n,o){{var p=[];l['copy'](j['getClearColor']()),oldClearAlpha=j['getClearAlpha'](),oldAutoClear=j['autoClear'],j['autoClear']=![],j['setClearColor'](0xffffff,0x1),k=new Map();var q=g['Control']['Model']['Object'],r={'r':0x0,'g':0x0,'b':0x1,'a':0x1},s=function(C){var D=0x1;if(C['b']+D<=0xff)C['b']+=D;else C['g']+D<=0xff?(C['g']+=D,C['b']=0x0):(C['r']+=D,C['g']=0x0,C['b']=0x0);},t=function(C,D,E){var F=this;for(var G=0x0;G<C['children']['length'];G++){if(C['children'][G]instanceof THREE['Mesh']){var H=![];for(var I=0x0;I<C['children'][G]['userData']['length'];I++){var J=C['children'][G]['userData'][I],K=J['BBox'],L=new THREE['Vector3']((K['max']['x']+K['min']['x'])/0x2,(K['max']['y']+K['min']['y'])/0x2,(K['max']['z']+K['min']['z'])/0x2),M=new THREE['Vector3'](D['x'],D['y'],0.3),N=new THREE['Vector3'](D['x'],D['y'],0.7),O=M['unproject'](F['camera']),P=N['unproject'](F['camera']),Q=new THREE['Vector3']();Q['subVectors'](L,O);var R=new THREE['Vector3']();R['subVectors'](L,P);var S=new THREE['Vector3']();S['crossVectors'](Q,R);var T=new THREE['Vector3']();T['subVectors'](O,P);var U=S['length']()/T['length'](),V=new THREE['Vector3'](K['max']['x']-K['min']['x'],K['max']['y']-K['min']['y'],K['max']['z']-K['min']['z'])['length']();V/=1.9;if(U<V){H=!![];break;}}!H?(C['children'][G]['visible']=![],w['push'](C['children'][G])):E['push'](C['children'][G]);}else t(C['children'][G],o,E);}},u=function(C,D,E){C['material']=D,C['material']['needsUpdate']=!![];for(var F=0x0;F<C['userData']['length'];F++){var G=C['userData'][F],H=function(J,K,L){for(var M=K['m_vnIdx']/0x3*0x4;M<K['m_vnIdx']/0x3*0x4+K['m_nVtx']+K['m_nVtx']/0x3;M=M+0x4){J['geometry']['attributes']['color']['array'][M]=L['r'],J['geometry']['attributes']['color']['array'][M+0x1]=L['g'],J['geometry']['attributes']['color']['array'][M+0x2]=L['b'],J['geometry']['attributes']['color']['array'][M+0x3]=L['a']*0xff;}J['geometry']['attributes']['color']['needsUpdate']=!![],s(L);},I=E['r']+(E['g']<<0x8)+(E['b']<<0x10)+(E['a']*0xff<<0x18);k['set'](I,{'bodyId':G['bodyId'],'mesh':C}),H(C,G,E);}},v=[],w=[];t(g['Control']['Model']['Object'],o,v);for(var x=0x0;x<v['length'];x++){u(v[x],g['Data']['Materials']['pick'],r);}j['autoClearDepth']=![],a['Data']['Materials']['pick']['depthTest']=!![],a['Data']['Materials']['pick']['depthWrite']=!![],j['autoClear']=!![],j['setViewport'](0x0,0x0,e['x'],e['y']);if(q!==null)q['visible']=!![];j['setRenderTarget'](this['renderTargetDepthBuffer']),j['clear'](),j['render'](i,h,this['renderTargetDepthBuffer'],!![]);var y=j['domElement']['getContext']('webgl');if(!y)y=j['domElement']['getContext']('experimental-webgl');if(!y)return;else{const C=n['x'],D=e['y']-n['y'],E=new Uint8Array(0x4);y['readPixels'](C,D,0x1,0x1,y['RGBA'],y['UNSIGNED_BYTE'],E);var z=E[0x0]+(E[0x1]<<0x8)+(E[0x2]<<0x10)+(E[0x3]<<0x18),A=k['get'](z);A!==undefined&&(p=g['Data']['GetIntersectionsByMesh'](o,A['mesh']));}for(var x=0x0;x<w['length'];x++){w[x]['visible']=!![];}var B=function(F,G,H,I){for(var J=0x0;J<F['length'];J++){if(F[J]instanceof THREE['Mesh']){F[J]['material']=H,F[J]['material']['needsUpdate']=!![];for(var K=0x0;K<F[J]['userData']['length'];K++){var L=F[J]['userData'][K],M=function(N,O,P){for(var Q=O['m_vnIdx']/0x3*0x4;Q<O['m_vnIdx']/0x3*0x4+O['m_nVtx']+O['m_nVtx']/0x3;Q=Q+0x4){N['geometry']['attributes']['color']['array'][Q]=P['r'],N['geometry']['attributes']['color']['array'][Q+0x1]=P['g'],N['geometry']['attributes']['color']['array'][Q+0x2]=P['b'],N['geometry']['attributes']['color']['array'][Q+0x3]=P['a']*0xff;}N['geometry']['attributes']['color']['needsUpdate']=!![];};M(F[J],L,{'r':L['color']['R'],'g':L['color']['G'],'b':L['color']['B'],'a':L['color']['A']/0xff});}}else f['UpdateObjectStatus'](F[J],G,H,I);}};return g['Data']['ResetObjectStatus'](v,g['Data']['Materials']['basic']),p;}};};