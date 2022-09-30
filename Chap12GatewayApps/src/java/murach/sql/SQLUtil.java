package murach.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQLUtil {

  public static String getHtmlTable(ResultSet results) throws SQLException {
    
    StringBuilder htmlTable = new StringBuilder();
    ResultSetMetaData metaData = results.getMetaData();
    int columnnCount = metaData.getColumnCount();
    
    htmlTable.append("<table>");
    
    // Add header row
    htmlTable.append("<tr>");
    for (int i = 1; i <= columnnCount; i++) {
      htmlTable.append("<th>");
      htmlTable.append(metaData.getColumnName(i));
      htmlTable.append("</th>");
    }
    htmlTable.append("</tr>");
    
    // Add all other rows
    while (results.next()) {
      htmlTable.append("<tr>");
      for (int i = 1; i <= columnnCount; i++) {
        htmlTable.append("<td>");
        htmlTable.append(results.getString(i));
        htmlTable.append("</td>");
      }
      htmlTable.append("</tr>");
    }
    
    htmlTable.append("</table");
    return htmlTable.toString();
  }
}
 