package edu.tju.ste.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;

import edu.tju.ste.common.DefaultInterceptor;
import edu.tju.ste.model.Accident;
import edu.tju.ste.model.Job;

public class JobController extends Controller {

  @Before({ GET.class, DefaultInterceptor.class })
  public void index() {
    Map<String, String> conditions = new HashMap<String, String>();
    String[] filters = { "weather", "road_section", "type" };
    for (String key : filters) {
      String value = getPara(key);
      if (value != null && !value.isEmpty())
        conditions.put(key, value);
    }
    this.setAttr("conditions", conditions);

    List<Job> jobData = conditions.isEmpty() ? Job.dao.findAll() : Job.dao
        .findBy(conditions);
    this.setAttr("jobData", jobData);

    this.render("index.html");
  }

  @Before({ GET.class, DefaultInterceptor.class })
  public void view() {
    int jobId = this.getParaToInt(0);
    Job job = Job.dao.findById(jobId);
    this.setAttr("jobData", job);

    Integer accidentId = job.getInt("accident_id");
    if (accidentId != null) {
      Accident accident = Accident.dao.findById(accidentId);
      this.setAttr("accidentData", accident);
      this.setAttr("accidentAttrs", Accident.attrs);
    }

    this.render("view.html");
  }

  @Before({ GET.class, DefaultInterceptor.class })
  public void add() {
    this.render("add.html");
  }

  @Before(POST.class)
  public void save() {
    Job job = new Job();
    job.set("created", this.getPara("created"));
    job.set("weather", this.getPara("weather"));
    job.set("highway", this.getPara("highway"));
    job.set("road_section", this.getPara("road_section"));
    job.set("roadway", this.getPara("roadway"));
    job.set("type", this.getPara("type"));
    job.set("content", this.getPara("content"));
    job.set("warning", this.getPara("warning"));
    job.set("speed_limit", this.getPara("speed_limit"));
    job.set("transition_warning", this.getPara("transition_warning"));
    job.set("buffer_facility", this.getPara("buffer_facility"));
    job.set("section_length", this.getPara("section_length"));
    if (job.save()) {
      renderJson("{\"isok\": true}");
    } else {
      renderJson("{\"isok\": false}");
    }
  }

  @Before(POST.class)
  public void delete() {
    int jobId = this.getParaToInt("job_id");
    if (Job.dao.deleteById(jobId)) {
      renderJson("{\"isok\": true}");
    } else {
      renderJson("{\"isok\": false}");
    }
  }

  public void test() {
    renderJson(Job.attrs);
  }

}
