define([],function(){const a={'CTUnknown':0x0,'CTGeneralBinaryBlock':0x1,'CTStructure':0x2,'CTMeshBlock':0x3,'CTClashTestData':0x4,'CTImageData':0x5,'CTImageIdxTable':0x6,'CTMeshBlockTable':0x7,'CTEntityBinaryBlock':0x8,'CTBodyMeshData':0x9,'CTPropDic':0xa,'CTUserDefGroups':0xb,'CTFileMetaData':0xc,'CTAuthority':0xd,'CTNodePropTable':0xe,'CTNodePropTableIndices':0xf,'CTInitialInfo':0x10,'CTUserCustom':0x11,'CTEdgeTable':0x12,'CTThumbnail':0x13,'CTMisc':0x14,'CTStructureHeader':0x15,'CTMeshBlockSub':0x16,'CTVIZWFileAxisInfo':0x2a,'CTVIZWFileFreeEdgeInfo':0x2b},b={'int':0x4,'float':0x4,'CFUInt8':0x1,'CFUInt16':0x2,'CFSize':0x4};function c(){}return c['prototype']={'constructor':c,'parsetype':0x1,'load':function(d,e,f,g,h){var i=this;i['parse'](d,e,f,g,h);},'parse':function(d,e,f,g,h){var i=e,j=0x0;function k(N,O,P){return new THREE['Vector3'](N,O,P);}function l(N,O){return new THREE['Vector2'](N,O);}function m(N,O,P,Q){return{'a':N,'b':O,'c':P,'color':Q};}function n(N,O,P,Q){return new THREE['Face3'](N,O,P,Q);}var o=new THREE['Group'](),p=o,q=new THREE['BufferGeometry'](),r=new THREE['MeshLambertMaterial'](),s=new THREE['Mesh'](q,r),t=[],u=[],v=[],w=[],x=[],y=new THREE['Color']();function z(N,O,P,Q){Q===undefined?geometry['faces']['push'](n(parseInt(N)-(face_offset+0x1),parseInt(O)-(face_offset+0x1),parseInt(P)-(face_offset+0x1))):geometry['faces']['push'](n(parseInt(N)-(face_offset+0x1),parseInt(O)-(face_offset+0x1),parseInt(P)-(face_offset+0x1),[v[parseInt(Q[0x0])-0x1]['clone'](),v[parseInt(Q[0x1])-0x1]['clone'](),v[parseInt(Q[0x2])-0x1]['clone']()]));}function A(N,O,P){geometry['faceVertexUvs'][0x0]['push']([w[parseInt(N)-0x1]['clone'](),w[parseInt(O)-0x1]['clone'](),w[parseInt(P)-0x1]['clone']()]);}function B(N,O,P){N[0x3]===undefined?(z(N[0x0],N[0x1],N[0x2],P),!(O===undefined)&&O['length']>0x0&&A(O[0x0],O[0x1],O[0x2])):(!(P===undefined)&&P['length']>0x0?(z(N[0x0],N[0x1],N[0x3],[P[0x0],P[0x1],P[0x3]]),z(N[0x1],N[0x2],N[0x3],[P[0x1],P[0x2],P[0x3]])):(z(N[0x0],N[0x1],N[0x3]),z(N[0x1],N[0x2],N[0x3])),!(O===undefined)&&O['length']>0x0&&(A(O[0x0],O[0x1],O[0x3]),A(O[0x1],O[0x2],O[0x3])));}function C(N,O){p['name']=O;for(var P=0x0;P<N['list_colormesh']['length'];P++){var Q=N['list_colormesh'][P],R=[],S=new THREE['BufferGeometry'](),T=0x0;for(var U=0x0;U<Q['meshes']['length'];U++){T+=Q['meshes'][U]['faces']['length'];}var V=T,W=new Float32Array(V*0x3*0x3),X=new Float32Array(V*0x3*0x3);T=0x0;var Y=f['GetTreeCount']();for(var U=0x0;U<Q['meshes']['length'];U++){var Z=k(0x0,0x0,0x0),a0=k(0x0,0x0,0x0);Q['meshes'][U]['faces']['forEach'](function(a7,a8){a8===0x0&&(Z['x']=u[a7['a']]['x'],Z['y']=u[a7['a']]['y'],Z['z']=u[a7['a']]['z'],a0['x']=u[a7['a']]['x'],a0['y']=u[a7['a']]['y'],a0['z']=u[a7['a']]['z']),W[a8*0x9+0x0+T]=u[a7['a']]['x'],W[a8*0x9+0x1+T]=u[a7['a']]['y'],W[a8*0x9+0x2+T]=u[a7['a']]['z'],W[a8*0x9+0x3+T]=u[a7['b']]['x'],W[a8*0x9+0x4+T]=u[a7['b']]['y'],W[a8*0x9+0x5+T]=u[a7['b']]['z'],W[a8*0x9+0x6+T]=u[a7['c']]['x'],W[a8*0x9+0x7+T]=u[a7['c']]['y'],W[a8*0x9+0x8+T]=u[a7['c']]['z'],X[a8*0x9+0x0+T]=v[a7['a']]['x'],X[a8*0x9+0x1+T]=v[a7['a']]['y'],X[a8*0x9+0x2+T]=v[a7['a']]['z'],X[a8*0x9+0x3+T]=v[a7['b']]['x'],X[a8*0x9+0x4+T]=v[a7['b']]['y'],X[a8*0x9+0x5+T]=v[a7['b']]['z'],X[a8*0x9+0x6+T]=v[a7['c']]['x'],X[a8*0x9+0x7+T]=v[a7['c']]['y'],X[a8*0x9+0x8+T]=v[a7['c']]['z'],Z=f['Min'](Z,u[a7['a']]),Z=f['Min'](Z,u[a7['b']]),Z=f['Min'](Z,u[a7['c']]),a0=f['Max'](a0,u[a7['a']]),a0=f['Max'](a0,u[a7['b']]),a0=f['Max'](a0,u[a7['c']]);});var a1=f['Part'](Q['meshes'][U]['ID'],Q['meshes'][U]['name'],T,Q['meshes'][U]['faces']['length']*0x9,Q['color'],{'min':Z,'max':a0},null,null);R['push'](a1),T+=Q['meshes'][U]['faces']['length']*0x9;}S['addAttribute']('position',new THREE['BufferAttribute'](W,0x3)),S['addAttribute']('normal',new THREE['BufferAttribute'](X,0x3)),S['computeBoundingSphere']();var a2=Q['color'],a3=D(parseInt(f['mColors'][a2]['r']),parseInt(f['mColors'][a2]['g']),parseInt(f['mColors'][a2]['b'])),a4=new THREE['MeshPhongMaterial']({'color':a3,'side':THREE['DoubleSide'],'transparent':!![],'vertexColors':THREE['NoColors'],'opacity':f['mColors'][a2]['a']});s=new THREE['Mesh'](S,a4),s['castShadow']=!![],s['receiveShadow']=!![];for(var a5=0x0;a5<R['length'];a5++){R[a5]['mesh']=s,f['SetPartData'](R[a5]);}if(!s['userData']['init']){var a6=N['Tag']();a6['color']=a2,a6['parts']=R,s['userData']=a6;}p['add'](s);}f['Clear']();}function D(N,O,P){return'#'+((0x1<<0x18)+(N<<0x10)+(O<<0x8)+P)['toString'](0x10)['slice'](0x1);}function E(N){return'#'+N['toString'](0x10)['slice'](0x2);}function F(N){var O;if(/^#([A-Fa-f0-9]{3}){1,2}$/['test'](N))return O=N['substring'](0x1)['split'](''),O['length']===0x3&&(O=[O[0x0],O[0x0],O[0x1],O[0x1],O[0x2],O[0x2]]),O='0x'+O['join'](''),'rgba('+[O>>0x10&0xff,O>>0x8&0xff,O&0xff]['join'](',')+',1)';throw new Error('Bad\x20Hex');}async function G(N){J(N);}function H(N){$('.loader')['fadeIn'](0x1f4);var O=0x0,P=0x0,Q=0x0,R=new DataView(N),S=R['getInt32'](Q,!![]);Q+=0x4;var T=R['getInt32'](Q,!![]);Q+=0x4;var U=R['getInt32'](Q,!![]);Q+=0x4;var V=R['getInt32'](Q,!![]);Q+=0x4;var W=R['getInt32'](Q,!![]);Q+=0x4;var X=f['GetTreeCount'](),Y=f['GetColorCount'](),Z=function(){for(var a9=0x0;a9<W;a9++){var aa=R['getInt32'](Q,!![]);Q+=0x4;var ab=R['getInt32'](Q,!![]);Q+=0x4;var ac=R['getFloat32'](Q,!![]),ad=R['getFloat32'](Q+0x4,!![]),ae=R['getFloat32'](Q+0x8,!![]);Q+=0xc;var af=R['getFloat32'](Q,!![]),ag=R['getFloat32'](Q+0x4,!![]),ah=R['getFloat32'](Q+0x8,!![]);Q+=0xc;var ai=k(ac,ad,ae),aj=k(af,ag,ah),ak={'min':ai,'max':aj},al=[];ab!==-0x1?(aa=aa+X,ab=ab+X):aa=aa+X,f['SetTreeData']({'ID':aa,'PID':ab,'bbox':ak,'children':al});}f['SetTreeHierarchy'](filename),setTimeout(function(){a7();},0x19);};u=[],v=[],y['setRGB'](0xff,0xff,0xff);var a0=0x0,a1=$('#progress'),a2=$('#bar'),a3=a1['width'](),a4=0x0,a5=0x32,a6=!![],a7=function(){a0=a4/T*0x64,g(a0),a2['width'](a3/0x64*a0);for(var a9=0x0;a9<a5;a9++){if(a4===T){a8(),g(0x64),a2['width'](a3),$('.loader')['fadeOut'](0x1f4),setTimeout(function(){i(o);},0x1f4),a6=![];break;}var aa=R['getInt32'](Q,!![]);aa=aa+X,Q+=0x4;var ab=R['getInt32'](Q,!![]);Q+=0x4;var ac='',ad=new Uint8Array(ab);for(var ae=0x0;ae<ab;ae++){ad[ae]=R['getUint8'](Q+ae,!![]);}ac=String['fromCharCode']['apply'](null,ad);ac['localeCompare']('Part\x201\x20of\x20/6501-SHAFT')===0x0&&console['log']('');Q+=ab;var af=[],ag=[],ah=R['getInt32'](Q,!![]);Q+=0x4;for(var ae=0x0;ae<ah;ae++){var ai=R['getInt32'](Q,!![]);Q+=0x4,face_offset=P;for(var aj=0x0;aj<ai;aj++){var ak=R['getInt32'](Q,!![]);Q+=0x4;var al='',am=new Uint8Array(ak);for(var an=0x0;an<ak;an++){am[an]=R['getUint8'](Q+an,!![]);}al=String['fromCharCode']['apply'](null,am),Q+=ak;var ao=R['getInt32'](Q,!![]);ao=ao+Y;var ap=-0x1;for(var aq=0x0;aq<ag['length'];aq++){if(ag[aq]['colorIdx']===ao){ap=aq;break;}}ap===-0x1?ag['push']({'colorIdx':ao,'count':0x1}):ag[ap]['count']=ag[ap]['count']+0x1;Q+=0x4;var ar='col_'+ao,as=R['getInt32'](Q,!![]);O=O+as,Q+=0x4;for(var an=0x0;an<as;an++){var at=R['getFloat32'](Q,!![]),au=R['getFloat32'](Q+0x4,!![]),av=R['getFloat32'](Q+0x8,!![]);Q+=0xc;{u['push'](k(at,au,av)),P++;}}var aw=R['getInt32'](Q,!![]);Q+=0x4;for(var an=0x0;an<aw;an++){var ax=R['getFloat32'](Q,!![]),ay=R['getFloat32'](Q+0x4,!![]),az=R['getFloat32'](Q+0x8,!![]);Q+=0xc;{v['push'](k(ax,ay,az));}}var aA=R['getInt32'](Q,!![]);Q+=0x4;for(var an=0x0;an<aA;an++){var aB=R['getInt32'](Q,!![]),aC=R['getInt32'](Q+0x4,!![]),aD=R['getInt32'](Q+0x8,!![]);Q+=0xc;{af['push'](m(aB-0x1,aC-0x1,aD-0x1,ao));}}}}var aE={'Idx':-0x1,'count':-0x1};for(var aF=0x0;aF<ag['length'];aF++){aF===0x0?(aE['Idx']=ag[aF]['colorIdx'],aE['count']=ag[aF]['count']):ag[aF]['count']>aE['count']&&(aE['Idx']=ag[aF]['colorIdx'],aE['count']=ag[aF]['count']);}ah!==0x0&&f['AddMesh'](ac,face_offset,as,aE['Idx'],af,aa),a4++;}if(a6)setTimeout(function(){a7();},0x19);};setTimeout(function(){Z();},0x1f4);var a8=function(){for(var a9=0x0;a9<U;a9++){var aa=R['getInt32'](Q,!![]);aa=aa+Y,Q+=0x4;var ab=R['getFloat32'](Q,!![]),ac=R['getFloat32'](Q+0x4,!![]),ad=R['getFloat32'](Q+0x8,!![]),ae=0x0;if(S===0x1)Q+=0xc;else S===0x2&&(ae=R['getFloat32'](Q+0xc,!![]),Q+=0x10);f['mColors'][aa]={'index':aa,'r':ab*0xff,'g':ac*0xff,'b':ad*0xff,'a':ae};}C(f,filename);};}function I(N,O,P){const Q=N['getUint32'](O,P),R=N['getUint32'](O+0x4,P),S=P?Q+0x2**0x20*R:0x2**0x20*Q+R;if(!Number['isSafeInteger'](S))console['warn'](S,'exceeds\x20MAX_SAFE_INTEGER.\x20Precision\x20may\x20be\x20lost');return S;}function J(N){var O=this;$('.loader')['fadeIn'](0x3e8),console['log']('Data\x20Loading\x20Start');var P=new DataView(N),Q=0x0,R={'typeStr':null,'version':null,'sizeTocItem':null,'nToc':null,'tocPos':null},S=new Uint8Array(N,Q,0x10);R['typeStr']=String['fromCharCode']['apply'](null,S),Q+=0x10,R['version']=P['getInt32'](Q,!![]),Q+=0x4,R['sizeTocItem']=P['getInt32'](Q,!![]),Q+=0x4,R['nToc']=P['getInt32'](Q,!![]),Q+=0x4,R['tocPos']=I(P,Q,!![]),Q+=0x8;var T=[],U=0x0,V=0x0,W=R['tocPos'];U=P['getInt32'](W,!![]),W+=0x4,V=I(P,W,!![]),W+=0x8;var X=R['nToc']-0x1;for(var Y=0x0;Y<U;Y++){Z(Y);}function Z(at){var au=R['tocPos']+0xc+at*R['sizeTocItem'],av=P['getInt32'](au,!![]);au+=0x8;var aw=I(P,au,!![]);au+=0x8;var ax=P['getUint32'](au,!![]);au+=0x4;var ay=P['getUint32'](au,!![]);au+=0x4;var az=0x0;scope['Parse']['parsetype']===0x1&&(az=P['getUint32'](au,!![]),au+=0x4);var aA={'type':av,'position':aw,'datasize':ax,'uncompsize':ay,'parsetype':az};T['push'](aA);}function a0(at){var au=-0x1;for(var av=T['length']-0x1;av>=0x0;av--){if(T[av]['type']===at){au=av;break;}}return au;}function a1(at){var au=-0x1;for(var av=0x0;av<T['length'];av++){if(T[av]['type']===at){au=av;break;}}return au;}function a2(at,au){try{var av=at['position'],aw;return au===undefined?(aw=new DataView(N,av,at['datasize']),aw):(aw=new DataView(au,av,at['datasize']),aw);}catch(ax){return undefined;}}var a3=a0(a['CTMeshBlock']),a4=T[a3];function a5(at,au){var av=0x0,aw;aw=at['getInt32'](av,!![]),av+=0x4;for(var ax=0x0;ax<aw;ax++){var ay={'R':at['getUint8'](av,!![]),'G':at['getUint8'](av+0x1,!![]),'B':at['getUint8'](av+0x2,!![]),'A':at['getUint8'](av+0x3,!![])};av+=0x4;var az=at['getUint32'](av,!![]);av+=0x4;var aA=at['getUint32'](av,!![]);av+=0x4;var aB=at['getUint32'](av,!![]);av+=0x4;if(scope['Parse']['parsetype']===0x1){var aC=at['getUint32'](av,!![]);av+=0x4;}var aD=az/0xf,aE=new Float32Array(N,av+at['byteOffset'],az);av+=az*0x4;var aF=new Float32Array(N,av+at['byteOffset'],aA);av+=aA*0x4;var aG=new Uint32Array(N,av+at['byteOffset'],aB);av+=aB*0x4;var aH=new THREE['BufferGeometry']();aH['setIndex'](new THREE['Uint32BufferAttribute'](aG,0x1)),aH['addAttribute']('position',new THREE['Float32BufferAttribute'](aE,0x3)),aH['addAttribute']('normal',new THREE['Float32BufferAttribute'](aF,0x3));var aI=aE['length']/0x3*0x4,aJ=[];for(var aK=0x0;aK<aI;aK=aK+0x4){aJ['push'](ay['R']),aJ['push'](ay['G']),aJ['push'](ay['B']),aJ['push'](ay['A']);}var aL=new THREE['Uint8BufferAttribute'](aJ,0x4);aL['normalized']=!![],aH['addAttribute']('color',aL),aH['computeBoundingSphere']();var aM=D(ay['R'],ay['G'],ay['B']);s=new THREE['Mesh'](aH,f['Materials']['basic']),s['castShadow']=!![],s['receiveShadow']=!![],p['add'](s);var aN=at['getUint32'](av,!![]);av+=0x4;var aO=[];for(var aP=0x0;aP<aN;aP++){var aQ=at['getUint32'](av,!![]);av+=0x4;var aR=at['getUint32'](av,!![]);av+=0x4;var aS=at['getUint32'](av,!![]);av+=0x4;var aT=at['getUint32'](av,!![]);av+=0x4;var aU=at['getUint16'](av,!![]);av+=0x2;var aV=at['getUint16'](av,!![]);av+=0x2;var aW=new Float32Array(at['buffer'],av+at['byteOffset'],0x6);av+=0x18;if(aR===0x160f8)console['log']('');var aX={'partId':aQ+f['GetMaxID'](),'bodyId':aR+f['GetMaxID'](),'color':ay,'m_vnIdx':aS*0x3,'m_triIdx':aT*0x3,'m_nVtx':aU*0x3,'m_nTris':aV*0x3,'BBox':{'min':{'x':aW[0x0],'y':aW[0x1],'z':aW[0x2]},'max':{'x':aW[0x3],'y':aW[0x4],'z':aW[0x5]}},'Tag':f['Tag']()};aO['push'](aX);}s['userData']=aO;}}function a6(at,au){var av=0x0,aw;aw=at['getInt32'](av,!![]),av+=0x4;for(var ax=0x0;ax<aw;ax++){var ay={'R':at['getUint8'](av,!![]),'G':at['getUint8'](av+0x1,!![]),'B':at['getUint8'](av+0x2,!![]),'A':at['getUint8'](av+0x3,!![])};av+=0x4;var az=at['getUint32'](av,!![]);av+=0x4;var aA=at['getUint32'](av,!![]);av+=0x4;var aB=at['getUint32'](av,!![]);av+=0x4;if(scope['Parse']['parsetype']===0x1){var aC=at['getUint32'](av,!![]);av+=0x4;}var aD=new Float32Array(N,av+at['byteOffset'],az);av+=az*0x4;var aE=new Float32Array(N,av+at['byteOffset'],aA);av+=aA*0x4;var aF=new Uint32Array(N,av+at['byteOffset'],aB);av+=aB*0x4;var aG=at['getUint32'](av,!![]);av+=0x4;for(var aH=0x0;aH<aG;aH++){var aI=[],aJ=at['getUint32'](av,!![]);av+=0x4;var aK=at['getUint32'](av,!![]);if(aK===0x160f8)console['log']('');av+=0x4;var aL=at['getUint32'](av,!![]);av+=0x4;var aM=at['getUint32'](av,!![]);av+=0x4;var aN=at['getUint16'](av,!![]);av+=0x2;var aO=at['getUint16'](av,!![]);av+=0x2;var aP=new Float32Array(at['buffer'],av+at['byteOffset'],0x6);av+=0x18;var aQ={'partId':aJ+f['GetMaxID'](),'bodyId':aK+f['GetMaxID'](),'color':ay,'m_vnIdx':aL*0x3,'m_triIdx':aM*0x3,'m_nVtx':aN*0x3,'m_nTris':aO*0x3,'BBox':{'min':{'x':aP[0x0],'y':aP[0x1],'z':aP[0x2]},'max':{'x':aP[0x3],'y':aP[0x4],'z':aP[0x5]}},'Tag':f['Tag']()},aR=aF['slice'](aQ['m_triIdx'],aQ['m_triIdx']+aQ['m_nTris']);for(var aS=0x0;aS<aR['length'];aS++){aR[aS]=aR[aS]-aQ['m_vnIdx']/0x3;}var aT=aD['slice'](aQ['m_vnIdx'],aQ['m_vnIdx']+aQ['m_nVtx']),aU=aE['slice'](aQ['m_vnIdx'],aQ['m_vnIdx']+aQ['m_nVtx']);aQ['m_triIdx']=0x0,aQ['m_vnIdx']=0x0;var aV=new THREE['BufferGeometry']();aV['setIndex'](new THREE['Uint32BufferAttribute'](aR,0x1)),aV['addAttribute']('position',new THREE['Float32BufferAttribute'](aT,0x3)),aV['addAttribute']('normal',new THREE['Float32BufferAttribute'](aU,0x3));var aW=aT['length']/0x3*0x4,aX=[];for(var aY=0x0;aY<aW;aY=aY+0x4){aX['push'](ay['R']),aX['push'](ay['G']),aX['push'](ay['B']),aX['push'](ay['A']);}var aZ=new THREE['Uint8BufferAttribute'](aX,0x4);aZ['normalized']=!![],aV['addAttribute']('color',aZ),aV['computeBoundingSphere'](),s=new THREE['Mesh'](aV,f['Materials']['basic']),s['castShadow']=!![],s['receiveShadow']=!![],p['add'](s),aI['push'](aQ),s['userData']=aI;}}}var a7=a0(a['CTStructure']),a8=T[a7],a9=a2(a8);let aa=[];function ab(at,au){var av=0x0,aw=at['getUint32'](av,!![]);av+=0x4;var ax=at['getUint32'](av,!![]);av+=0x4;var ay=at['getUint32'](av,!![]);av+=0x4;var az=[];for(var aA=0x0;aA<ay;aA++){HeaderTocTableItem={'index':-0x1,'type':0x0,'entFlags':0x0,'iNameIndex':-0x1,'attFlags':0x0,'transfrom':[],'bBox':[],'cCount':-0x1,'pIndex':-0x1,'orgNodeID':-0x1,'pNameBuff':null,'tocId':0x0,'btype':0x0,'name':null};var aB=at['getUint32'](av,!![]);HeaderTocTableItem['index']=aB===-0x1?aB:aB+f['GetMaxID'](),av+=0x4,HeaderTocTableItem['type']=at['getInt32'](av,!![]),av+=0x4,HeaderTocTableItem['entFlags']=at['getUint16'](av,!![]),av+=0x4,HeaderTocTableItem['iNameIndex']=at['getInt32'](av,!![]),av+=0x4,HeaderTocTableItem['attFlags']=at['getUint16'](av,!![]),av+=0x4,HeaderTocTableItem['transfrom']=[];for(var aC=0x0;aC<0x10;aC++){HeaderTocTableItem['transfrom'][aC]=at['getFloat32'](av,!![]),av+=0x4;}HeaderTocTableItem['bBox']=[];for(var aD=0x0;aD<0x6;aD++){HeaderTocTableItem['bBox'][aD]=at['getFloat32'](av,!![]),av+=0x4;}HeaderTocTableItem['cCount']=at['getInt32'](av,!![]),av+=0x4;var aE=at['getInt32'](av,!![]);HeaderTocTableItem['pIndex']=aE===-0x1?aE:aE+f['GetMaxID'](),av+=0x4,HeaderTocTableItem['orgNodeID']=at['getInt32'](av,!![]),av+=0x4;if(R['version']<0x12e)av+=0x8;HeaderTocTableItem['tocId']=at['getInt32'](av,!![]),av+=0x4,HeaderTocTableItem['btype']=at['getInt32'](av,!![]),av+=0x4;if(HeaderTocTableItem['type']===ENTITY_TYPES['EntBinaryBlock']){if(HeaderTocTableItem['btype']===0xb)aa['push'](HeaderTocTableItem);}if(HeaderTocTableItem['type']!==ENTITY_TYPES['EntAssembly']&&HeaderTocTableItem['type']!==ENTITY_TYPES['EntPart']&&HeaderTocTableItem['type']!==ENTITY_TYPES['EntBody']){ax--;continue;}az['push'](HeaderTocTableItem);}f['AddNodes'](az);var aF=at['getInt32'](av,!![]);av+=0x4;var aG=function(aO){return decodeURIComponent(escape(aO));},aH=[],aI=0x0;for(var aJ=0x0;aJ<ax;aJ++){if(az[aJ]['iNameIndex']<=0x0)continue;var aK={'nStringNum':0x0,'pos':0x0,'name':null};aK['pos']=aI,aK['nStringNum']=az[aJ]['iNameIndex'];var aL=new Uint8Array(aK['nStringNum']);for(var aM=0x0;aM<aK['nStringNum'];aM++){aL[aM]=at['getUint8'](aI+av+aM,!![]);}bodyName=String['fromCharCode']['apply'](null,aL);try{bodyName=aG(bodyName);}catch(aO){console['log'](aO);}az[aJ]['name']=bodyName['replace'](/\0/gi,''),aK['name']=bodyName['replace'](/\0/gi,''),aI+=az[aJ]['iNameIndex'],aH['push'](aK);}av+=aF;var aN=function(){var aP=at['getUint32'](av,!![]);av+=0x4;for(var aQ=0x0;aQ<ay;aQ++){var aR={'nodeTreeOrderID':-0x1,'hasColor':![],'color':[],'cacheidx':-0x1,'hasLayerNumber':![],'layerNum':-0x1,'attrCount':-0x1,'attrType':[],'volume':0x0,'area':0x0,'centroid':[],'desbyteLength':-0x1,'fdensity':-0x1,'edgeStartidx':-0x1,'edgeIdxNum':-0x1};aR['nodeTreeOrderID']=at['getInt32'](av,!![]),av+=0x4,aR['hasColor']=at['getUint8'](av,!![]),av+=0x4;for(var aS=0x0;aS<0x4;aS++){aR['color'][aS]=at['getFloat32'](av,!![]),av+=0x4;}aR['cacheidx']=at['getInt32'](av,!![]),av+=0x4,aR['hasLayerNumber']=at['getUint8'](av,!![]),av+=0x4,aR['layerNum']=at['getInt32'](av,!![]),av+=0x4,aR['attrCount']=at['getInt32'](av,!![]),av+=0x4;for(var aS=0x0;aS<0x2;aS++){aR['attrType'][aS]=at['getInt32'](av,!![]),av+=0x4;}aR['volume']=I(at,av,!![]),av+=0x8,aR['area']=I(at,av,!![]),av+=0x8;for(var aS=0x0;aS<0x3;aS++){aR['centroid'][aS]=I(at,av,!![]),av+=0x8;}aR['desbyteLength']=at['getInt32'](av,!![]),av+=0x4,aR['fdensity']=at['getInt32'](av,!![]),av+=0x4,aR['edgeStartidx']=at['getInt32'](av,!![]),av+=0x4,aR['edgeIdxNum']=at['getInt32'](av,!![]),av+=0x4;}};}function ac(){};function ad(at){let au=0x0,av=at['getInt32'](au,!![]);au+=0x4;for(let aw=0x0;aw<av;aw++){let ax=at['getInt8'](au,!![]);au+=0x1;let ay=[];for(let aD=0x0;aD<0x10;aD++){let aE=at['getFloat32'](au,!![]);ay['push'](aE),au+=0x4;}let az=at['getInt32'](au,!![]);au+=0x4;let aA=new Uint8Array(at['buffer'],at['byteOffset']+au,az),aB=String['fromCharCode']['apply'](null,aA);try{aB=decode_utf8(aB);}catch(aF){}au+=az;let aC=f['GetParent']()['AdditionalReview']['Item_Axis']();aC['visible']=ax,aC['matrix']['elements']=ay,aC['name']=aB,f['GetParent']()['AdditionalReview']['AddAxis'](aC);}}var ae=a0(a['CTVIZWFileAxisInfo']);if(ae!==-0x1){var af=T[ae];if(af!==undefined){var ag=a2(af);ad(ag);}}function ah(at){let au=0x0,av=at['getInt32'](au,!![]);au+=0x4;for(let aw=0x0;aw<av;aw++){let ax=at['getInt32'](au,!![]);au+=0x4;let ay=at['getInt32'](au,!![]);au+=0x4;let az=at['getInt32'](au,!![]);au+=0x4;let aA=[];for(let aD=0x0;aD<az;aD++){let aE=at['getFloat32'](au,!![]);aA['push'](aE),au+=0x4;}let aB=[];for(let aF=0x0;aF<0x4;aF++){let aG=at['getFloat32'](au,!![]);aB['push'](aG),au+=0x4;}let aC=f['GetParent']()['AdditionalReview']['Item_Edge']();aC['visible']=!![],aC['nType']=ay,aC['vtArray']=aA,aC['color']=aB,f['GetParent']()['AdditionalReview']['AddEdge'](aC);}}var ai=a0(a['CTVIZWFileFreeEdgeInfo']);if(ai!==-0x1){var aj=T[ai];if(aj!==undefined){var ak=a2(aj);ah(ak);}}var al=a0(a['CTNodePropTableIndices']),am=T[al],an=a2(am);function ao(at,au){var av=0x0,aw=at['getUint32'](av,!![]);av+=0x4;var ax=[];for(var ay=0x0;ay<aw;ay++){var az=at['getUint32'](av,!![]);av+=0x4;var aA={'tableTocIdx':az,'nLoadedNodes':0x0};ax['push'](aA);}for(var aB=0x0;aB<ax['length'];aB++){var aC=ax[aB],aD=T[aC['tableTocIdx']];if(aD===undefined){setTimeout(function(){i(o,d);},0x19);return;}else{var aE=a2(aD,at['buffer']);aF(aE,aD,aC);}}function aF(aG,aH,aI){var aJ=0x0,aK=aG['getUint32'](aJ,!![]);aJ+=0x4;if(aI['nLoadedNodes']===aK)return![];var aL=[];for(var aM=0x0;aM<aK;aM++){var aN={'nodeId':-0x1,'nNodeProps':0x0,'items':[]};aL['push'](aN),aN['nodeId']=aG['getUint32'](aJ,!![]),aN['nodeId']+=f['GetStartID'](),aJ+=0x4,aN['nNodeProps']=aG['getInt16'](aJ,!![]),aJ+=0x2;var aO=function(aW){return unescape(encodeURIComponent(aW));},aP=function(aW){return decodeURIComponent(escape(aW));};for(var aQ=0x0;aQ<aN['nNodeProps'];aQ++){var aR={'key':null,'value':null,'valueType':null},aS=aG['getUint32'](aJ,!![]);aJ+=0x4;var aT=new Uint8Array(aS);for(var aU=0x0;aU<aS;aU++){aT[aU]=aG['getUint8'](aJ+aU,!![]);}aR['key']=String['fromCharCode']['apply'](null,aT);try{aR['key']=aP(aR['key']);}catch(aW){}aJ+=aS,aS=aG['getUint16'](aJ,!![]),aJ+=0x2,aS=aG['getUint32'](aJ,!![]),aJ+=0x4;var aV=new Uint8Array(aS);for(var aU=0x0;aU<aS;aU++){aV[aU]=aG['getUint8'](aJ+aU,!![]);}aR['value']=String['fromCharCode']['apply'](null,aV);try{aR['value']=aP(aR['value']);}catch(aX){}aJ+=aS,aR['valType']=aG['getUint16'](aJ,!![]),aJ+=0x2,aN['items']['push'](aR);}}f['AddUserProperty'](aL),f['ResetID'](),setTimeout(function(){i(o,d);},0x19);}}if(a4===undefined)g(0x1,0x1),N=null,a8===undefined?setTimeout(function(){i(o,d);},0x19):setTimeout(function(){ab(a9,a8),console['log'](aa),am===undefined?setTimeout(function(){i(o,d);},0x19):setTimeout(function(){ao(an,am);},0x19);},0x19);else{var ap=a2(a4);Q=0x0;var aq=ap['getInt32'](Q,!![]);Q+=0x4;var ar=0x0,as=function(){for(var at=ar;at<aq;at++){var au=ar/aq;if(au===Infinity)au=0x0;console['log']('ReadMeshBlock\x20:\x20'+at);var av=0x0;av=ap['getInt32'](Q,!![]),Q+=0x4,toc=T[av];var aw=a2(toc);a5(aw,av),ar++;if(ar!==aq){setTimeout(function(){g(0x1,au),as();},0x19);break;}else setTimeout(function(){g(0x1,0x1),N=null,a8===undefined?setTimeout(function(){i(o,d);},0x19):setTimeout(function(){ab(a9,a8),am===undefined?setTimeout(function(){i(o,d);},0x19):setTimeout(function(){ao(an,am);},0x19);},0x19);},0x19);}};setTimeout(function(){g(0x1,0x0),as();},0x19);}}function K(){$['ajax']({'complete':function(){g(0x0,0x1);},'url':d['Url'],'type':'GET','beforeSend':function(){g(0x0,0x0,0x0,0x0);},'xhr':function(){var N=this,O=$['ajaxSettings']['xhr']();return O['responseType']='arraybuffer',O['onprogress']=function(P){var Q=P['loaded']/P['total'];g(0x0,Q,P['loaded'],P['total']);},O['addEventListener']('load',function(){var P=$()['jquery'],Q=P['split']('.'),R=Q[0x0]*0x1,S=Q[0x1]*0x1;if(R<=0x2&&S<0x2)N['success'](O['response']);}),O;},'responseType':'arraybuffer','processData':![],'async':!![],'success':function(N){console['log']('File\x20Downloading\x20Finish'),G(N),N=null;},'error':function(N,O,P){console['log'](N['status']),console['log'](P);}});}function L(){$['ajax']({'url':d['Url'],'type':'GET','dataType':'binary','responseType':'arraybuffer','processData':![],'async':![],'success':function(N){G(N);},'error':function(N,O,P){console['log'](N['status']),console['log'](P);}});}function M(){g(0x0,0x0,0x0,0x0);let N=new JSZip['external']['Promise'](function(O,P){JSZipUtils['getBinaryContent'](zip_url,function(Q,R){Q?P(Q):(console['log']('File\x20Downloading\x20Finish'),O(R));});});N['then'](JSZip['loadAsync'])['then'](function(O){console['log']('File\x20Uncompress\x20Start'),O['forEach'](function(P){let Q=O['file'](P);Q['async']('arraybuffer',function R(S){console['log']('progression:\x20'+S['percent']['toFixed'](0x0)+'\x20%');var T=S['percent']['toFixed'](0x2)/0x64;g(0x0,T,T,0x64);})['then'](function S(T){console['log']('File\x20Uncompress\x20Finish'),G(T),result=null;},function T(U){alert('error');});});});}if(h['Type']===BROWSER_TYPES['Edge']||h['Type']===BROWSER_TYPES['Internet_Explorer'])L();else K();}},c;});