package com.hurl.toolkit.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {
  public static Boolean executeProcedure(Connection con, String procName,Object ... param) throws SQLException{
    StringBuffer sb = new StringBuffer();
    
    sb.append("{call "+procName+"(");
    if(param!=null && param.length>0){
      for(int i=0;i<param.length;i++){
        if(i==0){
          sb.append("?");
        }else{
          sb.append(",?");
        }
      }
    }
    sb.append(")}");
    
    CallableStatement c = con.prepareCall(sb.toString());
    
    if(param!=null && param.length>0){
      for(int i=0;i<param.length;i++){
        c.setObject(i+1, param[i]);
      }
    }
    
    return c.execute();
  }
}
