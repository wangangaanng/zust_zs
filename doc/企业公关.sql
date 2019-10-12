INSERT INTO todb.xjh( OWID,ZWBT,ZW_CITY,ZWLX,CREATETIME,exp1,memo,STATE)

SELECT CONCAT(''zpgg'',a.pid) AS OWID, a.TITLE,a.city,2,FROM_UNIXTIME(a.`dateline`,''%Y-%m-%d %T'') ,a.`company_name` ,b.`content`,2 FROM `clf_campus_all` a,`clf_campus_all_more` b 
WHERE a.`pid`=b.`pid` ORDER 	BY  a.`dateline` DESC LIMIT 5000

INSERT INTO bckj_biz_job( OWID,ZWBT,ZW_CITY,ZWLX,CREATETIME,exp1,memo,state) SELECT OWID,ZWBT,ZW_CITY,ZWLX,CREATETIME,exp1,memo,state FROM xjh
