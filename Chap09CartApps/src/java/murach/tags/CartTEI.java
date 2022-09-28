package murach.tags;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

public class CartTEI extends TagExtraInfo {
  
  @Override
  public VariableInfo[] getVariableInfo(TagData data) {
    return new VariableInfo[] {
      new VariableInfo("productDescription",  "String",   true, VariableInfo.NESTED),
      new VariableInfo("productPrice",        "String",   true, VariableInfo.NESTED),
      new VariableInfo("quantity",            "Integer",  true, VariableInfo.NESTED),
      new VariableInfo("total",               "String",   true, VariableInfo.NESTED),
    };
  }
}
