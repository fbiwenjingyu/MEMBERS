CREATE  DEFINER=`root`@`localhost` PROCEDURE `GenPointTaskDetail`(IN `collectDate` date)
BEGIN
  declare taskId int; 
  declare userId int; 
  declare pointAmount int; 
  declare done int default 0;  #初始化变量done为0
  #declare collectDate date default IF(HOUR(CURRENT_TIME())>=12, CURDATE(),DATE_SUB(CURDATE(),INTERVAL 1 DAY));
  #SET collectDate = IF(HOUR(CURRENT_TIME())<12, CURDATE(),DATE_SUB(CURDATE(),INTERVAL 1 DAY));
  declare cur_task CURSOR for select P.TASK_ID,P.USER_ID,P.POINT_AMOUNT FROM M_POINT_TASK P LEFT JOIN  M_USER_INFO U  ON P.USER_ID = U.ID
  LEFT JOIN M_POINT_TASK_DETAIL X ON P.USER_ID = X.USER_ID AND X.COLLECT_DATE = collectDate
  WHERE P.EXEC_STATUS = 1 AND U.TYPE IN(1,3) AND P.TOTAL_DAYS > P.EXEC_DAYS AND X.ID IS NULL;
  #AND IFNULL(P.CURR_EXEC_DATE,P.CREATE_TIME)  < CURDATE() ; 
  declare CONTINUE HANDLER  FOR NOT FOUND SET done = 1; #当读到数据的最后一条时,设置done变量为1
     
     open cur_task; 
         fetch cur_task into taskId,userId,pointAmount; #读取游标中的数据一一复给变量。
           while done = 0 do       #判断是不是到了最后一条数据
		
              INSERT INTO M_POINT_TASK_DETAIL(TASK_ID, CREATE_TIME,COLLECT_DATE, USER_ID, POINT_AMOUNT, EXEC_STATUS, VERSION) 
              VALUES(taskId,CURRENT_TIMESTAMP(),collectDate,userId,pointAmount,1,1);

              update M_POINT_TASK set EXEC_DAYS = EXEC_DAYS + 1, LAST_EXEC_DATE = CURR_EXEC_DATE,CURR_EXEC_DATE =CURDATE(),VERSION = VERSION+1    where TASK_ID=taskId;

              fetch cur_task into taskId,userId,pointAmount; #读取游标中的数据一一复给变量。
          end while;    
     close cur_task;#最后关闭游标.游标里面存放了那么多数据总是要清理掉的吧。
 
END



