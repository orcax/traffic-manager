package edu.tju.ste.common;

public class Attribute {

  private String name = null;
  private String cnName = null;
  private String[] options = null;

  public Attribute(String name, String cnName) {
    this.name = name;
    this.cnName = cnName;
    this.options = null;
  }

  public Attribute(String name, String cnName, String[] options) {
    this.name = name;
    this.cnName = cnName;
    this.options = options;
  }

  public String getName() {
    return name;
  }

  public String getCnName() {
    return cnName;
  }

  public String[] getOptions() {
    return options;
  }

}
