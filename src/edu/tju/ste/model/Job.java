package edu.tju.ste.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Model;

import edu.tju.ste.common.Attribute;

@SuppressWarnings("serial")
public class Job extends Model<Job> {

  public static final String tableName = "jobs";

  public static final Job dao = new Job();

  public static final Map<String, Attribute> attrs = new HashMap<String, Attribute>();
  static {
    attrs.put("created", new Attribute("created", "时间日期"));
    attrs.put("weather", new Attribute("weather", "天气情况", new String[] { "晴天",
        "多云", "阴天", "雨天", "大风" }));
    attrs.put("highway", new Attribute("highway", "高速公路名称及标段号"));
    attrs.put("road_section", new Attribute("road_section", "道路断面形式",
        new String[] { "单向2车道", "单向3车道", "单向4车道" }));
    attrs.put("roadway", new Attribute("roadway", "作业所在车道", new String[] {
        "1号车道", "2号车道", "3号车道", "4号车道", "紧急停车带" }));
    attrs.put("type", new Attribute("type", "作业类型", new String[] { "移动养护维修作业",
        "临时定点养护维修作业", "短期定点养护维修作业", "长期定点养护维修作业" }));
    attrs.put("content", new Attribute("content", "作业内容"));
    attrs.put("warning", new Attribute("warning", "警告标志布置"));
    attrs.put("speed_limit", new Attribute("speed_limit", "作业区限制速度"));
    attrs.put("transition_warning", new Attribute("transition_warning",
        "过渡区警告设施"));
    attrs.put("buffer_facility", new Attribute("buffer_facility", "缓冲区防撞设施"));
    attrs.put("section_length", new Attribute("section_length", "作业区长度"));
  }

  public List<Job> findAll() {
    return this.find("SELECT * FROM " + tableName + ";");
  }
  
  public List<Job> findAllWithAccident() {
    return this.find("SELECT * FROM " + tableName + " WHERE accident_id IS NOT NULL;");
  }
  
  public List<Job> findBy(Map<String, String> conditions) {
    StringBuffer sql = new StringBuffer("SELECT * FROM " + tableName + " WHERE ");
    Iterator<String> it = conditions.keySet().iterator();
    while(it.hasNext()) {
      String key  = it.next();
      String value = conditions.get(key);
      sql.append("`" + key + "`='" + value + "' AND ");
    }
    String sql2 = sql.substring(0, sql.length() - 5) + ";";
    System.out.println(sql2);   
    return this.find(sql2);
  }
  
}