DROP PROCEDURE  IF  EXISTS getCollectDate;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCollectDate`(OUT `collectDate` date,OUT `nums` int)
BEGIN 
  declare tmpDate date;
  set collectDate = IF(HOUR(CURRENT_TIME())>=12, CURDATE(),DATE_SUB(CURDATE(),INTERVAL 1 DAY));
 
  
  
  IF tmpDate <  collectDate THEN  
   set collectDate = tmpDate;
  END IF ;
	
  SELECT COUNT(1) INTO nums FROM M_POINT_TASK_DETAIL  WHERE EXEC_STATUS = 1 AND COLLECT_DATE = collectDate;
END





CREATE DEFINER=`root`@`localhost` PROCEDURE `genHlgConsumptionData`(IN `collectDate` date)
BEGIN
	INSERT into M_USER_ACCOUNT_INOUT_HLG(ID,USER_ID, ACCOUNT_TYPE, INOUT_TYPE, AMOUNT, TRADE_NO, INOUT_DESC, CREATE_TIME,OPERATOR_NO, USER_TYPE,EXEC_FLAG)
SELECT A.ID,A.USER_ID, A.ACCOUNT_TYPE, A.INOUT_TYPE, A.AMOUNT, A.TRADE_NO, A.INOUT_DESC, A.CREATE_TIME,A.OPERATOR_NO, A.USER_TYPE ,1 EXEC_FLAG FROM M_USER_ACCOUNT_INOUT A

LEFT JOIN M_USER_ACCOUNT_INOUT_HLG X ON A.ID = X.ID WHERE A.USER_TYPE = 12 AND DATE(A.CREATE_TIME) = collectDate  AND X.ID IS NULL;

END











CREATE DEFINER=`root`@`localhost` PROCEDURE `GenDayPointDetailAndReport`(IN `collectDate` date)
BEGIN 
   
 declare vipNum int;
  declare drawReq decimal(11,2); 
  declare drawAmount decimal(11,2); 
  declare reAmount decimal(11,2); 
  declare reBeans int; 
  declare rePoint int;
   declare exchNum int;
   declare sendNum int;
   declare  hlgNum int;
   declare  hlgAmount decimal(11,2); 
   declare  hlgBeans int; 
   declare  actAmount decimal(11,2); 
   declare  actBeans int;
   declare  actPoint int;

     UPDATE M_DAY_POINT_DETAIL A,M_USER_INFO B SET  A.TEAM_MONEY = B.TEAM_MONEY, A.MY_MONEY = B.MY_MONEY,A.VERSION = A.VERSION+1
     WHERE A.USER_ID = B.ID AND A.COLLECT_DATE = collectDate;

	
     INSERT INTO M_DAY_POINT_DETAIL  (COLLECT_DATE, USER_ID, REWARD_AMOUNT, BEANS_NUM, POINT_AMOUNT, MANAGE_AMOUNT, COACH_AMOUNT, STATUS, VERSION, TEAM_MONEY, MY_MONEY) 
     SELECT  collectDate,A.ID, 0, 0, 0,0, 0,2,1, A.TEAM_MONEY, A.MY_MONEY
     FROM M_USER_INFO A LEFT JOIN M_DAY_POINT_DETAIL B ON A.ID = B.USER_ID  AND B.COLLECT_DATE = collectDate
     WHERE B.ID IS NULL;

     DELETE FROM M_DAY_REPORT WHERE COLLECT_DATE = collectDate;
	
     SELECT COUNT(1) into vipNum FROM M_USER_INFO WHERE DATE(ACTIVATE_TIME) = collectDate and IS_OPEND = 2 ;

     SELECT IFNULL(SUM(AMOUNT),0) into drawReq FROM M_DRAW WHERE DATE(CREATE_TIME) = collectDate;
     SELECT IFNULL(SUM(AMOUNT),0) into drawAmount FROM M_DRAW WHERE DATE(UPDATE_TIME) = collectDate and STATUS = 5;
     
     SELECT IFNULL(SUM(AMOUNT*IF(FORWARD = 0,1,-1)),0) into reAmount FROM M_RECHARGE WHERE DATE(CREATE_TIME) = collectDate and STATUS = 2 AND ACCOUNT_TYPE = 1;
     SELECT IFNULL(SUM(AMOUNT*IF(FORWARD = 0,1,-1)),0) into reBeans FROM M_RECHARGE WHERE DATE(CREATE_TIME) = collectDate and STATUS = 2 AND ACCOUNT_TYPE = 2;
     SELECT IFNULL(SUM(AMOUNT*IF(FORWARD = 0,1,-1)),0) into rePoint FROM M_RECHARGE WHERE DATE(CREATE_TIME) = collectDate and STATUS = 2 AND ACCOUNT_TYPE = 3;
	
     SELECT SUM(EXCHANGE_NUM) into exchNum FROM M_EXCHANGE_INFO WHERE DATE(APPLY_TIME) = collectDate;
     SELECT SUM(EXCHANGE_NUM) into sendNum FROM M_EXCHANGE_INFO WHERE DATE(SEND_TIME) = collectDate AND STATE = 2;

     SELECT COUNT(DISTINCT USER_ID) into hlgNum FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND USER_TYPE = 12;
     SELECT IFNULL(SUM(AMOUNT),0) into hlgAmount FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND  ACCOUNT_TYPE = 1 AND  USER_TYPE = 12;
     SELECT IFNULL(SUM(AMOUNT),0) into hlgBeans FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND   ACCOUNT_TYPE = 2 AND USER_TYPE = 12;


     
     SELECT IFNULL(SUM(AMOUNT),0) into actAmount FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND   INOUT_TYPE = 124;
     SELECT IFNULL(SUM(AMOUNT),0) into actBeans FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND   INOUT_TYPE = 224;
     SELECT IFNULL(SUM(AMOUNT),0) into actPoint FROM M_USER_ACCOUNT_INOUT WHERE DATE(CREATE_TIME) = collectDate  AND   INOUT_TYPE = 324;

     INSERT  INTO M_DAY_REPORT(COLLECT_DATE, REWARD_AMOUNT, BEANS_NUM, POINT_AMOUNT, MANAGE_AMOUNT, COACH_AMOUNT,VIP_NUM,DRAW_REQ,DRAW_AMOUNT,RECHARGE_AMOUNT,
     RECHARGE_BEANS,RECHARGE_POINT,EXCHANGE_NUM,SEND_NUM,HLG_NUM,HLG_AMOUNT,HLG_BEANS,ACT_USER_AMOUNT,ACT_USER_BEANS,ACT_USER_POINT, VERSION)
     SELECT COLLECT_DATE, SUM(REWARD_AMOUNT) , SUM(BEANS_NUM) , SUM(POINT_AMOUNT) ,
     SUM(MANAGE_AMOUNT) , SUM(COACH_AMOUNT) ,vipNum,drawReq,drawAmount,reAmount,reBeans,rePoint,exchNum,sendNum,hlgNum,-1*hlgAmount,-1*hlgBeans,-1*actAmount,-1*actBeans,-1*actPoint, 1
     FROM M_DAY_POINT_DETAIL WHERE COLLECT_DATE = collectDate GROUP BY COLLECT_DATE;

     

	     
END











CREATE DEFINER=`root`@`localhost` PROCEDURE `stockDividend`(IN `operatorNo` varchar(60))
BEGIN

  declare userId int; 
  declare accountAmount int; 
  declare accountNo varchar(80);
  declare done int default 0;  #初始化变量done为0
   
  declare cur_stock CURSOR for SELECT A.ID,A.ACCOUNT_NO,B.ACCOUNT_AMOUNT from  M_USER_INFO A  JOIN M_USER_ACCOUNT B 
  ON A.ID = B.USER_ID AND A.TYPE IN (1,3) AND  B.ACCOUNT_TYPE = 4  and ACCOUNT_AMOUNT > 0; 
  declare CONTINUE HANDLER  FOR NOT FOUND SET done = 1; #当读到数据的最后一条时,设置done变量为1
     
     open cur_stock; 
         fetch cur_stock into userId,accountNo,accountAmount; #读取游标中的数据一一复给变量。
           while done = 0 do       #判断是不是到了最后一条数据
		
              INSERT INTO M_USER_ACCOUNT_INOUT  (USER_ID, ACCOUNT_TYPE, INOUT_TYPE, AMOUNT, TRADE_NO, INOUT_DESC, CREATE_TIME, OPERATOR_NO, USER_TYPE) 
VALUES (userId, 1,114, accountAmount*2, accountNo, '股权分红', now(), operatorNo, 0);

              update M_USER_ACCOUNT set ACCOUNT_AMOUNT = ACCOUNT_AMOUNT + accountAmount*2,VERSION = VERSION+1    where USER_ID = userId AND ACCOUNT_TYPE = 1 ;

              fetch cur_stock into userId,accountNo,accountAmount; #读取游标中的数据一一复给变量。
          end while;    
     close cur_stock;#最后关闭游标.游标里面存放了那么多数据总是要清理掉的吧。




END