INSERT INTO todb.`job`(OWID,QYXX_REF_OWID,ZWBT,CREATETIME,ZW_CITY,ZW_LXYX,ZW_ZPRS,ZW_NLYQ,ZW_XLYQ,ZW_GZNX,ZW_YYYQ,ZW_GWZZ,zw_GZXZ,zw_GZZN) SELECT a. jobid,a.`companyid`,a.job_name ,FROM_UNIXTIME(a.`dateline`,'%Y-%m-%d %T') AS datatata,a.city ,b.`email`,b.num,b.d_age ,b.`d_education`,b.`d_work_years`,b.`d_lang`,
b.`description`,a.d_category,a.d_skill FROM `51clf_web`.`clf_job_all` a,
 51clf_web.`clf_job_all_more` b WHERE a.jobid=b.`jobid` AND a.`jobid`>176272 ORDER BY datatata DESC LIMIT 500;
 
 
 UPDATE `job` a,`clf_data_district844` b SET a.zw_city =b.zh WHERE a.zw_city=b.`value`; 
 /*[17:52:17][125 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_NLYQ ='C001' WHERE a.ZW_NLYQ=b.value AND b.value=10; 
/*[17:52:17][153 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_NLYQ ='C002' WHERE a.ZW_NLYQ=b.value AND b.value=20; 
/*[17:52:17][153 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_NLYQ ='C003' WHERE a.ZW_NLYQ=b.value AND b.value=30; 
/*[17:52:17][150 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_NLYQ ='C004' WHERE a.ZW_NLYQ=b.value AND b.value=40; 
/*[17:52:18][150 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_NLYQ ='C005' WHERE a.ZW_NLYQ=b.value AND b.value=50; 
                       UPDATE `job` a SET a.ZW_NLYQ ='C006' WHERE a.ZW_NLYQ IS NULL; 
/*[18:00:50][394 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E001' WHERE a.ZW_yyYQ=b.value AND b.value=100; 
/*[18:00:50][203 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E002' WHERE a.ZW_yyYQ=b.value AND b.value=101; 
/*[18:00:51][102 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E003' WHERE a.ZW_yyYQ=b.value AND b.value=102; 
/*[18:00:51][102 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E004' WHERE a.ZW_yyYQ=b.value AND b.value=103; 
/*[18:00:51][100 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E005' WHERE a.ZW_yyYQ=b.value AND b.value=104; 
/*[18:00:51][105 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E006' WHERE a.ZW_yyYQ=b.value AND b.value=105; 
/*[18:00:51][100 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E007' WHERE a.ZW_yyYQ=b.value AND b.value=106; 
/*[18:00:51][100 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E008' WHERE a.ZW_yyYQ=b.value AND b.value=107; 
/*[18:00:51][100 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E009' WHERE a.ZW_yyYQ=b.value AND b.value=108; 
/*[18:00:51][101 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E010' WHERE a.ZW_yyYQ=b.value AND b.value=109; 
/*[18:00:51][100 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E011' WHERE a.ZW_yyYQ=b.value AND b.value=110; 
/*[18:00:51][127 ms]*/ UPDATE `job` a,`clf_data_language` b SET a.ZW_yyYQ ='E012' WHERE a.ZW_yyYQ=b.value AND b.value=111; 

/*[14:48:58][31 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D001' WHERE a.ZW_XLYQ=b.value AND b.value=100; 
/*[14:48:58][48 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D002' WHERE a.ZW_XLYQ=b.value AND b.value=101; 
/*[14:48:58][21 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D003' WHERE a.ZW_XLYQ=b.value AND b.value=102; 
/*[14:48:58][4 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D004' WHERE a.ZW_XLYQ=b.value AND b.value=103; 
/*[14:48:58][5 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D005' WHERE a.ZW_XLYQ=b.value AND b.value=104; 
/*[14:48:58][5 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D006' WHERE a.ZW_XLYQ=b.value AND b.value=105; 
/*[14:48:58][26 ms]*/ UPDATE `job` a,`clf_data_education` b SET a.ZW_XLYQ ='D007' WHERE a.ZW_XLYQ=b.value AND b.value=0; 

