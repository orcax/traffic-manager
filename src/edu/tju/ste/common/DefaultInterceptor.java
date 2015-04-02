package edu.tju.ste.common;

import java.lang.reflect.Field;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class DefaultInterceptor implements Interceptor {

  @Override
  public void intercept(ActionInvocation ai) {
    ai.invoke();
    String key = ai.getControllerKey().substring(1);
    String model = key.toUpperCase().substring(0, 1) + key.substring(1);
    Controller controller = ai.getController();
    controller.setAttr(key + "Class", "active");
    System.out.println(String.format(
        "[INFO] Call setAttr(\"%sClass\", \"active\") in %sController.%s()",
        key, model, ai.getMethodName()));
    controller.setAttr("jsname", key);
    System.out.println(String.format(
        "[INFO] Call setAttr(\"jsname\", \"%s\") in %sController.%s()",
        key, model, ai.getMethodName()));
    try {
      Class<?> modelClass = Class.forName("edu.tju.ste.model." + model);
      Field attrs = modelClass.getField("attrs");
      controller.setAttr(key + "Attrs", attrs.get(null));
      System.out.println(String.format(
          "[INFO] Call setAttr(\"%sAttrs\", %s.attrs) in %sController.%s()",
          key, model, model, ai.getMethodName()));
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
