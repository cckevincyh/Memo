package indi.cc.memo.dao;

import indi.cc.memo.bean.MemoBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JdbcHelper implements JdbcConfig{

	//获得数据库的连接
	private static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);// 获得数据库连接
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	//增加备忘录
	public static boolean save(MemoBean memo){
		boolean b = true;
		Connection connection = getConnection();
		String sql = "insert into tb_memo (username, title, content, memotype, memotime) values (?, ?, ?, ?, ?);";
		PreparedStatement ps = null;
		try {
			 ps = connection.prepareStatement(sql);
			 ps.setString(1, memo.getUsername());
	         ps.setString(2, memo.getTitle());
	         ps.setString(3, memo.getContent());
	         ps.setString(4, memo.getMemotype());
	         ps.setString(5, memo.getMemotime());
	        if(ps.executeUpdate()==0){
	        	b = false;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}finally{
			//关闭资源
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return b;
	}
	
	
	// 修改备忘录
    public static boolean update(MemoBean memo) {
    	boolean b = true;
    	String sql = "update tb_memo set username = ?, title = ?, content = ?, memotype = ?, memotime = ? where id = ?;";
    	Connection connection = getConnection();
    	PreparedStatement ps = null;
    	try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, memo.getUsername());
	        ps.setString(2, memo.getTitle());
	        ps.setString(3, memo.getContent());
	        ps.setString(4, memo.getMemotype());
	        ps.setString(5, memo.getMemotime());
	        ps.setInt(6, memo.getId());
	        if(ps.executeUpdate()==0){
	        	b = false;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			b = false;
			e.printStackTrace();
		}finally{
			 if (ps != null) {
	                try {
	                    ps.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
		}
    	return b;
    }
    
 // 根据指定的条件查询并将结果保存在List中
    public static List<MemoBean> query(MemoBean memo){
    	String username = memo.getUsername();
        String title = memo.getTitle();
        String content = memo.getContent();
        String memotype = memo.getMemotype();
        String memotime = memo.getMemotime();
        
        StringBuilder sql = new StringBuilder("select * from tb_memo where 1=1");
        if (!username.isEmpty()) {
            sql.append(" and username like '%" + username + "%'  ");
        }
        if (!title.isEmpty()) {
            sql.append(" and title like '%" + title + "%'  ");
        }
        if (!content.isEmpty()) {
            sql.append(" and content like '%" + content + "%'  ");
        }
        if (!memotype.isEmpty()) {
            sql.append(" and memotype like '%" + memotype + "%'  ");
        }
        if (!memotime.isEmpty()) {
            sql.append(" and memotime like '%" + memotime + "%'  ");
        }
        sql.append(";");
        List<MemoBean> results = new ArrayList<MemoBean>();
        Connection connection = getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
			stat = connection.createStatement();
			rs = stat.executeQuery(sql.toString());
			while(rs.next()){
			   MemoBean tempMemo = new MemoBean();
	           tempMemo.setId(rs.getInt("id"));
	           tempMemo.setUsername(rs.getString("username"));
	           tempMemo.setTitle(rs.getString("title"));
	           tempMemo.setContent(rs.getString("content"));
	           tempMemo.setMemotype(rs.getString("memotype"));
	           tempMemo.setMemotime(rs.getString("memotime"));
	           results.add(tempMemo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
    
    
 // 查询备忘录中全部记录并转存到List中
    public static List<MemoBean> queryAll() {
    	String sql = "select * from tb_memo;";
        List<MemoBean> results = new ArrayList<MemoBean>();
        Connection conn = getConnection();
        Statement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                MemoBean memo = new MemoBean();
                memo.setId(rs.getInt("id"));
                memo.setUsername(rs.getString("username"));
                memo.setTitle(rs.getString("title"));
                memo.setContent(rs.getString("content"));
                memo.setMemotype(rs.getString("memotype"));
                memo.setMemotime(rs.getString("memotime"));
                results.add(memo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {// 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
    
    
    // 删除备忘录
    public static boolean delete(MemoBean memo) {
    	boolean b = true;
    		
    	String sql = "delete from tb_memo where id = " + memo.getId();
    	 Connection conn = getConnection();
         Statement stat = null;
         try {
             stat = conn.createStatement();
             if( stat.executeUpdate(sql)==0){
            	 b = false;
             }
             
         } catch (SQLException e) {
        	 b = false;
             e.printStackTrace();
         } finally {// 释放资源
             if (stat != null) {
                 try {
                     stat.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
             if (conn != null) {
                 try {
                     conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
    	return b;
    }
	
}