/*[14:57:48][9 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_GZXZ ='B001' WHERE a.ZW_GZXZ=b.value AND b.value=0; 
/*[14:57:48][57 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_GZXZ ='B002' WHERE a.ZW_GZXZ=b.value AND b.value=100; 
/*[14:57:48][28 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_GZXZ ='B003' WHERE a.ZW_GZXZ=b.value AND b.value=101; 
/*[14:57:48][33 ms]*/ UPDATE `job` a,`clf_data_age` b SET a.ZW_GZXZ ='B004' WHERE a.ZW_GZXZ=b.value AND b.value=102; 
/*[14:58:43][6 ms]*/ UPDATE `job` a SET a.ZW_GZXZ ='B001' WHERE a.ZW_GZXZ IS NULL; 
/*[15:20:04][84 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A001' WHERE a.ZW_GZZN=b.value AND b.value=230100; 
/*[15:20:04][59 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A002' WHERE a.ZW_GZZN=b.value AND b.value=230200; 
/*[15:20:05][55 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A003' WHERE a.ZW_GZZN=b.value AND b.value=230300; 
/*[15:20:05][93 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A004' WHERE a.ZW_GZZN=b.value AND b.value=230400; 
/*[15:20:05][60 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A005' WHERE a.ZW_GZZN=b.value AND b.value=230500; 
/*[15:20:05][66 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A006' WHERE a.ZW_GZZN=b.value AND b.value=230900; 
/*[15:20:05][55 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A007' WHERE a.ZW_GZZN=b.value AND b.value=231100; 
/*[15:20:05][55 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A008' WHERE a.ZW_GZZN=b.value AND b.value=231300; 
/*[15:20:05][48 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A009' WHERE a.ZW_GZZN=b.value AND b.value=231400; 
/*[15:20:05][57 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A010' WHERE a.ZW_GZZN=b.value AND b.value=231500; 
/*[15:20:05][58 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A011' WHERE a.ZW_GZZN=b.value AND b.value=231600; 
/*[15:20:05][67 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A012' WHERE a.ZW_GZZN=b.value AND b.value=231700; 
/*[15:20:05][49 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A013' WHERE a.ZW_GZZN=b.value AND b.value=231800; 
/*[15:20:05][66 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A014' WHERE a.ZW_GZZN=b.value AND b.value=232100; 
/*[15:20:05][55 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A015' WHERE a.ZW_GZZN=b.value AND b.value=232500; 
/*[15:20:05][47 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A016' WHERE a.ZW_GZZN=b.value AND b.value=232600; 
/*[15:20:05][42 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A017' WHERE a.ZW_GZZN=b.value AND b.value=232700; 
/*[15:20:05][49 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A018' WHERE a.ZW_GZZN=b.value AND b.value=232800; 
/*[15:20:06][50 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A019' WHERE a.ZW_GZZN=b.value AND b.value=233100; 
/*[15:20:06][45 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A020' WHERE a.ZW_GZZN=b.value AND b.value=233500; 
/*[15:20:06][49 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A021' WHERE a.ZW_GZZN=b.value AND b.value=233600; 
/*[15:20:06][51 ms]*/ UPDATE `job` a,`clf_data_skill` b SET a.ZW_GZZN ='A022' WHERE a.ZW_GZZN=b.value AND b.value=239900; 
INSERT INTO bckj_biz_job(OWID,QYXX_REF_OWID,ZWBT,CREATETIME,ZW_CITY,ZW_LXYX,ZW_ZPRS,ZW_NLYQ,ZW_XLYQ,ZW_GZNX,ZW_YYYQ,ZW_GWZZ,zw_GZXZ,zw_GZZN,zwlx)SELECT OWID,QYXX_REF_OWID,ZWBT,CREATETIME,ZW_CITY,ZW_LXYX,ZW_ZPRS,ZW_NLYQ,ZW_XLYQ,ZW_GZNX,ZW_YYYQ,ZW_GWZZ,zw_GZXZ,zw_GZZN ,0 FROM job
UPDATE bckj_biz_job A,COMP B SET A.EXP1=B.QYMC WHERE A.QYXX_REF_OWID=B.OWID; 