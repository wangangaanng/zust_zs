老数据中type=1表示社会招聘会  新系统值为3
老数据中type=3表示职来职往  新系统值为1

INSERT INTO shehuzph(owid,ZWBT,TYPE,ZW_CITY,ZPH_KSRQ,ZPH_JTSJ,ZPH_JBDD,MEMO,ZPH_JBF,ZPH_CBF,)
SELECT a.jid,a.`title`, a.type,a.`city` ,FROM_UNIXTIME(a.`starttime`,'%Y-%m-%d %T') AS stsatrt,a.`endtime` ,b.address,c.`content` ,a.large_sponsor ,a.large_undertake
FROM 51clf_web.`clf_jobfair_all` a ,51clf_web.`clf_jobfair_address` b ,51clf_web.`clf_jobfair_all_more` c
WHERE a.address=b.id AND a.jid=c.jid  AND a.jid>16960  ORDER BY  stsatrt DESC ;
UPDATE shehuzph set CREATETIME =ZPH_KSRQ;
UPDATE shehuzph SET ZWLX=3 WHERE TYPE=1
UPDATE shehuzph SET ZWLX=1 WHERE TYPE=3