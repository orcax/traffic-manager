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

public class AccidentController extends Controller {

  @Before({ GET.class, DefaultInterceptor.class })
  public void index() {
    Map<String, String> conditions = new HashMap<String, String>();
    String[] filters = { "location", "type", "duty" };
    for (String key : filters) {
      String value = getPara(key);
      if (value != null && !value.isEmpty())
        conditions.put(key, value);
    }
    this.setAttr("conditions", conditions);

    List<Accident> accidentData = conditions.isEmpty() ? Accident.dao.findAll()
        : Accident.dao.findBy(conditions);
    this.setAttr("accidentData", accidentData);

    List<Job> jobs = Job.dao.findAll();
    Map<Integer, Integer> accidentJobs = new HashMap<Integer, Integer>();
    for (Job job : jobs) {
      accidentJobs.put(job.getInt("accident_id"), job.getInt("id"));
    }
    this.setAttr("accidentJobs", accidentJobs);

    this.render("index.html");
  }

  @Before({ GET.class, DefaultInterceptor.class })
  public void view() {
    Integer accidentId = this.getParaToInt(0);
    Accident accidentData = Accident.dao.findById(accidentId);
    this.setAttr("accidentData", accidentData);

    Map<String, String> conditions = new HashMap<String, String>();
    conditions.put("accident_id", accidentId.toString());
    List<Job> jobs = Job.dao.findBy(conditions);
    if (jobs != null && jobs.size() > 0) {
      setAttr("jobData", jobs.get(0));
      setAttr("jobAttrs", Job.attrs);
    }

    this.render("view.html");
  }

  @Before({ GET.class, DefaultInterceptor.class })
  public void add() {
    int jobId = this.getParaToInt(0);
    setAttr("jobId", jobId);
    render("add.html");
  }

  @Before(POST.class)
  public void save() {
    Accident accident = new Accident();
    accident.set("created", this.getPara("created"));
    accident.set("job_location", this.getPara("job_location"));
    accident.set("location", this.getPara("location"));
    accident.set("type", this.getPara("type"));
    accident.set("vehicle", this.getPara("vehicle"));
    accident.set("result", this.getPara("result"));
    accident.set("description", this.getPara("description"));
    accident.set("duty", this.getPara("duty"));
    accident.set("compensation", this.getPara("compensation"));
    accident.set("human_factor", this.getPara("human_factor"));
    accident.set("vehicle_factor", this.getPara("vehicle_factor"));
    accident.set("road_factor", this.getPara("road_factor"));
    accident.set("env_factor", this.getPara("env_factor"));
    accident.set("job_factor", this.getPara("job_factor"));
    if (!accident.save()) {
      renderJson("{\"isok\": false}");
      return;
    }
    Integer accidentId = accident.getInt("id");
    Integer jobId = this.getParaToInt("job_id");
    Job job = Job.dao.findById(jobId);
    if (job == null) {
      renderJson("{\"isok\": false}");
      return;
    }
    if (job.set("accident_id", accidentId).update()) {
      renderJson("{\"isok\": true}");
    } else {
      renderJson("{\"isok\": false}");
    }
  }

  @Before(POST.class)
  public void delete() {
    Integer accidentId = this.getParaToInt("accident_id");
    if (!Accident.dao.deleteById(accidentId)) {
      renderJson("{\"isok\": false}");
      return;
    }

    Map<String, String> conditions = new HashMap<String, String>();
    conditions.put("accident_id", accidentId.toString());
    List<Job> jobs = Job.dao.findBy(conditions);
    if (jobs != null) {
      for (Job job : jobs) {
        job.set("accident_id", null).update();
      }
    }
    renderJson("{\"isok\": true}");
  }

}
