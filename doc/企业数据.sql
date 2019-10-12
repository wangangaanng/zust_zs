INSERT INTO todb.`comp`(owid,QYMC,QY_GSGM,QY_HYLB,QY_GSXZ,QY_YX,QY_CITY,QYDZ,QY_ZCSJ,QY_LXR,QY_LXRDH,QYLXFS,QY_GSWZ,
QY_GSJS,QY_YYZZZP, QY_TYSH) SELECT a.`companyid`,a.`company_name`,a.d_scale,a.`d_industry`,a.`d_nature`,a.`email`,a.city,b.address,
FROM_UNIXTIME(a.`dateline`,'%Y-%m-%d %T') ,b.linkman,b.contact,b.contact,b.website,b.description,b.license,b.organization_code_text
 FROM 51clf_web.clf_company_all a,51clf_web.`clf_company_all_more` b
WHERE a.companyid=b.companyid

UPDATE `comp` a,`clf_data_district844` b SET a.qy_city =b.zh WHERE a.qy_city=b.`value`; 
UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J001' WHERE a.QY_GSGM=b.`value` AND b.value=0; 
/*[17:31:20][5593 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J002' WHERE a.QY_GSGM=b.`value` AND b.value=100; 
/*[17:31:48][5047 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J003' WHERE a.QY_GSGM=b.`value` AND b.value=101; 
/*[17:32:58][6736 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J004' WHERE a.QY_GSGM=b.`value` AND b.value=102; 
/*[17:33:13][4682 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J005' WHERE a.QY_GSGM=b.`value` AND b.value=103; 
/*[17:33:25][4354 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J006' WHERE a.QY_GSGM=b.`value` AND b.value=104; 
/*[17:33:41][1971 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J007' WHERE a.QY_GSGM=b.`value` AND b.value=105; 
/*[17:33:57][2203 ms]*/ UPDATE `comp` a,`clf_data_scale` b SET a.QY_GSGM ='J008' WHERE a.QY_GSGM=b.`value` AND b.value=106; 

/*[17:37:49][2469 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H001' WHERE a.QY_HYLB=b.`value` AND b.value=11; 
/*[17:38:52][2497 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H002' WHERE a.QY_HYLB=b.`value` AND b.value=21; 
/*[17:42:23][4676 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H003' WHERE a.QY_HYLB=b.`value` AND b.value=22; 
/*[17:42:26][3051 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H004' WHERE a.QY_HYLB=b.`value` AND b.value=23; 
/*[17:42:28][2204 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H005' WHERE a.QY_HYLB=b.`value` AND b.value=24; 
/*[17:42:32][3782 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H006' WHERE a.QY_HYLB=b.`value` AND b.value=31; 
/*[17:42:35][2861 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H007' WHERE a.QY_HYLB=b.`value` AND b.value=32; 
/*[17:42:38][2917 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H008' WHERE a.QY_HYLB=b.`value` AND b.value=33; 
/*[17:42:43][4794 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H009' WHERE a.QY_HYLB=b.`value` AND b.value=34; 
/*[17:42:47][4389 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H010' WHERE a.QY_HYLB=b.`value` AND b.value=35; 
/*[17:42:51][3786 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H011' WHERE a.QY_HYLB=b.`value` AND b.value=36; 
/*[17:42:53][2632 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H012' WHERE a.QY_HYLB=b.`value` AND b.value=37; 
/*[17:42:56][2660 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H013' WHERE a.QY_HYLB=b.`value` AND b.value=38; 
/*[17:42:58][2408 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H014' WHERE a.QY_HYLB=b.`value` AND b.value=39; 
/*[17:43:02][3322 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H015' WHERE a.QY_HYLB=b.`value` AND b.value=41; 
/*[17:43:06][4525 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H016' WHERE a.QY_HYLB=b.`value` AND b.value=42; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H017' WHERE a.QY_HYLB=b.`value` AND b.value=43; 
/*[17:43:13][3527 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H018' WHERE a.QY_HYLB=b.`value` AND b.value=44; 
/*[17:43:16][3440 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H019' WHERE a.QY_HYLB=b.`value` AND b.value=45; 
/*[17:43:18][1244 ms]*/ UPDATE `comp` a,`clf_data_industry` b SET a.QY_HYLB ='H020' WHERE a.QY_HYLB=b.`value` AND b.value=46; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G001' WHERE a.QY_GSXZ=b.`value` AND b.value=150001; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G002' WHERE a.QY_GSXZ=b.`value` AND b.value=150002; 

/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G003' WHERE a.QY_GSXZ=b.`value` AND b.value=150003; 

/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G004' WHERE a.QY_GSXZ=b.`value` AND b.value=150004; 

/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G005' WHERE a.QY_GSXZ=b.`value` AND b.value=150005; 

/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G006' WHERE a.QY_GSXZ=b.`value` AND b.value=150006; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G007' WHERE a.QY_GSXZ=b.`value` AND b.value=150007; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G008' WHERE a.QY_GSXZ=b.`value` AND b.value=150008; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G009' WHERE a.QY_GSXZ=b.`value` AND b.value=150009; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G010' WHERE a.QY_GSXZ=b.`value` AND b.value=150010; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G011' WHERE a.QY_GSXZ=b.`value` AND b.value=150011; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G012' WHERE a.QY_GSXZ=b.`value` AND b.value=150012; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G013' WHERE a.QY_GSXZ=b.`value` AND b.value=150013; 
/*[17:43:09][3028 ms]*/ UPDATE `comp` a,`clf_data_nature_new` b SET a.`QY_GSXZ` ='G014' WHERE a.QY_GSXZ=b.`value` AND b.value=150014; 


SELECT MAX(QY_ZCSJ) FROM BCKJ_BIZ_QYXX


INSERT INTO  BCKJ_BIZ_QYXX ( owid,QYMC,QY_GSGM,QY_HYLB,QY_GSXZ,QY_YX,QY_CITY,QYDZ,QY_ZCSJ,QY_LXR,QY_LXRDH,QYLXFS,QY_GSWZ,
QY_GSJS,QY_YYZZZP, QY_TYSH,state)SELECT owid,QYMC,QY_GSGM,QY_HYLB,QY_GSXZ,QY_YX,QY_CITY,QYDZ,QY_ZCSJ,QY_LXR,QY_LXRDH,QYLXFS,QY_GSWZ,
QY_GSJS,QY_YYZZZP, QY_TYSH,2 FROM `comp` WHERE QY_ZCSJ>'2019-09-26 14:00:38'
