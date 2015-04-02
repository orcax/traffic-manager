CREATE TABLE accidents (
  id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  created datetime,
  job_location varchar(100),
  location varchar(100),
  type varchar(100),
  vehicle varchar(100),
  result varchar(100),
  description varchar(200),
  duty varchar(100),
  compensation varchar(100),
  human_factor varchar(100),
  vehicle_factor varchar(100),
  road_factor varchar(100),
  env_factor varchar(100),
  job_factor varchar(100)
);

CREATE TABLE jobs (
  id integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  accident_id integer,
  created datetime,
  weather varchar(100),
  highway varchar(100),
  road_section varchar(100),
  roadway varchar(100),
  type varchar(100),
  content varchar(100),
  warning varchar(100),
  speed_limit integer,
  transition_warning varchar(100),
  buffer_facility varchar(100),
  section_length integer
);

INSERT INTO jobs VALUES(1,NULL,"2014/07/30 17:30:00","晴天","S26沪常高速","单向4车道","2、3、4号车道","长期定点养护维修作业","路面养护","施工警告标志、限速标志、变道标志",41,"施工警告板","施工警告板",1000);
INSERT INTO jobs VALUES(2,NULL,"2015/01/10 10:00:00","晴天","G15嘉金高速","单向3车道","2号车道","临时定点养护维修作业","伸缩缝养护","施工警告标志、限速标志、变道标志",60,"无","防撞拖车",180);
INSERT INTO jobs VALUES(3,NULL,"2015/01/27 15:30:00","阴天","G1501绕城高速公路","单向3车道","1号、2号车道","长期定点养护维修作业","路面养护","施工警告标志、限速标志、变道标志",40,"无","施工警告板",3000);
INSERT INTO jobs VALUES(4,NULL,"2015/02/02 10:00:00","多云","G60沪昆高速","单向4车道","1号车道","临时定点养护维修作业","绿化隔离带养护","施工警告标志、限速标志、变道标志",60,"施工警告板","施工警告板",150);
INSERT INTO jobs VALUES(5,NULL,"2015/02/02 11:00:00","多云","G60沪昆高速","单向3车道","4号车道、紧急停车带","长期定点养护维修作业","路面养护","施工警告标志、限速标志、变道标志",61,"施工警告板","施工警告板",150);

INSERT INTO accidents VALUES(1,"2010/02/10 12:42","G50高速21K处","作业区内","撞击养护车辆","小客车","1名作业人员死亡","一辆小客车撞击正在超车道更换道路指向标牌作业的轻型货车","养护单位次要责任，社会车辆主要责任","","","","","","");
INSERT INTO accidents VALUES(2,"2010/06/06 09:00","S19高速公路9K+100处","作业区内","撞击养护车辆","小客车","1名作业人员死亡","养护公司在东侧边坡绿化带及紧急停车带内进行保洁作业，被一辆车辆撞击","无法认定责任","","","","","","");
INSERT INTO accidents VALUES(3,"2010/06/26 13:00","陈海公路35K+350处","作业区内","车辆正面撞击作业区","大型平板车","1名作业人员死亡，1人重伤，2人轻伤","一辆大型平板车与同方向行驶的养护作业手扶拖拉机发生追尾","养护单位次要责任，社会车辆主要责任","","","","","","");
INSERT INTO accidents VALUES(4,"2010/07/05 16:00","S32高速公路下行K28+100处","警告区段","车辆正面撞击作业区","小客车","1人死亡2人轻伤","一辆小轿车失控冲入养护维修作业区","无法认定责任","","","","","","");
INSERT INTO accidents VALUES(5,"2010/07/09 13:40","沪青平公路上行59K+650处","警告区段","车辆正面撞击作业区","中巴车","3名作业人员受伤","养护公司在中央绿化带 进行绿化修剪作业时，移动式标志车辆被一辆中巴追尾撞击，标志车被撞击移动时，将3名养护作业人员撞伤。","养护单位无责，社会车辆全部责任","","","","","","");
INSERT INTO accidents VALUES(6,"2010/08/27 21:35","S20外环高速龙吴路立交","警告区段","车辆正面撞击作业区","箱式货车","作业人员2人死亡，2人受伤，对方驾驶员死亡","进行桥梁伸缩缝清掏养护作业。一施工车辆，被一辆沪K19153的两吨厢式货车追尾，将正在前方检查桥梁伸缩缝的四名作业人员撞倒","养护单位次要责任，社会车辆主要责任","","","","","","");
INSERT INTO accidents VALUES(7,"2011/06/16 09:30","S32高速","过渡区段","车辆侧面撞进作业区","小客车","小客车内1死1伤，车辆损毁，清扫车驾驶员受伤","辆正在正常清扫作业的清扫车，被一辆小客车追尾","养护单位全责，社会车辆无责","","","","","","");
INSERT INTO accidents VALUES(8,"2011/06/29 08:10","G15高速（嘉金段）下行1288.2K","过渡区段","车辆侧面撞进作业区","集卡","1名作业人员死亡","桥梁伸缩缝养护维修作业，在布设养护维修安全控制区时，一辆集卡闯入控制区","养护单位次要责任，社会车辆主要责任","","","","","","");
INSERT INTO accidents VALUES(9,"2011/01/25 10:43","G2高速（沪宁）上行1200K","过渡区段","车辆侧面撞进作业区","小客车","作业人员1人死亡2人受伤，小客车驾驶员重伤事故","发生一起小客车闯入施工区","养护单位无责，社会车辆全部责任","","","","","","");
INSERT INTO accidents VALUES(10,"2011/11/08 14:20","G2高速（沪宁）下行1210K（沪杭铁路老桥）","缓冲区段","撞击作业人员","小客车","小客车驾驶死亡","生一起小客车爆胎失控撞击正在施工车辆","无法认定责任","","","","","","");
INSERT INTO accidents VALUES(11,"2012/04/10 15:30","外环线内圈1#车道（近北翟路匝道口）","缓冲区段","撞击作业人员","箱式货车","1名作业人员死亡","一名保洁工人女清理垃圾过程中，被一辆小型厢式货车撞击","双方同等责任","","","","","","");
INSERT INTO accidents VALUES(12,"2012/07/31 09:00","外环线内圈59K+900","下游过渡区段","撞击作业人员","小客车","1名作业人员死亡","中分带进行绿地保洁作业时，发现车道上有垃圾去捡拾，被一辆快速行驶在1#车道上的出租车碰擦后受伤倒地","养护单位主要责任，社会车辆次要责任","","","","","","");
INSERT INTO accidents VALUES(13,"2012/10/15 11:30","G40高速公路启东往上海近陈海公路1公里处","下游过渡区段","车辆追尾事故","小客车","5名作业人员死亡，三人重伤","9名养护公司工人在中央绿化带外绿化养护作业，无任何警示标志，被一辆小客车失控撞击","养护单位次要责任，社会车辆主要责任","","","","","","");
INSERT INTO accidents VALUES(14,"2012/12/03 12:40","海绕城高速公路南段G1501外圈119K处","下游过渡区段","车辆追尾事故","箱式货车","1人轻伤、1人重伤","辆浙江牌照的厢式货车由西向东行驶，在距离作业现场15米左右突然变道，侧向闯入施工作业区域，当即撞上两名公路养护人员","养护单位无责，社会车辆全部责任","","","","","","");