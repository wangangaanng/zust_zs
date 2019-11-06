/**招生数据新老变换**/
专业
   INSERT INTO zust_website.bckj_biz_xyzy(
			owid,
			parent_id,
			path,
			bh,
			mz,
			jj,tp,
			article_url)  SELECT id, parent_id,
			path,
			bh,
			mz,
			jj,
			tp, exp1 FROM `zust_zs`.`app_biz_school_major`

文章
INSERT INTO `zust_website`.bckj_biz_article(
			owid,
			tpjj,
			jjnr,
			lmbh,
			fbr,
			fbsj,
			sxsj,
			wzbt,
			wzly,
			wznr,
			wzzt,
			ydcs,
			istop,
			sxh,
			gjz,
			memo)
SELECT ID,TPJJ,jjnr,lmbh,fbr,fbsj,sxsj,wzbt,wzly,wznr,wzzt,ydcs,0,sxh,exp9,lmbh2 FROM `zust_zs`.`app_biz_article`


UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='216' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='218' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='219' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='215' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='213' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='232' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='257' ; -- 招生计划
UPDATE bckj_biz_article SET lmbh='66' WHERE lmbh='253' ;


UPDATE `zust_website`.bckj_biz_article a,`zust_website`.BCKJ_DIC_MENU  b ,`zust_zs`.`app_sys_code` c  SET a.lmbh=b.owid WHERE a.lmbh=c.id AND c.value=b.name


//附件
INSERT `zust_website`.`ourway_sys_files` (owid,FILE_CLASS_ID,FILE_CLASS,FILE_LABEL,FILE_PATH)
SELECT id ,app_id,'article',FJMC ,fjlj FROM zust_zs.`app_biz_att`


//图片
INSERT INTO`zust_website`.`bckj_biz_picvid`(owid,lmbh,bt ,XSBT )
SELECT id,lmbh,bt ,url FROM `zust_zs`.`app_biz_picvid` WHERE lmbh='162'
//
INSERT INTO `zust_website`.bckj_biz_zxzx(
			owid,
			zxlx,
			wtnr,
			danr,
			twrq,
			hdrq,
			sfxs,
			lyip
		) SELECT id,4,wtnr,danr,twrq,hdrq,exp1,exp2 FROM `zust_zs`.`app_biz_consult` ORDER BY TWRQ DESC LIMIT 2000