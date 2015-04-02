package edu.tju.ste.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

import edu.tju.ste.common.Attribute;

@SuppressWarnings("serial")
public class Accident extends Model<Accident> {

  public static final String tableName = "accidents";

  public static final Accident dao = new Accident();

  public static final Map<String, Attribute> attrs = new HashMap<String, Attribute>();
  static {
    attrs.put("created", new Attribute("created", "事故发生时间"));
    attrs.put("job_location", new Attribute("job_location", "作业区位置"));
    attrs.put("location", new Attribute("location", "事故位置", new String[] {
        "作业区内", "警告区段", "过渡区段", "缓冲区段", "工作区段", "下游过渡区段", "终止区段" }));
    attrs.put("type", new Attribute("type", "事故类型", new String[] { "撞击养护车辆",
        "车辆正面撞击作业区", "车辆侧面撞进作业区", "撞击作业人员", "车辆追尾事故", "车辆侧向碰擦事故" }));
    attrs.put("vehicle", new Attribute("vehicle", "肇事车辆类型"));
    attrs.put("result", new Attribute("result", "事故后果（财产损失及人员伤亡情况）"));
    attrs.put("description", new Attribute("description", "事故经过描述"));
    attrs.put("duty", new Attribute("duty", "责任认定", new String[] {
        "养护单位全责，社会车辆无责", "养护单位主要责任，社会车辆次要责任", "养护单位次要责任，社会车辆主要责任",
        "养护单位无责，社会车辆全部责任", "双方同等责任", "无法认定责任" }));
    attrs
        .put("compensation", new Attribute("compensation", "赔偿金额(甲、乙双方各赔付多少)"));
    attrs.put("human_factor", new Attribute("human_factor", "人的因素",
        new String[] { "注意力不集中", "判断失误", "无视交通管控", "酒驾", "视线问题" }));
    attrs.put("vehicle_factor", new Attribute("vehicle_factor", "车的因素",
        new String[] { "车辆年限长", "流量大", "大车比例高", "超速", "速度方差大", "跟车太近",
            "不合理的合流", "关闭车道影响交通流" }));
    attrs.put("road_factor", new Attribute("road_factor", "道路因素", new String[] {
        "道路信息复杂", "交通管理差", "无警察执法", "存在急弯", "车道宽度减小", "道路线形差" }));
    attrs.put("env_factor", new Attribute("env_factor", "环境因素", new String[] {
        "天气潮湿", "照明差" }));
    attrs.put("job_factor", new Attribute("job_factor", "作业区因素", new String[] {
        "作业区内出现重型设备", "作业时间长", "作业区长度长" }));
  };

  public List<Accident> findAll() {
    return this.find("SELECT * FROM " + tableName + ";");
  }

  public List<Accident> findBy(Map<String, String> conditions) {
    StringBuffer sql = new StringBuffer("SELECT * FROM " + tableName
        + " WHERE ");
    Iterator<String> it = conditions.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      String value = conditions.get(key);
      sql.append("`" + key + "`='" + value + "' AND ");
    }
    String sql2 = sql.substring(0, sql.length() - 5) + ";";
    System.out.println(sql2);
    return this.find(sql2);
  }
  
}
