package edu.tju.ste.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import edu.tju.ste.controller.AccidentController;
import edu.tju.ste.controller.JobController;
import edu.tju.ste.model.Accident;
import edu.tju.ste.model.Job;

public class DefaultConfig extends JFinalConfig {

  @Override
  public void configConstant(Constants me) {
    loadPropertyFile("db.properties");
    me.setViewType(ViewType.FREE_MARKER);
    me.setDevMode(true);
    System.out.println(this.getClass().getResource("/").getPath());
  }

  @Override
  public void configRoute(Routes me) {
    me.add("/", JobController.class, "/job");
    me.add("/job", JobController.class, "/job");
    me.add("/accident", AccidentController.class, "/accident");
  }

  @Override
  public void configPlugin(Plugins me) {
    C3p0Plugin cp = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"),
        getProperty("password"));
    cp.setDriverClass("org.sqlite.JDBC");
    me.add(cp);
    ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
    me.add(arp);
    arp.addMapping(Job.tableName, Job.class);
    arp.addMapping(Accident.tableName, Accident.class);
  }

  @Override
  public void configInterceptor(Interceptors me) {
    //me.add(new DefaultInterceptor());
  }

  @Override
  public void configHandler(Handlers me) {
    me.add(new ContextPathHandler());
  }

  public static void main(String[] args) {
    JFinal.start("WebRoot", 12345, "/", 5);
  }

}
